package com.huang.user.mapper;

import java.util.Map;

import com.huang.user.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
     * 通过用户名和密码查询用户是否存在，用于登录
     * @param paraMap
     * @return
     */
	Map<String, Object> selectByNameAndPwd(Map<String, Object> paraMap);
}