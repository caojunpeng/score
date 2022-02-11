package com.cao.score.controller;

import com.cao.score.entity.Menu;
import com.cao.score.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/adminPage")
    public ModelAndView adminPage(){
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> map=new HashMap();
        map.put("parentName","admin");
        map.put("status",1);
        List<Menu> menusList = menuService.getMenusByMaps(map);
        modelAndView.addObject("menuList",menusList);
        map.put("menuName","admin");
        Menu menu=menuService.getMenuOneByMaps(map);
        List<Menu> parentMenuList = new ArrayList();
        parentMenuList.add(menu);
        modelAndView.addObject("parentMenu",parentMenuList);
        modelAndView.setViewName("/admin");
        return modelAndView;
    }
}
