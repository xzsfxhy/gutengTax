package com.shinemo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * 测试日志配置
 * Created by guteng on 2017/6/28.
 */
public class TestLog {
    @Test
    public void testLog(){
        //通过日志工厂类获取日志对象
        Log log = LogFactory.getLog(getClass());
        //log.error输出错误信息
        try {
            int i=1/0;
        }catch (Exception e){
            //通过log对象输出异常的类对象
            log.error(e.getMessage());
            e.printStackTrace();
        }
        //log日志输出四个级别,从低到高
        log.debug("debug级别");
        log.info("info级别");
        log.warn("warn级别");
        log.error("error级别");
        log.fatal("fatal级别致命");

    }
}
