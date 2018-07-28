package com.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ST {

	/**
	 * 构建一个分页map，转换pagerows两个参数为beginrows，避免为util包中的MT类附加servlet-api包，在这里做转换，
	 * MT只做map相关的共性便利函数。
	 * 
	 * @author gegl
	 * @date 2017年2月16日 下午2:34:28
	 * @param request
	 * @param mustPage
	 *            true则当不存在pagerows参数时，使用默认值。false则仅过滤pagerows参数，有则转换无则跳过。
	 * @return
	 */
	public static Map<String, String> getParamMap(HttpServletRequest request,
			String key, boolean mustPage) {
		String[] keys = key == null || key.equals("") ? new String[0] : key
				.split(",");
		Map<String, String> reqMap = MT.getStringMap();
		for (String key1 : keys) {
			ST.setRequestValue(reqMap, key1, request);
		}
		int page = 1;
		int rows = 10;
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
		if (mustPage) {
			if (pageStr != null && rowsStr != null) {
				page = Integer.parseInt(pageStr);
				rows = Integer.parseInt(rowsStr);
				reqMap.put("begin", (page - 1) * rows + "");
				reqMap.put("rows", rows + "");
				return reqMap;
			} else {
				reqMap.put("begin", (page - 1) * rows + "");
				reqMap.put("rows", rows + "");
				return reqMap;
			}
		} else {
			if (pageStr != null && rowsStr != null) {
				reqMap.put("begin", (page - 1) * rows + "");
				reqMap.put("rows", rows + "");
				return reqMap;
			} else {
				return reqMap;
			}
		}

	}

	/**
	 * 清洁代码使用，获取request的key值塞入map，当其为null则不塞入
	 * @date 2017年2月16日 下午2:44:24
	 * @param request
	 * @return
	 */
	public static Map<String, String> setRequestValue(
			Map<String, String> reqMap, String key, HttpServletRequest request) {
		String value = request.getParameter(key);
		if (value != null)
			reqMap.put(key, value);
		return reqMap;
	}

	/**
	 * 塞入排序参数工具--判断request对象送来的值是否符合规则，符合时塞入规则值，如果规则中有null，则全部不符合时塞入默认值
	 * 
	 * @author gegl
	 * @date 2017年2月16日 下午2:50:18
	 * @param reqMap
	 * @param request
	 * @param requestKey
	 * @param mapKey=塞入map时的key
	 * @param ruleStr=规则参数：aaa:col1 desc,bbb:col2 desc,null:sord desc
	 * @return
	 */
	public static Map<String, String> setRequestValueForTrans(
			Map<String, String> reqMap, HttpServletRequest request,
			String requestKey, String mapKey, String ruleStr) {
		String requestValue = request.getParameter(requestKey);
		if (requestKey == null)
			requestKey = "null";
		String[] rules = ruleStr.split(",");
		for (String rule : rules) {
			String[] oneRule = rule.split(":");
			String k = oneRule[0];
			String v = oneRule[1];
			if (k.equals(requestValue)) {
				reqMap.put(mapKey, v);
			}
		}

		return reqMap;
	}


}
