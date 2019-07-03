package com.intflag.tendir.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intflag.tendir.dao.IRegionDao;
import com.intflag.tendir.entity.Region;
import com.intflag.tendir.service.IRegionService;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月23日 下午7:04:06
 * @Description 地区业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;

	@Override
	public Region findById(String id) {
		return regionDao.findById(id);
	}

	@Override
	public void update(Region region) {
		Region oldRegion = regionDao.findById(region.getRegionId());
		oldRegion.setLocationLat(region.getLocationLat());
		oldRegion.setLocationLng(region.getLocationLng());
		oldRegion.setRegionFlag(region.getRegionFlag());
		oldRegion.setRegionLevel(region.getRegionLevel());
		oldRegion.setRegionName(region.getRegionName());

		Region pRegion = region.getRegion();
		if (pRegion != null && pRegion.getRegionId().equals("")) {
			oldRegion.setRegion(null);
		} else {
			oldRegion.setRegion(pRegion);
		}
		regionDao.update(oldRegion);
	}

	@Override
	public List<Region> findByLevel(int i) {
		return regionDao.findByLevel(i);
	}

	@Override
	public List<Region> findByCriteria(DetachedCriteria detachedCriteria) {
		return regionDao.findByCriteria(detachedCriteria);
	}

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		regionDao.pageQuery(pageBean, keyWord, attributes);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Override
	public Region findByRegionName(String regionName) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Region.class);
		detachedCriteria.add(Restrictions.eq("regionName", regionName));
		List<Region> list = regionDao.findByCriteria(detachedCriteria);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void add(Region model) {
		Region pRegion = model.getRegion();
		if (pRegion != null && pRegion.getRegionId().equals("")) {
			model.setRegion(null);
		}
		regionDao.save(model);
	}

	@Override
	public Region find(Region model) {
		return regionDao.findById(model.getRegionId());
	}

	@Override
	public void deleteBatch(String modelIds) {
		if (StringUtils.isNotBlank(modelIds)) {
			String[] regionIds = modelIds.split(",");
			List<Region> list = new ArrayList<Region>();
			for (String regionId : regionIds) {
				Region region = regionDao.findById(regionId);
				list.add(region);
			}
			regionDao.deleteBatch(list);
		}
	}

}
