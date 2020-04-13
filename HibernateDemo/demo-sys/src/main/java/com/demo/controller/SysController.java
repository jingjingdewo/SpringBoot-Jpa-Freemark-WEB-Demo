package com.demo.controller;

import com.demo.constants.SysConst;
import com.demo.framework.annotate.Log;
import com.demo.framework.enums.ActionEnums;
import com.demo.framework.model.Message;
import com.demo.framework.utils.GsonUtil;
import com.demo.framework.utils.PasswordUtil;
import com.demo.model.User;
import com.demo.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author 静畏人心
 * @Description:系统登录及首页
 * @Date 2020/3/31 01:03
 */
@Controller("SysController")
@RequestMapping("/Sys")
public class SysController extends SysBaseController{

    @Autowired
    private ISysUserService sysUserService;
    /**
     * @Author 静畏人心
     * @Date 2020/4/9 0:09
     * @Description 管理员登录页面
     * @param modelMap
     */
    @RequestMapping(value = {"", "/", "/index","/login"}, method = RequestMethod.GET)
    public Object index(ModelMap modelMap){
        if (getCurAdmin() != null) {
            return "Home/index";
//           ## 这段代码可以通过数据库中的配置文件表来控制游客登录系统
//        } else if(StringUtils.trimToEmpty(SysConst.propConfig.get("sys_skip")).equals("true")) {
//            User user = new User();
//            user.setId((long) 0);
//            user.setLoginId(SysConst.propConfig.get("sa_name"));
//            user.setName(user.getLoginId());
//            user.setAlive(true);
//            setCurAdmin(user);
//            return "/index";
        }
        return "/index";
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/11 0:18
     * @Description 用户登录
     * @param username
     * @param password
     */
    @Log(userIdSessionKey = SysConst.SESSION_CUR_ADMIN_ID, userNameSessionKey = SysConst.SESSION_CUR_ADMIN_NAME, description = "后台用户登录", action = ActionEnums.Login)
    @RequestMapping(value = {"json/login"}, method = RequestMethod.POST)
    @ResponseBody
    public String jsonLogin(String username, String password) {
        Message msg = new Message();
        User user = null;
        if (StringUtils.isBlank(username)) {
            msg.setFailMsg(getMessage("message.login.no.username"));
        } else if (StringUtils.isBlank(password)) {
            msg.setFailMsg(getMessage("message.login.no.password"));
        } else {
            try {
                if (SysConst.propConfig.get("sa")
                        .equals(PasswordUtil.passwordToPasswordSalt(username, SysConst.propConfig.get("sa_salt")))) {
                    if (PasswordUtil
                            .passwordToPasswordSalt(password,
                                    SysConst.propConfig.get("sa_salt") + SysConst.propConfig.get("sa"))
                            .equals(SysConst.propConfig.get("sa_pwd"))) {
                        user = new User();
                        user.setId((long) 0);
                        user.setLoginId(SysConst.propConfig.get("sa_name"));
                        user.setName(user.getLoginId());
                        user.setAlive(true);
                        setCurAdmin(user);
                        msg.setSucceedMsg(getMessage("message.login.success"));
                    } else {
                        msg.setFailMsg(getMessage("message.login.error.info"));
                    }
                } else {
                    user = sysUserService.getByLoginId(username);
                    if (user == null) {
                        msg.setFailMsg(getMessage("message.login.error.info"));
                    } else if (user.getPwd()
                            .equals(PasswordUtil.passwordToPasswordSalt(password, user.getSecretKey()))) {
                        if (user.getAlive()) {
                            user.setPreVisit(user.getLastVisit());
                            user.setLastVisit(new Date());
                            user.setFirstVisit(user.getFirstVisit() == null ? new Date() : user.getFirstVisit());
                            user.setLoginCount(user.getLoginCount() + 1);
                            user.setOnline(true);
                            sysUserService.saveOrUpdate(user);
                            setCurAdmin(user);
                            msg.setSucceedMsg(getMessage("message.login.success"));
                        } else {
                            msg.setFailMsg(getMessage("message.login.user.disabeld"));
                        }
                    } else {
                        msg.setFailMsg(getMessage("message.login.error.info"));
                    }
                }
            } catch (Exception e) {
                msg.setFailMsg(getMessage("message.login.error"));
            }
        }
        return GsonUtil.toJson(msg);
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/11 1:06
     * @Description 退出登录
     * @param
     */
    @RequestMapping(value = "exit", method = { RequestMethod.GET, RequestMethod.POST })
    public Object jsonLogout() {
        User curUser = getCurAdmin();
        if (curUser != null && curUser.getId() != 0) {
            curUser = sysUserService.get(curUser.getId());
            curUser.setOnline(false);
            curUser.setLastVisit(new Date());
            sysUserService.saveOrUpdate(curUser);
        }
        getSession().removeAttribute(SysConst.SESSION_CUR_ADMIN);
        return "/index";
    }
}
