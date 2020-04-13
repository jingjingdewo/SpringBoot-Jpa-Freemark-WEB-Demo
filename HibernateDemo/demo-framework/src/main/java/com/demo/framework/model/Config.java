package com.demo.framework.model;

import com.demo.framework.annotate.FieldAnnotate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统参数表
 * 
 */
@Getter
@Setter
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "SYS_CONFIG")
public class Config implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConfigId id;

	@FieldAnnotate(name = "最后一次编辑时间", dataFormat = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME")
	private Date modifyTime;

	@FieldAnnotate(name = "最后一次编辑者ID")
	@Column(name = "MENDER_ID")
	private Long menderId;

	@FieldAnnotate(name = "最后一次编辑者")
	@Column(name = "MENDER_NAME", length = 32)
	private String menderName;

	@FieldAnnotate(name = "站点ID")
	@Column(name = "SITE_ID")
	private Long siteId;

	@FieldAnnotate(name = "所属组织")
	@Column(name = "ORGANIZE_ID")
	private Long organizeId;

	/**
	 * 值
	 */
	@FieldAnnotate(name = "值")
	@Column(name = "VAL", length = 512)
	private String val;

	public Config() {

	}

	public Config(String systemFlag, String key, String val) {
		this.id = new ConfigId(systemFlag, key);
		this.val = val;
	}

}
