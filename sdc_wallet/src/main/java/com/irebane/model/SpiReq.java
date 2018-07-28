/**
 * 
 */
package com.irebane.model;

import java.util.Map;

/**
 * 金融平台服务统一请求实体 标注出要访问的函数，并将其他参数放入Map对象来传递
 * 
 * @author gegl
 * @date 2016-10-1 下午03:56:13
 */
public class SpiReq implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;
	private Map<String, String> reqParaMap;

	/**
	 * @param action
	 * @param reqParaMap
	 */
	public SpiReq(String action, Map<String, String> reqParaMap) {
		this.action = action;
		this.reqParaMap = reqParaMap;
	}

	public String getAction() {
		return action;
	}

	public Map<String, String> getReqParaMap() {
		return reqParaMap;
	}

	@Override
	public String toString() {
		return "SpiReq [action=" + action + ", reqParaMap=" + reqParaMap + "]";
	}


}
