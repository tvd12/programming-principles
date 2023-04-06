package org.youngmonkeys.cc.v2.service;

import org.youngmonkeys.cc.v2.converter.ModelToEntityConverter;
import org.youngmonkeys.cc.v2.entity.User;
import org.youngmonkeys.cc.v2.model.CreateUserModel;
import org.youngmonkeys.cc.v2.repo.UserRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    private final ModelToEntityConverter modelToEntityConverter;

    public void createUser(
        CreateUserModel model,
        boolean asAdmin
    ) {
        User entity = modelToEntityConverter.toEntity(model);
        userRepository.save(entity);
        userRoleService.addRolesToNewUser(
            entity.getId(),
            asAdmin
        );
    }

    public boolean existsUserByEmail(String email) {
        return userRepository.containsByField(
            "email",
            email
        );
    }

    public boolean existsUserByUsername(String username) {
        return userRepository.containsByField(
            "username",
            username
        );
    }
}
