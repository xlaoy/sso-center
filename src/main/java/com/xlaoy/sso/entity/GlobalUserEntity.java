package com.xlaoy.sso.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@Data
@Document(collection = "global_user")
public class GlobalUserEntity {

    private String guid;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private String nickName;
}
