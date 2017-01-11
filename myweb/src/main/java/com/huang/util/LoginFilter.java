package com.huang.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.huang.user.model.User;

/**
 * 登录过滤器
 * 
 * @author huang
 *
 */
public class LoginFilter implements Filter {
	private static Logger logger = Logger.getLogger(LoginFilter.class);

	@Override
	public void destroy() {
		logger.debug("登录过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		logger.debug("登录过滤器进行过滤");
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		logger.debug("请求url：" + requestUrl);
		HttpSession session = request.getSession();
		logger.debug("session ID 为：" + session.getId());
		if (requestUrl.endsWith("login.jsp") || requestUrl.endsWith("login.do") || requestUrl.endsWith("index.jsp")) {
			filterchain.doFilter(request, response);
		} else {
			User loginUser = (User) session.getAttribute("loginUser");
			if (loginUser == null) {
				logger.debug("用户没有登录,拦截并跳转到登录页面");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			} else {
				logger.debug("用户已登录，放行");
				filterchain.doFilter(servletrequest, servletresponse);
			}
		}
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		logger.debug("登录过滤器初始化");
	}

}
