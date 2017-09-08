package com.shinemo.core.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by pc on 2017/7/6.
 */
public abstract class BaseAction extends ActionSupport{

    protected String[] selectedRow;//封装所有选的行的id号

    public String[] getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }
}
