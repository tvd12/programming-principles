package org.youngmonkeys.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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