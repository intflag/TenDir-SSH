package com.intflag.tendir.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.intflag.tendir.entity.Region;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月23日 下午7:03:36
 * @Description 地区业务层接口
 * @version V1.0
 */
public interface IRegionService {

	/**
	 * 根据ID查询
	 * 
	 * @param string
	 * @return
	 */
	Region findById(String id);

	/**
	 * 更新地区
	 * 
	 * @param region
	 */
	void update(Region region);

	/**
	 * 根据地区级别查询
	 * 
	 * @param i
	 * @return
	 */
	List<Region> findByLevel(int i);

	/**
	 * 离线查询
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	List<Region> findByCriteria(DetachedCriteria detachedCriteria);

	/**
	 * 分页查询地区
	 * 
	 * @param pageBean
	 * @param keyWord
	 * @param attributes
	 * @throws Exception
	 */
	void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception;

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<Region> findAll();

	/**
	 * 根据名称查找
	 * 
	 * @param regionName
	 * @return
	 */
	Region findByRegionName(String regionName);

	/**
	 * 添加
	 * 
	 * @param model
	 */
	void add(Region model);

	/**
	 * 查找地区
	 * 
	 * @param model
	 * @return
	 */
	Region find(Region model);

	/**
	 * 批量删除
	 * 
	 * @param modelIds
	 */
	void deleteBatch(String modelIds);

}
