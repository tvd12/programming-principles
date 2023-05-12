package org.youngmonkeys.cc.v2.controller.api;

import org.youngmonkeys.cc.v2.converter.RequestToModelConverter;
import org.youngmonkeys.cc.v2.request.RegistrationRequest;
import org.youngmonkeys.cc.v2.service.AuthService;
import org.youngmonkeys.cc.v2.validator.AuthValidator;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Api;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import lombok.AllArgsConstructor;

@Api
@Controller("/api/v1")
@AllArgsConstructor
public class ApiAuthController {

    private final AuthService authService;
    private final RequestToModelConverter requestToModelConverter;
    private final AuthValidator authValidator;

    @DoPost("/register")
    public ResponseEntity registerUser(
        @RequestBody RegistrationRequest request
    ) {
        authValidator.validate(request);
        authService.registerUser(
            requestToModelConverter.toModel(request),
            request.isRegisterAsAdmin()
        );
        return ResponseEntity.noContent();
    }
}
