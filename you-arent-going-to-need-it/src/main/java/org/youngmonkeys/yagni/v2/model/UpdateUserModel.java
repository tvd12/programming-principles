package org.youngmonkeys.yagni.v2.model;

import org.youngmonkeys.yagni.v1.dto.BaseDTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateUserModel extends BaseDTO {
    private String displayName;
    private String avatarUrl;
}
