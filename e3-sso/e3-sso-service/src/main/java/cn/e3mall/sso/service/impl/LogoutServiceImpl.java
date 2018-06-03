package cn.e3mall.sso.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.LogoutService;

@Service
public class LogoutServiceImpl implements LogoutService {

    @Resource
    private JedisClient jedisClient;

    @Override
    public E3Result userLogout(String token) {

        // 删除 redis 中的 key(即 token)
        jedisClient.del("SESSION:" + token);

        return E3Result.ok();
    }
}