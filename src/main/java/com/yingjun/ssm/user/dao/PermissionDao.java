package com.yingjun.ssm.user.dao;

import java.util.List;
import java.util.Map;

import com.yingjun.ssm.common.base.dao.Dao;
import com.yingjun.ssm.entity.Permission;
import org.apache.ibatis.annotations.Param;

/**
 * 权限持久化接口
 * 
 * @author Joe
 */
public interface PermissionDao extends Dao<Permission, String> {
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<String> idList);
	
	public int resetPassword(@Param("password") String password, @Param("idList") List<String> idList);

	public List<Permission> findByName(@Param("name") String name, @Param("appId") String appId, @Param("isEnable") Boolean isEnable);
	
	public int deleteByAppIds(@Param("idList") List<String> idList);
	
	public List<Map> findListById(@Param("appCode") String appCode, @Param("userId") String userId);
}
