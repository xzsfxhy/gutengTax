package com.shinemo.test.service;

import com.shinemo.test.entity.Person;

import java.io.Serializable;

/**
 * Created by pc on 2017/6/27.
 */
public interface TestService {
    //测试say
    public void say();
    //保存人员
    public void save(Person person);
    //根据id查询
    public Person findById(Serializable id);
}
