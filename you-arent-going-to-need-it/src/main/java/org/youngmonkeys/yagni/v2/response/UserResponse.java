package org.youngmonkeys.yagni.v2.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private long userId;
    private String displayName;
    private String avatarUrl;
}
