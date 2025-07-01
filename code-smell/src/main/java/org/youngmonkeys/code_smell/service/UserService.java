package org.youngmonkeys.code_smell.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.code_smell.constant.UserType;
import org.youngmonkeys.code_smell.converter.ModelToEntityConverter;
import org.youngmonkeys.code_smell.entity.UserEntity;
import org.youngmonkeys.code_smell.model.SaveUserModel;
import org.youngmonkeys.code_smell.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelToEntityConverter modelToEntityConverter;

    public void saveUser(SaveUserModel model) {
        UserEntity entity = modelToEntityConverter.toEntity(model);
        userRepository.saveUser(entity);
    }

    public void saveUser(
        UserType userType,
        SaveUserModel model
    ) {
        if (userType == UserType.USER) {
            saveNormalUser(model);
        } else if (userType == UserType.ADMIN) {
            saveAdminUser(model);
        } else {
            throw new IllegalArgumentException(
                "Unsupported user type: " + userType
            );
        }
    }

    interface SaveUserCommand {
        void save(SaveUserModel model);
    }

    private final Map<UserType, SaveUserCommand> saveUserCommands;
    {
        saveUserCommands = new HashMap<>();
        saveUserCommands.put(UserType.USER, this::saveNormalUser);
        saveUserCommands.put(UserType.ADMIN, this::saveAdminUser);
    }

    private final Consumer<SaveUserModel> userCreatedCallback;
    private final Consumer<SaveUserModel> userUpdatedCallback;
    public void saveUserUserCommandDesignPattern(
        UserType userType,
        SaveUserModel model
    ) {
        SaveUserCommand command = saveUserCommands.get(userType);
        if (command != null) {
            command.save(model);
        } else {
            throw new IllegalArgumentException(
                "Unsupported user type: " + userType
            );
        }
        userCreatedCallback.accept(model);
        userUpdatedCallback.accept(model);
    }

    private final List<Consumer<SaveUserModel>> callbacks =
        new ArrayList<>();

    public void addCallback(Consumer<SaveUserModel> callback) {
        callbacks.add(callback);
    }

    public void saveUserUserChainOfResponsibility(
        UserType userType,
        SaveUserModel model
    ) {
        SaveUserCommand command = saveUserCommands.get(userType);
        if (command != null) {
            command.save(model);
        } else {
            throw new IllegalArgumentException(
                "Unsupported user type: " + userType
            );
        }
        callbacks.forEach(it -> it.accept(model));
    }

    private void saveAdminUser(
        SaveUserModel model
    ) {
        UserEntity entity = modelToEntityConverter.toEntity(model);
        userRepository.saveUser(entity);
    }

    private void saveNormalUser(
        SaveUserModel model
    ) {
        UserEntity entity = modelToEntityConverter.toEntity(model);
        userRepository.saveUser(entity);
    }
}
