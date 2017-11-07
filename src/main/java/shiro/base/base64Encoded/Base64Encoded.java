package shiro.base.base64Encoded;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.junit.Assert;
import org.junit.Test;

public class Base64Encoded {
	// CodecSupport，提供了toBytes(str, "utf-8")
	// toString(bytes, "utf-8")用于在byte数组/String之间转换
	@Test
	public void base64Encoded() {
		String str = "hello";
		String base64Encoded = Base64.encodeToString(str.getBytes());
		String str2 = Base64.decodeToString(base64Encoded);
		Assert.assertEquals(str, str2);
	}

	@Test
	public void hex16Encoded() {
		String str = "hello";
		String base64Encoded = Hex.encodeToString(str.getBytes());
		String str2 = new String(Hex.decode(base64Encoded.getBytes()));
		Assert.assertEquals(str, str2);
	}

}
