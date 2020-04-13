package com.demo.framework.model;

import com.demo.framework.enums.StatusCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private int statusCode = StatusCodeEnum.error.getValue();

	private String message;

	private String rel;

	private String callbackType;

	private String forwardUrl;

	private Object obj;

	/**
	 * 设置成功值
	 * 
	 * @param obj    设置对象
	 * @param resMsg 设置码值解析
	 */
	public void setSucceed(Object obj, String resMsg) {
		this.setMessage(resMsg);
		this.setSucceed(obj);
	}

	/**
	 * 设置成功值
	 * 
	 * @param obj 设置对象
	 */
	public void setSucceed(Object obj) {
		this.obj = obj;
		this.setStatusCode(StatusCodeEnum.success.getValue());
	}

	/**
	 * 设置成功值
	 * 
	 * @param resMsg 返回码值解析
	 */
	public void setSucceedMsg(String resMsg) {
		this.setStatusCode(StatusCodeEnum.success.getValue());
		this.setMessage(resMsg);
	}

	/**
	 * 设置成功值
	 * 
	 * @param resMsg 返回码值解析
	 */
	public void setSucceedMsg(Object obj, String resMsg) {
		this.setStatusCode(StatusCodeEnum.success.getValue());
		this.setMessage(resMsg);
		this.setObj(obj);
	}

	public void setWarnMsg(String resMsg) {
		this.obj = null;
		this.setStatusCode(StatusCodeEnum.warning.getValue());
		this.setMessage(resMsg);
	}

	/**
	 * 设置失败值
	 * 
	 * @param obj    设置对象
	 * @param resMsg 设置码值解析
	 */
	public void setFailMsg(Object obj, String resMsg) {
		this.setMessage(resMsg);
		this.setFail(obj);
	}

	/**
	 * 设置失败值
	 * 
	 * @param obj 设置对象
	 */
	public void setFail(Object obj) {
		this.obj = obj;
		this.setStatusCode(StatusCodeEnum.error.getValue());
	}

	/**
	 * 设置失败值
	 * 
	 * @param resMsg 返回码值解析
	 */
	public void setFailMsg(String resMsg) {
		this.obj = null;
		this.setStatusCode(StatusCodeEnum.error.getValue());
		this.setMessage(resMsg);
	}

	public void setConfirmMsg(String resMsg) {
		this.obj = null;
		this.setStatusCode(StatusCodeEnum.confirm.getValue());
		this.setMessage(resMsg);
	}

	public void setConfirmMsg(String resMsg, Object obj) {
		this.obj = obj;
		this.setStatusCode(StatusCodeEnum.confirm.getValue());
		this.setMessage(resMsg);
	}

	public void setInfoMsg(String resMsg, Object obj) {
		this.obj = obj;
		this.setStatusCode(StatusCodeEnum.warning.getValue());
		this.setMessage(resMsg);
	}

	public void setInfoMsg(String resMsg) {
		this.obj = null;
		this.setStatusCode(StatusCodeEnum.warning.getValue());
		this.setMessage(resMsg);
	}

	public void setTimeout(String resMsg) {
		this.obj = null;
		this.setStatusCode(StatusCodeEnum.timeout.getValue());
		this.setMessage(resMsg);
	}

	public void setException(String resMsg) {
		this.obj = null;
		this.setStatusCode(StatusCodeEnum.exception.getValue());
		this.setMessage(resMsg);
	}
}
