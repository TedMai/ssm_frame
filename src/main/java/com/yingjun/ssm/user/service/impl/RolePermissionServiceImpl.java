package com.yingjun.ssm.user.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.yingjun.ssm.common.base.service.impl.ServiceImpl;
import com.yingjun.ssm.entity.RolePermission;
import com.yingjun.ssm.user.dao.RolePermissionDao;
import com.yingjun.ssm.user.service.AppService;
import com.yingjun.ssm.user.service.RolePermissionService;
import com.yingjun.ssm.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission, String> implements RolePermissionService {

	@Resource
	private RoleService roleService;
	@Resource
	private AppService appService;
//	@Resource
//	private PermissionJmsService permissionJmsService;

	@Autowired
	public void setDao(RolePermissionDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void allocate(String roleId, List<RolePermission> list) {
		dao.deleteByRoleIds(Arrays.asList(roleId));
		super.save(list);
		// JMS通知权限变更
//		permissionJmsService.send(appService.get(roleService.get(roleId).getAppId()).getCode());
	}

	public List<RolePermission> findByRoleId(String roleId) {
		return dao.findByRoleId(roleId);
	}

	public void deleteByPermissionIds(List<String> idList) {
		dao.deleteByPermissionIds(idList);
	}

	public void deleteByRoleIds(List<String> idList) {
		dao.deleteByRoleIds(idList);
	}

	public void deleteByAppIds(List<String> idList) {
		dao.deleteByAppIds(idList);
	}
}
