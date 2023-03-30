package org.youngmonkeys.wdc.duplicated_initiation.v1.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String uuid;
    private String displayName;
    private String profileUrl;
}
