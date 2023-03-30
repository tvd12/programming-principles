package org.youngmonkeys.kiss.simplify_unit_testing.service;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.youngmonkeys.kiss.simplify_unit_testing.converter.EntityToModelConverter;
import org.youngmonkeys.kiss.simplify_unit_testing.entity.User;
import org.youngmonkeys.kiss.simplify_unit_testing.model.UserModel;
import org.youngmonkeys.kiss.simplify_unit_testing.repo.UserRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityToModelConverter entityToModelConverter;

    public Map<Long, UserModel> getUserMapByIds(
        List<Long> userIds
    ) {
        return userRepository
            .findByIdIn(userIds)
            .stream()
            .collect(
                Collectors.toMap(
                    User::getId,
                    entityToModelConverter::toModel
                )
            );
    }
}