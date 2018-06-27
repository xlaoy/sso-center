package com.xlaoy.sso.repository;

import com.xlaoy.common.jpa.IBaseRepository;
import com.xlaoy.sso.entity.UserRoleRelaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*全局用户Repository
*/
@Repository("userRoleRelaRepository")
public interface IUserRoleRelaRepository extends IBaseRepository<UserRoleRelaEntity> {

    List<UserRoleRelaEntity> findByUserId(Integer userId);
}
