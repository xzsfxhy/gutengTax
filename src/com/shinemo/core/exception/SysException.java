package com.shinemo.core.exception;

/**
 * Created by pc on 2017/7/6.
 */
public abstract class SysException extends Exception{
    private String msgError;

    public SysException(){
        super();
    }

    public SysException(String message,Throwable cause){
        super(message,cause);
        msgError=message;
    }
    public SysException(String message) {
        super(message);
        msgError=message;
    }
    public SysException(Throwable throwable){
        super(throwable);
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
}
