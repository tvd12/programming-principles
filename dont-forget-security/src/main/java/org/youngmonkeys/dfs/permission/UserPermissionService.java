package org.youngmonkeys.dfs.permission;

import com.tvd12.ezyfox.collect.Sets;
import com.tvd12.ezyhttp.core.exception.HttpForbiddenException;
import com.tvd12.ezyhttp.core.exception.HttpNotFoundException;
import com.tvd12.ezyhttp.server.core.annotation.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;
import static java.util.Collections.singletonMap;

@Service
public class UserPermissionService {

    private final Map<String, String> usernameByToken = new HashMap<>();
    private final Map<String, Set<String>> usernameByUris = new HashMap<>();

    public UserPermissionService() {
        this.usernameByToken.put("token1", "admin1");
        this.usernameByToken.put("token2", "admin2");
        this.usernameByUris.put(
            "admin1",
            Sets.newHashSet("/admins")
        );
        this.usernameByUris.put(
            "admin2",
            Sets.newHashSet("/admin-names")
        );
    }

    public void checkUserPermission(String userToken, String apiUri) {
        String username = usernameByToken.get(userToken);
        if (isBlank(username)) {
            throw new HttpNotFoundException(
                singletonMap("token", "notFound")
            );
        }
        boolean hasPermission = usernameByUris
            .getOrDefault(username, Collections.emptySet())
            .contains(apiUri);
        if (!hasPermission) {
            throw new HttpForbiddenException(
                singletonMap("permission", "denied")
            );
        }
    }
}
