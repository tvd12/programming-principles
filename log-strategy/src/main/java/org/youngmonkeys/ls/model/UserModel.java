package org.youngmonkeys.ls.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserModel {
    private long id;
    private String username;
    private String displayName;
}
