package org.youngmonkeys.kiss.simplify_unit_testing.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class UserModel {
    private long id;
    private String username;
}
