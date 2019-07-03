package com.intflag.tendir.service.impl;

import java.sql.Timestamp;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intflag.tendir.dao.IMailLogDao;
import com.intflag.tendir.entity.MailLog;
import com.intflag.tendir.service.IMailLogService;
import com.intflag.tendir.utils.PageBean;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月24日 下午9:06:11
 * @Description 邮件日志业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class MailLogServiceImpl implements IMailLogService {

	@Autowired
	private IMailLogDao mailLogDao;

	@Override
	public void pageQuery(PageBean pageBean, String keyWord, String[] attributes) throws Exception {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		detachedCriteria.addOrder(Order.desc("cdate"));
		mailLogDao.pageQuery(pageBean, keyWord, attributes);
	}

	@Override
	public void add(MailLog model) throws Exception {
		//保存发送记录，状态置为2
		model.setFlag("2");
		model.setCdate(new Timestamp(System.currentTimeMillis()));
		mailLogDao.save(model);
		//发送邮件
		setMail(model);
		//发送成功修改状态
		model.setFlag("1");
		mailLogDao.update(model);
	}

	private void setMail(MailLog model) throws AddressException, MessagingException {
		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.host", model.getSmtpServer());
		mailProps.put("mail.smtp.auth", "true");
		mailProps.put("mail.username", model.getUsername());
		mailProps.put("mail.password", model.getPassword());

		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = mailProps.getProperty("mail.username");
				String password = mailProps.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(mailProps, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress from = new InternetAddress(mailProps.getProperty("mail.username"));
		message.setFrom(from);
		// 设置收件人
		InternetAddress to = new InternetAddress(model.getSendAddress());
		message.setRecipient(RecipientType.TO, to);
		// 设置邮件标题
		message.setSubject(model.getSendTitle());
		// 设置邮件的内容体
		message.setContent(model.getSendContent(), "text/html;charset=UTF-8");
		// 发送邮件
		Transport.send(message);
	}

}
