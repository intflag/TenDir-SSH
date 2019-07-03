package com.intflag.tendir.test;

import java.util.ArrayList;

import org.junit.Test;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月25日 上午10:38:36
 * @Description 腾讯云短信发送测试
 * @version V1.0
 */
public class QCloudSMSTest {

	@Test
	public void fun1() {
		// SDK AppID是短信应用的唯一标识，调用短信API接口时需要提供该参数。
		int appId = 1400078040;
		// App Key是用来校验短信发送请求合法性的密码，与SDK AppID对应，需要业务方高度保密，切勿把密码存储在客户端。
		String appKey = "9e337670add77ffc4bec135530cae62f";
		// 短信模板 ID
		int templetId = 107991;
		// 接收端电话号码
		String phone = "17604892557";
		// 随机验证码
		int activateCode = (int) ((Math.random() * 9 + 1) * 100000);
		// 超时时间
		int timeOut = 5;

		// 创建短信发送器
		SmsSingleSender sender = new SmsSingleSender(appId, appKey);
		// 创建参数集合
		ArrayList<String> params = new ArrayList<String>();
		params.add(activateCode + "");
		params.add(timeOut + "");
		// 创建接受结果集对象，返回json格式数据
		SmsSingleSenderResult result;
		try {
			// 执行发送
			result = sender.sendWithParam("86", phone, templetId, params, "", "", "");
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
