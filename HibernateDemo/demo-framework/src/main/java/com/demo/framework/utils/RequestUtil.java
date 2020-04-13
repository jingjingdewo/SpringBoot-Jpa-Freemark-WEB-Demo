package com.demo.framework.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * request工具类
 * 
 * @author
 * 
 */
public class RequestUtil {

	private static Pattern IPV6_PATTERN = Pattern.compile(
			"^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){1,4})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){0,4}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}))$");
	private static Pattern IPV4_PATTERN = Pattern
			.compile("^(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$");

	public static boolean isIpv4(String value) {
		return IPV4_PATTERN.matcher(value).matches();
	}

	public static boolean isIpv6(String value) {
		return IPV6_PATTERN.matcher(value).matches();
	}

	public static boolean isIp(String value) {
		return isIpv4(value) || isIpv6(value);
	}

	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}

	public static String encodeURI(String key, String value) {
		try {
			return URLEncoder.encode(key.toString(), "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String encodeURI(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String decodeURI(String value) {
		try {
			if (value != null) {
				return URLDecoder.decode(value, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/***
	 * 获取 request 参数内容
	 * 
	 * @param request
	 * @return :
	 * @throws IOException
	 */
	public static String getRequestGETQueryString(HttpServletRequest request) throws IOException {
		String submitMehtod = request.getMethod();
		if (submitMehtod.equals("GET")) {
			return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8").replaceAll("%22", "\"");
		} else {
			return null;
		}
	}

	/**
	 * 描述:获取 post 请求内容
	 * 
	 * <pre>
	 * 举例：
	 * </pre>
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestPostDataByInputStream(HttpServletRequest request) throws IOException {
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		StringBuffer content = new StringBuffer();
		InputStream in = request.getInputStream();
		BufferedInputStream buf = new BufferedInputStream(in);
		byte[] buffer = new byte[1024];
		int iRead;
		while ((iRead = buf.read(buffer)) != -1) {
			content.append(new String(buffer, 0, iRead, charEncoding));
		}
		return content.toString();
	}

	public static Map<String, String> requestQueryStringToMap(String queryString) {
		Map<String, String> mapRequest = new HashMap<String, String>();
		if (StringUtils.isBlank(queryString)) {
			return mapRequest;
		}
		String[] arrSplit = queryString.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");
			if (arrSplitEqual.length > 1) {
				mapRequest.put(arrSplitEqual[0], strSplit.substring(arrSplitEqual[0].length() + 1));
			} else {
				if (arrSplitEqual[0] != "") {
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取访问用户的客户端IP（适用于公网与局域网）.
	 */
	public static final String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String ipString = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getRemoteAddr();
		}

		// 多个路由时，取第一个非unknown的ip
		final String[] arr = ipString.split(",");
		for (final String str : arr) {
			if (!"unknown".equalsIgnoreCase(str)) {
				ipString = str;
				break;
			}
		}

		return ipString;
	}

	/**
	 * 根据IP地址获取mac地址
	 * 
	 * @param ipAddress 如：127.0.0.1
	 * @return
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static String getMac(String ipAddress) throws SocketException, UnknownHostException {
		String str = "";
		String macAddress = "";
		final String LOOPBACK_ADDRESS = "127.0.0.1";
		// 如果为127.0.0.1,则获取本地MAC地址。
		if (LOOPBACK_ADDRESS.equals(ipAddress)) {
			InetAddress inetAddress = InetAddress.getLocalHost();
			// 貌似此方法需要JDK1.6。
			byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
			// 下面代码是把mac地址拼装成String
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				// mac[i] & 0xFF 是为了把byte转化为正整数
				String s = Integer.toHexString(mac[i] & 0xFF);
				sb.append(s.length() == 1 ? 0 + s : s);
			}
			// 把字符串所有小写字母改为大写成为正规的mac地址并返回
			macAddress = sb.toString().trim().toUpperCase();
			return macAddress;
		} else {
			// 获取非本地IP的MAC地址
			try {
				Process p = Runtime.getRuntime().exec("nbtstat -A " + ipAddress);
				InputStreamReader ir = new InputStreamReader(p.getInputStream());
				BufferedReader br = new BufferedReader(ir);

				while ((str = br.readLine()) != null) {
					if (str.indexOf("MAC") > 1) {
						macAddress = str.substring(str.indexOf("MAC") + 9, str.length());
						macAddress = macAddress.trim();
						break;
					}
				}
				p.destroy();
				br.close();
				ir.close();
			} catch (IOException ex) {
			}
			return macAddress;
		}
	}
}
