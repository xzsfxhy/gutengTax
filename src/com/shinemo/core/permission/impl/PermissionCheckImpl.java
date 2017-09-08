package com.shinemo.core.permission.impl;

import com.shinemo.core.permission.PermissionCheck;
import com.shinemo.nsfw.role.entity.Role;
import com.shinemo.nsfw.role.entity.RolePrivilege;
import com.shinemo.nsfw.user.entity.User;
import com.shinemo.nsfw.user.entity.UserRole;
import com.shinemo.nsfw.user.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pc on 2017/7/31.
 */
public class PermissionCheckImpl implements PermissionCheck {
    @Resource
    private UserService userService;

    @Override
    public boolean isAccessible(User user, String code) {
        List<UserRole> list = user.getUserRoles();
        if(list==null){
            list=userService.findUserRolesById(user.getId());
        }
        if (list != null && list.size() > 0) {
            for (UserRole ur : list) {
                Role role = ur.getId().getRole();
                for(RolePrivilege rp:role.getRolePrivilegeSet()){
                    if(code.equals(rp.getId().getCode())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
