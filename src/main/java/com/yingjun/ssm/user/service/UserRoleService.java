package com.yingjun.ssm.user.service;

import com.yingjun.ssm.common.base.service.Service;
import com.yingjun.ssm.entity.UserRole;

import java.util.List;

/**
 * 管理员角色映射服务接口
 * 
 * @author Joe
 */
public interface UserRoleService extends Service<UserRole, String> {
	
	/**
	 * 根据管理员ID和角色ID查询映射
	 * @param userId 管理员ID
	 * @param roleId 角色ID
	 * @return
	 */
	public UserRole findByUserRoleId(String userId, String roleId);
	
	/**
	 * 根据管理员ID给管理员分配角色
	 * @param userId 管理员ID
	 * @param list 管理员角色映射集合
	 * @return
	 */
	public void allocate(String userId, String appId, List<UserRole> list);
	
	/**
	 * 根据角色ID集合删除映射
	 * @param idList 角色ID集合
	 * @return
	 */
	public void deleteByRoleIds(List<String> idList);
	
	/**
	 * 根据管理员ID集合删除映射
	 * @param idList 管理员ID集合
	 * @return
	 */
	public void deleteByUserIds(List<String> idList, String appId);
	
	/**
	 * 根据应用ID集合删除映射
	 * @param idList 应用ID集合
	 * @return
	 */
	public void deleteByAppIds(List<String> idList);
	
	/**
	 * 分配App时，删除无效的userRole
	 * @param userId 应用ID
	 * @param idList 可用应用ID集合
	 * @return
	 */
	public void deleteForChangeApp(String userId, List<String> idList);
}
