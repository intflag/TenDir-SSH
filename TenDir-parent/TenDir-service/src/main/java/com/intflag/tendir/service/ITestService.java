package com.intflag.tendir.service;

import com.intflag.tendir.entity.Test;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2018-07-10
 * @Description 测试API业务层接口
 * @version V1.0
 */
public interface ITestService {

	/**
	 * 分页
	 * 
	 * @param pageBean
	 * @param keyWord
	 * @param strings
	 */
	void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception;

	/**
	 * 新增
	 * @param model
	 * @throws Exception
	 */
	void add(Test model) throws Exception;

	/**
	 * 查找
	 * @param model
	 * @return
	 * @throws Exception
	 */
	Test find(Test model) throws Exception;

	/**
	 * 修改
	 * @param model
	 * @throws Exception
	 */
	void update(Test model) throws Exception;

	/**
	 * 批量删除
	 * @param modelIds
	 * @throws Exception
	 */
	void deleteBatch(String modelIds) throws Exception;

}
