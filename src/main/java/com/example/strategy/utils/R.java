package com.example.strategy.utils;

import java.io.Serializable;

/**
 * @author Administrator
 * @since 2020/2/20 18:18
 */
public class R<T> implements Serializable {
    private  static  R ar = null;
    private static final long serialVersionUID = -7442927961544597584L;

    private int code;

    private String msg;

    private double cost;


    private T data;

    public R(){}

    public  static final  R getInstance(){
        if(ar==null){//默认返回成功,data为空
            return  new R(OpCode.Success,null,new Object());
        }else {
            return ar;
        }
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R fail(String msg){
        return R.fail(msg,null);
    }
    public static R fail(String msg,Object data){
        return new R(OpCode.Internal,msg,data);
    }

    public static R sucess(String msg){
        return R.sucess(msg,null);
    }

    public static R sucess(String msg,Object data){
        return new R(OpCode.Success,msg,data);
    }

    public static R sucess(Object data){
        return new R(OpCode.Success,null,data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", cost=" + cost +
                ", data=" + data +
                '}';
    }
}
