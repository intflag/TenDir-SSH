package com.intflag.tendir.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色实体
 */
@SuppressWarnings("rawtypes")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 4429443028961488367L;
	// Fields

	private String roleId;
	private String rolename;
	private String flag;
	private Integer sort;
	private String description;
	private Timestamp cdate;
	private Timestamp mdate;

	private Set resources = new HashSet(0);
	private Set users = new HashSet(0);

	// Constructors

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

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String roleId, Timestamp cdate, Timestamp mdate) {
		this.roleId = roleId;
		this.cdate = cdate;
		this.mdate = mdate;
	}

	/** full constructor */
	public Role(String roleId, String rolename, String flag, Integer sort, String description, Timestamp cdate,
			Timestamp mdate, Set resources, Set users) {
		this.roleId = roleId;
		this.rolename = rolename;
		this.flag = flag;
		this.sort = sort;
		this.description = description;
		this.cdate = cdate;
		this.mdate = mdate;
		this.resources = resources;
		this.users = users;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCdate() {
		return this.cdate;
	}

	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}

	public Timestamp getMdate() {
		return this.mdate;
	}

	public void setMdate(Timestamp mdate) {
		this.mdate = mdate;
	}

	public Set getResources() {
		return this.resources;
	}

	public void setResources(Set resources) {
		this.resources = resources;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}