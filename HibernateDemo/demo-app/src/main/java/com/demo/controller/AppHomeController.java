package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 静畏人心
 * @Description: 客户端首页
 * @Date 2020/4/1 20:13
 */
@Controller("AppHomeController")
@RequestMapping("/App/Home")
public class AppHomeController extends AppBaseController{

    private final static String FOLDER = "App/";

    private final static String MODEL = "Home";

    private final static String REQUEST_MARK = "App";

    /**
     * @Author 静畏人心
     * @Date 2020/4/11 1:42
     * @Description 进入文章界面
     * @param modelMap
     */
    @RequestMapping("/index")
    public Object index(ModelMap modelMap){
        if(getCurUser() == null){
            return FOLDER + "index";
        }else{
            modelMap.put("PAGE_MODEL", MODEL);
            modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
            return FOLDER + "home/index";
        }
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/12 17:55
     * @Description 进入微语界面
     * @param modelMap
     */
    @RequestMapping("/whisper")
    public Object whisper(ModelMap modelMap){
        if(getCurUser() == null){
            return FOLDER + "index";
        }else{
            modelMap.put("PAGE_MODEL", MODEL);
            modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
            return FOLDER + "home/whisper";
        }
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/12 17:55
     * @Description 进入留言界面
     * @param modelMap
     */
    @RequestMapping("/leacots")
    public Object leacots(ModelMap modelMap){
        if(getCurUser() == null){
            return FOLDER + "index";
        }else{
            modelMap.put("PAGE_MODEL", MODEL);
            modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
            return FOLDER + "home/leacots";
        }
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/12 17:55
     * @Description 进入相册界面
     * @param modelMap
     */
    @RequestMapping("/album")
    public Object album(ModelMap modelMap){
        if(getCurUser() == null){
            return FOLDER + "index";
        }else{
            modelMap.put("PAGE_MODEL", MODEL);
            modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
            return FOLDER + "home/album";
        }
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/12 17:56
     * @Description 进入关于界面
     * @param modelMap
     */
    @RequestMapping("/about")
    public Object about(ModelMap modelMap){
        if(getCurUser() == null){
            return FOLDER + "index";
        }else{
            modelMap.put("PAGE_MODEL", MODEL);
            modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
            return FOLDER + "home/about";
        }
    }
}
