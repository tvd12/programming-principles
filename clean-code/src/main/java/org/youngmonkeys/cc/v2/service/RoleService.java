package org.youngmonkeys.cc.v2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.youngmonkeys.cc.v1.model.RoleName;
import org.youngmonkeys.cc.v2.entity.Role;
import org.youngmonkeys.cc.v2.repo.RoleRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Long> getAllRoleIds(
        boolean includeAdminRole
    ) {
        return roleRepository
            .findAll()
            .stream()
            .filter(
                role -> includeAdminRole
                        || RoleName.ROLE_ADMIN.equals(role.getName())
            )
            .map(Role::getId)
            .collect(Collectors.toList());
    }
}
