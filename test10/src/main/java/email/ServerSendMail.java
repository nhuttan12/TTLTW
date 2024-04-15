package email;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServerSendMail {
	// nguyenthihoaithu016@gmail.com
	// pas: laptrinhweb
//	final String from = "thuthaoltw@gmail.com";
//	final String pass = "gxmigoxjuukangdo";
	String to;
	String from = "thuthaoltw@gmail.com";
	String pass = "gxmigoxjuukangdo";
	String message;

	public ServerSendMail(String to, String message) {
		super();
		this.to = to;
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ServerSendMail() {
		super();
	}

	public void createMes() {

	}

	public String createVerification() {
		Random random = new Random();
		int randomNumber = random.nextInt(900000) + 100000;
		String m = String.valueOf(randomNumber);
		System.out.println("Số ngẫu nhiên có 6 chữ số: " + randomNumber);
		return m;
	}

	public boolean sendEmail() {

		// khai bao thuoc tinh
		Properties p = new Properties();
		// gui tu gmail sang gmail
		p.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host
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
			mes.setSubject("trang web TT thong bao");
			// ngay gui
			mes.setSentDate(new Date());
			// noi dung
			mes.setText(message);

			// gui email
			Transport.send(mes);
			;
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
		new ServerSendMail("nguyenthihoaithu014@gmail.com", "uowwww").sendEmail();
		;

	}

}
