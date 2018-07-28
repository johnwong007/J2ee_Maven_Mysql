package com.irebane.sdc;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irebane.job.SdcJob;
import com.irebane.model.AR;
import com.irebane.model.ARC;
import com.utils.HttpRequest;
import com.utils.UT;
import com.utils.servlet.JsonpUtils;

@Controller
public class SdcNewController {

	@RequestMapping("/querySdcTransferRecordForUser")
	public void querySdcTransferRecordForUser(HttpServletRequest request, HttpServletResponse response){
		String accAddress = StringUtils.isNotBlank(request.getParameter("accAddress"))?request.getParameter("accAddress"):"";
		String currency = StringUtils.isNotBlank(request.getParameter("currency"))?request.getParameter("currency"):"";
		if(StringUtils.isNotBlank(accAddress)&&StringUtils.isNotBlank(currency)&&"SDC".equals(currency)){
			String recordUrl = "http://api.etherscan.io/api?module=account&action=txlist&address=0xe85ed250e3d91fde61bf32e22c54f04754e695c5&startblock=5233898&endblock=99999999&sort=desc&apikey=INPIXQYXYN5GCEKU9WRGZEBBQETQ2IBKJC";
			String r = HttpRequest.sendGet(recordUrl,null);
			JSONArray ja1 = new JSONArray();
			if(StringUtils.isNotBlank(r)){
				JSONObject jo = JSONObject.fromObject(r);
				JSONArray ja = jo.getJSONArray("result");
				for(int i=0;i<ja.size();i++){
					JSONObject joo = ja.getJSONObject(i);
					JSONObject jo1 = new JSONObject();
					if(joo.getString("from").equals(accAddress)){
						String inputData = joo.getString("input");
						
						String to = inputData.substring(10,74).replaceAll("^(0+)", "");
						jo1.put("to", to);
						
						String money = inputData.substring(74,138);
						BigInteger bi = new BigInteger(money, 16);
						bi = bi.divide(new BigInteger("1000000000000000000"));
						money = String.valueOf(bi);
						jo1.put("quantity",money);

						String timeStamp = joo.getString("timeStamp");
						Date d = new Date(Long.parseLong(timeStamp+"000"));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						System.out.println(sdf.format(d));
						jo1.put("age_text",sdf.format(d));
						
						String txstatus_text = joo.getString("txreceipt_status");
						txstatus_text = "1".equals(txstatus_text)?"成功":"待核验";
						jo1.put("txstatus_text", txstatus_text);
						
						jo1.put("remark", "");
						ja1.add(jo1);
					}
				}
			}
			JsonpUtils.print(ja1.toString(), request, response);
		}else{
			JsonpUtils.print(AR.getSuccessObj(UT.getFailedRepByEc(ARC.PARA_IS_ERROR)), request, response);
		}
	}
	
}
