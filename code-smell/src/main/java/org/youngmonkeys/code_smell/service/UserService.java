package org.youngmonkeys.code_smell.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.code_smell.converter.ModelToEntityConverter;
import org.youngmonkeys.code_smell.entity.UserEntity;
import org.youngmonkeys.code_smell.model.SaveUserModel;
import org.youngmonkeys.code_smell.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelToEntityConverter modelToEntityConverter;

    public void saveUser(SaveUserModel model) {
        UserEntity entity = modelToEntityConverter.toEntity(model);
        userRepository.saveUser(entity);
    }
}
