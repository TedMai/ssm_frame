package com.yingjun.ssm.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.yingjun.ssm.cache.RedisCache;
import com.yingjun.ssm.common.result.Result;
import com.yingjun.ssm.common.result.ResultEnum;
import com.yingjun.ssm.common.result.ResultUtil;
import com.yingjun.ssm.exception.SSMException;
import com.yingjun.ssm.user.dao.UserDao;
import com.yingjun.ssm.entity.User;
import com.yingjun.ssm.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserDao userDao;
	@Resource
	private RedisCache cache;
	
	
	@Override
	public List<User> getUserList(int offset, int limit) {
		String cache_key=RedisCache.CAHCENAME+"|getUserList|"+offset+"|"+limit;
		//先去缓存中取
		List<User> result_cache=cache.getListCache(cache_key, User.class);
		if(result_cache==null){
			//缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
			result_cache=userDao.queryAll(offset, limit);
			cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
			LOG.info("put cache with key:"+cache_key);
		}else{
			LOG.info("get cache with key:"+cache_key);
		}
		LOG.debug("LIST: "+ JSON.toJSON(result_cache));
		return result_cache;
	}

	@Override
	public User authentication(User user) {
		System.out.println(JSON.toJSON(user));
		return userDao.authentication(user);
	}

	@Override
	public User selectByAccount(String account) {
		return userDao.findByAccount(account);
	}

	@Override
	public Result getAgeReturnResult(String id) {
		User user = userDao.findById(id);
		if (user == null){
			throw new SSMException(ResultEnum.INVALID_USER);
		}
		Integer count = user.getLoginCount();
		if (count <= 10) {
			//返回"参数错误" code=2001001
			throw new SSMException(ResultEnum.VALIDATE_ERROR);
		}else if (count > 10 && count < 16) {
			//返回"空指针异常" code=99980004
			throw new SSMException(ResultEnum.NUllPOINTER_ERROR);
		}else{
			return ResultUtil.success(user);
		}
	}


}
