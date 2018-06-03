package cn.e3mall.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.RegisterService;

/**
 * 注册功能Controller
 * Title: RegisterController
 * version 1.0
 */
@Controller
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public E3Result checkData(@PathVariable String param, @PathVariable Integer type) {
        E3Result e3Result = registerService.checkData(param, type);
        return e3Result;
    }
}