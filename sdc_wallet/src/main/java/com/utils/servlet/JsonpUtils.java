/**
 * 
 */
package com.utils.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * @author gegl
 * @date 2017年2月15日 下午12:04:34
 */
public class JsonpUtils {
	public static void print(JSONObject jo,HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		String callback = request.getParameter("callback") ;
		if(callback!=null){
			ServletUtils.print(response, callback+"("+jo.toString()+")");
		}else{
			ServletUtils.print(response, jo.toString());
		}
	}
	
	public static void print(String jsonStr,HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		String callback = request.getParameter("callback") ;
		if(callback!=null){
			ServletUtils.print(response, request.getParameter("callback")+"("+jsonStr+")");
		}else{
			ServletUtils.print(response, jsonStr);
		}
	}
}
