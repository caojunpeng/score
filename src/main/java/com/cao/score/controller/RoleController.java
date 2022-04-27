package com.cao.score.controller;

import com.cao.score.entity.Role;
import com.cao.score.entity.RoleMenu;
import com.cao.score.service.MenuService;
import com.cao.score.service.RoleMenuService;
import com.cao.score.service.RoleService;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.utiles.ScoreStringUtils;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import com.cao.score.vo.ZtreeObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * 角色表(Role)表控制层
 *
 * @author makejava
 * @since 2022-01-11 17:47:07
 */
@RestController
@RequestMapping("role")
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;
    /**
     * 服务对象
     */
    @Resource
    private RoleMenuService roleMenuService;


    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView roleMain(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/role/roleMain");
        return modelAndView;
    }
    /**
     * 权限管理
     * @return
     */
    @RequestMapping("/roleMenuList")
    public ModelAndView roleMenuList(Long roleId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("roleId",roleId);
        List<ZtreeObject> ztreeList= menuService.getZtreeObjectList(roleId);
        modelAndView.addObject("ztreeDatas",ScoreStringUtils.getJsonObj(ztreeList));
        modelAndView.setViewName("/role/roleMenuList");
        return modelAndView;
    }

    /**
     * 角色列表信息
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/datas")
    public DataTablesResult<Role> datas(ObjectParams params) {
        return roleService.dataLists(params);
    }

    /**
     * 跳转到修改角色界面
     * @param params
     * @return
     */
    @RequestMapping("/editRole")
    public ModelAndView editRole(ObjectParams params) {
        ModelAndView modelAndView = new ModelAndView();
        if(params!=null && params.getRoleId()!=null){
            Role role = roleService.queryById(params.getRoleId());
            modelAndView.addObject("role",role);
        }
        modelAndView.setViewName("/role/editRole");
        return modelAndView;
    }

    /**
     * 删除角色
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/delRole")
    public String delRole(ObjectParams params) {
        try{
            roleService.deleteById(params.getRoleId());
        }catch (Exception e){
            logger.error("删除角色异常,异常信息；"+e.getMessage(),e);
            ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"删除角色异常");
        }
        return ResponseUtil.printJson("删除角色成功",null);
    }

    /**
     * 保存角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveRole")
    public String saveRole(Role role) {
        try{
            if(role==null){
                ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"参数异常");
            }
            Long roleId = role.getRoleId();
            if(roleId!=null){
                roleService.update(role);
            }else{
                role.setCreateTime(new Date());
                roleService.insert(role);
            }
        }catch (Exception e){
            logger.error("保存角色异常,异常信息："+e.getMessage(),e);
            ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"保存角色异常");
        }
        return ResponseUtil.printJson("保存角色成功",null);
    }

    /**
     * 分配权限
     * @param id 角色id
     * @param menuIds 菜单id
     * @return
     */
    @ResponseBody
    @RequestMapping("/addRoleMenu")
    public String addRoleMenu(Long id,String[] menuIds) {
        try{
            //先删除
            roleMenuService.deleteByRoleId(id);
            if(menuIds.length > 0){
                for (String menuId : menuIds) {
                    RoleMenu roleMenu = new RoleMenu(null,Integer.valueOf(id+""),Integer.valueOf(menuId));
                    roleMenuService.insert(roleMenu);
                }
            }
        }catch (Exception e){
            logger.error("保存角色异常,异常信息："+e.getMessage(),e);
            ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"保存角色异常");
        }
        return ResponseUtil.printJson("保存角色成功",null);
    }
}