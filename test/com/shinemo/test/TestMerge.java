package com.shinemo.test;

import com.shinemo.test.entity.Person;
import com.shinemo.test.service.TestService;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pc on 2017/6/27.
 */
public class TestMerge {
    //spring的ioc容器
    ClassPathXmlApplicationContext cxp=null;
    @Before
    public void getIoc(){
        cxp=new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    //测试service层,测试spring和struts整合
    @Test
    public void testSpring(){
        //通过spring获取service
        TestService testService= (TestService) cxp.getBean("testService");
        testService.say();
    }
    //测试spring和hibernate整合
    @Test
    public void testHibernate(){
        SessionFactory sf = (SessionFactory) cxp.getBean("sessionFactory");
        //getcurrentsession是在绑定线程的时候使用的,这里用openSession,没有线程不能用getCurrentSession()
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        session.save(new Person("张三"));
        ts.commit();
        session.close();
    }
    //测试service和dao层
    @Test
    public void testServiceAndDao(){
        TestService testService = (TestService) cxp.getBean("testService");
        testService.save(new Person("古一"));
        //System.out.println();
    }
    //测试事务控制,只读事务,如果在只读事务中出现更新操作则回滚,testService这个接口来接收,切记
    @Test
    public void testTransaction(){
        TestService testService = (TestService) cxp.getBean("testService");
        System.out.println(testService.findById("40280d815ce9c567015ce9c56a540000").getName());
    }
    //回滚事务,如果回滚中有任何异常则回滚之前的操作
    @Test
    public void testRollback(){
        TestService testService = (TestService) cxp.getBean("testService");
        testService.save(new Person("古三"));
    }
}
