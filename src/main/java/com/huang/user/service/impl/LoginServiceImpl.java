package com.huang.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huang.user.mapper.UserMapper;
import com.huang.user.service.LoginService;
import com.huang.util.CheckUtil;

/**
 * 用户登录服务实现类
 * @author HJH
 * @since 2016年12月26日 下午3:13:49
 */
@Service("loginserviceimpl")
public class LoginServiceImpl implements LoginService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Map<String, Object> login(Map<String, Object> paraMap)
			throws Exception {
		Map<String,Object> reMap = new HashMap<String, Object>();
		
		boolean flag = CheckUtil.checkParaNotNull(paraMap, "userName","passwd");
		if(!flag){
			reMap.put("result", "success");
			reMap.put("msg", "用户名和密码不能为空");
			return reMap;
		}
		
		Map<String,Object> userMap = userMapper.selectByNameAndPwd(paraMap);
		if(userMap != null){
			reMap.put("result", "success");
			reMap.put("msg", "登陆成功");
		}else{
			reMap.put("result", "error");
			reMap.put("msg", "用户名或密码错误，请重新输入");
		}
		return reMap;
	}

}
