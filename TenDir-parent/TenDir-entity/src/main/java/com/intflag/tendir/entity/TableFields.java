package com.intflag.tendir.entity;

/**
 * 
 * @功能说明：表字段
 * @作者： herun
 * @创建日期：2015-09-19
 * @版本号：V1.0
 */
public class TableFields implements java.io.Serializable {

	private static final long serialVersionUID = 5720596145956843638L;
	private String columnName;// 字段名称
	private String columnType;// 字段类型
	private String columnKey;// 主键类型PRI、MUL
	private String columnComment;// 注释
	private String IsNullAble;// 是否为空

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getIsNullAble() {
		return IsNullAble;
	}

	public void setIsNullAble(String isNullAble) {
		IsNullAble = isNullAble;
	}

}
