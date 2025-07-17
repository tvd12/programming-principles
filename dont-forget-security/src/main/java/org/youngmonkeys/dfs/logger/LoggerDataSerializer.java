package org.youngmonkeys.dfs.logger;

import java.util.Map;

public interface LoggerDataSerializer<T> {

    Map<String, Object> serialize(T data);
}
