package org.youngmonkeys.oc.v1.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserModel {
    private String username;
    private String email;
    private String fullName;
    private long birthDay;
    private String phoneNumber;
    private String nickName;
    private String avatarUrl;
}
