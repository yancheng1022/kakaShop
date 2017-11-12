package utils;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.����һ���������ʼ��������Ự���� Session
		 MailSSLSocketFactory sf = null;  
	        try {  
	            sf = new MailSSLSocketFactory();  
	        } catch (GeneralSecurityException e) {  
	            e.printStackTrace();  
	        }  
	        sf.setTrustAllHosts(true);  

	        Properties props = new Properties();  
	        props.setProperty("mail.transport.protocol", "SMTP");  
	        props.put("mail.smtp.ssl.enable", "true");  
	        props.put("mail.smtp.ssl.socketFactory", sf);  
	        props.setProperty("mail.host", "smtp.qq.com");  
	        props.setProperty("mail.smtp.auth", "true");// ָ����֤Ϊtrue  
		
		// ������֤��
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("289799074@qq.com", "sdwnsvtjoqcwbgeg");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.����һ��Message�����൱�����ʼ�����
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress("289799074@qq.com")); // ���÷�����

		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // ���÷��ͷ�ʽ�������

		message.setSubject("�û�����");
		// message.setText("����һ�⼤���ʼ�����<a href='#'>���</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.���� Transport���ڽ��ʼ�����

		Transport.send(message);
	}
}
