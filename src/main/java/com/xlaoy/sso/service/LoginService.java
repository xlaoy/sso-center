package com.xlaoy.sso.service;

import com.xlaoy.common.exception.BizException;
import com.xlaoy.common.utils.JSONUtil;
import com.xlaoy.sso.dto.Login1DTO;
import com.xlaoy.sso.entity.GlobalUserEntity;
import com.xlaoy.sso.repository.IGlobalUserRepository;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@Service
public class LoginService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IGlobalUserRepository globalUserRepository;
    @Autowired
    private UserRoleRelaService userRoleRelaService;

    private Clock clock = DefaultClock.INSTANCE;

    public String login1(Login1DTO dto) {

        String requestId = UUID.randomUUID().toString().replace("-", "");

        logger.info("用户请求登录,info={},requestId={}", JSONUtil.toJsonString(dto), requestId);

        GlobalUserEntity userEntity = globalUserRepository.findByUserNameAndPassword(dto.getUsername(), dto.getPassword());
        if(userEntity == null) {
            throw new BizException("用户名密码错误");
        }

        //获取用户权限
        String roles = userRoleRelaService.getRolesByUserId(userEntity.getId());

        Map<String, Object> claims = new HashMap();
        claims.put("guid", userEntity.getGuid());
        claims.put("roles", roles);

        logger.info("用户登录成功,info={},requestId={}", JSONUtil.toJsonString(claims), requestId);

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
