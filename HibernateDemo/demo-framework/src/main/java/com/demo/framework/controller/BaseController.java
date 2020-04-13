package com.demo.framework.controller;


import com.demo.framework.ehcache.EhcacheHandler;
import com.demo.framework.model.Message;
import com.demo.framework.utils.RequestUtil;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public abstract class BaseController extends EhcacheHandler {

	/**
	 * 得到request对象
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	protected RequestContext getRequestContext() {
		return new RequestContext(getRequest());
	}

	protected String getMessage(String code) {
		return new RequestContext(getRequest()).getMessage(code);
	}

	/**
	 * 得到response对象
	 */
	protected HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		return response;
	}

	/**
	 * 得到session对象
	 */
	protected HttpSession getSession() {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return null;
		} else {
			return request.getSession();
		}
	}

	protected boolean isAjaxRequest(HttpServletRequest request) {
		return RequestUtil.isAjaxRequest(request);
	}

}
