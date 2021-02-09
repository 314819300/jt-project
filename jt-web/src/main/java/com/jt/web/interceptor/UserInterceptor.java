package com.jt.web.interceptor;

import com.jt.common.po.User;
import com.jt.common.util.MapperUtil;
import com.jt.common.util.UserThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangning
 * @create 2021-02-08 19:39
 */

public class UserInterceptor implements HandlerInterceptor {

	@Autowired
	private JedisCluster jedisCluster;
	/**
	 *执行业务之前拦截
	 * 1.通过request对象回去Cookie
	 * 2.从Cookie获取token数据
	 * 	如果没有数据，则证明用户没有登陆。
	 * 3.根据token的数据获取redis中的信息
	 * 	如果redis缓存中没有数据，则需要用户重新登陆
	 * 4.如果token有数据，redis中有记录，表示用户已经登陆，页面正确跳转
	 * @param request
	 * @param response
	 * @param handler
	 * @return 返回值true-请求放行，false-请求拦截，添加重定向地址，否则卡死
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//1.获取cookie
		Cookie[] cookies = request.getCookies();
		String token = null;
		for (Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie.getName())) {
				token = cookie.getValue();
				break;
			}
		}
		//2.判断是否有记录,如果有记录
		if(!StringUtils.isEmpty(token)) {
			//2.1判断redis中是否有记录
			String userJSON = jedisCluster.get(token);
			if(!StringUtils.isEmpty(userJSON)) {
				User user = MapperUtil.toObject1(userJSON, User.class);
				Long userId = user.getId();
				//然后把userId存入到request域的session中
				UserThreadLocalUtil.set(user);//存储的方法第一种
				request.getSession().setAttribute("JT_WEB_USER", userId);//存储的方法第二种
				return true;//表示放行
			}
		}
		//证明用户没有登陆，需要重定向到登录页面
		response.sendRedirect("/user/login.html");
		return false;
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	/**
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		request.getSession().removeAttribute("JT_WEB_USER");//删除数据，防止内存泄漏
		UserThreadLocalUtil.remove();
	}
}
