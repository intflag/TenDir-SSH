package com.intflag.tendir.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.intflag.tendir.dao.IQcloudSmsDao;
import com.intflag.tendir.entity.QcloudSms;
import com.intflag.tendir.service.IQcloudSmsService;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月25日 上午11:24:14
 * @Description 腾讯云短信业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class QcloudSmsServiceImpl implements IQcloudSmsService {

	@Autowired
	private IQcloudSmsDao qcloudSmsDao;

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		detachedCriteria.addOrder(Order.desc("cdate"));
		qcloudSmsDao.pageQuery(pageBean, keyWord, attributes);
	}

	@Override
	public void add(QcloudSms model) throws Exception {
		// 保存发送记录，状态置为2
		model.setFlag("2");
		model.setCdate(new Timestamp(System.currentTimeMillis()));
		qcloudSmsDao.save(model);

		// 发送短信
		// SDK AppID是短信应用的唯一标识，调用短信API接口时需要提供该参数。
		int appId = model.getAppId();
		// App Key是用来校验短信发送请求合法性的密码，与SDK AppID对应，需要业务方高度保密，切勿把密码存储在客户端。
		String appKey = model.getAppKey();
		// 短信模板 ID
		int templetId = model.getTempletId();
		// 接收端电话号码
		String phone = model.getAddress();
		// 随机验证码
		String content = model.getContent();

		SmsSingleSenderResult result = sendSms(appId, appKey, templetId, phone, content);
		if (result != null) {
			JSONObject jsonObject = new JSONObject(result.toString());
			model.setDescription(result.toString());
			Object obj = jsonObject.get("result");
			if ("0".equals(obj.toString())) {
				model.setFlag("1");
				qcloudSmsDao.update(model);
			}
		}
	}

	private SmsSingleSenderResult sendSms(int appId, String appKey, int templetId, String phone, String content)
			throws HTTPException, IOException {
		// 创建短信发送器
		SmsSingleSender sender = new SmsSingleSender(appId, appKey);
		// 创建参数集合
		ArrayList<String> params = new ArrayList<String>();
		params.add(content);
		// 超时时间
		params.add("5");
		// 创建接受结果集对象，返回json格式数据
		SmsSingleSenderResult result;
		// 执行发送
		result = sender.sendWithParam("86", phone, templetId, params, "", "", "");
		return result;
	}

}
