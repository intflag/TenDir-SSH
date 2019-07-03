package com.intflag.tendir.service;

import com.intflag.tendir.entity.${className};
import com.intflag.tendir.utils.PageBean;

/**
 * @author ${author}
 * @date ${date}
 * @Description ${functionComment}业务层接口
 * @version V1.0
 */
public interface I${className}Service {

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
	void add(${className} model) throws Exception;

	/**
	 * 查找
	 * @param model
	 * @return
	 * @throws Exception
	 */
	${className} find(${className} model) throws Exception;

	/**
	 * 修改
	 * @param model
	 * @throws Exception
	 */
	void update(${className} model) throws Exception;

	/**
	 * 批量删除
	 * @param modelIds
	 * @throws Exception
	 */
	void deleteBatch(String modelIds) throws Exception;

}
