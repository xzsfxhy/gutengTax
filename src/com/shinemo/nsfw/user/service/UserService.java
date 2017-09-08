package com.shinemo.nsfw.user.service;

import com.shinemo.core.service.BaseService;
import com.shinemo.nsfw.user.entity.User;
import com.shinemo.nsfw.user.entity.UserRole;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.util.List;

/**
 * 里面要包含basedao的基本方法
 * 然后再扩展
 * Created by guteng on 2017/6/28.
 */
public interface UserService extends BaseService<User>{
    //导出excel表格
    public void exportExcel(List<User> userList, ServletOutputStream outputStream);
    //导入excel表格
    public void importExcel(File userExcel, String userExcelFileName);

    public List<User> findUserByAccountAndId(String id, String account);

    public List<UserRole> findUserRolesById(String id);

    public void saveUserAndRole(User user, String... roleIds);

    public void updateUserAndRole(User user, String... roleIds);

    public List<User> findUserByAccountAndPass(String account, String password);
}
