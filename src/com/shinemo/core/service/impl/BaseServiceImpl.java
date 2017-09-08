package com.shinemo.core.service.impl;

import com.shinemo.core.dao.BaseDao;
import com.shinemo.core.service.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 2017/8/16.
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    private BaseDao<T> baseDao;
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao=baseDao;
    }
    @Override
    public void save(T entity) {
        baseDao.save(entity);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }

    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    public T findObjectById(Serializable id) {
        return (T)baseDao.findObjectById(id);
    }

    @Override
    public List<T> findObjects() {
        return baseDao.findObjects();
    }

    @Override
    public List<T> findObjects(String hql, List<Object> parameters) {
        return baseDao.findObjects(hql,parameters);
    }
}
