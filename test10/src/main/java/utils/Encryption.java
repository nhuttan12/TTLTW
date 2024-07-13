package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;

public class Encryption {
	public static String mahoaPass(String pass) {
		String bonus = "afkokqjm@ff1850";// tăng độ bảo mật
		String pass1 = pass + bonus;
		String result = null;
		try {
			byte[] pass_byte = pass1.getBytes("UTF-8");
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(digest.digest(pass_byte));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("khong tim thay thuat toan");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Khong ho tro encode");
			e.printStackTrace();
		}

		return result;

	}

	public static void main(String[] args) {
			System.out.println(mahoaPass("123456"));
	}

}
