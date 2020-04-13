package com.demo.framework.model;


import com.demo.framework.annotate.FieldAnnotate;
import com.demo.framework.utils.UuidUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = { AbstractModel.AbstractEntityListener.class })
public abstract class AbstractModel implements Cloneable, Serializable {

	private static final long serialVersionUID = -6005682028938078338L;

	@FieldAnnotate(name = "主键", formField = true, hiddenEl = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "UUID", unique = true, nullable = false, updatable = false, length = 36)
	private String uuid;

	@FieldAnnotate(name = "删除标志")
	@Column(name = "IS_DELETED", nullable = false)
	private Boolean deleted = false;

	@FieldAnnotate(name = "创建时间", dataFormat = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	private Date createTime;

	@FieldAnnotate(name = "创建者ID")
	@Column(name = "CREATOR_ID")
	private Long creatorId;

	@FieldAnnotate(name = "创建者")
	@Column(name = "CREATOR_NAME", length = 32)
	private String creatorName;

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

	@Override
	public boolean equals(Object o) {
		return (o == this || (o instanceof AbstractModel && getId().equals(((AbstractModel) o).getId())));
	}

	@Override
	public int hashCode() {
		return getId() == null ? 0 : getId().hashCode();
	}

	public static class AbstractEntityListener {

		@PrePersist
		public void onPrePersist(AbstractModel abstractEntity) {
			if (StringUtils.isBlank(abstractEntity.getUuid()))
				abstractEntity.setUuid(UuidUtil.getUuid());
			abstractEntity.setCreateTime(new Date());
		}

		@PreUpdate
		public void onPreUpdate(AbstractModel abstractEntity) {
			abstractEntity.setModifyTime(new Date());
		}
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
