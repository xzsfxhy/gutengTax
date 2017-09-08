package com.shinemo.nsfw.role.action;

import com.opensymphony.xwork2.ActionContext;
import com.shinemo.core.action.BaseAction;
import com.shinemo.core.constant.Constant;
import com.shinemo.nsfw.role.entity.Role;
import com.shinemo.nsfw.role.entity.RolePrivilege;
import com.shinemo.nsfw.role.entity.RolePrivilegeId;
import com.shinemo.nsfw.role.service.RoleService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * Created by pc on 2017/7/6.
 */
public class RoleAction extends BaseAction{
    @Resource
    private RoleService roleService;
    private Role role;
    private List<Role> roleList;
    private String[] rolePrivilegeIds;

    //列表页面
    public String listUI() throws Exception {
        //加载权限集合
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
        try {
            roleList=roleService.findObjects();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "listUI";
    }
    //进入新增页面
    public String addUI(){
        //加载权限集合
        ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_MAP);
        return "addUI";
    }
    //提交新增
    public String add(){
        try {
            if(role!=null){
                if(rolePrivilegeIds!=null){
                    HashSet<RolePrivilege> set=new HashSet<RolePrivilege>();
                    for(int i=0;i<rolePrivilegeIds.length;i++){
                        set.add(new RolePrivilege(new RolePrivilegeId(role,rolePrivilegeIds[i])));
                        role.setRolePrivilegeSet(set);
                    }
                    roleService.save(role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }
    //进入编辑页面
    public String editUI(){
        //加载权限集合
        ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_MAP);
        if(role!=null&&role.getRoleId()!=null){
            role = roleService.findObjectById(role.getRoleId());
            //处理数据回显
            if(role.getRolePrivilegeSet()!=null){
                rolePrivilegeIds=new String[role.getRolePrivilegeSet().size()];
                int i=0;
                for(RolePrivilege rp:role.getRolePrivilegeSet()){
                    rolePrivilegeIds[i++]=rp.getId().getCode();
                }
            }
        }
        return "editUI";
    }
    //提交编辑
    public String edit(){
        try {
            if(role!=null){
                if(rolePrivilegeIds!=null){
                    HashSet<RolePrivilege> set=new HashSet<RolePrivilege>();
                    for(int i=0;i<rolePrivilegeIds.length;i++){
                        set.add(new RolePrivilege(new RolePrivilegeId(role,rolePrivilegeIds[i])));
                        role.setRolePrivilegeSet(set);
                    }
                    roleService.save(role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }
    //删除
    public String delete(){
        if(role!=null&&role.getRoleId()!=null){
            roleService.delete(role.getRoleId());
        }
        return "list";
    }
    //批量删除
    public String deleteSelected(){
        if(selectedRow!=null){
            //遍历,批量删除
            for(String id:selectedRow){
                roleService.delete(id);
            }
        }
        return "list";
    }
    public RoleService getRoleService() {
        return roleService;
    }
    public RoleAction setRoleService(RoleService roleService) {
        this.roleService = roleService;
        return this;
    }
    public Role getRole() {
        return role;
    }
    public RoleAction setRole(Role role) {
        this.role = role;
        return this;
    }
    public List<Role> getRoleList() {
        return roleList;
    }
    public RoleAction setRoleList(List<Role> roleList) {
        this.roleList = roleList;
        return this;
    }
}
