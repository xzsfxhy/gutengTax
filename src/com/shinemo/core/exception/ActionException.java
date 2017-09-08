package com.shinemo.core.exception;

/**
 * Created by pc on 2017/7/6.
 */
public abstract class ActionException extends SysException{
    public ActionException(){
        super("控制层出错了!");
    }
    public ActionException(String message){
        super(message);
    }
}
