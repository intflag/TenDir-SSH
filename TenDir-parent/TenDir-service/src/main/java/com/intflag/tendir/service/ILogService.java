package com.intflag.tendir.service;

import com.intflag.tendir.entity.Log;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月17日 上午10:55:55
 * @Description 日志业务层接口
 * @version V1.0
 */
public interface ILogService {

	/**
	 * 分页
	 * 
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	/**
	 * 保存日志
	 * @param log
	 */
	void save(Log log);

	/**
	 * 根据条件分页查询
	 * @param pageBean
	 * @param keyWord
	 * @param attributes
	 * @throws Exception 
	 */
	void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception;

	/**
	 * 批量删除日志
	 * @param modelIds
	 */
	void deleteBatch(String modelIds);

}
