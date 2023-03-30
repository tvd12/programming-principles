package org.youngmonkeys.oc.v1.service;

import java.util.Map;

import org.youngmonkeys.oc.v1.model.UserModel;

import com.tvd12.ezyfox.util.EzyMapBuilder;
import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class UserService {

    private final Map<String, UserModel> userByUuid = EzyMapBuilder
        .mapBuilder()
        .put(
            "uuid1",
            UserModel
                .builder()
                .username("user1")
                .email("user1@youngmonkeys.org")
                .phoneNumber("0123456789")
                .fullName("User 1")
                .birthDay(System.currentTimeMillis())
                .nickName("Monkey")
                .avatarUrl("https://youngmonkeys.org/logo.png")
                .build()
        )
        .toMap();

    public UserModel getUserByUuid(String uuid) {
        return userByUuid.get(uuid);
    }
}
