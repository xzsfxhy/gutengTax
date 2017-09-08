package com.shinemo.login.action;

import com.opensymphony.xwork2.ActionSupport;
import com.shinemo.core.constant.Constant;
import com.shinemo.nsfw.user.entity.User;
import com.shinemo.nsfw.user.entity.UserRole;
import com.shinemo.nsfw.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pc on 2017/7/30.
 */
public class LoginAction extends ActionSupport{
    private User user;
    private String loginResult;
    @Resource
    private UserService userService;

    public String toLoginUI(){
        return "loginUI";
    }

    public String login(){
        if(user!=null){
            if(StringUtils.isNotBlank(user.getAccount())&&StringUtils.isNotBlank(user.getPassword())){
                List<User> list=userService.findUserByAccountAndPass(user.getAccount(),user.getPassword());
                if(list!=null&list.size()>0){
                    User user = list.get(0);
                    //查询用户角色,并保存到session
                    List<UserRole> userRoleList = userService.findUserRolesById(user.getId());
                    ServletActionContext.getRequest().setAttribute(Constant.USER,user);
                    Log log = LogFactory.getLog(getClass());
                    log.info("用户名称为:"+user.getName()+"的用户登录了系统");
                    return "home";
                }else{
                    loginResult="账号或密码不正确!";
                }
            }else{
                loginResult="账号或密码不能为空!";
            }
        }else {
            loginResult="请输入用户和密码!";
        }

        return toLoginUI();
    }

    public User getUser() {
        return user;
    }

    public LoginAction setUser(User user) {
        this.user = user;
        return this;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public LoginAction setLoginResult(String loginResult) {
        this.loginResult = loginResult;
        return this;
    }

    public String logout(){
        ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
        return toLoginUI();
    }

    public String toNoPermissionUI(){
        return "noPermissionUI";
    }
}
