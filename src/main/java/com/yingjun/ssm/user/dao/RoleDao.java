package com.yingjun.ssm.user.dao;

import java.util.List;

import com.yingjun.ssm.common.base.dao.Dao;
import com.yingjun.ssm.common.page.Pagination;
import com.yingjun.ssm.entity.Role;
import org.apache.ibatis.annotations.Param;

/**
 * 角色持久化接口
 *
 * @author Joe
 */
public interface RoleDao extends Dao<Role, String> {

    public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<String> idList);

    public int resetPassword(@Param("password") String password, @Param("idList") List<String> idList);

    public List<Role> findPaginationByName(@Param("name") String name, @Param("isEnable") Boolean isEnable,
                                           @Param("appId") String appId, Pagination<Role> p);

    public int deleteByAppIds(@Param("idList") List<String> idList);
}
