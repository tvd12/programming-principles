package org.youngmonkeys.di.v2.ioc;

import java.lang.reflect.Method;
import java.util.Set;

import com.tvd12.ezyfox.reflect.EzyReflection;
import com.tvd12.ezyfox.reflect.EzyReflectionProxy;

public class Library {
    public static void main(String[] args) throws Exception {
        EzyReflection reflections = new EzyReflectionProxy(
            Library.class.getPackage().getName()
        );
        Set<Class<?>> classes = reflections
            .getExtendsClasses(Component.class);
        for (Class<?> clazz : classes) {
            Object instance = clazz
                .getDeclaredConstructor()
                .newInstance();
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println(method.invoke(instance, 1, 2));
            }
        }
    }
}
