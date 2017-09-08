package com.shinemo.nsfw.role.dao.impl;

import com.shinemo.core.dao.impl.BaseDaoImpl;
import com.shinemo.nsfw.role.dao.RoleDao;
import com.shinemo.nsfw.role.entity.Role;
import org.hibernate.Query;

/**
 * 根据角色id删除角色对应的所有权限
 * Created by pc on 2017/7/6.
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{
    @Override
    public void deletePrivilegesByRoleId(String roleId) {
        Query query=getSession().createQuery("DELETE FROM RolePrivilege where id.role.roleId=? ");
        query.setParameter(0,roleId);
        query.executeUpdate();
    }
}
