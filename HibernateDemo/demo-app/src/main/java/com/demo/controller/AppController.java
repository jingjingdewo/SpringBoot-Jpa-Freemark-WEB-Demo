package com.demo.controller;

import com.demo.constants.AppConst;
import com.demo.framework.annotate.Log;
import com.demo.framework.enums.ActionEnums;
import com.demo.framework.model.Message;
import com.demo.framework.utils.GsonUtil;
import com.demo.framework.utils.PasswordUtil;
import com.demo.model.Person;
import com.demo.service.IAppPersonService;
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
 * @Description: 客户端登录
 * @Date 2020/4/6 16:33
 */
@Controller("AppController")
@RequestMapping("/App")
public class AppController extends AppBaseController{

    private final static String FOLDER = "App/";

    private final static String REQUEST_MARK = "App";

    @Autowired
    private IAppPersonService appPersonService;
    /**
     * @Author 静畏人心
     * @Date 2020/4/8 0:06
     * @Description 客户端首页
     * @param modelMap
     */
    @RequestMapping(value = {"", "/", "/index","/login"}, method = RequestMethod.GET)
    public Object index(ModelMap modelMap) {
        if (getCurUser() != null) {
            return FOLDER + "/home/index";
//           ## 这段代码可以通过数据库中的配置文件表来控制游客登录系统
//        } else if(StringUtils.trimToEmpty(AppConst.propConfig.get("app_skip")).equals("true")) {
//            Person person = new Person();
//            person.setId((long) 0);
//            person.setLoginId(AppConst.propConfig.get("sa_name"));
//            person.setName(person.getLoginId());
//            person.setAlive(true);
//            setCurUser(person);
//            return "/index";
        }
        return FOLDER + "index";
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/12 15:56
     * @Description 客户端用户登录
     * @param username
     * @param password
     */
    @Log(userIdSessionKey = AppConst.SESSION_CUR_USER_ID, userNameSessionKey = AppConst.SESSION_CUR_USER_NAME, description = "后台用户登录", action = ActionEnums.Login)
    @RequestMapping(value = {"json/login"}, method = RequestMethod.POST)
    @ResponseBody
    public String jsonLogin(String username, String password) {
        Message msg = new Message();
        Person person = null;
        if (StringUtils.isBlank(username)) {
            msg.setFailMsg(getMessage("message.login.no.username"));
        } else if (StringUtils.isBlank(password)) {
            msg.setFailMsg(getMessage("message.login.no.password"));
        } else {
            try {
                person = appPersonService.getByLoginId(username);
                if (person == null) {
                    msg.setFailMsg(getMessage("message.login.error.info"));
                } else if (person.getPwd()
                        .equals(PasswordUtil.passwordToPasswordSalt(password, person.getSecretKey()))) {
                    if (person.getAlive()) {
                        person.setPreVisit(person.getLastVisit());
                        person.setLastVisit(new Date());
                        person.setFirstVisit(person.getFirstVisit() == null ? new Date() : person.getFirstVisit());
                        person.setLoginCount(person.getLoginCount() + 1);
                        person.setOnline(true);
                        appPersonService.saveOrUpdate(person);
                        setCurUser(person);
                        msg.setSucceedMsg(getMessage("message.login.success"));
                    } else {
                        msg.setFailMsg(getMessage("message.login.user.disabeld"));
                    }
                } else {
                    msg.setFailMsg(getMessage("message.login.error.info"));
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
        Person curPerson = getCurUser();
        if (curPerson != null && curPerson.getId() != 0) {
            curPerson = appPersonService.get(curPerson.getId());
            curPerson.setOnline(false);
            curPerson.setLastVisit(new Date());
            appPersonService.saveOrUpdate(curPerson);
        }
        getSession().removeAttribute(AppConst.SESSION_CUR_USER);
        return "/index";
    }
}

