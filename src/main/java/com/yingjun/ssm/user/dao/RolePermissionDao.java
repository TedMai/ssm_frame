package com.yingjun.ssm.user.dao;

import java.util.List;

import com.yingjun.ssm.common.base.dao.Dao;
import com.yingjun.ssm.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

/**
 * 角色权限映射持久化接口
 * 
 * @author Joe
 */
public interface RolePermissionDao extends Dao<RolePermission, String> {
	
	public List<RolePermission> findByRoleId(@Param("roleId") String roleId);
	
	public int deleteByPermissionIds(@Param("idList") List<String> idList);
	
	public int deleteByRoleIds(@Param("idList") List<String> idList);
	
	public int deleteByAppIds(@Param("idList") List<String> idList);
}
