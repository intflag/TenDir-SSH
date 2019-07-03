package com.intflag.tendir.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.intflag.tendir.dao.IUserDao;
import com.intflag.tendir.dao.base.impl.BaseDaoImpl;
import com.intflag.tendir.entity.User;

/**
 * @author 刘国鑫
 * @date 2018年3月26日 下午12:32:04
 * @Description 用户持久层接口实现
 * @version V1.0
 */
@Repository
@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		String hql = "FROM User u WHERE u.username = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
