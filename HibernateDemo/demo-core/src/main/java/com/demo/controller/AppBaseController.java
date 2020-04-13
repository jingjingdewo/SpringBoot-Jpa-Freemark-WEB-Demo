package com.demo.controller;

import com.demo.constants.AppConst;
import com.demo.framework.controller.BaseController;
import com.demo.model.Person;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

/**
 * @Author 静畏人心
 * @Description: 添加注释
 * @Date 2020/4/12 1:28
 */
public abstract class AppBaseController extends BaseController {
    protected void setPageCommonParams(ModelMap modelMap, String requestMark, String modelName) {
        modelMap.put("PAGE_REQUEST_MARK", requestMark);
        modelMap.put("PAGE_MODEL", modelName);
    }

    protected Person getCurUser() {
        HttpSession session = getSession();
        if (session == null) {
            return null;
        } else {
            return (Person) session.getAttribute(AppConst.SESSION_CUR_USER);
        }
    }

    protected void setCurUser(Person person) {
        getSession().setAttribute(AppConst.SESSION_CUR_USER, person);
        getSession().setAttribute(AppConst.SESSION_CUR_USER_ID, person.getId());
        getSession().setAttribute(AppConst.SESSION_CUR_USER_NAME, person.getName());
    }
}
