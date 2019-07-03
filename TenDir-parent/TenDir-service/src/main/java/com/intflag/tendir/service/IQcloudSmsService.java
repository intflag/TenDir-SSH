package com.intflag.tendir.service;

import com.intflag.tendir.entity.QcloudSms;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月25日 上午11:22:54
 * @Description 腾讯云短信业务层接口
 * @version V1.0
 */
public interface IQcloudSmsService {

	/**
	 * 分页
	 * 
	 * @param pageBean
	 * @param keyWord
	 * @param strings
	 */
	void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception;

	/**
	 * 短信发送
	 * @param model
	 * @throws Exception
	 */
	void add(QcloudSms model) throws Exception;

}
