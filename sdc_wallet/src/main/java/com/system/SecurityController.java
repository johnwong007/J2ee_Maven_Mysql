package com.system;

import java.rmi.RemoteException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irebane.ML;
import com.irebane.model.AR;
import com.irebane.model.ARC;
import com.utils.ST;
import com.utils.servlet.JsonpUtils;

/**
 * 登录退出等安全管理
 * 
 * @author gegl
 * 
 */
@Controller
public class SecurityController {
	private static final Logger logger = Logger.getLogger(SecurityController.class);
	
	@RequestMapping(value = "/login")
	public void login(HttpServletRequest request,
			HttpServletResponse response) throws RemoteException {
		System.out.println(request.getRequestURL().toString());
		Map<String, String> paramMap = ST.getParamMap(request, "login_no,login_pass", false);
		System.out.println("登陆");
		System.out.println(paramMap);
		Map userMap = ML.userMapper.login(paramMap);
		System.out.println(userMap);
		if(userMap==null){
			JsonpUtils.print(AR.getFailedObj(ARC.LOGIN_NULL, null, null), request, response);
			return;
		}else{
//			String token = gglRandomUtils.getRandomString(32);
//			userMap.put("token", token);
			VerifyLoginInterceptor.tokenMap.put(userMap.get("login_no").toString(), userMap);
//			request.getSession().setAttribute(userMap.get("login_no").toString(), userMap);
			JsonpUtils.print(AR.getSuccessObj(JSONObject.fromObject(userMap), null), request, response);
			return;
		}
	}
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws RemoteException {
		String login_no = request.getParameter("login_no");
		if(StringUtils.isNotBlank(login_no)){
			VerifyLoginInterceptor.tokenMap.remove("login_no");
			JsonpUtils.print(AR.getSuccessObj(null, null), request, response);
		}
	}
	public static boolean getUserLogin(HttpServletRequest request,HttpServletResponse response){
		System.out.println(request.getRequestURL().toString());
		String login_no = request.getParameter("login_no");
		if(login_no==null || "".equals(login_no)){
			System.out.println("没有login_no");
			JsonpUtils.print(AR.getFailedObj(ARC.LOGIN_NULL, null, null), request, response);
			return false;
		}
//		Map userMap = (Map)request.getSession().getAttribute("login_no");
		Map userMap = VerifyLoginInterceptor.tokenMap.get(login_no);
		if(userMap==null){
			System.out.println("拦截-用户未登录");
			JsonpUtils.print(AR.getFailedObj(ARC.LOGIN_NULL, null, null), request, response);
			return false;
		}
		System.out.println("拦截-用户已登录");
		return true;
	}
	
	public static String getUserId(HttpServletRequest request){
		String token = request.getParameter("token");
		Map userMap = VerifyLoginInterceptor.tokenMap.get(token);
//		Map userMap = (Map)request.getSession().getAttribute(CodeForSession.currUser);
		return String.valueOf(userMap.get("id"));
	}
	public static String getUserName(HttpServletRequest request){
		String token = request.getParameter("token");
		Map userMap = VerifyLoginInterceptor.tokenMap.get(token);
//		Map userMap = (Map)request.getSession().getAttribute(CodeForSession.currUser);
		return String.valueOf(userMap.get("login_name"));
	}
	public static String getUserNo(HttpServletRequest request){
		String token = request.getParameter("token");
		Map userMap = VerifyLoginInterceptor.tokenMap.get(token);
//		Map userMap = (Map)request.getSession().getAttribute(CodeForSession.currUser);
		return String.valueOf(userMap.get("login_no"));
	}

}
