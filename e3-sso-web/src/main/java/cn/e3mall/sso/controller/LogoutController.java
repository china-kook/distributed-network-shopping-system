package cn.e3mall.sso.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.LogoutService;

/**
 * 退出登录功能Controller
 * Title: RegisterController
 * version 1.0
 */
@Controller
public class LogoutController {

    @Resource
    private LogoutService logoutService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping("/page/logout")
    public String showRegister() {
        return "login";
    }

    @RequestMapping("/user/logout/{token}")
    public String logout(@PathVariable String token, String callback, HttpServletRequest request,
                         HttpServletResponse response) {

        E3Result e3Result = logoutService.userLogout(token);

        if (e3Result.getStatus() == 200) {
            // 删除 Cookie
            CookieUtils.deleteCookie(request, response, TOKEN_KEY);
        }

//        //响应结果之前，判断是否为jsonp请求
//        if (StringUtils.isNotBlank(callback)) {
//            //把结果封装成一个js语句响应
//            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(e3Result);
//            mappingJacksonValue.setJsonpFunction(callback);
//            return mappingJacksonValue;
//        }

        return "login";

    }


}














