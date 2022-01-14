package com.cao.score.controller;

import com.cao.score.entity.Menu;
import com.cao.score.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 菜单表(Menu)表控制层
 *
 * @author makejava
 * @since 2022-01-11 17:46:12
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView menuMain(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/menu/menuMain");
        return modelAndView;
    }

}