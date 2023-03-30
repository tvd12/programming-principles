package org.youngmonkeys.kiss.reduce_if_else.v3.validator;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

import java.util.HashMap;
import java.util.Map;

import org.youngmonkeys.kiss.reduce_if_else.request.AddPostRequest;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;

@EzySingleton
public class PostValidator {

    private static final int MIN_POST_TITLE_LENGTH = 6;

    public void validate(
        AddPostRequest request
    ) {
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
