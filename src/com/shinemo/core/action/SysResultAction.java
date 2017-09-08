package com.shinemo.core.action;

import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pc on 2017/7/6.
 */
public class SysResultAction extends StrutsResultSupport{
    /**
      * @Description:
      * @author: guteng
      * @Param: [s, actionInvocation]
      * @Date:  2017/7/6
     */
          
    @Override
    protected void doExecute(String s, ActionInvocation actionInvocation) throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        BaseAction baseAction= (BaseAction) actionInvocation.getAction();
        //......需要处理的扩展异常信息
        System.out.println("进入了sysResultAction...");
    }
}
