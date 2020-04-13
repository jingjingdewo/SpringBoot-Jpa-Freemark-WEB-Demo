package com.demo.controller;

import com.demo.constants.SysConst;
import com.demo.framework.controller.BaseController;
import com.demo.model.User;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

/**
 * @Author 静畏人心
 * @Date 2020/4/9 0:25
 * @Description 全局控制
 */
public abstract class SysBaseController extends BaseController {

	protected void setPageCommonParams(ModelMap modelMap, String requestMark, String modelName) {
		modelMap.put("PAGE_REQUEST_MARK", requestMark);
		modelMap.put("PAGE_MODEL", modelName);
	}

	protected User getCurAdmin() {
		HttpSession session = getSession();
		if (session == null) {
			return null;
		} else {
			return (User) session.getAttribute(SysConst.SESSION_CUR_ADMIN);
		}
	}

	protected void setCurAdmin(User user) {
		getSession().setAttribute(SysConst.SESSION_CUR_ADMIN, user);
		getSession().setAttribute(SysConst.SESSION_CUR_ADMIN_ID, user.getId());
		getSession().setAttribute(SysConst.SESSION_CUR_ADMIN_NAME, user.getName());
	}

}
