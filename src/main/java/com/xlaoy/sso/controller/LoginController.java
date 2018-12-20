package com.xlaoy.sso.controller;

import com.xlaoy.common.constants.SSOConstants;
import com.xlaoy.sso.dto.LoginDTO;
import com.xlaoy.sso.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2018/2/27 0027.
 */
@Api(tags = "登陆 API")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/sso/login")
    @ApiOperation(response = String.class, value = "登陆")
    public LocalDateTime login1(@RequestBody LoginDTO dto, HttpServletResponse response) {
        response.addHeader(SSOConstants.JWT_TOKEN, loginService.login(dto));
        return LocalDateTime.now();
    }

}
