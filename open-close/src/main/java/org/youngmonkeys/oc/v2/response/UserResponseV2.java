package org.youngmonkeys.oc.v2.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseV2 {
    private String displayName;
    private String avatarUrl;
}
