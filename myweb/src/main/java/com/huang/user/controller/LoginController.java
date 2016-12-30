package com.huang.user.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huang.common.BaseController;
import com.huang.user.service.LoginService;
import com.huang.util.JedisOpExecuter;

/**
 *
 * @author HJH
 * @since 2016年12月20日 下午2:01:52
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
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
		logger.debug("DEBUG ---- 登录，参数：" + paraMap);
		logger.info("INFO ------ 登录，参数：" + paraMap);
		logger.error("ERROR ---- 登录，参数：" + paraMap);
		Map<String,Object> reMap = loginserviceimpl.login(paraMap);
		
		String key = "huang";
		String value = "------";
		
		JedisOpExecuter.putSingleObject(key, value);
		logger.info("redis 放入数据，KEY: " + key + " VALUE: " + value);
		String reValue = (String)JedisOpExecuter.getSingleObject("huang");
		logger.info("redis 取出数据，KEY: " + key + " VALUE: " + reValue);
		
		return reMap;
	}
	/**
	 * 返回页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginforward")
	public String loginForward() throws Exception{
		Map<String,Object> paraMap = getParamData();
		String userName = (String)paraMap.get("userName");
		String passwd = (String)paraMap.get("passwd");
		if("huang".equals(userName) && "huang".equals(passwd)){
			return "main";//new ModelAndView("main");
		}else{
			return "error";//new ModelAndView("error");
		}
	}
}
