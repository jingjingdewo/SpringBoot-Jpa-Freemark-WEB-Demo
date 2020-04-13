package com.demo.framework.dao.enums;

public enum FileType {

	文件夹("folder.gif"), excel文件("file_extension_xls.png"), word文件("file_extension_doc.png"), pdf文件(
			"file_extension_pdf.png"), 压缩文件("file_extension_zip.png"), 日志文件(
			"file_extension_log.png"), txt文件("file_extension_txt.png"), sql文件("sql.png"), 图像文件(
			"page_white.png"), 其他文件("page_white.png");

	private final String icon;

	private FileType(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

}
