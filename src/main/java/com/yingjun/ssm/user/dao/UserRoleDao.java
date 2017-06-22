package com.yingjun.ssm.user.dao;

import java.util.List;

import com.yingjun.ssm.common.base.dao.Dao;
import com.yingjun.ssm.entity.UserRole;
import org.apache.ibatis.annotations.Param;


/**
 * 管理员角色映射持久化接口
 * 
 * @author Joe
 */
public interface UserRoleDao extends Dao<UserRole, String> {

	public UserRole findByUserRoleId(@Param("userId") String userId, @Param("roleId") String roleId);

	public int deleteByRoleIds(@Param("idList") List<String> idList);

	public int deleteByUserIds(@Param("idList") List<String> idList, @Param("appId") String appId);

	public int deleteByAppIds(@Param("idList") List<String> idList);
	
	public int deleteForChangeApp(@Param("userId") String userId, @Param("idList") List<String> idList);
}
