package com.shinemo.nsfw.role.entity;

/**
 * Created by pc on 2017/7/6.
 */
public class RolePrivilege {
    private RolePrivilegeId id;

    public RolePrivilege(){}

    public RolePrivilege(RolePrivilegeId id) {
        this.id = id;
    }

    public RolePrivilegeId getId() {
        return id;
    }

    public void setId(RolePrivilegeId id) {
        this.id = id;
    }
}
