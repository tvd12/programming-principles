package org.youngmonkeys.cc.v2.service;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

import java.util.Collection;

import org.youngmonkeys.cc.v2.entity.UserRole;
import org.youngmonkeys.cc.v2.repo.UserRoleRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserRoleService {

    private final RoleService roleService;
    private final UserRoleRepository userRoleRepository;

    public void addRolesToNewUser(
        long newUserId,
        boolean asAdmin
    ) {
        addUserRoles(
            newUserId,
            roleService.getAllRoleIds(
                asAdmin
            )
        );
    }

    private void addUserRoles(
        long userId,
        Collection<Long> roleIds
    ) {
        userRoleRepository.save(
            newArrayList(
                roleIds,
                roleId -> new UserRole(userId, roleId)
            )
        );
    }
}
