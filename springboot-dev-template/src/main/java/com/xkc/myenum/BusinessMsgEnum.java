package com.xkc.myenum;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: chenwei
 * @CreateTime: 2022-05-05  15:01
 * @Description: 异常信息枚举
 * @Version: 1.0
 */
@Getter
public enum BusinessMsgEnum {

    /** 参数异常 */
    PARMETER_EXCEPTION("102", "参数异常!"),
    /** 等待超时 */
    SERVICE_TIME_OUT("103", "服务调用超时！"),
    /** 500 根据需要自己添加其他 该出直接500处理其他任何异常 */
    UNEXPECTED_EXCEPTION("500", "系统发生异常，请联系管理员！");

    /**
     * 消息码
     */
    private String code;
    /**
     * 消息内容
     */
    private String msg;

    private BusinessMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     *  根据异常码获取描述信息
     * @param key
     * @return
     */
    public static String getValueByKey(String key) {
        BusinessMsgEnum[] enums = BusinessMsgEnum.values();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getCode().equals(key)) {
                return enums[i].getMsg();
            }
        }
        return "";
    }
}
