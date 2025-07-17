package org.youngmonkeys.dfs.logger;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserData {
    private final long id;
    private final String username;
    private final String password;
}
