package com.yingjun.ssm.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.yingjun.ssm.common.base.service.impl.ServiceImpl;
import com.yingjun.ssm.common.page.Pagination;
import com.yingjun.ssm.entity.Role;
import com.yingjun.ssm.user.dao.RoleDao;
import com.yingjun.ssm.user.service.RolePermissionService;
import com.yingjun.ssm.user.service.RoleService;
import com.yingjun.ssm.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role, String> implements RoleService {

	@Resource
	private UserRoleService userRoleService;
	@Resource
	private RolePermissionService rolePermissionService;

	@Autowired
	public void setDao(RoleDao dao) {
		this.dao = dao;
	}

	public void enable(Boolean isEnable, List<String> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "角色数据库更新失败");
	}

	public void save(Role t) {
		super.save(t);
	}

	public Pagination<Role> findPaginationByName(String name, String appId, Pagination<Role> p) {
		dao.findPaginationByName(name, null, appId, p);
		return p;
	}

	public List<Role> findByAppId(Boolean isEnable, String appId) {
		if (appId == null)
			return new ArrayList<Role>(0);
		return dao.findPaginationByName(null, isEnable, appId, null);
	}

	@Transactional
	public void deleteById(List<String> idList) {
		userRoleService.deleteByRoleIds(idList);
		rolePermissionService.deleteByRoleIds(idList);
		verifyRows(dao.deleteById(idList), idList.size(), "角色数据库删除失败");
	}

	public void deleteByAppIds(List<String> idList) {
		dao.deleteByAppIds(idList);
	}
}
