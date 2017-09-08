package com.shinemo.core.dao.impl;

import com.shinemo.core.dao.BaseDao;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 核心dao层实现
 * Created by guteng on 2017/6/28.
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    //声明泛型类类型为T
    Class<T> clazz;

    //通过反射获取该泛型对象类型
    public BaseDaoImpl() {
        //通过当前类的反射获取到的是当前类的类型:BaseDaoImpl<T>
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取第一个参数就是T的类型
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    //增,保存
    @Override
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    //删,删除
    @Override
    public void delete(Serializable id) {
        //调用当前类中的其他方法实现
        getHibernateTemplate().delete(findObjectById(id));
    }

    //更改,更新
    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public T findObjectById(Serializable id) {
        //通过反射获取类类型
        return getHibernateTemplate().get(clazz, id);
    }

    @Override
    public List<T> findObjects() {
        //hibernatedaosupport支持getsession和gethibernateTemplate
        Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
        return query.list();

    }

    @Override
    public List<T> findObjects(String hql, List<Object> parameters) {
        Query query = getSession().createQuery(hql);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
            }
        }
        return query.list();
    }
}
