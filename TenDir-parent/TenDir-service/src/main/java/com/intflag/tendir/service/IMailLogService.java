package com.intflag.tendir.service;

import com.intflag.tendir.entity.MailLog;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月24日 下午9:05:15
 * @Description 邮件日志业务层接口
 * @version V1.0
 */
public interface IMailLogService {

	/**
	 * 分页查询
	 * 
	 * @param pageBean
	 * @param keyWord
	 * @param strings
	 */
	void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception;

	void add(MailLog model) throws Exception;

}
