package com.shuyu.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shuyu.utils.Log;

/**
 * @ClassName: AccessInterceptor 
 * @Description:  
 *  SpringMVC 中的Interceptor 拦截器也是相当重要和相当有用的，它的主要作用是拦截用户的请求并进行相应的处理。
 *  比如通过它来进行权限验证，或者是来判断用户是否登陆等。
 *一. 使用场景
 *1、日志记录：记录请求信息的日志，以便进行信息监控、信息统计、计算PV（Page View）等。
 *2、权限检查：如登录检测，进入处理器检测检测是否登录，如果没有直接返回到登录页面；
 *3、性能监控：有时候系统在某段时间莫名其妙的慢，可以通过拦截器在进入处理器之前记录开始时间，在处理完后记录结束时间，从而得到该请求的处理时间（如果有反　　　　　　向代理，如apache可以自动记录）；
 *4、通用行为：读取cookie得到用户信息并将用户对象放入请求，从而方便后续流程使用，还有如提取Locale、Theme信息等，只要是多个处理器都需要的即可使用拦截器实　　　　　　现。
 *5、OpenSessionInView：如Hibernate，在进入处理器打开Session，在完成后关闭Session。
 * @author shuyu.wang
 * @date 2017年10月17日 下午5:38:56 
 * @version V1.0
 */
public class AccessInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");

		String host = request.getRemoteHost();
        String url = request.getRequestURI();
        Log.d("IP为---->>> " + host + " <<<-----访问了:"+url);
		return true;// 只有返回true才会继续向下执行，返回false取消当前请求
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
	
		String host = request.getRemoteHost();
        String url = request.getRequestURI();
        Log.d("IP为---->>> " + host + " <<<-----成功访问了:"+url);
	}

}
