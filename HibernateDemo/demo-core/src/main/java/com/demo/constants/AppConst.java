package com.demo.constants;

import com.demo.framework.config.MailConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 静畏人心
 * @Description: 添加注释
 * @Date 2020/4/12 1:34
 */
public class AppConst {
    
    public final static String PROP_FILE = "config_app";

    public static Map<String, String> propConfig = new HashMap<String, String>();

    public final static String SESSION_CUR_USER_ID = "curUserId";

    public final static String SESSION_CUR_USER_NAME = "curUserName";

    public final static String SESSION_CUR_USER = "curUser";

    public static MailConfig mailConfig = new MailConfig();

    public final static String VISIT_CONTROL = "/app/visit/";
}
