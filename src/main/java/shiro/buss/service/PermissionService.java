/**
 * 
 */
package shiro.buss.service;

import org.apache.shiro.authz.Permission;

/**
 *  DELL
 *
 */
public interface PermissionService {
	public Permission createPermission(Permission permission);

	public void deletePermission(Long permissionId);
}
