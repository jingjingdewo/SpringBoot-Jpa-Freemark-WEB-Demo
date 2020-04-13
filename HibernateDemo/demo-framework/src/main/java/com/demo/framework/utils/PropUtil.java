package com.demo.framework.utils;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class PropUtil {

	private static PropUtil instance = null;

	private PropUtil() {
	}

	public static PropUtil getInstance() {
		if (instance == null) {
			instance = new PropUtil();
		}
		return instance;
	}

	public static Map<String, String> getPropertyMap(String resourceName) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Properties prop = new Properties();
			prop.load(PropUtil.getInstance().getClass().getClassLoader()
					.getResourceAsStream(resourceName + ".properties"));
			Set<Entry<Object, Object>> set = prop.entrySet();
			Iterator<Entry<Object, Object>> it = set.iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				String fuKey = getWildcard(value);
				if (null != fuKey && null != prop.get(fuKey)) {
					String fuValue = prop.get(fuKey).toString();
					value = value.replaceAll("\\$\\{" + fuKey + "\\}", fuValue);
				}
				map.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return map;
	}

	private static String getWildcard(String str) {
		if (null != str && str.indexOf("${") != -1) {
			int start = str.indexOf("${");
			int end = str.indexOf("}");
			if (start != -1 && end != -1) {
				return str.substring(start + 2, end);
			}
		}
		return null;
	}

	public String getByKey(String fname, String key) {
		try {
			Properties p = new Properties();
			p.load(PropUtil.getInstance().getClass().getClassLoader().getResourceAsStream(fname + ".properties"));
			if (p.containsKey(key)) {
				return p.getProperty(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
