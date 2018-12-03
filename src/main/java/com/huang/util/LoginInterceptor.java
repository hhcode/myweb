package com.huang.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.huang.user.model.User;

public class LoginInterceptor implements HandlerInterceptor{
	private static Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	/*
	 *  整个请求完成后执行 主要用于清理资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		logger.debug("afterCompletion");
	}
	/*
	 * Controller之后DispatcherServlet进行视图渲染前执行，此方法中可以操作ModelAndView
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse respomse, Object handler, ModelAndView modelAndView)
			throws Exception {
		logger.debug("postHandle");
	}
	/*
	 * Controller 执行前调用 返回true继续执行 返回false不再向下执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("登录拦截器拦截");

		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		logger.debug("请求url：" + requestUrl);
		HttpSession session = request.getSession();
		logger.debug("session ID 为：" + session.getId());
		if (requestUrl.endsWith("login.jsp") || requestUrl.endsWith("login.do") || requestUrl.endsWith("index.jsp")) {
			return true;
		} else {
			User loginUser = (User) session.getAttribute("loginUser");
			if (loginUser == null) {
				logger.debug("用户没有登录,拦截并跳转到登录页面");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				return false;
			} else {
				logger.debug("用户已登录，放行");
				return true;
			}
		}
	}

}
