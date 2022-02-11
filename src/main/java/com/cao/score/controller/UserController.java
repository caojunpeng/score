package com.cao.score.controller;

import com.cao.score.entity.User;
import com.cao.score.service.UserService;
import com.cao.score.shiro.SaltUtil;
import com.cao.score.utiles.ResponseUtil;
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
import java.util.Date;

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
        }
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
                //1.获取随机盐
                String salt = SaltUtil.getSalt(8);
                //2.将随机盐保存到数据
                user.setSalt(salt);
                //3.明文密码进行md5 + salt + hash散列
                Md5Hash md5 = new Md5Hash(user.getUserPwd(),salt,1024);
                user.setUserPwd(md5.toHex());
                user.setCreateTime(new Date());
                User u=(User)SecurityUtils.getSubject().getPrincipal();
                if(u!=null){
                    user.setCreator(u.getUserName());
                }
                userService.insert(user);
            }
        }catch (Exception e){
            logger.error("新增对象异常,异常信息："+e.getMessage(),e);
            ResponseUtil.printFailJson(ResponseUtil.SERVERUPLOAD,"新增对象异常");
        }
        return ResponseUtil.printJson("新增对象成功",null);
    }

}