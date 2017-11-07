package shiro.base.base64Encoded;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class HashedCredentialsMatcher {

	public void hashedCredentialsMatcher() {
		String algorithmName = "md5";
		String username = "liu";
		String password = "123";
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println(encodedPassword);
	}
}
