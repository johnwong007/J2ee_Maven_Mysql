package com.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class CommonTool {


	public static HashMap<String, String> getMap(HttpServletRequest request) {
		HashMap<String, String> hash = new HashMap();
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> set = map.entrySet();
		Iterator<Entry<String, String[]>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			String value = entry.getValue()[0];
			String key = entry.getKey();
			hash.put(key, value);
		}
		return hash;

	}

	/**
	 * login_code //famil用户名 id //famil用户ID house_id //房屋ID login_id
	 * //login_info ID estate_id //物业ID login_type //用户类型 village_id //小区ID
	 * 
	 * 
	 * @return
	 */
/*	public static HashMap<String, String> getPhoneLogin(HttpServletRequest request) {
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession().getServletContext());
		String token = request.getParameter("token");
		HashMap<String, String> hash = new HashMap<>();
		if (applicationContext != null && token != null) {
			RedisService redisService = (RedisService) applicationContext.getBean("redisServiceImpl");
			Map data = redisService.getMap(token);
			hash.putAll(data);
		}
		return hash;

	}
*/
/*	public static String getPhoneLoginId(HttpServletRequest request) {
		HashMap<String, String> phoneLogin = getPhoneLogin(request);
		String id = phoneLogin.get("id");
		if (id == null) {
			id = "0";
		}
		return id;
	}*/

	public static HashMap<String, String> getPageMap(HttpServletRequest request, int maxrow) {
		HashMap<String, String> hash = new HashMap();
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> set = map.entrySet();
		Iterator<Entry<String, String[]>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			String value = entry.getValue()[0];
			String key = entry.getKey();
			hash.put(key, value);
		}

		String page_str = request.getParameter("page");
		int page = page_str == null ? 1 : Integer.parseInt(page_str);
		String rows_str = request.getParameter("rows");
		int rows = rows_str == null ? maxrow : Integer.parseInt(rows_str);
		int start = (page - 1) * rows;
		hash.put("start", Integer.toString(start));
		hash.put("rows", Integer.toString(rows));
		hash.put("page", Integer.toString(page));
		return hash;
	}
	
	/***
	* 
	* 金额
	* 
	* 如果需要精确计算，必须用String来够造BigDecimal！ ！！
	* 
	* Java里面的商业计算，不能用float和double，因为他们无法 进行精确计算。
	* 但是Java的设计者给编程人员提供了一个很有用的类BigDecimal，
	* 他可以完善float和double类无法进行精确计算的缺憾。
	* BigDecimal类位于java.maths类包下。
	* 它的构造函数很多，最常用的:
	* BigDecimal(double val)
	* BigDecimal(String str)
	* BigDecimal(BigInteger val)
	* BigDecimal(BigInteger unscaledVal, int scale)

	
	/***
	* 保留2位小数
	* 四舍五入
	* @param a
	* 
	* @return
	* 返回一个double类型的2位小数
	*/
	public static Double get2Double(Double doubleVal,int scale){
	if(null == doubleVal){
	doubleVal = new Double(0);
	}
	return new BigDecimal(doubleVal).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/***
	* 格式化Double类型并保留scale位小数
	* 四舍五入
	* @param doubleVal
	* @param scale
	* scale必须为大于0的正整数，不能等于0
	* @return
	*/
	public static String formatBy2Scale(Double doubleVal,int scale){
	if(null == doubleVal){
	doubleVal = new Double(0);
	}
	StringBuffer sbStr = new StringBuffer("0.");
	for (int i = 0; i < scale; i++) {
	sbStr.append("0");
	}
	DecimalFormat myformat = new DecimalFormat(sbStr.toString());
	return myformat.format(doubleVal);
	}

	/***
	* Double类型相加 <font color="red">+</font><br/>
	* ROUND_HALF_UP <font color="red">四舍五入</font><br/>
	* @param val1
	* 
	* @param val2
	* 
	* @param scale
	* <font color="red">保留scale位小数</font><br/>
	* @return
	*/
	public static Double add(Double val1,Double val2,int scale){
	if(null == val1){
	val1 = new Double(0);
	}
	if(null == val2){
	val2 = new Double(0);
	}
	return new BigDecimal(Double.toString(val1)).add(new BigDecimal(Double.toString(val2))).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/***
	* Double类型相减 <font color="red">—</font><br/>
	* ROUND_HALF_UP <font color="red">四舍五入</font><br/>
	* @param val1
	* 
	* @param val2
	* 
	* @param scale
	* <font color="red">保留scale位小数</font><br/>
	* @return
	*/
	public static Double subtract(Double val1,Double val2,int scale){
	if(null == val1){
	val1 = new Double(0);
	}
	if(null == val2){
	val2 = new Double(0);
	}
	return new BigDecimal(Double.toString(val1)).subtract(new BigDecimal(Double.toString(val2))).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/***
	* Double类型相乘 <font color="red">*</font><br/>
	* ROUND_HALF_UP <font color="red">四舍五入</font><br/>
	* @param val1
	* 
	* @param val2
	* 
	* @param scale
	* <font color="red">保留scale位小数</font><br/>
	* @return
	*/
	public static Double multiply(Double val1,Double val2,int scale){
	if(null == val1){
	val1 = new Double(0);
	}
	if(null == val2){
	val2 = new Double(0);
	}
	return new BigDecimal(Double.toString(val1)).multiply(new BigDecimal(Double.toString(val2))).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/***
	* Double类型相除 <font color="red">/</font><br/>
	* ROUND_HALF_UP <font color="red">四舍五入</font><br/>
	* @param val1
	* 
	* @param val2
	* 
	* @param scale
	* <font color="red">保留scale位小数</font><br/>
	* @return
	*/
	public static Double divide(Double val1,Double val2,int scale){
	if(null == val1){
	val1 = new Double(0);
	}
	if(null == val2 || val2 == 0){
	val2 = new Double(1);
	}
	return new BigDecimal(Double.toString(val1)).divide(new BigDecimal(Double.toString(val2))).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	/***
	* Double类型取余    <font color="red">%</font><br/>
	* ROUND_HALF_UP <font color="red">四舍五入</font><br/>
	* @param val1
	* 
	* @param val2
	* 
	* @param scale
	* <font color="red">保留scale位小数</font><br/>
	* @return
	*/
	public static int divideAndRemainder(Double val1,Double val2,int scale){
	if(null == val1){
	val1 = new Double(0);
	}
	if(null == val2 || val2 == 0){
	val2 = new Double(1);
	}
	return new BigDecimal(Double.toString(val1)).divideAndRemainder(new BigDecimal(Double.toString(val2)))[1].setScale(scale, BigDecimal.ROUND_HALF_UP).intValue();
	}

	/***
	* 格式化Double类型数据
	* 
	* @param val
	* @param fmt
	* NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用 
	* NumberFormat percent = NumberFormat.getPercentInstance(); //建立百分比格式化引用 
	* @param maximumFractionDigits
	* 如果是百分比 设置小数位数（四舍五入）
	* @return
	*/
	public static String formatByNumberFormat(Double val,NumberFormat fmt,int maximumFractionDigits){
	if(fmt.equals(NumberFormat.getPercentInstance())){
	fmt.setMaximumFractionDigits(maximumFractionDigits); //百分比小数点最多3位 
	}
	return fmt.format(val);

	}

	/***
	* 比较大小
	* -1、0、1，即左边比右边数大，返回1，相等返回0，比右边小返回-1。
	* @param doubleVal
	* @return
	*/
	public static int compareTo(Double val1,Double val2){
	if(null == val1){
	val1 = new Double(0);
	}
	if(null == val2){
	val2 = new Double(0);
	}
	return new BigDecimal(val1).compareTo(new BigDecimal(val2));
	}




	
	
}
