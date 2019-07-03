package com.intflag.tendir.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.intflag.tendir.dao.IResourceDao;
import com.intflag.tendir.dao.base.impl.BaseDaoImpl;
import com.intflag.tendir.entity.Resource;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年3月31日 下午4:20:41
 * @Description 资源菜单持久层接口实现
 * @version V1.0
 */
@Repository
@SuppressWarnings("unchecked")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements IResourceDao {
	@Override
	public List<Resource> findAll() {
		String hql = "FROM Resource r WHERE r.parentResource IS NULL";
		return (List<Resource>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Resource> findListByUserId(String userId) {
		String hql = "SELECT DISTINCT res FROM Resource res LEFT OUTER JOIN res.roles r LEFT OUTER JOIN r.users u WHERE u.userId = ?";
		List<Resource> list = (List<Resource>) this.getHibernateTemplate().find(hql, userId);
		return list;
	}

	@Override
	public List<Resource> findAllMenu() {
		//String hql = "FROM Resource res WHERE res.parentResource IS NULL AND (res.type = '0' OR res.type = '1') ORDER BY res.sort";
		String hql = "FROM Resource res WHERE (res.type = '0' OR res.type = '1') ORDER BY res.sort";
		List<Resource> list = (List<Resource>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Resource> findMenuByUserId(String userId) {
		//String hql = "SELECT DISTINCT res FROM Resource res LEFT OUTER JOIN res.roles r LEFT OUTER JOIN r.users u WHERE u.userId = ? AND res.parentResource IS NULL AND (res.type = '0' OR res.type = '1') ORDER BY res.sort";
		String hql = "SELECT DISTINCT res FROM Resource res LEFT OUTER JOIN res.roles r LEFT OUTER JOIN r.users u WHERE u.userId = ? AND (res.type = '0' OR res.type = '1' OR res.type = '2') ORDER BY res.sort";
		List<Resource> list = (List<Resource>) this.getHibernateTemplate().find(hql, userId);
		return list;
	}
}
