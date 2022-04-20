package com.springboot.xuhui.study.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
//RestController
public class redistest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping(value = "/redistest")
    @ResponseBody
    public String redisTest(){

        redisTemplate.opsForValue().set("sex","male");
        String sex = redisTemplate.opsForValue().get("sex");
        return sex;
    }
}
