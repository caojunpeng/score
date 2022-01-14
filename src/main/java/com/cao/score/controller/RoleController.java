package com.cao.score.controller;

import com.cao.score.entity.Role;
import com.cao.score.entity.User;
import com.cao.score.service.RoleService;
import com.cao.score.shiro.SaltUtil;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

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
        if(params!=null && params.getId()!=null){
            Role role = roleService.queryById(params.getId());
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
            roleService.deleteById(params.getId());
        }catch (Exception e){
            logger.error("删除角色异常,异常信息；"+e.getMessage(),e);
            ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"删除角色异常",e.getMessage());
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
            Long roleId = role.getRoleId();
            if(roleId!=null){
                roleService.update(role);
            }else{
                roleService.insert(role);
            }
        }catch (Exception e){
            logger.error("保存角色异常,异常信息："+e.getMessage(),e);
            ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"保存角色异常",e.getMessage());
        }
        return ResponseUtil.printJson("保存角色成功",null);
    }
}