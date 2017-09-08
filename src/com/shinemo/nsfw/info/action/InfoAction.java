package com.shinemo.nsfw.info.action;

import com.opensymphony.xwork2.ActionContext;
import com.shinemo.core.action.BaseAction;
import com.shinemo.core.util.QueryHelper;
import com.shinemo.nsfw.info.entity.Info;
import com.shinemo.nsfw.info.service.InfoService;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2017/7/6.
 */
public class InfoAction extends BaseAction{
    @Resource
    private InfoService infoService;
    private Info info;
    private List<Info> infoList;
    private String[] infoPrivilegeIds;
    private String i;

    //异步发布信息
    public void publicInfo(){
        try {
            if(info!=null){
                //更新信息状态
                Info item = infoService.findObjectById(info.getInfoId());
                item.setState(info.getState());
                infoService.update(item);
                //输出结果
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setContentType("text/html");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write("更新状态成功".getBytes("utf-8"));
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //列表页面
    public String listUI() throws Exception {
        //加载权限集合
        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);
        QueryHelper queryHelper=new QueryHelper(Info.class,i);

        try {
            infoList=infoService.findObjects();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "listUI";
    }
    //进入新增页面
    public String addUI(){
        //加载权限集合
        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);
        info=new Info();
        info.setCreateTime(new Timestamp(new Date().getTime()));
        return "addUI";
    }
    //提交新增
    public String add(){
        try {
            infoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }
    //进入编辑页面
    public String editUI(){
        //加载权限集合
        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);
        if(info!=null&&info.getInfoId()!=null){
            info = infoService.findObjectById(info.getInfoId());
        }
        return "editUI";
    }
    //提交编辑
    public String edit(){
        try {
            if(info!=null){
                    infoService.update(info);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }
    //删除
    public String delete(){
        if(info!=null&&info.getInfoId()!=null){
            infoService.delete(info.getInfoId());
        }
        return "list";
    }
    //批量删除
    public String deleteSelected(){
        if(selectedRow!=null){
            //遍历,批量删除
            for(String id:selectedRow){
                infoService.delete(id);
            }
        }
        return "list";
    }
    public InfoService getInfoService() {
        return infoService;
    }
    public InfoAction setInfoService(InfoService infoService) {
        this.infoService = infoService;
        return this;
    }
    public Info getInfo() {
        return info;
    }
    public InfoAction setInfo(Info info) {
        this.info = info;
        return this;
    }
    public List<Info> getInfoList() {
        return infoList;
    }
    public InfoAction setInfoList(List<Info> infoList) {
        this.infoList = infoList;
        return this;
    }
}
