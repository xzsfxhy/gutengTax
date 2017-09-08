package com.shinemo.nsfw.user.entity;

import com.shinemo.nsfw.role.entity.Role;

import java.io.Serializable;

/**
 * 用户角色的联合主键
 * Created by pc on 2017/7/17.
 */
public class UserRoleId implements Serializable {
    private Role role;
    private String userId;

    public UserRoleId() {}
    public UserRoleId(Role role, String userId) {
        this.role = role;
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }
    public UserRoleId setRole(Role role) {
        this.role = role;
        return this;
    }
    public String getUserId() {
        return userId;
    }
    public UserRoleId setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserRoleId other = (UserRoleId) obj;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }


}
