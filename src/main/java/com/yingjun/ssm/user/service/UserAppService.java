package com.yingjun.ssm.user.service;

import com.yingjun.ssm.common.base.service.Service;
import com.yingjun.ssm.entity.UserApp;

import java.util.List;

/**
 * 管理员角色映射服务接口
 * 
 * @author Joe
 */
public interface UserAppService extends Service<UserApp, String> {
	
	/**
	 * 根据管理员ID和角色ID查询映射
	 * @param userId 管理员ID
	 * @param roleId 角色ID
	 * @return
	 */
	public UserApp findByUserAppId(String userId, String roleId);
	
	/**
	 * 根据管理员ID给管理员分配角色
	 * @param userId 管理员ID
	 * @param idList 应用ID集合
	 * @param list 管理员角色映射集合
	 * @return
	 */
	public void allocate(String userId, List<String> idList, List<UserApp> list);
	
	/**
	 * 根据管理员ID集合删除映射
	 * @param idList 管理员ID集合
	 * @return
	 */
	public void deleteByUserIds(List<String> idList);
	
	/**
	 * 根据应用ID集合删除映射
	 * @param idList 应用ID集合
	 * @return
	 */
	public void deleteByAppIds(List<String> idList);
}
