package org.youngmonkeys.coi.service;

import java.util.Map;

import org.youngmonkeys.coi.model.UserModel;

import com.tvd12.ezyfox.exception.NotFoundException;
import com.tvd12.ezyfox.util.EzyMapBuilder;
import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class UserService {

    private final Map<Long, UserModel> userById =
        EzyMapBuilder
            .mapBuilder()
            .put(
                1L,
                UserModel
                    .builder()
                    .id(1)
                    .name("user1")
                    .build()
            )
            .toMap();

    public UserModel getUserById(long id) {
        return userById.get(id);
    }

    public void updateUser(long id, String name) {
        UserModel user = userById.get(id);
        if (user == null) {
            throw new NotFoundException("user not found");
        }
        userById.put(
            id,
            UserModel
                .builder()
                .id(id)
                .name(name)
                .build()
        );
    }

    public Long getLatestUserId() {
        return userById
            .keySet()
            .stream()
            .max(Long::compareTo)
            .orElse(0L);
    }
}
