package cn.e3mall.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册功能Controller
 * Title: RegitsterController
 * version 1.0
 */
@Controller
public class RegisterController {

    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }
}
