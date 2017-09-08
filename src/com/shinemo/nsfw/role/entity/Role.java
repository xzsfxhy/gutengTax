package com.shinemo.nsfw.role.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by pc on 2017/7/6.
 */
public class Role implements Serializable{
    private String roleId;
    private String name;
    private String state;
    private Set<RolePrivilege> rolePrivilegeSet;
    public static String ROLE_STATE_VALIDE="1";
    public static String ROLE_STATE_INVALIDE="0";

    public Role() {
    }

    public Role(String roleId, String name, String state, RolePrivilege rolePrivilege, Set<RolePrivilege> rolePrivilegeSet) {
        this.roleId = roleId;
        this.name = name;
        this.state = state;
        this.rolePrivilegeSet = rolePrivilegeSet;
    }

    public Role(String roleId) {
        this.roleId=roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<RolePrivilege> getRolePrivilegeSet() {
        return rolePrivilegeSet;
    }

    public void setRolePrivilegeSet(Set<RolePrivilege> rolePrivilegeSet) {
        this.rolePrivilegeSet = rolePrivilegeSet;
    }
}
