package com.intflag.tendir.service;

import com.intflag.tendir.entity.User;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫
 * @date 2018年3月26日 下午2:04:25
 * @Description 用户管理业务层接口
 * @version V1.0
 */
public interface IUserService {

	/**
	 * 用户登录
	 * 
	 * @param model
	 * @return
	 */
	User login(User model);

	/**
	 * 修改用户
	 * 
	 * @param model
	 */
	void update(User model, String roleIds);

	/**
	 * 分页查询用户
	 * 
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	/**
	 * 查询用户
	 * 
	 * @param model
	 * @return
	 */
	User find(User model);

	/**
	 * 新增用户
	 * 
	 * @param model
	 */
	void add(User model, String roleIds);

	/**
	 * 批量删除用户
	 * 
	 * @param modelIds
	 */
	void deleteBatch(String modelIds);

	/**
	 * 锁屏解锁
	 * @param model
	 * @return 
	 */
	User unlock(User model);

	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	User findByName(String username);

	/**
	 * 根据条件进行分页查询
	 * @param pageBean
	 * @param keyWord
	 * @param strings
	 */
	void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception;
}
