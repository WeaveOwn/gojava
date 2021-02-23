package com.weaveown.design.structural.proxy.custom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author wangwei
 * @date 2021/2/22
 */
public class WClassLoader extends ClassLoader {
    private File classPathFile;

    public WClassLoader() {
        classPathFile = new File(WClassLoader.class.getResource("").getPath());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = WClassLoader.class.getPackage().getName() + "." + name;
        if (classPathFile.exists()) {
            File file = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
            try {
                FileInputStream in = new FileInputStream(file);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                int len;
                while ((len = in.read(b)) != -1) {
                    out.write(b, 0, len);
                }
                return defineClass(classPath, out.toByteArray(), 0, out.size());
            } catch (Exception e) {
                throw new ClassNotFoundException();
            }
        }
        return null;
    }
}
