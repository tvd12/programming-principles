package org.youngmonkeys.dfs.logger;

public class LoggerConsoleAppender implements LoggerAppender {

    @Override
    public void append(String message) {
        System.out.println(message);
    }
}
