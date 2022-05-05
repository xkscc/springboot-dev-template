package com.xkc.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: chenwei
 * @CreateTime: 2022-05-05  15:14
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class JsonResult<T> {

    private T data;
    private String code;
    private String msg;

    /**
     * 请求成功 没有数据返回时
     */
    public JsonResult(){
        code = "0";
        msg = "请求成功";
    }

    /**
     * 请求成功 没有数据返回 自定义返回信息以及状态码
     * @param code
     * @param msg
     */
    public JsonResult(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 请求成功 没有数据返回 自定义返回信息
     * @param msg
     */
    public JsonResult(String msg){
        this.code = "0";
        this.msg = msg;
    }

    /**
     * 请求成功 有数据返回 不需要自定义返回信息
     * @param data
     */
    public JsonResult(T data){
        code = "0";
        msg = "请求成功";
        this.data = data;
    }

    /**
     * 请求成功 有数据返回 同时自定义返回信息以及状态码
     * @param code
     * @param msg
     * @param data
     */
    public JsonResult(String code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     *  请求成功 有数据返回 自定义返回信息
     * @param msg
     * @param data
     */
    public JsonResult(String msg,T data){
        this.code = "0";
        this.msg = msg;
        this.data = data;
    }
}
