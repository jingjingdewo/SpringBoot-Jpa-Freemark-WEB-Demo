package com.demo.framework.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<T> results = new ArrayList<T>();

	private Integer totalCount = 0;

	private Integer pageSize = 25;

	private Integer pageNo = 0;

	private Integer current = 0;

	private Integer totalPage = 0;

	private List<Integer> paginator = new ArrayList<Integer>();

	private String orderField;

	private String orderDirection;

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public List<Integer> getPaginator() {
		return paginator;
	}

	public void setPaginator(List<Integer> paginator) {
		this.paginator = paginator;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isPrev() {
		if (this.pageNo <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isNext() {
		if (this.pageNo >= this.totalPage - 1) {
			return false;
		} else {
			return true;
		}
	}

	public StringBuffer getPageHtml(StringBuffer html, Page<T> page) {
		if (page.getTotalPage() >= 1) {
			if (page.getPageNo() == 0) {
				html.append("<span class='disabled'>首页</span>");
				html.append("<span class='disabled'>上一页</span>");
			} else {
				html.append("<span><a href='javascript:void(0);' onclick='load(1)'").append(
						">首页</a></span>");
				html.append("<span><a href='javascript:void(0);' onclick='load(" + page.getPageNo()
						+ ")'>上一页</a></span>");
			}
			for (Integer i : page.getPaginator()) {
				if (i - 1 == page.getPageNo()) {
					html.append("<span class='current'>");
					html.append(i).append("</span>");
				} else {
					html.append("<span>");
					html.append("<a href='javascript:void(0);' onclick='load(" + i + ")'")
							.append(">").append(i).append("</a></span>");
				}

			}
			if (page.getPageNo() == page.getTotalPage() - 1) {
				html.append("<span class='disabled'>下一页</span>");
				html.append("<span class='disabled'>尾页</span>");
			} else {
				html.append("<span><a href='javascript:void(0);' onclick='load("
						+ (page.getPageNo() + 2) + ")'>下一页</a></span>");
				html.append(
						"<span><a href='javascript:void(0);' onclick='load("
								+ (page.getTotalPage()) + ")'").append(">尾页</a></span>");
			}

		}
		return html;
	}
	
	public String getPageString(StringBuffer html, Page<T> page) {
        if (page.getTotalPage() >= 1) {
            if (page.getPageNo() == 1) {
                html.append("<li style='display:none'>首页</li>");
                html.append("<li style='display:none'>上一页</li>");
            } else {
                html.append("<li><a href='javascript:void(0);' onclick='load(1)'").append(
                        ">首页</a></li>");
                html.append("<li><a href='javascript:void(0);' onclick='load(" + (page.getPageNo()-1)
                        + ")'>上一页</a></li>");
            }
            for (Integer i : page.getPaginator()) {
                if (i == page.getPageNo()) {
                    html.append("<li class='active'>");
                } else {
                    html.append("<li>");                    
                }
                html.append("<a href='javascript:void(0);' onclick='load(" + i + ")'")
                .append(">").append(i).append("</a></li>");
            }
            if (page.getPageNo() == page.getTotalPage()) {
                html.append("<li style='display:none'>下一页</li>");
                html.append("<li style='display:none'>尾页</li>");
            } else {
                html.append("<li><a href='javascript:void(0);' onclick='load("
                        + (page.getPageNo() + 1) + ")'>下一页</a></li>");
                html.append(
                        "<li><a href='javascript:void(0);' onclick='load("
                                + (page.getTotalPage()) + ")'").append(">尾页</a></li>");
            }
           
        }
        return html.toString();
    }
}
