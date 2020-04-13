package com.demo.controller;

import com.demo.constants.SysConst;
import com.demo.framework.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * @Author 静畏人心
 * @Date 2020/4/10 23:53
 * @Description 获取系统配置信息
 */
@Controller("sysInitializeController")
public class InitializeController {

	@Autowired
	private IConfigService configServ;

	@PostConstruct
	private void startInit() {
		System.out.println("Sys初始化配置信息");
		SysConst.propConfig = configServ.initConfig(SysConst.PROP_FILE);
//		SysConst.mailConfig = new MailConfig(SysConst.propConfig);
	}
}
