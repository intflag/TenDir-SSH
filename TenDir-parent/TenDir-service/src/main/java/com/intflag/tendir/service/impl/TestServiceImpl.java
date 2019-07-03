package com.intflag.tendir.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intflag.tendir.dao.ITestDao;
import com.intflag.tendir.entity.Test;
import com.intflag.tendir.service.ITestService;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2018-07-10
 * @Description 测试API业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class TestServiceImpl implements ITestService {

	@Autowired
	private ITestDao testDao;

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		testDao.pageQuery(pageBean, keyWord, attributes);
	}

	@Override
	public void add(Test model) throws Exception {
		testDao.save(model);
	}

	@Override
	public Test find(Test model) throws Exception {
		return testDao.findById(model.getTestId());
	}

	@Override
	public void update(Test model) throws Exception {
		Test test = testDao.findById(model.getTestId());
		if (test != null) {
			test.setTname(model.getTname());
			test.setTpwd(model.getTpwd());
			test.setEmail(model.getEmail());
			test.setPhone(model.getPhone());
			test.setAddress(model.getAddress());
			testDao.update(test);
		}
	}

	@Override
	public void deleteBatch(String modelIds) throws Exception {
		if (StringUtils.isNotBlank(modelIds)) {
			String[] testIds = modelIds.split(",");
			List<Test> list = new ArrayList<Test>();
			for (String testId : testIds) {
				Test test = new Test();
				test.setTestId(testId);
				list.add(test);
			}
			testDao.deleteBatch(list);
		}
	}

}
