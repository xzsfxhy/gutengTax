package com.shinemo.test.action;

import com.opensymphony.xwork2.ActionSupport;
import com.shinemo.test.service.TestService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by pc on 2017/6/27.
 */
@Controller
public class TestAction extends ActionSupport{
    @Resource
    TestService testService;

    public String execute(){
        testService.say();
        return SUCCESS;
    }
}
