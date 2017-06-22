package com.yingjun.ssm.user.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.yingjun.ssm.common.base.service.impl.ServiceImpl;
import com.yingjun.ssm.common.page.Pagination;
import com.yingjun.ssm.entity.App;
import com.yingjun.ssm.user.dao.AppDao;
import com.yingjun.ssm.user.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("appService")
public class AppServiceImpl extends ServiceImpl<AppDao, App, String> implements AppService {
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private UserAppService userAppService;
	@Resource
	private RolePermissionService rolePermissionService;

	@Autowired
	public void setDao(AppDao dao) {
		this.dao = dao;
	}
	
	public void enable(Boolean isEnable, List<String> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "应用数据库更新失败");
	}
	
	public void save(App t) {
		super.save(t);
	}

	public List<App> findByAll(String name) {
		return dao.findPaginationByName(name, null);
	}

	public Pagination<App> findPaginationByName(String name, Pagination<App> p) {
		dao.findPaginationByName(name, p);
		return p;
	}

	public App findByCode(String code) {
		return dao.findByCode(code);
	}
	
	public List<App> findByUserId(Boolean isEnable, String userId) {
		return dao.findByUserId(isEnable, userId);
	}
	
	@Transactional
	public void deleteById(List<String> idList) {
		rolePermissionService.deleteByAppIds(idList);
		userRoleService.deleteByAppIds(idList);
		userAppService.deleteByAppIds(idList);
		permissionService.deleteByAppIds(idList);
		roleService.deleteByAppIds(idList);
		verifyRows(dao.deleteById(idList), idList.size(), "应用数据库删除失败");
	}

	public Set<String> findAppCodeByUserId(Boolean isEnable, String userId) {
		return dao.findAppCodeByUserId(isEnable, userId);
	}
}
