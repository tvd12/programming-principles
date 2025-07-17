package org.youngmonkeys.dfs.logger;

import com.tvd12.ezyfox.util.EzyMapBuilder;

import java.util.Map;

public class UserDataSerializer implements LoggerDataSerializer<UserData> {

    @Override
    public Map<String, Object> serialize(UserData data) {
        return EzyMapBuilder.mapBuilder()
            .put("id", data.getId())
            .put("username", "*****")
            .put("password", "*****")
            .toMap();
    }
}
