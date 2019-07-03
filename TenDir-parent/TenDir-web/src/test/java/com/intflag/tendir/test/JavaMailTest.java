package com.intflag.tendir.test;

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

import org.junit.Test;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月24日 下午8:09:44
 * @Description 发送邮件测试
 * @version V1.0
 */
public class JavaMailTest {

	private static String smtpServer = "smtp.163.com";
	private static String username = "15848644100@163.com";
	private static String password = "include047740";
	private static String sendTitle = "TenDir-Mail";
	private static String sendContent = "测试邮件";
	private static String sendAddress = "1598749808@qq.com";

	@Test
	public void fun1() {
		try {
			Properties mailProps = new Properties();
			mailProps.put("mail.smtp.host", smtpServer);
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("mail.username", username);
			mailProps.put("mail.password", password);

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
			InternetAddress to = new InternetAddress(sendAddress );
			message.setRecipient(RecipientType.TO, to);
			// 设置邮件标题
			message.setSubject(sendTitle);
			// 设置邮件的内容体
			message.setContent(sendContent, "text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
