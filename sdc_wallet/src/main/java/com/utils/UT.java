/**
 * 
 */
package com.utils;

import java.util.Iterator;
import java.util.Map;

import com.irebane.model.ARC;
import com.irebane.model.SpiResp;


/**
 * 常用参数方面的工具
 * @date 2016-10-1 下午05:18:04
 */
public class UT {
	/**
	 * 是否为null或空
	 * 
	 * @author gegl
	 * @date 2016-10-1 下午05:19:12
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if (value == null || "".equals(value))
			return false;
		return true;
	}

	/**
	 * 将后台提供的resp翻译为前端可用的resp，主要是去掉部分无价值errorCode
	 * @date 2017年2月15日 下午2:01:40
	 * @param resp
	 * @return
	 */
	/*public static SpiResp translateRespForAjax(SpiResp resp) {
		//0000 0001 可以返回 其他都转换为9999
		String errorCode = resp.getErrorCode();
		if (errorCode.equals(EC.SUCCESS.getCode()) || errorCode.equals(EC.PARAM_ERROR.getCode())) {
			return resp;
		} else {
			resp.setErrorCode(EC.FAILED.getCode());
			return resp;
		}
	}*/

	/**
	 * 构造错误返回对象
	 * 
	 * @author gegl
	 * @date 2016-10-1 下午05:20:32
	 * @param failed
	 * @return
	 */
	public static SpiResp getFailedRepByEc(ARC arc) {
		SpiResp rep = new SpiResp();
		rep.setErrorCode(arc.getErrorCode());
		rep.setErrorMsg(arc.getErrorMsg());
		return rep;
	}

	/**
	 * 构造错误返回对象，msg可自定义
	 * 
	 * @author gegl
	 * @date 2016-10-1 下午06:08:04
	 * @param ec
	 * @param msg
	 * @return
	 */
	public static SpiResp getFailedRepByEc(ARC arc, String msg) {
		SpiResp rep = new SpiResp();
		rep.setErrorCode(arc.getErrorCode());
		rep.setErrorMsg(msg);
		return rep;
	}

	/**
	 * 构造成功的返回对象
	 * 
	 * @author gegl
	 * @date 2016-10-1 下午06:07:50
	 * @return
	 */
	public static SpiResp getSuccessRep() {
		SpiResp rep = new SpiResp();
		rep.setErrorCode(ARC.SUCCESS.getErrorCode());
		rep.setErrorMsg(ARC.SUCCESS.getErrorMsg());
		return rep;
	}

	/**
	 * 校验输入参数是否异常
	 * 
	 * @author gegl
	 * @date 2016-10-1 下午06:29:37
	 * @param reqParam
	 *            外面传来的参数
	 * @param whitelist
	 *            自家白名单
	 * @param canAllEmpty
	 *            非必填项参数是否可以都为空
	 * @return
	 */
	public static String checkReqMap(Map<String, String> a,
			Map<String, Map<String, Integer>> whitelist, boolean canAllEmpty) {
		Map<String, Integer> b = whitelist.get(getMethodName(3));
		Iterator<String> ia = a.keySet().iterator();
		while (ia.hasNext()) {
			String k = ia.next();
			if (b.get(k) == null) {
				return "参数[" + k + "]无效";
			}
		}
		Iterator<String> ib = b.keySet().iterator();
		boolean allIsEmpty = true;
		while (ib.hasNext()) {
			String k = ib.next();
			int c = b.get(k);
			String d = a.get(k);
			if (d == null || "".equals(d)) {
				if (c == 1) {
					return "参数[" + k + "]不能为空";
				}
			} else {
				if (c != 1)
					allIsEmpty = false;
			}
		}
		if (!canAllEmpty)
			if (allIsEmpty)
				return "参数不可以全部为空";
		return null;
	}

	/**
	 * 获取当前函数名称，参数表示显示哪层，2是调用方
	 * 
	 * @author gegl
	 * @date 2016-10-1 下午06:16:41
	 * @return
	 */
	public static String getMethodName(int lvl) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[lvl];
		String methodName = e.getMethodName();
		return methodName;
	}

	/**
	 * 白名单是否已初始化
	 * 
	 * @author gegl
	 * @date 2016-10-1 下午06:24:58
	 * @param whitelist
	 * @return
	 */
	public static boolean isInitWhiteList(
			Map<String, Map<String, Integer>> whitelist) {
		if (whitelist.get(getMethodName(3)) == null)
			return false;
		return true;
	}

	/**
	 * @author gegl
	 * @date 2016-10-1 下午06:26:02
	 * @param wlm
	 */
	public static void initWhiteList(Map<String, Integer> wlm,
			Map<String, Map<String, Integer>> whitelist) {
		whitelist.put(getMethodName(3), wlm);
	}

	/**
	 * @author gegl
	 * @date 2016-10-1 下午06:37:16
	 * @param string
	 * @return
	 */
	public static int getInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
			return 0;
		}
	}

	/**
	 * @author gegl
	 * @date 2016-10-1 下午07:58:59
	 * @param paramMap
	 * @param string
	 * @return
	 */
	public static Map<String, Object> mapStrToInt(Map<String, Object> paramMap,
			String str) {
		String[] strs = str.split(",");
		for (String s : strs) {
			paramMap.put(s, UT.getInt(paramMap.get(s).toString()));
		}
		return paramMap;
	}

}
