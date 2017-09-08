package com.shinemo.nsfw.role.service.impl;

import com.shinemo.core.service.impl.BaseServiceImpl;
import com.shinemo.nsfw.role.dao.RoleDao;
import com.shinemo.nsfw.role.entity.Role;
import com.shinemo.nsfw.role.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by pc on 2017/7/6.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{
    private RoleDao roleDao;
    @Resource
    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }

    @Resource

    @Override
    public void update(Role role) {
        //插入新权限之前,先删除原有的权限集合
        roleDao.deletePrivilegesByRoleId(role.getRoleId());
        roleDao.update(role);
    }

}
