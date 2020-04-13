package com.demo.controller;

import com.demo.constants.AppConst;
import com.demo.framework.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * @Author 静畏人心
 * @Description: 获取客户端配置信息
 * @Date 2020/4/12 1:42
 */
@Controller("appInitializeController")
public class InitializeController {

    @Autowired
    private IConfigService configServ;

    @PostConstruct
    private void startInit() {
        System.out.println("App初始化配置信息");
        AppConst.propConfig = configServ.initConfig(AppConst.PROP_FILE);
//		AppConst.mailConfig = new MailConfig(AppConst.propConfig);
    }
}