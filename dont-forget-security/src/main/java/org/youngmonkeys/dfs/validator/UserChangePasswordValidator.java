package org.youngmonkeys.dfs.validator;

import lombok.AllArgsConstructor;

import java.util.regex.Pattern;

@AllArgsConstructor
public class UserChangePasswordValidator {

    private final PasswordManager passwordManager;
    private final UserService userService;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$"
    );

    public void validate(ChangeUserPasswordRequest request) {
        String oldPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();
        if (oldPassword.equals(newPassword)) {
            throw new IllegalArgumentException(
                "old password must be difference new password"
            );
        }
        if (isValidPassword(newPassword)) {
            throw new IllegalArgumentException("new password is not correct");
        }
        String token = request.getToken();
        User user = userService.getUserByToken(token);
        String username = request.getUsername();
        if (!username.equals(user.getUsername())) {
            throw new IllegalArgumentException("user not found");
        }
        if (!passwordManager.isMatchPassword(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("password is not correct");
        }
    }

    public static boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
