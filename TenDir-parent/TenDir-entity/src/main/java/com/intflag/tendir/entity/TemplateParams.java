package com.intflag.tendir.entity;

/**
 * 
 * @功能说明：数据模型 @作者： herun @创建日期：2015-09-19 @版本号：V1.0
 */
public class TemplateParams implements java.io.Serializable {

	private static final long serialVersionUID = 7295265283690710498L;

	private String tableName;// 表名称
	private String functionComment;// 功能说明
	private String classNames;// 类名称
	private String classPath;// 生成文件的存放路径
	private String filePath;// 文件的存放路径,app_areacodeInf_AreacodeInf
	private String pkColumn;// 主键字段
	private String sortColumn;// 排序字段
	private String action;// action地址
	private String add;// 新增方法
	private String update;// 修改方法
	private String select;// 查询方法
	private String delete;// 删除方法
	private String addmenu;// 是否自动配置菜单
	private String pid;// 上一级菜单
	private String author;// 作者
	private String pattern;// 代码模式

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getAddmenu() {
		return addmenu;
	}

	public void setAddmenu(String addmenu) {
		this.addmenu = addmenu;
	}

	public String getPkColumn() {
		return pkColumn;
	}

	public void setPkColumn(String pkColumn) {
		this.pkColumn = pkColumn;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFunctionComment() {
		return functionComment;
	}

	public void setFunctionComment(String functionComment) {
		this.functionComment = functionComment;
	}

	public String getClassNames() {
		return classNames;
	}

	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
}