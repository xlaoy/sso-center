package com.xlaoy.sso.controller;

import com.xlaoy.sso.dto.Login1DTO;
import com.xlaoy.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/2/27 0027.
 */
@RestController
@RequestMapping("/sso")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login1")
    public String login1(Login1DTO dto, HttpServletResponse response) {
        response.addHeader("jwttoken", loginService.login1(dto));
        return "xlaoy";
    }

}
