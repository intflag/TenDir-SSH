package com.intflag.tendir.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intflag.tendir.dao.ILogDao;
import com.intflag.tendir.entity.Log;
import com.intflag.tendir.service.ILogService;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月17日 上午10:57:47
 * @Description 日志业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class LogServiceImpl implements ILogService {

	@Autowired
	private ILogDao logDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		detachedCriteria.addOrder(Order.desc("cdate"));
		logDao.pageQuery(pageBean);
	}

	@Override
	public void save(Log log) {
		logDao.save(log);
	}

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		detachedCriteria.addOrder(Order.desc("cdate"));
		logDao.pageQuery(pageBean, keyWord, attributes);
	}

	@Override
	public void deleteBatch(String modelIds) {
		if (StringUtils.isNotBlank(modelIds)) {
			String[] logIds = modelIds.split(",");
			List<Log> list = new ArrayList<Log>();
			for (String logId : logIds) {
				Log log = new Log();
				log.setLogId(logId);
				list.add(log);
			}
			logDao.deleteBatch(list);
		}
	}

}
