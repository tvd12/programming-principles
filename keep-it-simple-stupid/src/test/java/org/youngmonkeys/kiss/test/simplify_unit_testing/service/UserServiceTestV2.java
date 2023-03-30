package org.youngmonkeys.kiss.test.simplify_unit_testing.service;

import static com.tvd12.ezyfox.io.EzyLists.combine;
import static com.tvd12.ezyfox.io.EzyLists.newArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.annotations.Test;
import org.youngmonkeys.kiss.simplify_unit_testing.converter.EntityToModelConverter;
import org.youngmonkeys.kiss.simplify_unit_testing.entity.User;
import org.youngmonkeys.kiss.simplify_unit_testing.model.UserModel;
import org.youngmonkeys.kiss.simplify_unit_testing.repo.UserRepository;
import org.youngmonkeys.kiss.simplify_unit_testing.service.UserService;

import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.util.RandomUtil;

public class UserServiceTestV2 {

    @SuppressWarnings("unchecked")
    @Test
    public void getUserByIdTest() {
        // given
        int foundUserCount = RandomUtil.randomInt(2, 10);
        List<Long> foundUserIds = RandomUtil.randomList(
            foundUserCount,
            RandomUtil::randomLong
        );
        int notFoundUserCount = RandomUtil.randomInt(2, 10);
        List<Long> notFoundUserIds = RandomUtil.randomList(
            foundUserCount,
            RandomUtil::randomLong
        );
        List<User> foundUsers = newArrayList(
            foundUserIds,
            userId -> new User(
                userId,
                RandomUtil.randomShortAlphabetString()
            )
        );
        List<Long> userIds = combine(
            foundUserIds,
            notFoundUserIds
        );
        UserRepository userRepository =
            mock(UserRepository.class);
        when(
            userRepository.findByIdIn(userIds)
        )
            .thenReturn(foundUsers);

        EntityToModelConverter entityToModelConverter =
            mock(EntityToModelConverter.class);
        foundUsers.forEach(
            user ->
               when(
                   entityToModelConverter.toModel(user)
               ).thenReturn(
                   UserModel
                       .builder()
                       .id(user.getId())
                       .username(user.getUsername())
                       .build()
               )
        );

        UserService instance = new UserService(
            userRepository,
            entityToModelConverter
        );

        // when
        Map<Long, UserModel> actual = instance.getUserMapByIds(
            userIds
        );

        // then
        Asserts.assertEquals(
            actual,
            foundUsers
                .stream()
                .collect(
                    Collectors.toMap(
                        User::getId,
                        user -> UserModel
                            .builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .build()
                    )
                )
        );
        verify(
            userRepository,
            times(1)
        )
            .findByIdIn(userIds);
        verifyNoMoreInteractions(userRepository);

        foundUsers.forEach(
            user ->
                verify(
                    entityToModelConverter,
                    times(1)
                )
                    .toModel(user)
        );
        verifyNoMoreInteractions(entityToModelConverter);
    }
}
