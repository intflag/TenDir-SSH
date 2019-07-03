package com.intflag.tendir.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.intflag.tendir.dao.IRegionDao;
import com.intflag.tendir.dao.base.impl.BaseDaoImpl;
import com.intflag.tendir.entity.Region;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月23日 上午11:21:34
 * @Description
 * @version V1.0
 */
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Region> findByLevel(int i) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Region.class);
		criteria.add(Restrictions.eq("regionLevel", i));
		return (List<Region>) getHibernateTemplate().findByCriteria(criteria);
	}

}
