package com.weaveown.design.structural.proxy.custom;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author wangwei
 * @date 2021/2/22
 */
public class WProxy {
    public static Object newProxyInstance(WClassLoader loader,
                                          Class<?>[] interfaces,
                                          WInvocationHandler h) {
        String src = generateSrc(interfaces);
        try {
            String filePath = interfaces[0].getResource("").getPath();
            File file = new File(filePath + "$Proxy0.java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(src);
            fileWriter.flush();
            fileWriter.close();
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable javaFileObjects = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, javaFileObjects);
            task.call();
            manager.close();

            try {
                Class<?> p = loader.findClass("$Proxy0");
                Constructor<?> constructor = p.getConstructor(WInvocationHandler.class);
                Object o = constructor.newInstance(h);
                return o;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        sb.append("package com.weaveown.design.structural.proxy.custom;\n");
        sb.append("import java.lang.reflect.*;\n");
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{\n");
        sb.append("WInvocationHandler h;\n");
        sb.append("public $Proxy0(WInvocationHandler h) {\n");
        sb.append("this.h = h;\n");
        sb.append("}\n");
        for (Method method : interfaces[0].getMethods()) {
            sb.append("public " + method.getReturnType() + " " + method.getName() + "() {\n");
            sb.append("try{\n");
            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\");\n");
            sb.append("this.h.invoke(this,m, null);\n");
            sb.append("}catch(Throwable e){}\n");
            sb.append("}\n");
        }

        sb.append("}\n");
        return sb.toString();
    }

    public void aa() {

    }
}
