package com.yingjun.ssm.user.service.impl;

import java.util.Arrays;
import java.util.List;

import com.yingjun.ssm.common.base.service.impl.ServiceImpl;
import com.yingjun.ssm.entity.UserRole;
import com.yingjun.ssm.user.dao.UserRoleDao;
import com.yingjun.ssm.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole, String> implements UserRoleService {

	@Autowired
	public void setDao(UserRoleDao dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void allocate(String userId, String appId, List<UserRole> list) {
		dao.deleteByUserIds(Arrays.asList(userId), appId);
		super.save(list);
	}
	
	public UserRole findByUserRoleId(String userId, String roleId) {
		return dao.findByUserRoleId(userId, roleId);
	}
	
	public void deleteByRoleIds(List<String> idList) {
		dao.deleteByRoleIds(idList);
	}
	
	public void deleteByUserIds(List<String> idList, String appId) {
		dao.deleteByUserIds(idList, appId);
	}
	
	public void deleteByAppIds(List<String> idList) {
		dao.deleteByAppIds(idList);
	}

	public void deleteForChangeApp(String userId, List<String> idList) {
		dao.deleteForChangeApp(userId, idList);
	}
}
