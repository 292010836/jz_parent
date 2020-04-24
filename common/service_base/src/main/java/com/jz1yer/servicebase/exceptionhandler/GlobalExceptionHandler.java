package com.jz1yer.servicebase.exceptionhandler;


import com.jz1yer.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j//代替private  final Logger logger = LoggerFactory.getLogger(当前类名.class);
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return new Result("全局异常!!");
    }

    @ExceptionHandler(MyException.class)
    public Result error(MyException e){
        e.printStackTrace();
        log.error(e.getMsg());
        return new Result(e.getCode(),e.getMsg());
    }
}
