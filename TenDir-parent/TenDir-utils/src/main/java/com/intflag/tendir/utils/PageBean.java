package com.intflag.tendir.utils;
/**
 * 分页实体类
 * @author 刘国鑫	2018年3月5日19:38:26
 *
 */

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {
	private Integer currentPage;// 当前页码
	private Integer pageSize;// 每页显示条数
	private Integer total;// 总记录数
	private Integer code = 0;// 数据状态
	private String msg = "";// 状态信息
	private List rows;// 当前页数据集合
	private DetachedCriteria detachedCriteria;// 离线查询对象

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
