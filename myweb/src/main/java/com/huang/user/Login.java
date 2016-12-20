package com.huang.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huang.common.BaseController;

/**
 *
 * @author HJH
 * @since 2016年12月20日 下午2:01:52
 */
@Controller
@RequestMapping("/login")
public class Login extends BaseController{
	
	@RequestMapping(value="/login")
	@ResponseBody
	public Map<String,Object> login() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		Map<String,Object> paraMap = getParamData();
		String userName = (String)paraMap.get("userName");
		String passwd = (String)paraMap.get("passwd");
		if("huang".equals(userName) && "huang".equals(passwd)){
			reMap.put("msg", "登录成功");
			reMap.put("result", "success");
		}else{
			reMap.put("msg", "用户名或密码错误");
			reMap.put("result", "error");
		}
		return reMap;
	}
}
