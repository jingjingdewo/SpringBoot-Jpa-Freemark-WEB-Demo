package com.demo.model;

import com.demo.framework.annotate.FieldAnnotate;
import com.demo.framework.annotate.enums.FieldType;
import com.demo.framework.annotate.enums.Gender;
import com.demo.framework.model.AbstractModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author 静畏人心
 * @Date 2020/3/31 1:36
 * @Description 系统用户表
 */
@Getter
@Setter
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "SYS_USER")
public class User extends AbstractModel {

	private static final long serialVersionUID = 1L;

	/**
	 * role表的id
	 */
	@FieldAnnotate(name = "角色Id")
	@Column(name = "ROLE_ID")
	private Long roleId;

	/**
	 * 姓名
	 */
	@FieldAnnotate(name = "姓名", exportSort = 3)
	@Column(name = "NAME", length = 64)
	private String name;

	/**
	 * 身份证号码
	 */
	@FieldAnnotate(name = "身份证号码", exportSort = 3)
	@Column(name = "ID_NUM", length = 64)
	private String idNum;

	/**
	 * 手机号
	 */
	@FieldAnnotate(name = "手机号", exportSort = 5)
	@Column(name = "MOBILE", length = 12)
	private String mobile;

	/**
	 * 电子邮箱
	 */
	@FieldAnnotate(name = "电子邮箱", exportSort = 6)
	@Column(name = "EMAIL", length = 50)
	private String email;

	/**
	 * 性别
	 */
	@FieldAnnotate(name = "性别", fieldType = FieldType.ENUM, exportSort = 4)
	@Enumerated(EnumType.STRING)
	@Column(name = "GENDER")
	private Gender gender;

	/**
	 * 用户头像
	 */
	@FieldAnnotate(name = "用户头像")
	@Column(name = "HEADIMG", length = 300)
	private String headImg;

	/**
	 * 登录名
	 */
	@FieldAnnotate(name = "登录名", exportSort = 2)
	@Column(name = "LOGIN_ID", length = 32)
	private String loginId;

	/**
	 * 登录密码
	 */
	@FieldAnnotate(autoJson = false, canExport = false)
	@Column(name = "PWD", length = 32)
	private String pwd;

	/**
	 * 密码密钥
	 */
	@FieldAnnotate(autoJson = false, canExport = false)
	@Column(name = "SECRET_KEY", length = 32)
	private String secretKey;

	/**
	 * 首次访问时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FIRST_VISIT")
	private Date firstVisit;

	/**
	 * 最近访问时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRE_VISIT")
	private Date preVisit;

	/**
	 * 最后访问时间
	 */
	@FieldAnnotate(name = "最后登录时间", exportSort = 9)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_VISIT")
	private Date lastVisit;

	/**
	 * 登录次数
	 */
	@FieldAnnotate(name = "登录次数", exportSort = 10)
	@Column(name = "LOGIN_COUNT")
	private Integer loginCount = 0;

	/**
	 * 是否在线
	 */
	@Column(name = "ONLINE")
	private Boolean online = false;

	/**
	 * 有效
	 */
	@FieldAnnotate(name = "有效", exportSort = 7)
	@Column(name = "ALIVE")
	private Boolean alive = false;

	/**
	 * 备注
	 */
	@FieldAnnotate(name = "备注", exportSort = 10)
	@Column(name = "REMAKR", length = 200)
	private String remark;

}
