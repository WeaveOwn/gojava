package com.weaveown.reflection;

import com.weaveown.reflection.annotation.WAutoWired;
import com.weaveown.reflection.annotation.WController;
import com.weaveown.reflection.scan.TestController;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangwei
 * @date 2020/4/21 22:45
 */
public class IocDemo {
    static Map<String, Object> ioc = new HashMap<>();

    public static void main(String[] args) throws Exception {
        URL resource = IocDemo.class.getClassLoader().getResource("com.weaveown.reflection.scan".replaceAll("\\.", "/"));
        File file = new File(resource.getFile());
        for (File listFile : file.listFiles()) {
            String className = "com.weaveown.reflection.scan" + "." + listFile.getName().replaceAll(".class", "");
            System.out.println(className);
            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(WController.class)) {
                Object instance = clazz.newInstance();
                ioc.put(clazz.getSimpleName(), instance);
            }
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                WAutoWired annotation = field.getAnnotation(WAutoWired.class);
                if (annotation == null) {
                    continue;
                }
                String beanName = annotation.value();
                if ("".equals(annotation.value())) {
                    beanName = field.getType().getSimpleName();
                }
                field.setAccessible(true);
                field.set(entry.getValue(), ioc.get(beanName));
            }
        }
        TestController testController = (TestController) ioc.get("TestController");
        testController.hello();
    }
}
