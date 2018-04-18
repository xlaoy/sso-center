package com.xlaoy.sso.service;

import com.xlaoy.common.exception.BizException;
import com.xlaoy.sso.dto.Login1DTO;
import com.xlaoy.sso.entity.GlobalUserEntity;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@Service
public class LoginService {

    private Clock clock = DefaultClock.INSTANCE;

    public String login1(Login1DTO dto) {
        GlobalUserEntity userEntity = new GlobalUserEntity();
        userEntity.setGuid("1216b6eac408476a9223cc3358853adb");
        userEntity.setUsername("xlaoy");
        userEntity.setPassword("xlaoy");
        userEntity.setPhone("15618503525");
        userEntity.setEmail("768881412@qq.com");

        if(!userEntity.getUsername().equals(dto.getUsername())
                && userEntity.getPassword().equals(dto.getPassword())) {
            throw new BizException("用户名密码错误");
        }

        //获取用户权限
        String roles = "ROLE_USER,ROLE_SHOP";

        Map<String, Object> claims = new HashMap();
        claims.put("guid", userEntity.getGuid());
        claims.put("roles", roles);

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
