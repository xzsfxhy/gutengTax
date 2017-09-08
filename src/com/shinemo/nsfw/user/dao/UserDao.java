package com.shinemo.nsfw.user.dao;

import com.shinemo.core.dao.BaseDao;
import com.shinemo.nsfw.user.entity.User;
import com.shinemo.nsfw.user.entity.UserRole;

import java.io.Serializable;
import java.util.List;

/**
 * dao层直接继承核心basedao
 * Created by guteng on 2017/6/28.
 */
public interface UserDao extends BaseDao<User>{
    List<User> findUserByAccountAndId(String id, String account);

    List<UserRole> findUserRolesById(String id);
    //保存用户角色
    void saveUserRole(UserRole userRole);
    //删除用户角色
    void deleteUserRoleByUserId(Serializable id);

    List<User> findUsersByAccountAndPass(String account, String password);
}


