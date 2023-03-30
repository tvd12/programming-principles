package org.youngmonkeys.coi.test.controller.api.v3;

import static com.tvd12.test.util.RandomUtil.randomLong;
import static com.tvd12.test.util.RandomUtil.randomShortAlphabetString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;
import org.youngmonkeys.coi.controller.api.v3.ApiUserProfileController;
import org.youngmonkeys.coi.converter.ModelToResponseConverter;
import org.youngmonkeys.coi.model.UserModel;
import org.youngmonkeys.coi.request.UpdateUserRequest;
import org.youngmonkeys.coi.response.UserResponse;
import org.youngmonkeys.coi.service.PhoneNumberService;
import org.youngmonkeys.coi.service.UserService;

import com.tvd12.test.assertion.Asserts;

public class ApiUserProfileControllerTest {

    @Test
public void usersUserIdPutTest() {
    // given
    long userId = randomLong();
    String newUserName = randomShortAlphabetString();
    UserModel userModel = UserModel
        .builder()
        .id(userId)
        .name(newUserName)
        .build();
    UserService userService = mock(UserService.class);
    when(
        userService.getLatestUserId()
    ).thenReturn(userId);
    when(
        userService.getUserById(userId)
    ).thenReturn(userModel);
    doNothing()
        .when(userService)
        .updateUser(
           userId,
           newUserName
       );

    String phoneNumber = randomShortAlphabetString();
    ModelToResponseConverter modelToResponseConverter =
        mock(ModelToResponseConverter.class);
    UserResponse userResponse = UserResponse
        .builder()
        .id(userId)
        .name(userModel.getName())
        .phoneNumber(phoneNumber)
        .build();
    when(
        modelToResponseConverter.toResponse(
            userModel,
            phoneNumber
        )
    ).thenReturn(userResponse);

    PhoneNumberService phoneNumberService =
        mock(PhoneNumberService.class);
    when(
        phoneNumberService.getPhoneNumberByUserId(
            userId
        )
    ).thenReturn(phoneNumber);

    ApiUserProfileController underTest =
        new ApiUserProfileController(
            userService,
            phoneNumberService,
            modelToResponseConverter
        );

    // when
    UpdateUserRequest request = new UpdateUserRequest();
    request.setName(newUserName);
    UserResponse actual = underTest.usersUserIdPut(
        userId,
        request
    );

    // then
    Asserts.assertEquals(actual, userResponse);

    verify(userService, times(1))
        .updateUser(userId, newUserName);
    verify(userService, times(1))
        .getUserById(userId);
    verifyNoMoreInteractions(userService);

    verify(modelToResponseConverter, times(1))
        .toResponse(userModel, phoneNumber);
    verifyNoMoreInteractions(modelToResponseConverter);
}
}
