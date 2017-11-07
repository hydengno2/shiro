package shiro.base.base64Encoded;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

public class PwdCredentialsMatcher {
	public interface CredentialsMatcher {
		// 匹配用户输入的token的凭证（未加密）与系统提供的凭证（已加密）
		boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info);
	}

	public interface PasswordService {
		// 输入明文密码得到密文密码
		String encryptPassword(Object plaintextPassword) throws IllegalArgumentException;
	}
}
