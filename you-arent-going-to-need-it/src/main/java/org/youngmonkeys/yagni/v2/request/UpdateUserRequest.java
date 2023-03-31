package org.youngmonkeys.yagni.v2.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String displayName;
    private String avatarUrl;
}
