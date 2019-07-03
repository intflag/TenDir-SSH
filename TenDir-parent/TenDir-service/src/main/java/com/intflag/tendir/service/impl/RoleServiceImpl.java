package com.intflag.tendir.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intflag.tendir.dao.IRoleDao;
import com.intflag.tendir.entity.Resource;
import com.intflag.tendir.entity.Role;
import com.intflag.tendir.service.IRoleService;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年4月4日 上午8:46:30
 * @Description
 * @version V1.0
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@SuppressWarnings("unchecked")
	@Override
	public void add(Role model, String resourceIds) {
		if (StringUtils.isNotBlank(resourceIds)) {
			String[] rId = resourceIds.split(",");
			for (String resourceId : rId) {
				Resource resource = new Resource();
				resource.setResourceId(resourceId);
				model.getResources().add(resource);
			}
		}
		roleDao.save(model);
		model.setCdate(new Timestamp(System.currentTimeMillis()));
		model.setMdate(new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}

	@Override
	public Role find(Role model) {
		return roleDao.findById(model.getRoleId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Role model, String resourceIds) {
		Role role = roleDao.findById(model.getRoleId());
		role.getResources().clear();
		role.setMdate(new Timestamp(System.currentTimeMillis()));
		if (StringUtils.isNotBlank(resourceIds)) {
			String[] rId = resourceIds.split(",");
			for (String resourceId : rId) {
				Resource resource = new Resource();
				resource.setResourceId(resourceId);
				role.getResources().add(resource);
			}
		}
		role.setDescription(model.getDescription());
		role.setRolename(model.getRolename());
		role.setSort(model.getSort());
		role.setFlag(model.getFlag());
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public void deleteBatch(String modelIds) {
		if (StringUtils.isNotBlank(modelIds)) {
			String[] roleIds = modelIds.split(",");
			List<Role> list = new ArrayList<Role>();
			for (String roleId : roleIds) {
				Role role = new Role();
				role.setRoleId(roleId);
				list.add(role);
			}
			roleDao.deleteBatch(list);
		}
	}

	@Override
	public Role findByRoleName(String rolename) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
		detachedCriteria.add(Restrictions.eq("rolename", rolename));
		List<Role> list = roleDao.findByCriteria(detachedCriteria );
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		roleDao.pageQuery(pageBean, keyWord, attributes);
	}

}
