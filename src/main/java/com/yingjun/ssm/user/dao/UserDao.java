package com.yingjun.ssm.user.dao;

import java.util.List;

import com.yingjun.ssm.common.base.dao.Dao;
import com.yingjun.ssm.common.page.Pagination;
import org.apache.ibatis.annotations.Param;

import com.yingjun.ssm.entity.User;

public interface UserDao extends Dao {

	/**
     * 根据手机号查询用户对象
     *
     * @param phone
     * @return
     */
    User queryByPhone(String phone);
    
    
    /**
     * 根据偏移量查询用户列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    
    /**
     * 增加积分
     */
    void addScore(@Param("add")int add);


    public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);

    public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

    public List<User> findPaginationByAccount(@Param("account") String account, @Param("appId") Integer appId, Pagination<User> p);

    public User findByAccount(@Param("account") String account);
    public User findById(@Param("id") String id);

    User authentication(User user);

}
