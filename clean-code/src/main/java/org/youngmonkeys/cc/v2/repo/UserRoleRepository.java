package org.youngmonkeys.cc.v2.repo;

import org.youngmonkeys.cc.v2.entity.UserRole;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface UserRoleRepository extends EzyDatabaseRepository<Long, UserRole> {}
