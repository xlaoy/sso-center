package com.xlaoy.sso.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@Data
public class Login1DTO implements Serializable {

    private String username;

    private String password;
}
