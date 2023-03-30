package org.youngmonkeys.kiss.reduce_if_else.v2.validator;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;
import static java.util.Collections.singletonMap;

import java.util.HashMap;
import java.util.Map;

import org.youngmonkeys.kiss.reduce_if_else.model.UserStatus;
import org.youngmonkeys.kiss.reduce_if_else.request.AddPostRequest;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.exception.HttpForbiddenException;

@EzySingleton
public class PostValidator {

    private static final int MIN_POST_TITLE_LENGTH = 6;

    public void validate(
        UserStatus userStatus,
        AddPostRequest request
    ) {
        if (userStatus == UserStatus.BLOCKED) {
            throw new HttpForbiddenException(
                singletonMap(
                    "userStatus",
                    UserStatus.BLOCKED
                )
            );
        }
        Map<String, String> errors = new HashMap<>();
        String postTitle = request.getTitle();
        if (isBlank(postTitle)) {
            errors.put("postTitle", "required");
        } else if (postTitle.length() < MIN_POST_TITLE_LENGTH) {
            errors.put("postTitle", "tooShort");
        }
        if (errors.size() > 0) {
            throw new HttpBadRequestException(errors);
        }
    }
}
