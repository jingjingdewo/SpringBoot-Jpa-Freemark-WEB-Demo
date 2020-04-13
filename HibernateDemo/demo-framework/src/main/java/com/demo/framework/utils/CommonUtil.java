package com.demo.framework.utils;

import com.demo.framework.entity.ZTreeNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.util.*;

public class CommonUtil {

	/**
	 * @return 当前日期
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * @return 精确到分钟的当前日期
	 */
	public static Date getMinuteDate() {
		return DateUtils.addMinutes(DateUtils.setSeconds(DateUtils.setMilliseconds(getDate(), 0), 0), 1);
	}

	/**
	 * @param var
	 * @return 是否为非空
	 */
	public static boolean notEmpty(String var) {
		return StringUtils.isNotBlank(var);
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(String var) {
		return StringUtils.isBlank(var);
	}

	/**
	 * @param var
	 * @return 是否非空
	 */
	public static boolean notEmpty(Number var) {
		return null != var;
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(Number var) {
		return null == var;
	}

	/**
	 * @param var
	 * @return 是否非空
	 */
	public static boolean notEmpty(List<?> var) {
		return null != var && !var.isEmpty();
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(List<?> var) {
		return null == var || var.isEmpty();
	}

	/**
	 * @param var
	 * @return 是否非空
	 */
	public static boolean notEmpty(Map<?, ?> var) {
		return null != var && !var.isEmpty();
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(Map<?, ?> var) {
		return null == var || var.isEmpty();
	}

	/**
	 * @param file
	 * @return 是否非空
	 */
	public static boolean notEmpty(File file) {
		return null != file && file.exists();
	}

	/**
	 * @param file
	 * @return 是否为空
	 */
	public static boolean empty(File file) {
		return null == file || !file.exists();
	}

	/**
	 * @param var
	 * @return 是否非空
	 */
	public static boolean notEmpty(Object[] var) {
		return null != var && 0 < var.length;
	}

	/**
	 * @param var
	 * @return 是否为空
	 */
	public static boolean empty(Object[] var) {
		return null == var || 0 == var.length;
	}

	public static String join(Object[] var, String separator) {
		if (empty(var)) {
			return "";
		} else {
			return StringUtils.join(var, ",");
		}
	}

	public static String join(List<?> var, String separator) {
		if (empty(var)) {
			return "";
		} else {
			return StringUtils.join(var, ",");
		}
	}

	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * @param str
	 * @return
	 */
	public static String htmlSpecialChars(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("\"", "&quot;");
			return str;
		} else {
			return "";
		}
	}

	public static List<Integer> getYears(int begin, int end) {
		List<Integer> years = new ArrayList<Integer>();
		begin = begin == 0 ? DateUtil.getCurrentYear() : begin;
		end = end == 0 ? DateUtil.getCurrentYear() : end;
		for (; begin <= end; begin++) {
			years.add(begin);
		}
		return years;
	}

	public static List<Integer> getReverseYears(int begin, int end) {
		List<Integer> years = new ArrayList<Integer>();
		begin = begin == 0 ? DateUtil.getCurrentYear() : begin;
		end = end == 0 ? DateUtil.getCurrentYear() : end;
		for (; end >= begin; end--) {
			years.add(end);
		}
		return years;
	}

	public static String getCodeNo(String beginCode, int i) {
		int len = beginCode.length();
		int v = Integer.valueOf(beginCode) + i;
		String code = "";
		for (int j = 0; j < len; j++) {
			code += "0";
		}
		code += v;
		return code.substring(code.length() - len);
	}

	public static List<ZTreeNode> getTree(List<ZTreeNode> nodes, Object id) {
		List<ZTreeNode> nNodes = new ArrayList<>();
		Iterator<ZTreeNode> it = nodes.iterator();
		while (it.hasNext()) {
			ZTreeNode _Node = it.next();
			if ((id == null && _Node.getPId() == null)
					|| (_Node.getPId() != null && id != null && (_Node.getPId().equals(id)))) {
				_Node.setChildren(getTree(nodes, _Node.getId()));
				_Node.setParent(!_Node.getChildren().isEmpty());
				_Node.setOpen(true);
				nNodes.add(_Node);
			}
		}
		return nNodes;
	}

	public static String getCodeRandomDigits(Integer digits, Integer start) {
		return String.format("%0" + digits + "d", start);
	}

}
