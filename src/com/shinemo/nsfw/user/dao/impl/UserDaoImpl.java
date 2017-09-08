package com.shinemo.nsfw.user.dao.impl;

import com.shinemo.core.dao.impl.BaseDaoImpl;
import com.shinemo.nsfw.user.dao.UserDao;
import com.shinemo.nsfw.user.entity.User;
import com.shinemo.nsfw.user.entity.UserRole;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

/**
 * 继承核心dao的实现,实现userdao的接口
 * Created by pc on 2017/6/28.
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public List<User> findUserByAccountAndId(String id, String account) {
        String hql = "FROM User WHERE account = ?";
        if(StringUtils.isNotBlank(id)){
            hql += " AND id!=?";
        }
        Query query = getSession().createQuery(hql);
        query.setParameter(0, account);
        if(StringUtils.isNotBlank(id)){
            query.setParameter(1, id);
        }

        return query.list();
    }

    @Override
    public List<UserRole> findUserRolesById(String id) {
        Query query = getSession().createQuery("from UserRole where id.userId=?");
        query.setParameter(0,id);
        return query.list();
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        getHibernateTemplate().save(userRole);
    }

    @Override
    public void deleteUserRoleByUserId(Serializable id) {
        Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId=?");
        query.setParameter(0,id);
        query.executeUpdate();
    }

    @Override
    public List<User> findUsersByAccountAndPass(String account, String password) {
        Query query = getSession().createQuery("DELETE FROM User WHERE account=? AND password=?");
        query.setParameter(0,account);
        query.setParameter(1,password);
        return query.list();
    }
}
