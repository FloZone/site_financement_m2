package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5PasswordPolicy implements PasswordPolicy {
	
	private MessageDigest md;
	
	public MD5PasswordPolicy() {
		try {
			this.md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String hash(String password) {
		try {
			return new String(md.digest(getBytes(password)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public boolean match(String password, String hash) {
		String hashedPassword = hash(password);
		try {
			return MessageDigest.isEqual(getBytes(hashedPassword), getBytes(hash));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	private byte[] getBytes(String str) throws UnsupportedEncodingException {
		return str.getBytes("UTF-8");
	}
}
