package com.shinemo.nsfw.user.entity;

import java.io.Serializable;

/**
 * 联合主键:角色和用户的id
 * 用户的权限都是通过角色关联实现
 * Created by pc on 2017/7/17.
 */
public class UserRole implements Serializable{
    private UserRoleId id;

    public UserRole() {}
    public UserRole(UserRoleId id) {
        this.id = id;
    }

    public UserRoleId getId() {
        return id;
    }
    public UserRole setId(UserRoleId id) {
        this.id = id;
        return this;
    }
}
