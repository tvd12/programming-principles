package org.youngmonkeys.cc.v2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_roles")
@IdClass(UserRoleId.class)
public class UserRole {

    @Id
    @Column(name = "user_id")
    private long userId;

    @Id
    @Column(name = "role_id")
    private long roleId;
}
