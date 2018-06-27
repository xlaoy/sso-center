package com.xlaoy.sso.service;

import com.xlaoy.sso.entity.RolesEntity;
import com.xlaoy.sso.entity.UserRoleRelaEntity;
import com.xlaoy.sso.repository.IRolesRepository;
import com.xlaoy.sso.repository.IUserRoleRelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Administrator on 2018/6/26 0026.
 */
@Service
public class UserRoleRelaService {

    @Autowired
    private IUserRoleRelaRepository userRoleRelaRepository;
    @Autowired
    private IRolesRepository rolesRepository;


    public String getRolesByUserId(Integer userId) {
        List<UserRoleRelaEntity> relaList = userRoleRelaRepository.findByUserId(userId);
        List<Integer> roleIdList = relaList.stream().map(UserRoleRelaEntity::getRoleId).collect(Collectors.toList());
        List<RolesEntity> rolesEntityList = rolesRepository.findAllById(roleIdList);
        StringBuilder sb = new StringBuilder("");
        rolesEntityList.stream().forEach(role -> {
            if(StringUtils.isEmpty(sb.toString())) {
                sb.append(role.getRoleName() + ",");
            } else {
                sb.append(role.getRoleName());
            }
        });
        return sb.toString();
    }
}
