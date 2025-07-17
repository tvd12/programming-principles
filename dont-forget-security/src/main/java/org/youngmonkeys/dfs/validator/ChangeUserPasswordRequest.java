package org.youngmonkeys.dfs.validator;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeUserPasswordRequest {
    private String token;
    private String username;
    private String oldPassword;
    private String newPassword;
}
