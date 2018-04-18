package com.xlaoy.sso.entity;

import com.xlaoy.common.jpa.AbstractEntity;
import lombok.Data;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@Data
public class GlobalUserEntity extends AbstractEntity {

    private String guid;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String showname;
}
