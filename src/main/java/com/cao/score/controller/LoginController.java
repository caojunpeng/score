package com.cao.score.controller;


import com.cao.score.entity.User;
import com.cao.score.service.UserService;
import com.cao.score.shiro.SaltUtil;
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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class LoginController {

    private Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    /**
     * 登录验证
     * @return
     */
    @RequestMapping("/loginHtml")
    private String login(String userName, String userPwd){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(userName,userPwd));
            System.out.println("登录成功！！！");
            return "redirect:/index";
        }catch (UnknownAccountException e) {
            System.out.println("用户错误！！！");
            return "redirect:/login";
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误！！！");
            return "redirect:/login";
        }
    }

    /**
     * 登录界面
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 新增用户页面
     * @return
     */
    @RequestMapping("/saveUserPage")
    public ModelAndView saveUserPage() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/user/addUser");
        return modelAndView;
    }

    /**
     * 保存用户
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
            Md5Hash md5 = new Md5Hash(user.getUserPwd(),salt,1024);
            user.setUserPwd(md5.toHex());
            userService.insert(user);
            return "redirect:/login";
        } catch (Exception e) {
            logger.error("新增用户异常，异常信息："+e.getMessage(),e);
            return "redirect:/login";
        }

    }

    /**
     * 退出登录
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
     * @return
     */
    @RequestMapping("/index")
    private ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }
}
