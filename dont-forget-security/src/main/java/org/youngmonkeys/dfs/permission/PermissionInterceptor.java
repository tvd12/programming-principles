package org.youngmonkeys.dfs.permission;

import com.tvd12.ezyhttp.core.annotation.Interceptor;
import com.tvd12.ezyhttp.core.constant.HttpMethod;
import com.tvd12.ezyhttp.server.core.interceptor.RequestInterceptor;
import com.tvd12.ezyhttp.server.core.manager.RequestURIManager;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;
import lombok.AllArgsConstructor;

import java.lang.reflect.Method;

@Interceptor
@AllArgsConstructor
public class PermissionInterceptor implements RequestInterceptor {

    private final RequestURIManager requestUriManager;
    private final UserPermissionService userPermissionService;

    @Override
    public boolean preHandle(
        RequestArguments arguments,
        Method handler
    ) {
        HttpMethod method = arguments.getMethod();
        String uriTemplate = arguments.getUriTemplate();
        if (requestUriManager.isAuthenticatedURI(method, uriTemplate)) {
            String token = arguments.getHeader("token");
            userPermissionService.checkUserPermission(
                token,
                uriTemplate
            );
        }
        return true;
    }
}
