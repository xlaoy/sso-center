package com.xlaoy.sso.entity;

import com.xlaoy.common.jpa.AbstractEntity;
import lombok.Data;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
/*@Entity
@Table(name = "global_user")
@DynamicInsert
@DynamicUpdate*/
@Data
public class GlobalUserEntity {//extends AbstractEntity {

    private String guid;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private String nickName;
}
