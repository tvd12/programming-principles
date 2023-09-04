package org.youngmonkeys.sr.v2.interceptor;

import static org.youngmonkeys.sr.v2.constants.Constants.COOKIE_NAME_ACCESS_TOKEN;

import java.lang.reflect.Method;

import org.youngmonkeys.sr.v2.annotation.Username;
import org.youngmonkeys.sr.v2.service.UserService;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.constant.HttpMethod;
import com.tvd12.ezyhttp.server.core.interceptor.RequestInterceptor;
import com.tvd12.ezyhttp.server.core.manager.RequestURIManager;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;

import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class AuthenticationInterceptor
    implements RequestInterceptor {

    private final UserService userService;
    private final RequestURIManager requestUriManager;

    @Override
    public boolean preHandle(
        RequestArguments arguments,
        Method handler
    ) {
        String accessToken = arguments.getCookieValue(
            COOKIE_NAME_ACCESS_TOKEN
        );

        String uriTemplate = arguments.getUriTemplate();
        HttpMethod method = arguments.getMethod();
        if (requestUriManager.isAuthenticatedURI(
            method,
            uriTemplate
        )) {
            String username = userService
                .validateUserAccessToken(accessToken);
            arguments.setArgument(Username.class, username);
        }
        return true;
    }
}
