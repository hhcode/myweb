package com.huang.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.common.BaseController;
import com.huang.util.JsonUtil;
/**
 * 测试controller
 * @author huang
 *
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	
	@RequestMapping(value="/testjson")
	public void testJson() throws Exception{
		Map<String,Object> reMap = new HashMap<String, Object>();
		Map<String,Object> paraMap = getParamData();
		
		String jsonArrStr = "[{\"zqgwbh\": \"1L74Y7ZS7VXZ1MH13HJVSLGQCP4HQ97J\",\"zqgwmc\": \"交警大队门卫\",\"bcbh\": \"3EB62B0147016B95E0532566A8C0BF98\",\"bcmc\": \"内_午班18-23\",\"bckssj\": \"18:01\",\"bcjssj\": \"23:00\",\"gwtx\": \"1\",\"sfkr\": \"0\",\"dwbh\": \"320100000000\",\"zh\": \"1\",\"jybh\": \"00000005\",\"jyxm\": \"钱多多\",\"zqweek\": \"1,2,3,4,5\",\"pbrqStr\": \"2017-01-03\",\"week\": \"2\",\"zqsj\": \"2\"},{\"zqgwbh\": \"1L74Y7ZS7VXZ1MH13HJVSLGQCP4HQ97J\",\"zqgwmc\": \"交警大队门卫\",\"bcbh\": \"3EB62B0147016B95E0532566A8C0BF98\",\"bcmc\": \"内_午班18-23\",\"bckssj\": \"18:01\",\"bcjssj\": \"23:00\",\"gwtx\": \"1\",\"sfkr\": \"0\",\"dwbh\": \"320100000000\",\"zh\": \"1\",\"jybh\": \"00000008\",\"jyxm\": \"李四\",\"zqweek\": \"1,2,3,4,5\",\"pbrqStr\": \"2017-01-03\",\"week\": \"2\",\"zqsj\": \"2\"},{\"zqgwbh\": \"1L74Y7ZS7VXZ1MH13HJVSLGQCP4HQ97J\",\"zqgwmc\": \"交警大队门卫\",\"bcbh\": \"3EB62B0147016B95E0532566A8C0BF98\",\"bcmc\": \"内_午班18-23\",\"bckssj\": \"18:01\",\"bcjssj\": \"23:00\",\"gwtx\": \"1\",\"sfkr\": \"0\",\"dwbh\": \"320100000000\",\"zh\": \"1\",\"jybh\": \"00000005\",\"jyxm\": \"钱多多\",\"zqweek\": \"1,2,3,4,5\",\"pbrqStr\": \"2017-01-03\",\"week\": \"2\",\"zqsj\": \"2\"},{\"zqgwbh\": \"1L74Y7ZS7VXZ1MH13HJVSLGQCP4HQ97J\",\"zqgwmc\": \"交警大队门卫\",\"bcbh\": \"3EB62B0147016B95E0532566A8C0BF98\",\"bcmc\": \"内_午班18-23\",\"bckssj\": \"18:01\",\"bcjssj\": \"23:00\",\"gwtx\": \"1\",\"sfkr\": \"0\",\"dwbh\": \"320100000000\",\"zh\": \"1\",\"jybh\": \"00000008\",\"jyxm\": \"李四\",\"zqweek\": \"1,2,3,4,5\",\"pbrqStr\": \"2017-01-03\",\"week\": \"2\",\"zqsj\": \"2\"}]";
		
		String jsonStr = "{\"zqgwbh\": \"1L74Y7ZS7VXZ1MH13HJVSLGQCP4HQ97J\",\"zqgwmc\": \"交警大队门卫\",\"bcbh\": \"3EB62B0147016B95E0532566A8C0BF98\",\"bcmc\": \"内_午班18-23\",\"bckssj\": \"18:01\",\"bcjssj\": \"23:00\",\"gwtx\": \"1\",\"sfkr\": \"0\",\"dwbh\": \"320100000000\",\"zh\": \"1\",\"jybh\": \"00000005\",\"jyxm\": \"钱多多\",\"zqweek\": \"1,2,3,4,5\",\"pbrqStr\": \"2017-01-03\",\"week\": \"2\",\"zqsj\": \"2\"}";
		System.out.println(jsonStr);
		Map<String,Object> jsonMap = JsonUtil.toHashMap(jsonStr);
		List<Map<String,Object>> list = JsonUtil.toList(jsonArrStr);
		System.out.println(jsonMap);
	}

}
