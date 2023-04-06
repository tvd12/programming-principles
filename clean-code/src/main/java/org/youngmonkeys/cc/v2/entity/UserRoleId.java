package org.youngmonkeys.cc.v2.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleId implements Serializable {
    private long userId;
    private long roleId;
}
