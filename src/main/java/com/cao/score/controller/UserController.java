package com.cao.score.controller;

import com.cao.score.entity.Role;
import com.cao.score.entity.User;
import com.cao.score.entity.UserRole;
import com.cao.score.service.RoleService;
import com.cao.score.service.UserRoleService;
import com.cao.score.service.UserService;
import com.cao.score.shiro.SaltUtil;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.utiles.ScoreStringUtils;
import com.cao.score.vo.DataTablesResult;
import com.cao.score.vo.ObjectParams;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-01-11 17:47:34
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static Logger logger= LoggerFactory.getLogger(UserController.class);

    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;


    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView userMain(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/user/userMain");
        return modelAndView;
    }

    /**
     * 用户列表信息
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/datas")
    public DataTablesResult<User> datas(ObjectParams params) {
        return userService.dataLists(params);
    }

    /**
     * 跳转到修改用户界面
     * @param params
     * @return
     */
    @RequestMapping("/editUser")
    public ModelAndView editUser(ObjectParams params) {
        ModelAndView modelAndView = new ModelAndView();
        if(params!=null && params.getUserId()!=null){
            User userByUserId = userService.queryById(params.getUserId());
            modelAndView.addObject("user",userByUserId);
            List<String> roles=userRoleService.selectUserRoles(params.getUserId());
            modelAndView.addObject("roles",ScoreStringUtils.getJsonObj(roles));
        }
        List<Role> roleList = roleService.queryAllByMap(null);
        modelAndView.addObject("roleList",roleList);

        modelAndView.setViewName("/user/editUser");
        return modelAndView;
    }


    /**
     * 删除用户
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/delUser")
    public String delUser(ObjectParams params) {
        try{
            userService.deleteById(params.getUserId());
        }catch (Exception e){
            logger.error("删除用户异常,异常信息；"+e.getMessage(),e);
            ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"删除用户异常");
        }
        return ResponseUtil.printJson("删除用户成功",null);
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveUser")
    public String saveUser(User user) {
        try{
            Long userId = user.getUserId();
            if(userId!=null){
                User user1 = userService.update(user);
            }else{
                //密码赋值
                user=userService.setUserPossword(user.getUserPwd(),user,true);
                user.setCreateTime(new Date());
                Object u=SecurityUtils.getSubject().getPrincipal();
                if(u!=null){
                    user.setCreator(String.valueOf(u));
                }
                userService.insert(user);
            }
            String[] roleIds=user.getRoleIds().split(",");
            userRoleService.editUserRoles(roleIds,userId);
        }catch (Exception e){
            logger.error("新增对象异常,异常信息："+e.getMessage(),e);
            ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"新增对象异常");
        }
        return ResponseUtil.printJson("新增对象成功",null);
    }

    /**
     * 修改当前用户的密码
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping("/editPassword")
    public String editPassword(String password) {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if(principal!=null){
            String userName =String.valueOf(principal);
            User u=new User();
            u.setUserName(userName);
            User user = userService.queryOne(u);
            if(user!=null) {
                //密码赋值
                user=userService.setUserPossword(password,user,false);
                userService.update(user);
            }else{
                return ResponseUtil.printFailJson(ResponseUtil.PARAMS_ERROR,"参数异常");
            }
        }
        return ResponseUtil.printJson("修改密码成功",null);
    }
    /**
     * 通过用户名重置密码
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/resetPassword")
    public String resetPassword(String studentId) {
        String result = "";
        try {
            if(ScoreStringUtils.isBlank(studentId)){
                return ResponseUtil.printFailJson(ResponseUtil.PARAMS_ERROR,"参数异常");
            }
            Object principal = SecurityUtils.getSubject().getPrincipal();
            if(principal!=null){
                String userName =studentId;

                User u=new User();
                u.setUserName(userName);
                User user = userService.queryOne(u);
                if(user!=null) {
                    //密码赋值
                    user=userService.setUserPossword(user.getOriginalPassword(),user,false);
                    userService.update(user);
                }else{
                    return ResponseUtil.printFailJson(ResponseUtil.PARAMS_ERROR,"参数异常");
                }
            }
        }catch (Exception e){
            logger.error("重置密码异常,异常信息："+e.getMessage(),e);
            return ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"重置密码异常");
        }
        return ResponseUtil.printJson("重置密码成功",null);
    }


}