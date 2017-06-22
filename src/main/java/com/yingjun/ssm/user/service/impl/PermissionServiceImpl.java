package com.yingjun.ssm.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yingjun.ssm.common.base.service.impl.ServiceImpl;
import com.yingjun.ssm.entity.Permission;
import com.yingjun.ssm.user.dao.PermissionDao;
import com.yingjun.ssm.user.service.AppService;
import com.yingjun.ssm.user.service.PermissionJmsService;
import com.yingjun.ssm.user.service.PermissionService;
import com.yingjun.ssm.user.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission, String> implements PermissionService {

	@Resource
	private RolePermissionService rolePermissionService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private AppService appService;
	@Resource
	private PermissionJmsService permissionJmsService;

	@Autowired
	public void setDao(PermissionDao dao) {
		this.dao = dao;
	}

	public void save(Permission t) {
		super.save(t);
		// JMS通知权限变更
//		permissionJmsService.send(appService.get(t.getAppId()).getCode());
	}

	public List<Permission> findByName(String name, String appId, Boolean isEnable) {
		return dao.findByName(name, appId, isEnable);
	}

	@Transactional
	public void deletePermission(String id, String appId) {
		List<String> idList = new ArrayList<String>();

		List<Permission> list = permissionService.findByName(null, appId, null);
		loopSubList(id, idList, list);
		idList.add(id);

		rolePermissionService.deleteByPermissionIds(idList);

		verifyRows(dao.deleteById(idList), idList.size(), "权限数据库删除失败");
	}

	// 递归方法，删除子权限
	protected void loopSubList(String id, List<String> idList, List<Permission> list) {
		for (Permission p : list) {
			if (id.equals(p.getParentId())) {
				idList.add(p.getId());
				loopSubList(p.getId(), idList, list);
			}
		}
	}

	public void deleteByAppIds(List<String> idList) {
		dao.deleteByAppIds(idList);
	}

	public List<Map> findListById(String appCode, String userId) {
		return dao.findListById(appCode, userId);
	}
}
