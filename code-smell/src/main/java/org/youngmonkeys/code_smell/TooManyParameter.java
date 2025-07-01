package org.youngmonkeys.code_smell;

import lombok.AllArgsConstructor;
import org.youngmonkeys.code_smell.entity.UserEntity;
import org.youngmonkeys.code_smell.repository.UserRepository;

@AllArgsConstructor
public class TooManyParameter {

    private final UserRepository userRepository;

    public void saveUser(
        String username,
        String password,
        String displayName,
        String email
    ) {
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setDisplayName(displayName);
        entity.setEmail(email);
        userRepository.saveUser(entity);
    }
}
