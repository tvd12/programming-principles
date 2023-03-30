package org.youngmonkeys.coi.test.util;

import static com.tvd12.test.util.RandomUtil.randomShortAlphabetString;

import org.youngmonkeys.coi.model.UserModel;

public final class DataRandomUtil {

    private DataRandomUtil() {}

    public static UserModel randomUserModel(
        long userId
    ) {
        return UserModel
            .builder()
            .id(userId)
            .name(randomShortAlphabetString())
            .build();
    }
}
