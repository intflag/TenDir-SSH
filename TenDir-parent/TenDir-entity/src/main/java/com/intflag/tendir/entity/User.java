package com.intflag.tendir.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体
 */
@SuppressWarnings("rawtypes")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 5915880935700497042L;
	private String userId;
	private String username;
	private String password;
	private String nickname;
	private String gender;
	private String email;
	private String telephone;
	private String flag;
	private Timestamp cdate;
	private Timestamp mdate;

	private Set roles = new HashSet(0);

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
	public User() {
	}

	/** minimal constructor */
	public User(String userId, Timestamp cdate, Timestamp mdate) {
		this.userId = userId;
		this.cdate = cdate;
		this.mdate = mdate;
	}

	/** full constructor */
	public User(String userId, String username, String password, String nickname, String gender, String email,
			String telephone, String flag, Timestamp cdate, Timestamp mdate, Set roles) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.email = email;
		this.telephone = telephone;
		this.flag = flag;
		this.cdate = cdate;
		this.mdate = mdate;
		this.roles = roles;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public Set getRoles() {
		return this.roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

}