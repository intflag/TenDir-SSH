package com.intflag.tendir.dao;

import com.intflag.tendir.dao.base.IBaseDao;
import com.intflag.tendir.entity.User;

/** 
* @author 刘国鑫
* @date 2018年3月26日 下午12:31:14
* @Description 用户持久层接口
* @version V1.0
*/
public interface IUserDao extends IBaseDao<User> {

	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	User findByUsernameAndPassword(String username, String password);

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

}
