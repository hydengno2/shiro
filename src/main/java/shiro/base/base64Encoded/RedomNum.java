package shiro.base.base64Encoded;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.junit.Test;

public class RedomNum {
	@Test
	public void redomNum() {
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		randomNumberGenerator.setSeed("123".getBytes());
		String hex = randomNumberGenerator.nextBytes().toHex();
		System.out.println(hex);
	}
}
