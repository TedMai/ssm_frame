package com.yingjun.ssm.user.service;

import com.yingjun.ssm.common.base.service.Service;
import com.yingjun.ssm.common.page.Pagination;
import com.yingjun.ssm.entity.Role;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author Joe
 */
public interface RoleService extends Service<Role, String> {

    /**
     * 启用禁用操作
     *
     * @param isEnable 是否启用
     * @param idList   角色ID集合
     * @return
     */
    public void enable(Boolean isEnable, List<String> idList);

    /**
     * 根据角色名称和应用ID查询分页列表
     *
     * @param name     角色名称
     * @param appId    应用ID
     * @param pageNo   分页起始
     * @param pageSize 分页记录数
     * @return
     */
    public Pagination<Role> findPaginationByName(String name, String appId, Pagination<Role> p);

    /**
     * 查询应用可用角色
     *
     * @param isEnable 是否启用
     * @param appId    应用ID
     * @return
     */
    public List<Role> findByAppId(Boolean isEnable, String appId);

    /**
     * 删除某个应用下的所有角色
     *
     * @param idList 应用ID集合
     * @return
     */
    public void deleteByAppIds(List<String> idList);
}
