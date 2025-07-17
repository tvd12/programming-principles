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

    private Map<UserType, SaveUserCommand> saveUserCommands;
    {
        saveUserCommands = new HashMap<>();
        saveUserCommands.put(UserType.USER, this::saveNormalUser);
        saveUserCommands.put(UserType.ADMIN, this::saveAdminUser);
    }

    private final Consumer<SaveUserModel> userCreatedHandler;
    private final Consumer<SaveUserModel> userUpdatedHandler;
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
        // can throw exception
        userCreatedHandler.accept(model);

        // can throw exception
        userUpdatedHandler.accept(model);
    }

    private final List<Consumer<SaveUserModel>> handlers =
        new ArrayList<>();

    public void addHandler(Consumer<SaveUserModel> handler) {
        handlers.add(handler);
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
        handlers.forEach(it -> it.accept(model));
    }

    public abstract class UserEventHandler {

        protected UserEventHandler nextHandler;

        public void setNextHandler(UserEventHandler handler) {
            this.nextHandler = handler;
        }

        public abstract void handle(SaveUserModel model);
    }

    public class UserCreatedEventHandler extends UserEventHandler {
        @Override
        public void handle(SaveUserModel model) {
            if (model.getEmail() == null) {
                throw new IllegalArgumentException("email is null");
            }
            // do something
            nextHandler.handle(model);
        }
    }

    public class UserUpdatedEventHandler extends UserEventHandler {
        @Override
        public void handle(SaveUserModel model) {
            // do something
        }
    }

    public void saveUserUserChainOfResponsibility2(
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
        UserCreatedEventHandler userCreatedEventHandler = new UserCreatedEventHandler();
        userCreatedEventHandler.setNextHandler(new UserUpdatedEventHandler());
        userCreatedEventHandler.handle(model);
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

    /*
    public long saveMail(MailModel model, String status) {
        long mailId = model.getId();
        Mail entity = mailId > 0
            ? mailRepository.findById(mailId)
            : null;
        if (mailId > 0 && entity == null) {
            throw new ResourceNotFoundException("mail");
        }
        if (entity == null) {
            entity = modelToEntityConverter.toEntity(model);
        } else {
            modelToEntityConverter.mergeToEntity(model, entity);
        }
        entity.setStatus(status);
        mailRepository.save(entity);
        return entity.getId();
    }
    */
}
