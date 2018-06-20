package com.xlaoy.sso.controller;

import com.xlaoy.sso.dto.Login1DTO;
import com.xlaoy.sso.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2018/2/27 0027.
 */
@Api(tags = "登陆 API")
@RestController
@RequestMapping("/sso")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login1")
    @ApiOperation(response = String.class, value = "登陆")
    public LocalDateTime login1(@RequestBody Login1DTO dto, HttpServletResponse response) {
        response.addHeader("jwttoken", loginService.login1(dto));
        return LocalDateTime.now();
    }

}
