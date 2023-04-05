package org.youngmonkeys.cc.v1.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youngmonkeys.cc.v1.event.ApplicationEventPublisher;
import org.youngmonkeys.cc.v1.event.OnUserRegistrationCompleteEvent;
import org.youngmonkeys.cc.v1.exception.UserRegistrationException;
import org.youngmonkeys.cc.v1.model.payload.ApiResponse;
import org.youngmonkeys.cc.v1.model.payload.RegistrationRequest;
import org.youngmonkeys.cc.v1.service.AuthService;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Api;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

@Api
@Controller
public class ApiAuthController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AuthService authService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ApiAuthController(AuthService authService, ApplicationEventPublisher applicationEventPublisher) {
        this.authService = authService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @DoPost("/register")
    public ResponseEntity registerUser(@RequestBody
                                       RegistrationRequest registrationRequest) {

        return authService.registerUser(registrationRequest)
                          .map(user -> {
                              String redirectUrl = "/api" +
                                                   "/auth/registrationConfirmation";
                              OnUserRegistrationCompleteEvent onUserRegistrationCompleteEvent =
                                  new OnUserRegistrationCompleteEvent(user, redirectUrl);
                              applicationEventPublisher.publishEvent(onUserRegistrationCompleteEvent);
                              logger.info("Registered User returned [API[: " + user);
                              return ResponseEntity.ok(new ApiResponse(true, "User registered successfully. Check your email " +
                                                                             "for verification"));
                          })
                          .orElseThrow(() -> new UserRegistrationException(registrationRequest.getEmail(), "Missing user object" +
                                                                                                           " in database"));
    }

}
