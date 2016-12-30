package com.huang.common;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;
/**
 * 日志Appender 
 * 实现不同级别输出到不同文件
 * @author huang
 *
 */
public class MyAppender extends DailyRollingFileAppender{
	@Override
	 public boolean isAsSevereAsThreshold(Priority priority) {
	  //只判断是否相等，而不判断优先级
	  return this.getThreshold().equals(priority);
	 }
}
