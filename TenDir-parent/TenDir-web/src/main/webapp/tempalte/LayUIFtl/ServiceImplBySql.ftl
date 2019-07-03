package com.intflag.tendir.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intflag.tendir.dao.I${Action}Dao;
import com.intflag.tendir.entity.${Action};
import com.intflag.tendir.service.I${Action}Service;
import com.intflag.tendir.utils.PageBean;

/**
 * @author ${author}
 * @date ${date}
 * @Description ${functionComment}业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class ${Action}ServiceImpl implements I${Action}Service {

	@Autowired
	private I${Action}Dao ${action}Dao;

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		${action}Dao.pageQuery(pageBean, keyWord, attributes);
	}

	@Override
	public void add(${Action} model) throws Exception {
		${action}Dao.save(model);
	}

	@Override
	public ${Action} find(${Action} model) throws Exception {
		return ${action}Dao.findById(model.get${EntityId}());
	}

	@Override
	public void update(${Action} model) throws Exception {
		${Action} ${action} = ${action}Dao.findById(model.get${EntityId}());
		if (${action} != null) {
			<#list cloums as c>
			${action}.set${c.UpUmnName}(model.get${c.UpUmnName}());
			</#list>	
			${action}Dao.update(${action});
		}
	}

	@Override
	public void deleteBatch(String modelIds) throws Exception {
		if (StringUtils.isNotBlank(modelIds)) {
			String[] ${action}Ids = modelIds.split(",");
			List<${Action}> list = new ArrayList<${Action}>();
			for (String ${action}Id : ${action}Ids) {
				${Action} ${action} = new ${Action}();
				${action}.set${EntityId}(${action}Id);
				list.add(${action});
			}
			${action}Dao.deleteBatch(list);
		}
	}

}
