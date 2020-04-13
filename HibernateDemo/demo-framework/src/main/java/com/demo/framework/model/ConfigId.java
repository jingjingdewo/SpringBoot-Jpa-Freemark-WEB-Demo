package com.demo.framework.model;

import com.demo.framework.annotate.FieldAnnotate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 系统参数表
 * 
 */
@Getter
@Setter
@Embeddable
public class ConfigId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SYSTEM_FLAG", nullable = false, length = 32)
	private String systemFlag;

	@FieldAnnotate(name = "KEY值")
	@Column(name = "ID_KEY", nullable = false, length = 50)
	private String key;

	public ConfigId() {

	}

	public ConfigId(String systemFlag, String key) {
		this.systemFlag = systemFlag;
		this.key = key;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConfigId))
			return false;
		ConfigId castOther = (ConfigId) other;

		return (this.getSystemFlag() != null && castOther.getSystemFlag() != null
				&& this.getSystemFlag().equals(castOther.getSystemFlag()))
				&& (this.getKey() != null && castOther.getKey() != null && this.getKey().equals(castOther.getKey()));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getSystemFlag().hashCode();
		result = 37 * result + (getKey() == null ? 0 : this.getKey().hashCode());
		return result;
	}

}
