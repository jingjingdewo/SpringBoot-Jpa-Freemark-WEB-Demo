package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 静畏人心
 * @Description: 首页
 * @Date 2020/4/1 20:13
 */
@Controller("HomeController")
@RequestMapping("/Sys/Home")
public class HomeController extends SysBaseController{

    private final static String FOLDER = "home/";

    private final static String MODEL = "Home";

    private final static String REQUEST_MARK = "Sys";

    /**
     * @Author 静畏人心
     * @Date 2020/4/11 1:42
     * @Description 进入首页界面
     * @param modelMap
     */
    @RequestMapping("/index")
    public Object index(ModelMap modelMap){
        if(getCurAdmin() == null){
            return "index";
        }else{
            modelMap.put("PAGE_MODEL", MODEL);
            modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
            return FOLDER + "index";
        }
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/11 1:04
     * @Description 首页我的桌面
     * @param modelMap
     */
    @RequestMapping("/frame")
    public Object frame(ModelMap modelMap){
        modelMap.put("PAGE_MODEL", MODEL);
        modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
        return FOLDER + "frame";
    }
}
