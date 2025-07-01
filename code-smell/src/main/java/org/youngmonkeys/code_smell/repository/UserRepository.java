package org.youngmonkeys.code_smell.repository;

import org.youngmonkeys.code_smell.entity.UserEntity;

public interface UserRepository {

    void saveUser(UserEntity entity);
}
