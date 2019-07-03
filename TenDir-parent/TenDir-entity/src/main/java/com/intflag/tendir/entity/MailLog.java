package com.intflag.tendir.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 邮件日志实体
 */

public class MailLog implements java.io.Serializable {

	private static final long serialVersionUID = -680473466503854213L;
	private String mailLogId;
	private String smtpServer;
	private String username;
	private String password;
	private String sendTitle;
	private String sendAddress;
	private String sendContent;
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
	public MailLog() {
	}

	/** minimal constructor */
	public MailLog(String mailLogId, Timestamp cdate) {
		this.mailLogId = mailLogId;
		this.cdate = cdate;
	}

	/** full constructor */
	public MailLog(String mailLogId, String smtpServer, String username, String password, String sendTitle,
			String sendAddress, String sendContent, String flag, Integer sort, Timestamp cdate, String description) {
		this.mailLogId = mailLogId;
		this.smtpServer = smtpServer;
		this.username = username;
		this.password = password;
		this.sendTitle = sendTitle;
		this.sendAddress = sendAddress;
		this.sendContent = sendContent;
		this.flag = flag;
		this.sort = sort;
		this.cdate = cdate;
		this.description = description;
	}

	// Property accessors

	public String getMailLogId() {
		return this.mailLogId;
	}

	public void setMailLogId(String mailLogId) {
		this.mailLogId = mailLogId;
	}

	public String getSmtpServer() {
		return this.smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSendTitle() {
		return this.sendTitle;
	}

	public void setSendTitle(String sendTitle) {
		this.sendTitle = sendTitle;
	}

	public String getSendAddress() {
		return this.sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getSendContent() {
		return this.sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
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