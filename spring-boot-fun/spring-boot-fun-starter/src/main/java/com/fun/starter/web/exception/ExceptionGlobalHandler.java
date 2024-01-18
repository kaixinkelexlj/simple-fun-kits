package com.fun.starter.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xulujun
 * @date 2018/06/27
 */
@ControllerAdvice
public class ExceptionGlobalHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionGlobalHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(HttpServletRequest request, HttpServletResponse response, Exception ex)  throws Exception{
        LOGGER.error(String.format("exception,request[%s]", request.getRequestURI()), ex);
        return "error";
    }

}
