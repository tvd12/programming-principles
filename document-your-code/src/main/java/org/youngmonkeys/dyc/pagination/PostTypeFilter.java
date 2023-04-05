package org.youngmonkeys.dyc.pagination;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostTypeFilter implements PostFilter {

    private String postType;

    @Override
    public String matchingCondition() {
        return "e.postType = :postType";
    }
}
