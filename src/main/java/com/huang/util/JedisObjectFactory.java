package com.huang.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.huang.common.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * redis对象工厂 redis有关对象调用，由此类对象生成
 * 
 * @author huang
 *
 */
public class JedisObjectFactory implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(JedisObjectFactory.class);
	
	private static final long serialVersionUID = -1L;
	
	private static ApplicationContext context ;
	
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	 /**
	  * Jedis连接池
	  */
	 @Autowired
	 private static JedisPool jedisPool;
	 
	 
	 public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	
	 /**
	  * 获取Jedis连接代理
	  * @return
	  * @throws JedisException
	  */
	 public static Jedis getJedisInstance() throws JedisException{
		 jedisPool = getJedisPoolInstance();
		 Jedis redis = (Jedis)jedisPool.getResource();
		 if(redis == null){
			 logger.error("Jedis 从 JedisPool 加载失败！");
			 throw new JedisException("Jedis 从 JedisPool 加载失败！");
		 }
		 return redis;
	 }
	 
	 /**
	  * 获取Jedis连接池
	  * @return
	  * @throws JedisException
	  */
	 public static JedisPool getJedisPoolInstance() throws JedisException{
		 if(jedisPool == null){
			 jedisPool = (JedisPool)context.getBean(Constants.JEDIS_POOL_BEAN_NAME);
		 }
		 if(jedisPool == null){
			 logger.error("JedisPool 初始化失败");
			 throw new JedisException("JedisPool 初始化失败");
		 }
		 
		 return jedisPool;
		 
	 }
	 
}
