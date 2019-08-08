package com.shadow.common.constant;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
//@Component//需要一个空参的构造器
public class GlobalExceptionUtil  extends  RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

//    public GlobalExceptionUtil() {
//        this.code = code;
//    }

    public GlobalExceptionUtil(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code=resultCodeEnum.getCode();
    }

    private  Integer code;

    public GlobalExceptionUtil(String message, Integer code) {
        super(message);
        this.code = code;
    }

    @Override
    public String toString() {
        return "ExceptionUtil{" +
                "code='" + code + '\'' +
                '}';
    }
}
