package com.yingjun.ssm.user.service;

import com.yingjun.ssm.common.base.service.Service;
import com.yingjun.ssm.entity.RolePermission;

import java.util.List;

/**
 * 角色权限映射服务接口
 * 
 * @author Joe
 */
public interface RolePermissionService extends Service<RolePermission, String> {
	
	/**
	 * 根据角色ID查询映射
	 * @param roleId 角色ID
	 * @return
	 */
	public List<RolePermission> findByRoleId(String roleId);
	
	/**
	 * 根据角色ID给角色授权
	 * @param roleId 角色ID
	 * @param list 角色权限映射集合
	 * @return
	 */
	public void allocate(String roleId, List<RolePermission> list);
	
	/**
	 * 根据权限ID集合删除映射
	 * @param idList 权限ID集合
	 * @return
	 */
	public void deleteByPermissionIds(List<String> idList);
	
	/**
	 * 根据角色ID集合删除映射
	 * @param idList 角色ID集合
	 * @return
	 */
	public void deleteByRoleIds(List<String> idList);
	
	/**
	 * 根据应用ID集合删除映射
	 * @param idList 应用ID集合
	 * @return
	 */
	public void deleteByAppIds(List<String> idList);
}
