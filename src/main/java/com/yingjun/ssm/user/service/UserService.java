package com.yingjun.ssm.user.service;

import java.util.List;

import com.yingjun.ssm.common.result.Result;
import com.yingjun.ssm.entity.User;

public interface UserService {

	List<User> getUserList(int offset, int limit);

	/**
	 * 用户验证
	 *
	 * @param user
	 * @return
	 */
	User authentication(User user);

	/**
	 * 根据用户名查询用户
	 *
	 * @param account
	 * @return
	 */
	User selectByAccount(String account);

    Result getAgeReturnResult(String id);
}
