package com.njty.tydp.model;

import com.njty.tydp.commom.ErrCode;

import java.io.Serializable;

public class MsgModel implements Serializable{
    private String msg = "操作失败";
    private int code;
    private boolean success = false;
    private int total;
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setErrTip(int code, String msg){
        this.success = false;
        this.code = code;
        this.msg = msg;
    }


    public void setErrCode(ErrCode errCode){
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.success = false;
    }

    public static MsgModel getErrRet(int errCode, String msg) {
        MsgModel msgModel = new MsgModel();
        msgModel.setErrTip(errCode, msg);
        return msgModel;
    }

    public static MsgModel getErrRet(ErrCode errCode){
        MsgModel msgModel = new MsgModel();
        msgModel.setErrCode(errCode);
        return  msgModel;
    }

    public static MsgModel getSuccessRet(Object data) {
        MsgModel msgModel = new MsgModel();
        msgModel.setSuccessData(data);
        return msgModel;
    }

    public void setSuccessData(Object data) {
        this.msg = "操作成功";
        this.data = data;
        this.success = true;

    }
}
