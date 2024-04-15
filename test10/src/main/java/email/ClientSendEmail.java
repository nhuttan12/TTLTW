package email;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jakarta.websocket.Session;
import javax.activation.*;

public class ClientSendEmail {
	// nguyenthihoaithu016@gmail.com
	//pas: laptrinhweb
//	final String from = "thuthaoltw@gmail.com";
//	final String pass = "gxmigoxjuukangdo";
	String to = "nguyenthihoaithu014@gmail.com";
	String from;
	String pass;
	String message;
	
	
	public ClientSendEmail(String from, String pass, String message) {
		super();
		this.from = from;
		this.pass = pass;
		this.message = message;
	}

	

	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public ClientSendEmail() {
		super();
	}



	public boolean sendEmail() {

		// khai bao thuoc tinh
		Properties p = new Properties();
		// gui tu gmail sang gmail
		p.put("mail.smtp.host", "smtp.gmail.com"); //SMTP host
		p.put("mail.smtp.port", "587"); // giao thuc TLS: 587 SSL: 465
		p.put("mail.smtp.auth", "true"); // phai dang nhap khi gui mail
		p.put("mail.smtp.starttls.enable", "true"); // khai bao giao thuc sd
		
		// dang nhap
		
		javax.mail.Authenticator au = new javax.mail.Authenticator() {

			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new javax.mail.PasswordAuthentication(from, pass);
			}
			
		};
		// tao phien lam viec 
		javax.mail.Session sess = javax.mail.Session.getInstance(p, au);
	
		// tao tin nhan
		MimeMessage mes = new MimeMessage(sess);
		try {
			
			mes.setFrom(from);
			mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			// tieu de
			mes.setSubject("khach hang gui");
			// ngay gui
			mes.setSentDate(new Date());
			// noi dung
			mes.setText(message);
			
			// gui email
			Transport.send(mes);;
			System.out.println("gui thanh cong");
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("gui that bai");
			return false;
		}
		
	}
	

	public static void main(String[] args) {
		new ClientSendEmail("thuthaoltw@gmail.com", "gxmigoxjuukangdo", "uowwww").sendEmail();;
		
		

	}

}
