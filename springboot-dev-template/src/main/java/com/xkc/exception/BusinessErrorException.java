package com.xkc.exception;

import com.xkc.myenum.BusinessMsgEnum;
import lombok.Data;

/**
 * @Author: chenwei
 * @CreateTime: 2022-05-05  14:59
 * @Description: 自定义异常
 * @Version: 1.0
 */
@Data
public class BusinessErrorException extends RuntimeException {

    /**
     * 异常码
     */
    private String code;
    /**
     * 异常提示信息
     */
    private String message;

    public BusinessErrorException(BusinessMsgEnum businessMsgEnum) {
        this.code = businessMsgEnum.getCode();
        this.message = businessMsgEnum.getMsg();
    }

}
