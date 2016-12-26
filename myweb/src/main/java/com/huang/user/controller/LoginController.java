package com.huang.user.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huang.common.BaseController;
import com.huang.user.service.LoginService;

/**
 *
 * @author HJH
 * @since 2016年12月20日 下午2:01:52
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Resource
	private LoginService loginserviceimpl;
	/**
	 * 登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public Map<String,Object> login() throws Exception{
		Map<String,Object> paraMap = getParamData();
		Map<String,Object> reMap = loginserviceimpl.login(paraMap);
		return reMap;
	}
	/**
	 * 返回页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginforward")
	@ResponseBody
	public ModelAndView loginForward() throws Exception{
		Map<String,Object> paraMap = getParamData();
		String userName = (String)paraMap.get("userName");
		String passwd = (String)paraMap.get("passwd");
		if("huang".equals(userName) && "huang".equals(passwd)){
			return new ModelAndView("main");
		}else{
			return new ModelAndView("error");
		}
	}
}
