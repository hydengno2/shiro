package shiro.base.base64Encoded;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class SHA_MD5 {

	final String salt = "123qaz";

	@Test
	public void md5ToString() {
		String str = "hello";
		String md5 = new Md5Hash(str, salt).toString();
		// 还可以转换为 toBase64()/toHex()
		System.out.println(md5);
	}

	@Test
	public void sha256ToString() {
		String str = "hello";
		String sha1 = new Sha256Hash(str, salt).toString();
		System.out.println(sha1);
		// 另外还有如SHA1、SHA512算法
	}

	@Test
	public void simpleToString() {
		String str = "hello";
		// 内部使用MessageDigest
		String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
		System.out.println(simpleHash);
	}
}
