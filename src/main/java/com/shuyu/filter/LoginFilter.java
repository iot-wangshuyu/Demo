package com.shuyu.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuyu.utils.GsonUtil;
import com.shuyu.utils.Log;


/**
 * @ClassName: LoginFilter
 * @Description: 过滤器
 * @author shuyu.wang
 * @date 2017年10月17日 下午4:55:25
 * @version V1.0
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURL().toString();
		Log.d(url);
		response.setHeader("Access-Control-Allow-Origin", "*");
		// response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,
		// DELETE");
		// response.setHeader("Access-Control-Max-Age", "3600");
		// response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		String token = getToken(request);
		if (token==null||"".equals(token)) {
			response.setContentType( "text/json;charset=UTF-8");
			Map<String, String> map=new HashMap<>();
			PrintWriter out = response.getWriter();
			map.put("message", "请先登录");
		    out.write(GsonUtil.GsonString(map));
		    out.close();
		    return;
		}
		//该方法的调用会将请求转发给下一个过滤器或目标资源
	    chain.doFilter(req,res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	// get request headers
	/** 
	* @Title: getToken 
	* @Description: 获取hedaer中的token 
	* @param @param request
	* @param @return 
	* @return String 
	* @throws 
	*/
	private String getToken(HttpServletRequest request) {
		Enumeration<?> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			if ("token".equals(key)) {
				String value = request.getHeader(key);
				return value;

			}
		}
		return null;
	}

}
