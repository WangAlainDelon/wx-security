package com.wx.wxsecuritydemo.controller;

import com.wx.wxsecuritydemo.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: wangxiang
 * Date: 2019/9/10
 * To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    //现在我要我自定义异常返回的信息是一个Map
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handUserNotExistException(UserNotExistException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", ex.getId());
        map.put("message", ex.getMessage());
        return map;
    }
}
