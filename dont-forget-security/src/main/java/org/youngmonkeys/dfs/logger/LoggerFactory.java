package org.youngmonkeys.dfs.logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerFactory {

    private final LoggerFilter filter = new DefaultDataFilter();
    private final List<LoggerAppender> appenders = Collections.singletonList(
        new LoggerConsoleAppender()
    );
    private final Map<Object, Logger> loggerByName = new ConcurrentHashMap<>();

    private static final LoggerFactory INSTANCE = new LoggerFactory();

    public static LoggerFactory getInstance() {
        return INSTANCE;
    }

    public Logger getLogger(Object name) {
        return loggerByName.computeIfAbsent(
            name,
            k -> Logger.builder()
                .name(name)
                .filter(filter)
                .appenders(appenders)
                .build()
        );
    }
}
