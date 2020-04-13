package com.demo.framework.enums;

public enum ActionEnums {
	GetModel("获取对象"), Grid("获取表格数据"), TreeGrid("获取表格树数据"), Combox("获取Select数据"), Picklist("获取PickList"), Tree("获取树数据"),
	Add("新增"), Edit("编辑"), Classify("分类"), Delete("删除"), BatchDel("批量删除"), Copy("拷贝"), Reset("重置"),
	SavePermission("保存权限"), SaveUserRelation("保存用户关系"), Save("保存"), ButtonLi(""), ModuleTree(""), UserRelationLi(""),
	RoleRelationLi(""), Upload("上传"), Download("下载"), Count("获取合计数"), DelFile("删除文件"), Update("更新"), Refresh("刷新"),
	Visit("访问"), Leave("离开"), Login("登录"), Logout("登出"), GetFile("获取文件"), Shortcut("快捷方式"), Verify("验证"),
	Exp_Excel("导出Excel"), Imp_Excel("导入Excel"), Set_Default("设置默认"), BatchCopy("批量拷贝"), Deploy("部署"), Undeploy("卸载"),
	Pause("暂停"), Resume("继续"), RunOnce("运行一次"), Set("设置"), Solve("解决"), UploadDeploy("上传部署"), Exp("导出"), Startup("启动"),
	Claim("签收"), Renew("重复"), MonitGrid("监控数据"), AutoCplt("自动填充"), Quit("离职"), View("预览"), Audit("审核");

	private final String name;

	private ActionEnums(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
