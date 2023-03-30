package org.youngmonkeys.oc.v1.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String username;
    private String email;
    private String fullName;
    private long birthDay;
    private String phoneNumber;
    private String displayName;
    private String avatarUrl;
}
