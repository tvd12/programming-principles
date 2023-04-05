package org.youngmonkeys.cc.v1.repo;

import org.youngmonkeys.cc.v1.model.Role;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface RoleRepository extends EzyDatabaseRepository<Long, Role> {}
