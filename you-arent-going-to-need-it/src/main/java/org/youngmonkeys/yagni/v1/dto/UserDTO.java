package org.youngmonkeys.yagni.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends BaseDTO {
    private long userId;
    private String displayName;
    private String avatarUrl;
}
