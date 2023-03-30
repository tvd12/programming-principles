package org.youngmonkeys.coi.test.controller.api.v2;

import static com.tvd12.test.util.RandomUtil.randomLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.youngmonkeys.coi.test.util.DataRandomUtil.randomUserModel;

import org.testng.annotations.Test;
import org.youngmonkeys.coi.controller.api.v2.ApiUserManagementController;
import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.model.UserModel;
import org.youngmonkeys.coi.response.UserResponse;
import org.youngmonkeys.coi.service.UserService;

import com.tvd12.test.assertion.Asserts;

public class ApiUserManagementControllerTest {

    @Test
    public void userManagementLatestUserGetTest() {
        // given
        long userId = randomLong();
        UserModel userModel = randomUserModel(userId);
        UserService userService = mock(UserService.class);
        when(
            userService.getLatestUserId()
        ).thenReturn(userId);
        when(
            userService.getUserById(userId)
        ).thenReturn(userModel);

        ModelToResponseConverter modelToResponseConverter =
            mock(ModelToResponseConverter.class);
        UserResponse userResponse = UserResponse
            .builder()
            .id(userId)
            .name(userModel.getName())
            .build();
        when(
            modelToResponseConverter.toResponse(
                userModel
            )
        ).thenReturn(userResponse);

        ApiUserManagementController underTest =
            new ApiUserManagementController(
                userService,
                modelToResponseConverter
            );

        // when
        UserResponse actual = underTest.userManagementLatestUserGet();

        // then
        Asserts.assertEquals(actual, userResponse);

        verify(userService, times(1))
            .getLatestUserId();
        verify(userService, times(1))
            .getUserById(userId);
        verifyNoMoreInteractions(userService);

        verify(modelToResponseConverter, times(1))
            .toResponse(userModel);
        verifyNoMoreInteractions(modelToResponseConverter);
    }
}
