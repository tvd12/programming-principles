package org.youngmonkeys.dyc.pagination;

import static org.youngmonkeys.dyc.util.ReflectionObjects.getObjectProperties;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface ParameterMapFetcher {

    @JsonIgnore
    default Map<String, Object> getParameters() {
        return getObjectProperties(this);
    }
}
