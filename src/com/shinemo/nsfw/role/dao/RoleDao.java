package com.shinemo.nsfw.role.dao;

import com.shinemo.core.dao.BaseDao;
import com.shinemo.nsfw.role.entity.Role;

/**
 * Created by pc on 2017/7/6.
 */
public interface RoleDao extends BaseDao<Role>{
    //根据角色id删除该角色对应的所有权限
    public void deletePrivilegesByRoleId(String roleId);
}
