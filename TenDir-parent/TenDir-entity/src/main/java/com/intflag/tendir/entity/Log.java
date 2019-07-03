package com.intflag.tendir.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 日志实体
 */

public class Log implements java.io.Serializable {

	private static final long serialVersionUID = -3753083574842095791L;
	private String logId;
	private String username;
	private String method;
	private String ip;
	private String address;
	private String flag;
	private Integer sort;
	private Timestamp cdate;
	private String description;

	// Constructors

	public String getCdateStr() {
		if (this.cdate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(this.cdate);
		}
		return "";
	}

	public Log() {
		super();
	}

	public Log(String username, String method, String ip, String address, String flag, Integer sort, Timestamp cdate,
			String description) {
		super();
		this.username = username;
		this.method = method;
		this.ip = ip;
		this.address = address;
		this.flag = flag;
		this.sort = sort;
		this.cdate = cdate;
		this.description = description;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}