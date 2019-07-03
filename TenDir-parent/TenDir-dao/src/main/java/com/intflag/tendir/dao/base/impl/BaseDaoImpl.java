package com.intflag.tendir.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.intflag.tendir.dao.base.IBaseDao;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫
 * @date 2018年3月26日 上午11:43:38
 * @Description 持久层通用接口实现
 * @version V1.0
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	// 代表某个实体的类型
	private Class<T> entityClass;

	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// 在父类的构造方法中动态获取子类的运行时类型
	public BaseDaoImpl() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获得父类上声明的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}

	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		String hql = "FROM " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void pageQuery(PageBean pageBean) {
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();
		DetachedCriteria criteria = pageBean.getDetachedCriteria();
		// 查询总数据量
		criteria.setProjection(Projections.rowCount());
		List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		pageBean.setTotal(countList.get(0).intValue());
		// 查询数据
		criteria.setProjection(null);// 清除条件
		// 指定hibernate封装对象的方式
		criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult = (currentPage - 1) * pageSize;
		int maxResults = pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}

	@Override
	public void deleteBatch(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		// 设置参数
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		if (StringUtils.isNoneBlank(keyWord)) {
			keyWord = new String(keyWord.getBytes("ISO-8859-1"), "UTF-8");
			// 匹配任意字段
			DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
			Disjunction disjunction = Restrictions.disjunction();
			for (String attr : attributes) {
				disjunction.add(Restrictions.like(attr, "%" + keyWord + "%"));
			}
			detachedCriteria.add(disjunction);
		}
		this.pageQuery(pageBean);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> findBySql(String sql, Map<String, Object> params) {
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery q = session.createSQLQuery(sql );
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

}
