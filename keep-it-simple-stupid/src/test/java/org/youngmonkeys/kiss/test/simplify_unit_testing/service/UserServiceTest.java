package org.youngmonkeys.kiss.test.simplify_unit_testing.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

public class UserServiceTest {

    @Test
    public void getUserByIdTest() {
        // given
        int userCount = RandomUtil.randomInt(2, 10);
        List<Long> userIds = RandomUtil.randomList(
            userCount,
            RandomUtil::randomLong
        );
        List<User> foundUsers = new ArrayList<>();
        for (int i = 0 ; i < userCount ; ++i) {
            if (i % 2 == 0) {
                foundUsers.add(
                    new User(
                        userIds.get(i),
                        RandomUtil.randomShortAlphabetString()
                    )
                );
            }
        }
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
