package org.youngmonkeys.scheduler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.tvd12.ezyfox.reflect.EzyClasses;
import com.tvd12.ezyfox.reflect.EzyReflection;
import com.tvd12.ezyfox.reflect.EzyReflectionProxy;

public class HourlyScheduler {
    public static void main(String[] args) throws Exception {
        EzyReflection reflections = new EzyReflectionProxy(
            "org.youngmonkeys.di"
        );
        Set<Class<?>> classes = reflections
            .getExtendsClasses(Worker.class);
        List<Worker> workers = new ArrayList<>();
        for (Class<?> clazz : classes) {
            workers.add(
                (Worker) clazz
                    .getDeclaredConstructor()
                    .newInstance()
            );
        }
        ScheduledExecutorService scheduler = Executors
            .newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(
            () -> workers.forEach(Worker::run),
            0,
            1,
            TimeUnit.HOURS
        );
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Instant {}

@Retention(RetentionPolicy.RUNTIME)
@interface Inject {}

class BeanManagement {
    public static void main(String[] args) throws Exception {
        EzyReflection reflections = new EzyReflectionProxy(
            "org.youngmonkeys.di"
        );
        Set<Class<?>> classes = reflections.getAnnotatedClasses(Instant.class);
        Map<String, Object> beanByName = new HashMap<>();
        Map<Class<?>, Object> beanByType = new HashMap<>();
        for (Class<?> clazz : classes) {
            Object bean = clazz
                .getDeclaredConstructor()
                .newInstance();
            beanByName.put(EzyClasses.getVariableName(clazz), bean);
            Class<?> parentClass = clazz;
            while (parentClass != null) {
                beanByType.put(parentClass, bean);
                parentClass = parentClass.getSuperclass();
            }
            for (Class<?> inf : clazz.getInterfaces()) {
                beanByType.put(inf, bean);
            }
            injectDependenciesToBean(beanByName, beanByType, bean);
        }
    }

    public static void injectDependenciesToBean(
        Map<String, Object> beanByName,
        Map<Class<?>, Object> beanByType,
        Object bean
    ) throws Exception {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                // lấy phụ thuộc từ name trước, nếu không có thì lấy theo type
                Object dependency = beanByName.get(
                    EzyClasses.getVariableName(field.getType())
                );
                if (dependency == null) {
                    Class<?> parentClass = field.getType();
                    while (parentClass != null && dependency == null) {
                        dependency = beanByType.get(
                            parentClass
                        );
                        parentClass = parentClass.getSuperclass();
                    }
                    for (Class<?> inf : field.getType().getInterfaces()) {
                        dependency = beanByType.get(
                            inf
                        );
                        if (dependency != null) {
                            break;
                        }
                    }
                }
                field.setAccessible(true);
                field.set(bean, dependency);
            }
        }
    }
}
