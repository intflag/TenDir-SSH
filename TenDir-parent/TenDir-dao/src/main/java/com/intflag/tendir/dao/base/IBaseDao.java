package com.intflag.tendir.dao.base;

/** 
* @author 刘国鑫
* @date 2018年3月26日 上午11:24:17 
* @Description 持久层通用接口
* @version V1.0
*/
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.intflag.tendir.utils.PageBean;

public interface IBaseDao<T> {
	/**
	 * 保存
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除
	 * 
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void deleteBatch(List<T> list);

	/**
	 * 修改
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 通用修改方法
	 * 
	 * @param queryName
	 * @param objects
	 */
	void executeUpdate(String queryName, Object... objects);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	T findById(Serializable id);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 分页
	 * 
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	/**
	 * 根据条件查询
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	List<T> findByCriteria(DetachedCriteria detachedCriteria);

	/**
	 * 根据条件进行分页查询
	 * @param pageBean
	 * @param keyWord
	 * @param attributes
	 * @throws Exception
	 */
	void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception;
	/**
	 * 根据SQL查询
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List<Map> findBySql(String sql, Map<String, Object> params);
}
