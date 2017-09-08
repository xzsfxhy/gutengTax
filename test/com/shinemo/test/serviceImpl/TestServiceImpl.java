package com.shinemo.test.serviceImpl;

import com.shinemo.test.dao.TestDao;
import com.shinemo.test.entity.Person;
import com.shinemo.test.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by pc on 2017/6/27.
 */
@Service("testService")
public class TestServiceImpl implements TestService{
    @Resource TestDao testDao;
    @Override
    public void say() {
        System.out.println("first");
    }

    @Override
    public void save(Person person) {
        testDao.save(person);
        //int i=1/0;测试回滚事务
    }

    @Override
    public Person findById(Serializable id) {

        return testDao.findById(id);
    }
}
