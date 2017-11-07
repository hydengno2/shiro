package shiro.base.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class SecurityRealm extends AuthorizingRealm {
	private PasswordService passwordService;

	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalcollection) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("role1");
		authorizationInfo.addRole("role2");
		authorizationInfo.addStringPermission("+user2+10");
		authorizationInfo.addStringPermission("user2:*");
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken) throws AuthenticationException {
		String uid = String.valueOf(authenticationtoken.getPrincipal());
		String password = new String((char[]) authenticationtoken.getCredentials());
		AuthenticationShiro authenticationShiro = new AuthenticationShiro();
		authenticationShiro.login("", uid, password);
		// PasswordService 密码服务/证书匹配 CredentialsMatcher
		return new SimpleAuthenticationInfo(uid, passwordService.encryptPassword(password), getName());
	}

//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		String username = "liu"; // 用户名及salt1
//		String password = "202cb962ac59075b964b07152d234b70"; // 加密后的密码
//		String salt2 = "202cb962ac59075b964b07152d234b70";
//		SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(username, password, getName());
//		ai.setCredentialsSalt(ByteSource.Util.bytes(username + salt2)); // 盐是用户名+随机数
//		return ai;
//	}
}
