package com.cao.score.controller;

import com.cao.score.entity.Menu;
import com.cao.score.service.MenuService;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 菜单表(Menu)表控制层
 *
 * @author makejava
 * @since 2022-01-11 17:46:12
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    private static Logger logger= LoggerFactory.getLogger(MenuController.class);

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

    @RequestMapping("/editMenu")
    public ModelAndView editmenu(ObjectParams menuParams) {
        ModelAndView modelAndView = new ModelAndView();
        if(menuParams!=null && menuParams.getMenuId()!=null){
            Menu menuBymenuId = menuService.queryById(menuParams.getMenuId());
            modelAndView.addObject("menu",menuBymenuId);
        }
        modelAndView.setViewName("/menu/editMenu");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/datas")
    public DataTablesResult<Menu> datas(ObjectParams menuParams) {
        return menuService.dataLists(menuParams);
    }

    @ResponseBody
    @RequestMapping("/delMenu")
    public boolean delmenu(ObjectParams menuParams) {
        return menuService.deleteById(menuParams.getMenuId());
    }

    @ResponseBody
    @RequestMapping("/saveMenu")
    public String savemenu(Menu menu) {
        try{
            if(menu.getMenuId()!=null){
                menuService.update(menu);
            }else{
                menu.setCreateTime(new Date());
                menuService.insert(menu);
            }
        }catch (Exception e){
            logger.error("菜单更新异常，异常信息："+e.getMessage(),e);
            return ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"菜单更新异常");
        }

        return ResponseUtil.printJson("菜单更新成功",null);
    }

}