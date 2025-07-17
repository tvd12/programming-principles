package org.youngmonkeys.dfs.logger;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
public class Logger {
    private Object name;
    private List<LoggerAppender> appenders;
    private LoggerFilter filter;

    public void info(String msg, Object... params) {
        StringBuilder fullMessageBuilder = new StringBuilder()
            .append(LocalDateTime.now())
            .append(" - ")
            .append(name)
            .append(" - ")
            .append("msg: ")
            .append(msg)
            .append("params: ");
        for (Object param : params) {
            Map<String, Object> filteredFields = filter.filter(param);
            fullMessageBuilder.append(filteredFields);
        }
        String fullMessage = fullMessageBuilder.toString();
        for (LoggerAppender appender : appenders) {
            appender.append(fullMessage);
        }
    }
}
