package security;

public interface PasswordPolicy {
	String hash(String password);
	boolean match(String password, String hash);
}
