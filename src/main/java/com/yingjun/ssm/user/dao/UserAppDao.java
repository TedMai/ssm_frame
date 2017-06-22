package com.yingjun.ssm.user.dao;

import java.util.List;

import com.yingjun.ssm.common.base.dao.Dao;
import com.yingjun.ssm.entity.UserApp;
import org.apache.ibatis.annotations.Param;


/**
 * 管理员角色映射持久化接口
 * 
 * @author Joe
 */
public interface UserAppDao extends Dao<UserApp, String> {

	public UserApp findByUserAppId(@Param("userId") String userId, @Param("appId") String appId);

	public int deleteByAppIds(@Param("idList") List<String> idList);

	public int deleteByUserIds(@Param("idList") List<String> idList);
}
