package com.cao.score.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/main")
    private ModelAndView login(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }


    /**
     * 获取数据(测试)
     * @return
     */
    @ResponseBody
    @RequestMapping("/data")
    private String data(){

        return JSON.toJSONString(null);
    }
}
