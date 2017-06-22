package com.yingjun.ssm.user.service;

import com.yingjun.ssm.common.base.service.Service;
import com.yingjun.ssm.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * 权限服务接口
 * 
 * @author Joe
 */
public interface PermissionService extends Service<Permission, String> {

	/**
	 * 根据名称和应用ID查询
	 * @param name 权限名称
	 * @param appId 应用ID
	 * @return
	 */
	public List<Permission> findByName(String name, String appId, Boolean isEnable);
	
	/**
	 * 删除权限
	 * @param id 权限ID
	 * @param appId 应用ID
	 * @return
	 */
	public void deletePermission(String id, String appId);
	
	/**
	 * 删除应用下所有权限
	 * @param idList 应用ID集合
	 * @return
	 */
	public void deleteByAppIds(List<String> idList);
	
	/**
	 * 根据应用编码和管理员ID查权限
	 * @param appCode 应用编码
	 * @param userId 管理员ID
	 * @return
	 */
	public List<Map> findListById(String appCode, String userId);
}
