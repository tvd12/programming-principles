package org.youngmonkeys.cc.v1.service;

import java.util.Collection;

import org.youngmonkeys.cc.v1.model.Role;
import org.youngmonkeys.cc.v1.repo.RoleRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Find all roles from the database
     */
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }
}
