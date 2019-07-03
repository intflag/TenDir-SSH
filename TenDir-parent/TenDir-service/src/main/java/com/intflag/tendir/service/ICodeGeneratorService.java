package com.intflag.tendir.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.intflag.tendir.entity.TableFields;
import com.intflag.tendir.entity.TemplateParams;

/** 
* @author 刘国鑫  QQ:1598749808
* @date 2018年6月13日 上午8:26:33
* @Description 代码生成器业务层接口
* @version V1.0
*/
public interface ICodeGeneratorService {

	/**
	 * 查找数据路所有表
	 * @return
	 */
	List<TemplateParams> findAllTable() throws Exception;

	/**
	 * 根据表名查找该表所有字段
	 * @param model
	 * @return
	 */
	List<TableFields> findFieldByTableName(TemplateParams model) throws Exception;

	/**
	 * 代码生成
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	boolean createCode(TemplateParams model, HttpSession session, HttpServletRequest request) throws Exception;

}
