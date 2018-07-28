package com.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 一些涉及到MAP的工具放这里面
 * @date 2017年3月7日 上午9:42:54
 */
public class MT {
	/**
	 * 取MAP里面的某个KEY的值 并做has校验和null校验 null的话输出为空字符串
	 */
	public static String getMapstr(String key, Map map) {
		return map.get(key) == null ? "" : map.get(key).toString();
	}

	/**
	 * 获取一个String泛型的Map
	 * @author gegl
	 * @date 2016年12月7日 上午10:51:28
	 * @return
	 */
	public static Map<String, String> getStringMap(){
		 return new HashMap<String, String>();
	}

	/**
	 * 获取一个新的HashMap (¯﹃¯)省事儿
	 * @author gegl
	 * @date 2016年12月7日 上午11:26:17
	 * @return
	 */
	public static Map getHashMap() {
		return new HashMap();
	}
	
	/** 
	 * 方法名称:transMapToString 
	 * 传入参数:map 
	 * 返回值:String 形如 username'chenziwen^password'1234 
	*/  
	public static String mapToString(Map map){  
	  java.util.Map.Entry entry;  
	  StringBuffer sb = new StringBuffer();  
	  for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)  
	  {  
	    entry = (java.util.Map.Entry)iterator.next();  
	      sb.append(entry.getKey().toString()).append( "=" ).append(null==entry.getValue()?"":  
	      entry.getValue().toString()).append (iterator.hasNext() ? "&" : "");  
	  }  
	  return sb.toString();  
	}  
}
