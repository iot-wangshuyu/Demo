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
 * @Description: 
 *过Filter技术，对web服务器管理的所有web资源：例如Jsp, Servlet, 静态图片文件或静态html 文件等进行拦截，从而实现一些特殊的功能。
 *例如实现URL级别的权限访问控制、用户自动登录，解决全站乱码，过滤敏感词汇、压缩响应信息等一些高级功能。
 *filter在开发中的常见应用：
 * 1.filter可以目标资源执行之前，进行权限检查，检查用户有无权限，如有权限则放行，如没有，则拒绝访问
 * 2.filter可以放行之前，对request和response进行预处理，从而实现一些全局性的设置。
 * 3.filter在放行之后，可以捕获到目标资源的输出，从而对输出作出类似于压缩这样的设置
 * 一，解决全网站的乱码：
 * 二，实现自动登录
 * 三，禁止浏览器缓存所有动态页面的过滤器：
 * 四，控制浏览器缓存页面中的静态资源的过滤器：
 * 五、使用Decorator模式包装request对象，实现html标签转义功能（Tomcat服务器中提供了转义html标签的工具类）
 * 六、压缩响应信息服务器发给浏览器的数据时，先进行压缩，然后在发出，（注意filter要拦截的对象，都会压缩，所以要在web.xml中配置好想要拦截的对象）。
 * 七、敏感词过滤(z注意词库中词语的格式：例如：傻逼|1   后面的1表示识别词库中的等级 ，禁用词为1，审查词为2，替换词为3)
 * http://blog.csdn.net/qq_36753550/article/details/53443580
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
//		if (token==null||"".equals(token)) {
//			response.setContentType( "text/json;charset=UTF-8");
//			Map<String, String> map=new HashMap<>(16);
//			PrintWriter out = response.getWriter();
//			map.put("message", "请先登录");
//		    out.write(GsonUtil.GsonString(map));
//		    out.close();
//		    return;
//		}
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
