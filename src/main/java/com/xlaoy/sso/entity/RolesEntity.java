package com.xlaoy.sso.entity;

import com.xlaoy.common.jpa.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@Entity
@Table(name = "roles")
@DynamicInsert
@DynamicUpdate
@Data
public class RolesEntity extends AbstractEntity {

    private String roleName;

}
