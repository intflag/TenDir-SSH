package com.intflag.tendir.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @功能说明：测试API
 * @作者： 刘国鑫QQ1598749808
 * @创建日期：2018-07-10
 * @版本号：V1.0
 */
public class Test implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String testId;//主键

	private String tname;//名称

	private String tpwd;//密码

	private String email;//邮箱

	private String phone;//电话

	private String address;//地址


	//构造方法
	public Test() {
	}
	
	
	public String getTestId() {
		return  testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	
	public String getTname() {
		return  tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	
	public String getTpwd() {
		return  tpwd;
	}

	public void setTpwd(String tpwd) {
		this.tpwd = tpwd;
	}

	
	public String getEmail() {
		return  email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPhone() {
		return  phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getAddress() {
		return  address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
