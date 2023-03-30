package org.youngmonkeys.coi.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private long id;
    private String name;

    private String phoneNumber;
}
