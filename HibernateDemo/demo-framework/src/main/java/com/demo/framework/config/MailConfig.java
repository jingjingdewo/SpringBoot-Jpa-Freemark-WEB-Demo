package com.demo.framework.config;

import java.io.Serializable;
import java.util.Map;

/** 邮箱配置类 */
public class MailConfig implements Serializable {

	private static final long serialVersionUID = -4298457290909125631L;

	/** 邮件传输协议地址 */
	private String smtp;

	private String sslEnable;
	/** 端口 */
	private String port;
	/** 邮箱地址 */
	private String email;
	/** 邮箱地址别名 */
	private String emailName;
	/** 邮箱登陆用户名 */
	private String userName;
	/** 邮箱登陆密码 */
	private String password;

	public MailConfig() {
	}

	public MailConfig(Map<String, String> params) {
		this.email = params.get("email");
		this.emailName = params.get("emailName");
		this.password = params.get("password");
		this.port = params.get("port");
		this.smtp = params.get("smtp");
		this.sslEnable = params.get("sslEnable");
		this.userName = params.get("userName");
	}

	public MailConfig(String smtp, String port, String email, String emailName, String userName, String password) {
		this.smtp = smtp;
		this.port = port;
		this.email = email;
		this.emailName = emailName;
		this.userName = userName;
		this.password = password;
	}

	public MailConfig(String smtp, String port, String email, String emailName, String userName, String password,
                      String sslEnable) {
		this.smtp = smtp;
		this.port = port;
		this.email = email;
		this.emailName = emailName;
		this.userName = userName;
		this.password = password;
		this.sslEnable = sslEnable;
	}

	public String getSslEnable() {
		return sslEnable;
	}

	public void setSslEnable(String sslEnable) {
		this.sslEnable = sslEnable;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
