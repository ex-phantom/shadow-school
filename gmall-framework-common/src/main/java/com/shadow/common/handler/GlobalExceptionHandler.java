package com.shadow.common.handler;

import com.shadow.common.constant.GlobalExceptionUtil;
import com.shadow.common.constant.R;
import com.shadow.common.constant.ResultCodeEnum;
import com.shadow.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
//        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e){
        e.printStackTrace();
//        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R error(HttpMessageNotReadableException e){
        e.printStackTrace();
//        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }


    @ExceptionHandler(GlobalExceptionUtil.class)
    @ResponseBody
    public R error(GlobalExceptionUtil e){
        e.printStackTrace();
//        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.error().code(e.getCode()).message(e.getMessage());
    }
}
