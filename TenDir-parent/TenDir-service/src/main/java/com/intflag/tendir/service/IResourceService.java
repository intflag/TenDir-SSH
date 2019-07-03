package com.intflag.tendir.service;

import java.util.List;

import com.intflag.tendir.entity.Resource;
import com.intflag.tendir.utils.PageBean;

/** 
* @author 刘国鑫  QQ:1598749808
* @date 2018年3月31日 下午4:17:20
* @Description 资源菜单业务层接口
* @version V1.0
*/
public interface IResourceService {

	/**
	 * 新增
	 * @param model
	 */
	void add(Resource model);

	/**
	 * 分页
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	/**
	 * 查找所有
	 * @return
	 */
	List<Resource> findAll();

	/**
	 * 查找
	 * @param model
	 * @return
	 */
	Resource find(Resource model);

	/**
	 * 修改
	 * @param model
	 */
	void update(Resource model);

	/**
	 * 查询菜单
	 * @return
	 */
	List<Resource> findMenu();

	/**
	 * 批量删除资源菜单
	 * @param modelIds
	 */
	void deleteBatch(String modelIds);

	/**
	 * 根据资源名查找
	 * @param resname
	 * @return
	 */
	Resource findByResName(String resname);

	/**
	 * 根据添加分页查询
	 * @param pageBean
	 * @param keyWord
	 * @param strings
	 * @throws Exception 
	 */
	void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception;

}
