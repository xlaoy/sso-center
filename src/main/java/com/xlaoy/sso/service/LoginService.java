package com.xlaoy.sso.service;

import com.xlaoy.common.constants.SSOConstants;
import com.xlaoy.common.utils.JSONUtil;
import com.xlaoy.sso.dto.Login1DTO;
import com.xlaoy.sso.entity.GlobalUserEntity;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@Service
public class LoginService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Clock clock = DefaultClock.INSTANCE;

    public String login1(Login1DTO dto) {

        logger.info("用户请求登录,info={}", JSONUtil.toJsonString(dto));

        /*GlobalUserEntity userEntity = globalUserRepository.findByUserNameAndPassword(dto.getUsername(), dto.getPassword());
        if(userEntity == null) {
            throw new BizException("用户名密码错误");
        }*/
        GlobalUserEntity userEntity = new GlobalUserEntity();
        userEntity.setGuid("asdfew2wcd2fds6sd");


        Map<String, Object> claims = new HashMap();
        claims.put(SSOConstants.GUID, userEntity.getGuid());

        logger.info("用户登录成功,info={}", JSONUtil.toJsonString(claims));

        return createJwtToken(claims);
    }

    private String createJwtToken(Map<String, Object> claims) {
        final Date createdDate = clock.now();
        Long expirationTime = createdDate.getTime() + (60 * 1000 * 30);
        final Date expirationDate = new Date(expirationTime);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, "wocao")
                .compact();
    }
}
