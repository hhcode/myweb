package com.huang.user.service;

import java.util.Map;

/**
 * 用户登录服务类
 * @author HJH
 * @since 2016年12月26日 下午3:10:19
 */
public interface LoginService {
	/**
	 * 用户登录
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> login(Map<String,Object> paraMap) throws Exception;
}
