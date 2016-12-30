package com.huang.common;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

/**
 * 系统初始化类
 * 加载公共配置文件
 * @author huang
 *
 */
public class InitializationApp {
	private Logger logger = Logger.getLogger(InitializationApp.class);
	@Value("${jedis.pool.bean.name}")
	private String jedis_pool_bean_name;
	
	/**
	 * bean初始化执行
	 * @throws Exception
	 */
	@PostConstruct
	private void init() throws Exception{
		this.loadConfig();
		logger.info("配置文件初始化完成");
	}
	
	private void loadConfig() {
		Constants.JEDIS_POOL_BEAN_NAME = jedis_pool_bean_name;
	}
}
