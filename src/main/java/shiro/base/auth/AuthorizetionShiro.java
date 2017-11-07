package shiro.base.auth;

import java.util.Arrays;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class AuthorizetionShiro {

	@Test
	public void testHasRole() {
		AuthenticationShiro authorizetionShiro = new AuthenticationShiro();
		Subject subject = authorizetionShiro.login("classpath:shiro-role.ini", "zhang", "123");
		// 判断拥有角色：role1
		Assert.assertTrue(subject.hasRole("role1"));
		// 判断拥有角色：role1 and role2
		Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
		// 判断拥有角色：role1 and role2 and !role3
		boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		Assert.assertEquals(false, result[2]);
	}

	@Test(expected = UnauthorizedException.class)
	public void testCheckRole() {
		AuthenticationShiro authorizetionShiro = new AuthenticationShiro();

		Subject subject = authorizetionShiro.login("classpath:shiro-role.ini", "zhang", "123");
		// 断言拥有角色：role1
		subject.checkRole("role1");
		// 断言拥有角色：role1 and role3 失败抛出异常
		subject.checkRoles("role1", "role3");
	}

	@Test
	public void testIsPermitted() {
		AuthenticationShiro authorizetionShiro = new AuthenticationShiro();
		Subject subject = authorizetionShiro.login("classpath:shiro-permission.ini", "zhang", "123");
		// 判断拥有权限：user:create
		Assert.assertTrue(subject.isPermitted("user:create"));
		// 判断拥有权限：user:update and user:delete
		Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
		// 判断没有权限：user:view
		Assert.assertFalse(subject.isPermitted("user:view"));
	}

	@Test(expected = UnauthorizedException.class)
	public void testCheckPermission() {
		AuthenticationShiro authorizetionShiro = new AuthenticationShiro();
		Subject subject = authorizetionShiro.login("classpath:shiro-permission.ini", "zhang", "123");
		// 断言拥有权限：user:create
		subject.checkPermission("user:create");
		// 断言拥有权限：user:delete and user:update
		subject.checkPermissions("user:delete", "user:update");
		// 断言拥有权限：user:view 失败抛出异常
		subject.checkPermissions("user:view");
	}

	@Test
	public void testIsSystemPermitted() {
		AuthenticationShiro authorizetionShiro = new AuthenticationShiro();
		Subject subject = authorizetionShiro.login("classpath:shiro-permission.ini", "zhang", "123");
		// 单个资源单个权限：system:user:update
		subject.checkPermissions("system:user:update");
		// 单个资源多个权限：user:update and user:delete or system:user:update,delete
		subject.checkPermissions("system:user:update", "system:user:delete");
		subject.checkPermissions("system:user:update,delete");
		// 单个资源全部权限 5
		// system:user:create,update,delete,view or system:user:* or system:user
		subject.checkPermissions("system:user:create,delete,update:view");
		subject.checkPermissions("system:user:*");
		subject.checkPermissions("system:user");
		// 所有资源全部权限 6
		subject.checkPermissions("user:view");
		// 单个实例单个权限 7
		subject.checkPermissions("user:view:1");
		// 单个实例多个权限 8
		subject.checkPermissions("user:delete,update:1");
		subject.checkPermissions("user:update:1", "user:delete:1");
		// 单个实例所有权限 9
		subject.checkPermissions("user:update:1", "user:delete:1", "user:view:1");
		// 所有实例单个权限 10
		subject.checkPermissions("user:auth:1", "user:auth:2");
		// 所有实例所有权限 11
		subject.checkPermissions("user:view:1", "user:auth:2");
	}

	@Test
	public void testIsPermitted1() {
		AuthenticationShiro authorizetionShiro = new AuthenticationShiro();
		Subject subject = authorizetionShiro.login("classpath:shiro-permission.ini", "zhang", "123");
		// 判断拥有权限：user:create
		Assert.assertTrue(subject.isPermitted("user1:update"));
		Assert.assertTrue(subject.isPermitted("user2:update"));
		// 通过二进制位的方式表示权限
		Assert.assertTrue(subject.isPermitted("+user1+2"));// 新增权限
		Assert.assertTrue(subject.isPermitted("+user1+8"));// 查看权限
		Assert.assertTrue(subject.isPermitted("+user2+10"));// 新增及查看

		Assert.assertFalse(subject.isPermitted("+user1+4"));// 没有删除权限

		Assert.assertTrue(subject.isPermitted("menu:view"));// 通过MyRolePermissionResolver解析得到的权限
	}
}
