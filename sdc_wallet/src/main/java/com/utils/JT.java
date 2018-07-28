/**
 * 
 */
package com.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @date 2017年2月14日 上午10:22:34
 */
public class JT {
	/**
	 * jsonobject串 转 map
	 */
	public static Map<String, Object> jsonobject_to_map(JSONObject json) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			if("net.sf.json.JSONNull".equals(json.get(k).getClass().getName())){
				v = null;
			}
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(jsonobject_to_map(json2));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
	
	public static JSONObject getJsonObj(Object obj){
		return JSONObject.fromObject(obj);
	}
	public static String getJsonObjStr(Object obj){
		return JT.getJsonObj(obj).toString();
	}
	public static JSONObject getJsonArray(Object obj){
		return JSONObject.fromObject(obj);
	}
	public static String getJsonArrayStr(Object obj){
		return JT.getJsonObj(obj).toString();
	}
	/**
	 * 取JSONOBJECT里面的某个KEY的值 并做has校验和null校验
	 */
	public static String getJSONstr(String name, JSONObject jo) {
		if (!jo.has(name))
			return "";
		return jo.getString(name);
	}
	
}
