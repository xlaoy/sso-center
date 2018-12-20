package com.xlaoy.sso.repository;

import com.xlaoy.sso.entity.GlobalUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
*全局用户Repository
*/

@Repository("globalUserRepository")
public interface IGlobalUserRepository extends MongoRepository<GlobalUserEntity, String> {

    GlobalUserEntity findByPhone(String phone);
}

