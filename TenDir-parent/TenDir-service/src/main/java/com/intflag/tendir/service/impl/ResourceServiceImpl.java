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

import com.intflag.tendir.dao.IResourceDao;
import com.intflag.tendir.entity.Resource;
import com.intflag.tendir.entity.User;
import com.intflag.tendir.service.IResourceService;
import com.intflag.tendir.utils.PageBean;
import com.intflag.tendir.utils.TenDirUtils;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年3月31日 下午4:18:05
 * @Description 资源菜单业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private IResourceDao resourceDao;

	@Override
	public void add(Resource model) {
		Resource parentResource = model.getParentResource();
		if (parentResource != null && parentResource.getResourceId().equals("")) {
			model.setParentResource(null);
		}
		model.setCdate(new Timestamp(System.currentTimeMillis()));
		model.setMdate(new Timestamp(System.currentTimeMillis()));
		resourceDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		resourceDao.pageQuery(pageBean);
	}

	@Override
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	@Override
	public Resource find(Resource model) {
		return resourceDao.findById(model.getResourceId());
	}

	@Override
	public void update(Resource model) {
		Resource resource = resourceDao.findById(model.getResourceId());
		resource.setMdate(new Timestamp(System.currentTimeMillis()));
		resource.setDescription(model.getDescription());
		resource.setFlag(model.getFlag());
		resource.setIcon(model.getIcon());
		Resource parentResource = model.getParentResource();
		if (parentResource != null && parentResource.getResourceId().equals("")) {
			resource.setParentResource(null);
		} else {
			resource.setParentResource(parentResource);
		}
		resource.setRescode(model.getRescode());
		resource.setResname(model.getResname());
		resource.setResurl(model.getResurl());
		resource.setSort(model.getSort());
		resource.setType(model.getType());
		resourceDao.save(resource);
	}

	@Override
	public List<Resource> findMenu() {
		User user = TenDirUtils.getLoginUser();
		List<Resource> list = null;
		if ("Admin".equals(user.getUsername())) {
			// 如果是超级管理员内置用户，查询所有菜单
			list = resourceDao.findAllMenu();
		} else {
			// 其他用户，根据用户id查询菜单
			list = resourceDao.findMenuByUserId(user.getUserId());
		}
		return list;
	}

	@Override
	public void deleteBatch(String modelIds) {
		if (StringUtils.isNotBlank(modelIds)) {
			String[] resIds = modelIds.split(",");
			List<Resource> list = new ArrayList<Resource>();
			for (String resId : resIds) {
				Resource resource = resourceDao.findById(resId);
				list.add(resource);
			}
			resourceDao.deleteBatch(list);
		}
		/*
		 * 下面的方法删除会抛出
		 * A different object with the same identifier value was already associated with the session
		 * 异常
		 */
		/*if (StringUtils.isNotBlank(modelIds)) {
			String[] resIds = modelIds.split(",");
			for (String resId : resIds) {
				Resource resource = new Resource();
				resource.setResourceId(resId);
				resourceDao.delete(resource);
			}
		}*/
	}

	@Override
	public Resource findByResName(String resname) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Resource.class);
		detachedCriteria.add(Restrictions.eq("resname", resname));
		List<Resource> list = resourceDao.findByCriteria(detachedCriteria );
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		resourceDao.pageQuery(pageBean, keyWord, attributes);
	}

}
