package com.intflag.tendir.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intflag.tendir.dao.IUserDao;
import com.intflag.tendir.entity.Role;
import com.intflag.tendir.entity.User;
import com.intflag.tendir.service.IUserService;
import com.intflag.tendir.utils.MD5Utils;
import com.intflag.tendir.utils.PageBean;
import com.intflag.tendir.utils.TenDirUtils;

/**
 * @author 刘国鑫
 * @date 2018年3月26日 下午2:05:29
 * @Description 用户管理业务层实现
 * @version V1.0
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public User login(User model) {
		String username = model.getUsername();
		String password = MD5Utils.md5(model.getPassword());
		return userDao.findByUsernameAndPassword(username, password);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(User model, String roleIds) {
		User user = userDao.findById(model.getUserId());

		user.setPassword(MD5Utils.md5(model.getPassword()));
		user.setMdate(new Timestamp(System.currentTimeMillis()));
		user.setNickname(model.getNickname());
		user.setEmail(model.getEmail());
		user.setFlag(model.getFlag());
		user.setGender(model.getGender());
		user.setTelephone(model.getTelephone());

		//获取当前登录用户，只有当前用户为超级管理员时才能修改角色
		User loginUser = TenDirUtils.getLoginUser();
		if ("Admin".equals(loginUser.getUsername())) {
			user.setRoles(model.getRoles());
			if (StringUtils.isNotBlank(roleIds)) {
				String[] rIds = roleIds.split(",");
				for (String rId : rIds) {
					Role role = new Role();
					role.setRoleId(rId);
					model.getRoles().add(role);
				}
			}
		}
		userDao.update(user);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		User loginUser = TenDirUtils.getLoginUser();
		if (loginUser != null && !"Admin".equals(loginUser.getUsername())) {
			detachedCriteria.add(Restrictions.ne("username", "Admin"));
		}
		detachedCriteria.addOrder(Order.desc("mdate"));
		userDao.pageQuery(pageBean);
	}

	@Override
	public User find(User model) {
		return userDao.findById(model.getUserId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(User model, String roleIds) {
		model.setPassword(MD5Utils.md5(model.getPassword()));
		model.setCdate(new Timestamp(System.currentTimeMillis()));
		model.setMdate(new Timestamp(System.currentTimeMillis()));
		if (StringUtils.isNotBlank(roleIds)) {
			String[] rIds = roleIds.split(",");
			for (String rId : rIds) {
				Role role = new Role();
				role.setRoleId(rId);
				model.getRoles().add(role);
			}
		}
		userDao.save(model);
	}

	@Override
	public void deleteBatch(String modelIds) {
		String userId2 = TenDirUtils.getLoginUser().getUserId();
		if (StringUtils.isNotBlank(modelIds)) {
			String[] userIds = modelIds.split(",");
			List<User> list = new ArrayList<User>();
			for (String userId : userIds) {
				if (userId2.equals(userId)) {
					continue;
				}
				User user = new User();
				user.setUserId(userId);
				list.add(user);
			}
			userDao.deleteBatch(list);
		}
	}

	@Override
	public User unlock(User user) {
		User loginUser = TenDirUtils.getLoginUser();
		User mUser = null;
		if (loginUser != null) {
			mUser = userDao.findByUsernameAndPassword(loginUser.getUsername(), MD5Utils.md5(user.getPassword()));
		}
		return mUser;
	}

	@Override
	public User findByName(String username) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("username", username));
		List<User> list = userDao.findByCriteria(detachedCriteria);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		User loginUser = TenDirUtils.getLoginUser();
		if (loginUser != null && !"Admin".equals(loginUser.getUsername())) {
			detachedCriteria.add(Restrictions.ne("username", "Admin"));
		}
		detachedCriteria.addOrder(Order.desc("mdate"));
		userDao.pageQuery(pageBean, keyWord, attributes);
	}

}
