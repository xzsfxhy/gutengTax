package com.shinemo.nsfw.role.entity;

/**
 * Created by pc on 2017/7/6.
 */
public class RolePrivilegeId {
    private Role role;
    private String code;

    public RolePrivilegeId() {
    }

    public RolePrivilegeId(Role role, String code) {
        this.role = role;
        this.code = code;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
