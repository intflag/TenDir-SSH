package com.intflag.tendir.service;

import java.util.List;

import com.intflag.tendir.entity.Role;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年4月4日 上午8:45:54
 * @Description 角色业务层接口
 * @version V1.0
 */
public interface IRoleService {

	/**
	 * 新增
	 * 
	 * @param model
	 * @param resourceIds
	 */
	void add(Role model, String resourceIds);

	/**
	 * 分页查询
	 * 
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	/**
	 * 查找
	 * 
	 * @param model
	 * @return
	 */
	Role find(Role model);

	/**
	 * 修改
	 * 
	 * @param model
	 * @param resourceIds
	 */
	void update(Role model, String resourceIds);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Role> findAll();

	/**
	 * 批量删除
	 * 
	 * @param modelIds
	 */
	void deleteBatch(String modelIds);

	/**
	 * 根据角色名查找
	 * 
	 * @param rolename
	 * @return
	 */
	Role findByRoleName(String rolename);

	/**
	 * 根据条件分页查询
	 * 
	 * @param pageBean
	 * @param keyWord
	 * @param strings
	 */
	void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception;

}
