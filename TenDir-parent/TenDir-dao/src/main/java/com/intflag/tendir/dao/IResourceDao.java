package com.intflag.tendir.dao;

import java.util.List;

import com.intflag.tendir.dao.base.IBaseDao;
import com.intflag.tendir.entity.Resource;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年3月31日 下午4:19:47
 * @Description 资源菜单持久层接口
 * @version V1.0
 */
public interface IResourceDao extends IBaseDao<Resource> {

	/**
	 * 根据用户ID查找对应的资源
	 * @param userId
	 * @return
	 */
	List<Resource> findListByUserId(String userId);

	/**
	 * 查询所有菜单
	 * @return
	 */
	List<Resource> findAllMenu();

	/**
	 * 根据用户查询菜单
	 * @param userId
	 * @return
	 */
	List<Resource> findMenuByUserId(String userId);

}
