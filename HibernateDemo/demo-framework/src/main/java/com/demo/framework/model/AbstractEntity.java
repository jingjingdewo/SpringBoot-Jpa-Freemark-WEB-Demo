package com.demo.framework.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public abstract class AbstractEntity implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private Object id;

	/**
	 * Ztree
	 * 
	 * 节点的 checkBox / radio 的 勾选状态。[setting.check.enable = true & treeNode.nocheck =
	 * false 时有效]
	 * 
	 * 1、如果不使用 checked 属性设置勾选状态，请修改 setting.data.key.checked
	 * 
	 * 2、建立 treeNode 数据时设置 treeNode.checked = true 可以让节点的输入框默认为勾选状态
	 * 
	 * 3、修改节点勾选状态，可以使用 treeObj.checkNode / checkAllNodes / updateNode
	 * 方法，具体使用哪种请根据自己的需求而定
	 * 
	 * 4、为了解决部分朋友生成 json 数据出现的兼容问题, 支持 "false","true" 字符串格式的数据
	 * 
	 * 默认值：false
	 * 
	 * true 表示节点的输入框被勾选
	 * 
	 * false 表示节点的输入框未勾选
	 */
	private Boolean checked;

	private Boolean isLeaf;

	private Boolean expanded;

	private Integer rgt;

	private Integer lft;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
