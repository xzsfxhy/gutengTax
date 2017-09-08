package com.shinemo.core.filter;

import com.shinemo.core.constant.Constant;
import com.shinemo.core.permission.PermissionCheck;
import com.shinemo.nsfw.user.entity.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pc on 2017/7/31.
 */
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri=request.getRequestURI();
        if(uri.contains("/sys/login_")){
            if(request.getSession().getAttribute(Constant.USER)!=null){
                if(uri.contains("/nsfw/")){
                    User user=(User) request.getSession().getAttribute(Constant.USER);
                    WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                    PermissionCheck pc= (PermissionCheck) webApplicationContext.getBean("permissionCheck");
                    if(pc.isAccessible(user,"nsfw")){
                        chain.doFilter(request,response);
                    }else {
                        response.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
                    }
                }else{
                    chain.doFilter(request,response);
                }
            }else {
                response.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action");
            }
        }else {
            chain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
