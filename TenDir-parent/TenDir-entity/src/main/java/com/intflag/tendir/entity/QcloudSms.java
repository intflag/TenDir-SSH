package com.intflag.tendir.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 腾讯云短信实体
 */

public class QcloudSms implements java.io.Serializable {

	private static final long serialVersionUID = 3802325537316711996L;
	// Fields

	private String smsId;
	private Integer appId;
	private String appKey;
	private Integer templetId;
	private String address;
	private String content;
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

	/** default constructor */
	public QcloudSms() {
	}

	/** minimal constructor */
	public QcloudSms(String smsId, Timestamp cdate) {
		this.smsId = smsId;
		this.cdate = cdate;
	}

	/** full constructor */
	public QcloudSms(String smsId, Integer appId, String appKey, Integer templetId, String address, String content,
			String flag, Integer sort, Timestamp cdate, String description) {
		this.smsId = smsId;
		this.appId = appId;
		this.appKey = appKey;
		this.templetId = templetId;
		this.address = address;
		this.content = content;
		this.flag = flag;
		this.sort = sort;
		this.cdate = cdate;
		this.description = description;
	}

	// Property accessors

	public String getSmsId() {
		return this.smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public Integer getAppId() {
		return this.appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Integer getTempletId() {
		return this.templetId;
	}

	public void setTempletId(Integer templetId) {
		this.templetId = templetId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Timestamp getCdate() {
		return this.cdate;
	}

	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}