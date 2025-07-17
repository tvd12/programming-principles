package org.youngmonkeys.dfs.logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultDataFilter implements LoggerFilter {

    private final Map<Class<?>, LoggerDataSerializer> serializerByType = new HashMap<>();

    public DefaultDataFilter() {
        this.serializerByType.put(UserData.class, new UserDataSerializer());
    }

    @Override
    public Map<String, Object> filter(Object input) {
        return serializerByType.get(input.getClass()).serialize(input);
    }
}
