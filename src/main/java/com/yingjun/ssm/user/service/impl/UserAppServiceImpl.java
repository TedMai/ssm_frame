package com.yingjun.ssm.user.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.yingjun.ssm.common.base.service.impl.ServiceImpl;
import com.yingjun.ssm.entity.UserApp;
import com.yingjun.ssm.user.dao.UserAppDao;
import com.yingjun.ssm.user.service.UserAppService;
import com.yingjun.ssm.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userAppService")
public class UserAppServiceImpl extends ServiceImpl<UserAppDao, UserApp, String> implements UserAppService {

	@Resource
	private UserRoleService userRoleService;
	
	@Autowired
	public void setDao(UserAppDao dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void allocate(String userId, List<String> idList, List<UserApp> list) {
		userRoleService.deleteForChangeApp(userId, idList);
		dao.deleteByUserIds(Arrays.asList(userId));
		super.save(list);
	}
	
	public UserApp findByUserAppId(String userId, String roleId) {
		return dao.findByUserAppId(userId, roleId);
	}
	
	public void deleteByUserIds(List<String> idList) {
		dao.deleteByUserIds(idList);
	}
	
	public void deleteByAppIds(List<String> idList) {
		dao.deleteByAppIds(idList);
	}
}
