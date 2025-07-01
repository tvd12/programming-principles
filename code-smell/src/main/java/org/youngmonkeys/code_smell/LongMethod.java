package org.youngmonkeys.code_smell;

import com.tvd12.ezyfox.security.EzySHA256;
import lombok.AllArgsConstructor;
import org.youngmonkeys.code_smell.entity.UserEntity;
import org.youngmonkeys.code_smell.model.SaveUserModel;
import org.youngmonkeys.code_smell.repository.UserRepository;
import org.youngmonkeys.code_smell.request.AddUserRequest;

@AllArgsConstructor
public class LongMethod {

    private final UserRepository userRepository;

    public void saveUser(AddUserRequest request) {
        SaveUserModel model = SaveUserModel.builder()
            .username(request.getUsername())
            .password(
                EzySHA256.cryptUtfToLowercase(
                    request.getPassword()
                )
            )
            .displayName(request.getDisplayName())
            .email(request.getEmail())
            .build();
        UserEntity entity = new UserEntity();
        entity.setUsername(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setEmail(model.getEmail());
        entity.setDisplayName(model.getDisplayName());
        userRepository.saveUser(entity);
    }
}
