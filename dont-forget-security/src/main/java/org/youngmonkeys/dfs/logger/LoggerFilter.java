package org.youngmonkeys.dfs.logger;

import java.util.Map;

public interface LoggerFilter {
    Map<String, Object> filter(Object input);
}
