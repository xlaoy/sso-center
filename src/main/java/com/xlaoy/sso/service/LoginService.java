package com.xlaoy.sso.service;

import com.xlaoy.common.constants.SSOConstants;
import com.xlaoy.common.exception.BizException;
import com.xlaoy.common.utils.JSONUtil;
import com.xlaoy.sso.dto.LoginDTO;
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
import org.springframework.util.StringUtils;

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

    private Clock clock = DefaultClock.INSTANCE;

    @Autowired
    private IGlobalUserRepository userRepository;

    public String login(LoginDTO dto) {

        logger.info("用户请求登录,info={}", JSONUtil.toJsonString(dto));

        if(StringUtils.isEmpty(dto.getPhone())) {
            throw new BizException("手机号不能为空");
        }
        if(!"123456".equals(dto.getCode())) {
            throw new BizException("验证码错误");
        }

        GlobalUserEntity userEntity = userRepository.findByPhone(dto.getPhone());
        if(userEntity == null) {
            userEntity = new GlobalUserEntity();
            userEntity.setGuid(UUID.randomUUID().toString().replace("-", ""));
            userEntity.setNickName("xlaoy_" + dto.getPhone());
            userEntity.setPhone(dto.getPhone());
            userEntity.setPassword("123456");
            userRepository.save(userEntity);
        }

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
