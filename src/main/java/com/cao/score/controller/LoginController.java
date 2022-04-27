package com.cao.score.controller;


import com.cao.score.entity.Menu;
import com.cao.score.entity.RoleMenu;
import com.cao.score.entity.User;
import com.cao.score.service.MenuService;
import com.cao.score.service.RoleMenuService;
import com.cao.score.service.UserRoleService;
import com.cao.score.service.UserService;
import com.cao.score.shiro.SaltUtil;
import com.cao.score.utiles.ResponseUtil;
import com.cao.score.utiles.ScoreStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录管理
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private MenuService menuService;

    /**
     * 登录验证
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/loginHtml")
    private String login(String userName, String userPwd) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(userName, userPwd));
            System.out.println("登录成功！！！");
            return ResponseUtil.printJson("登录成功",null);
        } catch (UnknownAccountException e) {
            System.out.println("用户错误！！！");
            return ResponseUtil.printFailJson(10000,"未知用户");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误！！！");
            return ResponseUtil.printFailJson(20000,"密码错误");
        }
    }

    /**
     * 登录界面
     *
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    /**
     * 新增用户页面
     *
     * @return
     */
    @RequestMapping("/saveUserPage")
    public ModelAndView saveUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/addUser");
        return modelAndView;
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public String register(User user) {
        try {
            //1.获取随机盐
            String salt = SaltUtil.getSalt(8);
            //2.将随机盐保存到数据
            user.setSalt(salt);
            //3.明文密码进行md5 + salt + hash散列
            Md5Hash md5 = new Md5Hash(user.getUserPwd(), salt, 1024);
            user.setUserPwd(md5.toHex());
            userService.insert(user);
            return "redirect:/login";
        } catch (Exception e) {
            logger.error("新增用户异常，异常信息：" + e.getMessage(), e);
            return "redirect:/login";
        }

    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/loginOut")
    public String loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/index")
    private ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        Object object = SecurityUtils.getSubject().getPrincipal();
        modelAndView.addObject("userName", object);
        //登录用户的最高权限
        Integer role = userRoleService.selectRolesByUserName();
        modelAndView.addObject("role", role);
        Map<String, Object> map = new HashMap();
        //授权的所有菜单
        List<Integer> menuIds = new ArrayList<>();
        //当前登录对象
        User user = userService.queryOne(new User(String.valueOf(object)));
        if (user != null) {
            List<String> roleIds = userRoleService.selectUserRoles(user.getUserId());
            if (!roleIds.isEmpty()) {
                for (String roleId : roleIds) {
                    map.put("roleId", roleId);
                    List<RoleMenu> list=roleMenuService.queryByMap(map);
                    if(!list.isEmpty()){
                        for (RoleMenu roleMenu : list) {
                            menuIds.add(roleMenu.getMenuId());
                        }
                    }
                }
            }
        }
        map.clear();
        map.put("parentName", "main");
        map.put("status", 1);
        //返回的菜单
        List<Menu> result=new ArrayList<>();
        //所有的菜单
        List<Menu> menusList = menuService.getMenusByMaps(map);
        if(!menusList.isEmpty()){
            for (Menu menu : menusList) {
                if(menuIds.contains(Integer.valueOf(menu.getMenuId()+""))){
                    result.add(menu);
                }
            }
        }
        modelAndView.addObject("menulist", result);
        modelAndView.setViewName("/index");
        return modelAndView;
    }
}
