/**
 * 
 */
package com.irebane.model;


import com.utils.JT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author gegl
 */
public class AR {
	
	public static JSONObject getSuccessObj(JSONObject dataJo,JSONArray dataJr){
		JSONObject jo = new JSONObject();
		jo.put(ARC.codeKey, ARC.SUCCESS.getErrorCode());
		jo.put(ARC.codeMsg, ARC.SUCCESS.getErrorMsg());
		jo.put(ARC.codeObj, dataJo);
		jo.put(ARC.codeArr, dataJr);
		return jo;
	}
	
	public static JSONObject getSuccessObj(JSONObject dataJo,JSONArray dataJr,String message){
		JSONObject jo = new JSONObject();
		jo.put(ARC.codeKey, ARC.SUCCESS.getErrorCode());
		jo.put(ARC.codeMsg, message);
		jo.put(ARC.codeObj, dataJo);
		jo.put(ARC.codeArr, dataJr);
		return jo;
	}
	
	public static JSONObject getFailedObj(ARC arc,JSONObject dataJo,JSONArray dataJr){
		JSONObject jo = new JSONObject();
		jo.put(ARC.codeKey, arc.getErrorCode());
		jo.put(ARC.codeMsg, arc.getErrorMsg());
		jo.put(ARC.codeObj, dataJo);
		jo.put(ARC.codeArr, dataJr);
		return jo;
	}
	
	public static JSONObject getFailedObj(ARC arc,JSONObject dataJo,JSONArray dataJr,String message){
		JSONObject jo = new JSONObject();
		jo.put(ARC.codeKey, arc.getErrorCode());
		jo.put(ARC.codeMsg, message);
		jo.put(ARC.codeObj, dataJo);
		jo.put(ARC.codeArr, dataJr);
		return jo;
	}
	
    public static JSONObject getSuccessObj(SpiResp resp){
    	JSONObject dataJo=JT.getJsonObj(resp);
    	JSONObject jo = new JSONObject();
    	if(!dataJo.get("errorCode").equals(ARC.SUCCESS.getErrorCode())&&!dataJo.get("errorCode").equals(ARC.OTHER.getErrorCode())){
    		jo.put("errorCode", ARC.OTHER.getErrorCode());
    	}else{
    		jo.put("errorCode", dataJo.get("errorCode"));
    	}
    	jo.put("errorMsg", dataJo.get("errorMsg"));
		jo.put("l", dataJo.get("l"));
		jo.put("m", dataJo.get("m"));
		jo.put("total", dataJo.get("total"));
		return jo;
    	
    }

}
