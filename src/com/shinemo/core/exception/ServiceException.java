package com.shinemo.core.exception;

/**
 * Created by pc on 2017/7/6.
 */
public class ServiceException extends SysException{
    public ServiceException(){
        super("业务层出错!");
    }
    public ServiceException(String message){
        super(message);
    }
}
