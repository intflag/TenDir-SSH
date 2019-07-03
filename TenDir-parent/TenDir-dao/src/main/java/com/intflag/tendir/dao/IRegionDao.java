package com.intflag.tendir.dao;

import java.util.List;

import com.intflag.tendir.dao.base.IBaseDao;
import com.intflag.tendir.entity.Region;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月23日 上午11:20:17
 * @Description 全国地区持久层接口
 * @version V1.0
 */
public interface IRegionDao extends IBaseDao<Region> {

	/**
	 * 根据层级查找
	 * @param i
	 * @return 
	 */
	List<Region> findByLevel(int i);

}
