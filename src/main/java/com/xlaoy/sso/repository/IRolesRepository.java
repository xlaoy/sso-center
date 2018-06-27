package com.xlaoy.sso.repository;

import com.xlaoy.common.jpa.IBaseRepository;
import com.xlaoy.sso.entity.RolesEntity;
import org.springframework.stereotype.Repository;

/**
*全局用户Repository
*/
@Repository("rolesRepository")
public interface IRolesRepository extends IBaseRepository<RolesEntity> {

}
