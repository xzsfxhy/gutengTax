package com.shinemo.test.dao;

import com.shinemo.test.entity.Person;

import java.io.Serializable;

/**
 * Created by guteng on 2017/6/27.
 */

public interface TestDao {
    //保存人员
    public void save(Person person);
    //根据id查询
    public Person findById(Serializable id);
}
