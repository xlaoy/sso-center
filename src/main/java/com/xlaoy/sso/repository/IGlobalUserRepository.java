package com.xlaoy.sso.repository;

import com.xlaoy.common.jpa.IBaseRepository;
import com.xlaoy.sso.entity.GlobalUserEntity;
import org.springframework.stereotype.Repository;

/**
*全局用户Repository
*/
@Repository("globalUserRepository")
public interface IGlobalUserRepository extends IBaseRepository<GlobalUserEntity> {

    GlobalUserEntity findByUserNameAndPassword(String userName, String password);
}
