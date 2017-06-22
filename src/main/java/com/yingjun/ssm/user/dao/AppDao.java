package com.yingjun.ssm.user.dao;

import java.util.List;
import java.util.Set;

import com.yingjun.ssm.common.base.dao.Dao;
import com.yingjun.ssm.common.page.Pagination;
import com.yingjun.ssm.entity.App;
import org.apache.ibatis.annotations.Param;

/**
 * 应用持久化接口
 * 
 * @author Joe
 */
public interface AppDao extends Dao<App, String> {
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<String> idList);
	
	public List<App> findPaginationByName(@Param("name") String name, Pagination<App> p);
	
	public App findByCode(@Param("code") String code);
	
	public List<App> findByUserId(@Param("isEnable") Boolean isEnable, @Param("userId") String userId);
	
	public Set<String> findAppCodeByUserId(@Param("isEnable") Boolean isEnable, @Param("userId") String userId);
}
