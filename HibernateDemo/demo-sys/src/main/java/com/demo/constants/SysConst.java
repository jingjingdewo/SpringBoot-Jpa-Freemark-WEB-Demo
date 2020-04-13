package com.demo.constants;

import com.demo.framework.config.MailConfig;

import java.util.HashMap;
import java.util.Map;

public class SysConst {

	public final static String PROP_FILE = "config_sys";

	public static Map<String, String> propConfig = new HashMap<String, String>();

	public final static String SESSION_CUR_ADMIN_ID = "curAdminId";

	public final static String SESSION_CUR_ADMIN_NAME = "curAdminName";

	public final static String SESSION_CUR_ADMIN = "curAdmin";

	public static MailConfig mailConfig = new MailConfig();

	public final static String VISIT_CONTROL = "/sys/visit/";

}
