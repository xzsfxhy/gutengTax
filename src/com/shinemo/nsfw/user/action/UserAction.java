package com.shinemo.nsfw.user.action;

import com.opensymphony.xwork2.ActionContext;
import com.shinemo.core.action.BaseAction;
import com.shinemo.nsfw.role.service.RoleService;
import com.shinemo.nsfw.user.entity.User;
import com.shinemo.nsfw.user.entity.UserRole;
import com.shinemo.nsfw.user.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 约定:跳转页面方法都以UI结尾
 * crud,对应action至少包含6个方法,分别对应增删改查
 * 查:列表页面(查)
 * 增:进入新增(增)页面
 * 增:保存新增,跳转到列表页面
 * 删:跳转到列表页面
 * 改:进入更改编辑页面
 * 改:保存更改,跳转到列表页面
 * Created by pc on 2017/6/28.
 */
@Component
public class UserAction extends BaseAction {
    @Resource
    UserService userService;
    private List<User> userList;
    @Resource
    private RoleService roleService;
    private User user;
    private File headImg;//接收客户端上传文件
    private String headImgContentType;//接收上传文件类型
    private String headImgFileName;
    private File userExcel;
    private String userExcelContentType;
    private String userExcelFileName;
    private String[] userRoleIds;

    //查:列表页面(查)
    public String listUI() throws Exception{
        //查出列表所有user,返回给list<user>,封装所有user,jsp前端展示要用
        try {
            userList=userService.findObjects();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return "listUI";
    }

    //增:进入新增(增)页面
    public String addUI() {
        //加载角色列表
        ActionContext.getContext().getContextMap().put("roleList",roleService.findObjects());
        return "addUI";
    }

    //增:保存新增,跳转到列表页面
    public String add() {
        try {
            if (user != null) {
                if(headImg!=null){
                    //1.保存头像到指定目录,绝对路径
                    String filePath= ServletActionContext.getServletContext().getRealPath("upload/user");
                    String fileName= UUID.randomUUID().toString().replaceAll("-","")
                            +headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    FileUtils.copyFile(headImg,new File(filePath,fileName));
                    //2.设置头像路径,封装到user实体中
                    user.setHeadImg("user/"+fileName);

                }
                userService.saveUserAndRole(user,userRoleIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    //删:跳转到列表页面
    public String delete() {
        //根据id删除的,所有判断条件必须包含id不能为空,否则空指针异常
        if (user != null&&user.getId()!=null) {
            userService.delete(user.getId());
        }
        return "list";
    }

    //删:批量删除,跳转到列表页面
    public String deleteRows() {
        if(selectedRow!=null){
            for(String id:selectedRow){
                userService.delete(id);
            }
        }
        return "list";
    }

    //改:进入更改编辑页面,要获取封装的user信息
    public String editUI() {
        //进入编辑页面,加载角色列表
        ActionContext.getContext().getContextMap().put("roleList",roleService.findObjects());
        //判断条件,只要user和用户的id不为空
        if (user != null && user.getId()!=null) {
            user=userService.findObjectById(user.getId());
            //处理数据回显
            List<UserRole> userRoleList=userService.findUserRolesById(user.getId());
            if(userRoleList!=null&&userRoleList.size()>0){
                userRoleIds=new String[userRoleList.size()];
                for(int i=0;i<userRoleList.size();i++){
                    userRoleIds[i]=userRoleList.get(i).getId().getRole().getRoleId();
                }
            }
        }
        return "editUI";
    }

    //改:保存更改,跳转到列表页面
    public String edit() {
        //判断条件,只要user不为空就能跳转
        try {
            if (user != null ) {
                if(headImg!=null){
                    //1.保存头像到指定目录,绝对路径
                    String filePath= ServletActionContext.getServletContext().getRealPath("/upload/user");
                    String fileName= UUID.randomUUID().toString().replaceAll("-","")
                            +headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    FileUtils.copyFile(headImg,new File(filePath,fileName));
                    //2.设置头像路径,封装到user实体中
                    user.setHeadImg("user/"+fileName);

                }
                userService.updateUserAndRole(user,userRoleIds);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "list";
    }
    //导出用户列表
    public void exportExcel(){
        try {
            //1、查找用户列表
            userList = userService.findObjects();
            //2、导出
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
            ServletOutputStream outputStream = response.getOutputStream();
            userService.exportExcel(userList, outputStream);
            if(outputStream != null){
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //导入
    /*public String importExcel(){
        if(userExcel!=null){
            if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
                userService.importExcel(userExcel,userExcelFileName);
            }
        }
        return "list";
    }*/
    public String importExcel(){
        //1、获取excel文件
        if(userExcel != null){
            //是否是excel
            if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
                //2、导入
                userService.importExcel(userExcel, userExcelFileName);
            }
        }
        return "list";
    }

    //校验账号的唯一性
    //校验用户帐号唯一
    //校验用户帐号唯一
    public void verifyAccount(){
        try {
            //1、获取帐号
            if(user != null && StringUtils.isNotBlank(user.getAccount())){
                //2、根据帐号到数据库中校验是否存在该帐号对应的用户
                List<User> list = userService.findUserByAccountAndId(user.getId(), user.getAccount());
                String strResult = "true";
                if(list != null && list.size() > 0){
                    //说明该帐号已经存在
                    strResult = "false";
                }

                //输出
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setContentType("text/html");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(strResult.getBytes());
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public File getUserExcel() {
        return userExcel;
    }

    public void setUserExcel(File userExcel) {
        this.userExcel = userExcel;
    }

    public String getUserExcelContentType() {
        return userExcelContentType;
    }

    public void setUserExcelContentType(String userExcelContentType) {
        this.userExcelContentType = userExcelContentType;
    }

    public String getUserExcelFileName() {
        return userExcelFileName;
    }

    public void setUserExcelFileName(String userExcelFileName) {
        this.userExcelFileName = userExcelFileName;
    }

    public String[] getUserRoleIds() {
        return userRoleIds;
    }

    public UserAction setUserRoleIds(String[] userRoleIds) {
        this.userRoleIds = userRoleIds;
        return this;
    }
}
