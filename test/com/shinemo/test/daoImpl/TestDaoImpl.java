package com.shinemo.test.daoImpl;

import com.shinemo.test.dao.TestDao;
import com.shinemo.test.entity.Person;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;

/**
 * Created by guteng on 2017/6/27.
 */
public class TestDaoImpl extends HibernateDaoSupport implements TestDao{
    @Override
    public void save(Person person) {
        getHibernateTemplate().save(person);

    }

    @Override
    public Person findById(Serializable id) {
        return getHibernateTemplate().get(Person.class,id);
    }
}
