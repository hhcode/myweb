package com.huang.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.huang.util.JsonUtil;
/**
 * 所有的Controller必须继承此类
 *
 */
public abstract class BaseController {
	private HttpServletRequest request;
	private HttpServletResponse response;
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * 自动注入request、response对象
	 * 注意：被@ModelAttribute标注的方法会在当前controller每个方法执行前被执行
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	/**
	 * 页面每次请求时调用本方法 将request请求参数全部放到Map里
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getParamData() {
		Map<String, Object> paramData = Maps.newConcurrentMap();
		Enumeration<String> fields = getRequest().getParameterNames();
		while (fields.hasMoreElements()) {
			String field = fields.nextElement();
			String[] values = getRequest().getParameterValues(field);
			if (values.length > 1) {
				paramData.put(field, values);
			} else {
				if(!StringUtils.isEmpty(values[0])){
					paramData.put(field, values[0]);
				}
			}
		}
		return paramData;
	}
	
	/**
	 * 获取request中参数组装成Map
	 * 
	 * @return
	 * @throws IOException
	 */
	protected Map<String, Object> getParamJSONMap() throws IOException {
		return JsonUtil.toHashMap(IOUtils.toString(getRequest().getInputStream(),"UTF-8"));
	}
	
	/**
	 * 返回json对象
	 * 说明：虽然spring的@ResponseBody能解决大部分问题，但有一些特殊情况还需要做特殊处理
	 * 
	 * @param obj
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T> T responseBody(Object obj) throws Exception{
		Object json = "{}";
		if (obj instanceof Map) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.putAll((Map) obj);
			json = jsonObject.toString();
		}else{
			json = obj;
		}
//		log.debug("response Json:" + json);
		return (T)json;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected String responseBody2String(Object obj) throws Exception{
		String json = "{}";
		if (obj instanceof Map) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.putAll((Map) obj);
			json = jsonObject.toString();
			//json=JsonUtil.idata2JsonString((Map) obj);
		}else{
			if(obj!=null) {
				json =JSONObject.toJSONString(obj);
			}
		}
//		log.debug("response Json:" + json);
		return json;
	}
	
	/**
	 * 下载文件到浏览器
	 * @param ins
	 * @param fileName
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void responseOutFile(InputStream ins,String fileName) throws Exception{
		Preconditions.checkNotNull(ins, "输入流不能为空！");
		Preconditions.checkNotNull(fileName, "文件名不能为空！");
		Preconditions.checkNotNull(response, "响应不能为空！");
		try{
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/x-download;");
			response.setHeader("Content-disposition","attachment;fileName="+ fileName);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[10];
			int len = 0;
			while((len=ins.read(b)) != -1){
				os.write(b,0,len);
			}
			os.close();
			ins.close();
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * byte写入文件
	 * @param ins
	 * @param fileName
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void responseOutFile(byte[] ins,String fileName) throws Exception{
		Preconditions.checkNotNull(ins, "输入流不能为空！");
		Preconditions.checkNotNull(fileName, "文件名不能为空！");
		Preconditions.checkNotNull(response, "响应不能为空！");
		try{
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/x-download;");
			response.setHeader("Content-disposition","attachment;fileName="+ fileName);
			OutputStream os = response.getOutputStream();
			os.write(ins);
			os.close();
		}catch(Exception e){
			throw e;
		}
	}
	/**
	 * 注册属性绑定
	 * @param request
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder){
	    //设置需要包裹的元素个数，默认为256
	    binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
	}

	
}
