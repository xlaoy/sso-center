package com.xlaoy.sso.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@Data
public class GlobalUserEntity implements Serializable{

    private String guid;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String showname;
}
