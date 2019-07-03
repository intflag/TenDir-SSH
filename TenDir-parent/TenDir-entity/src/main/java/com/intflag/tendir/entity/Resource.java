package com.intflag.tendir.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * 资源实体
 */
@SuppressWarnings("rawtypes")
public class Resource implements java.io.Serializable {

	private static final long serialVersionUID = 5592457285664235145L;
	// Fields

	private String resourceId;
	private Resource parentResource;
	private String resname;
	private String resurl;
	private String rescode;
	private String type;//资源类型（0目录 1菜单 2按钮）
	private String icon;
	private String description;
	private String flag;
	private Integer sort;
	private Timestamp cdate;
	private Timestamp mdate;
	
	private String parentId;
	
	private Set roles = new HashSet(0);
	private Set children = new HashSet(0);
	
	public String getCdateStr() {
		if (this.cdate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(this.cdate);
		}
		return "";
	}
	public String getMdateStr() {
		if (this.mdate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(this.mdate);
		}
		return "";
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Resource getParentResource() {
		return parentResource;
	}

	public void setParentResource(Resource parentResource) {
		this.parentResource = parentResource;
		if (parentResource != null) {
			setParentId(parentResource.getResourceId());
		}
	}

	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public String getResurl() {
		return resurl;
	}

	public void setResurl(String resurl) {
		this.resurl = resurl;
	}

	public String getRescode() {
		return rescode;
	}

	public void setRescode(String rescode) {
		this.rescode = rescode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Timestamp getCdate() {
		return cdate;
	}

	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}

	public Timestamp getMdate() {
		return mdate;
	}

	public void setMdate(Timestamp mdate) {
		this.mdate = mdate;
	}

	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public Set getChildren() {
		return children;
	}

	public void setChildren(Set children) {
		this.children = children;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	

}