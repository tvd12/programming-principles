package org.youngmonkeys.kiss.simplify_unit_testing.repo;

import java.util.List;

import org.youngmonkeys.kiss.simplify_unit_testing.entity.User;

public interface UserRepository {

    List<User> findByIdIn(List<Long> ids);
}
