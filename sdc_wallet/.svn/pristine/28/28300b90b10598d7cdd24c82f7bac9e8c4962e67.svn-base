package com.irebane.sdc;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irebane.ML;
import com.irebane.job.SdcJob;
import com.irebane.model.AR;
import com.irebane.model.ARC;
import com.system.SecurityController;
import com.utils.CommonTool;
import com.utils.MT;
import com.utils.UT;
import com.utils.servlet.JsonpUtils;

@Controller
public class SdcTxRecordController {

	private static final Logger logger = Logger.getLogger(SdcTxRecordController.class);

	
	/**
	 * 添加转账类交易记录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/addTxRecordByTransfer")
	public void addTxRecordByTransfer(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String txhash = StringUtils.isNotBlank(request.getParameter("txhash"))?request.getParameter("txhash"):"";
		String from = StringUtils.isNotBlank(request.getParameter("from"))?request.getParameter("from"):"";
		String to = StringUtils.isNotBlank(request.getParameter("to"))?request.getParameter("to"):"";
		String quantity = StringUtils.isNotBlank(request.getParameter("quantity"))?request.getParameter("quantity"):"";
		String currency = StringUtils.isNotBlank(request.getParameter("currency"))?request.getParameter("currency"):"";
		String remark = StringUtils.isNotBlank(request.getParameter("remark"))?request.getParameter("remark"):"";
		if(!"".equals(txhash)&&!"".equals(from)&&!"".equals(to)&&!"".equals(quantity)&&!"".equals(currency)){
			//检查txhash是否存在
			Map recordMap = ML.sdcTxRecordMapper.getSdcTxRecordByTxHash(txhash);
			boolean exist = recordMap!=null && !recordMap.isEmpty()?true:false;
			if(!exist){//如果不存在txhash的交易记录
				//把参数记录到数据库中（待校验）
				Map<String,String> toCheckMap = new HashMap<String,String>();
				toCheckMap.put("txhash", txhash);
				toCheckMap.put("from", from);
				toCheckMap.put("to", to);
				toCheckMap.put("quantity", quantity);
				toCheckMap.put("currency", currency);
				toCheckMap.put("remark", remark);
				toCheckMap.put("txstatus", "1");
				toCheckMap.put("txtype", "1");
				//向交易表插入待校验的交易记录
				ML.sdcTxRecordMapper.addToCheckTxRecordByTransfer(toCheckMap);
				JsonpUtils.print(AR.getSuccessObj(null, null), request, response);
			}else{
				JsonpUtils.print(AR.getSuccessObj(UT.getFailedRepByEc(ARC.DANHAO_IS_EXIST)), request, response);
			}
		}else{
			JsonpUtils.print(AR.getSuccessObj(UT.getFailedRepByEc(ARC.PARA_IS_ERROR)), request, response);
		}
	}
	/**
	 * 添加募集类交易记录（增加邀请码、特定地址转账）
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/addTxRecordByCollect")
	public void addTxRecordByCollect(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String ratioid = StringUtils.isNotBlank(request.getParameter("ratioid"))?request.getParameter("ratioid"):"";
		String txhash = StringUtils.isNotBlank(request.getParameter("txhash"))?request.getParameter("txhash"):"";
		String from = StringUtils.isNotBlank(request.getParameter("from"))?request.getParameter("from"):"";
		String to = StringUtils.isNotBlank(request.getParameter("to"))?request.getParameter("to"):"";
		String quantity = StringUtils.isNotBlank(request.getParameter("quantity"))?request.getParameter("quantity"):"";
		String currency = StringUtils.isNotBlank(request.getParameter("currency"))?request.getParameter("currency"):"";
		//邀请码
		String Invitationcode = StringUtils.isNotBlank(request.getParameter("Invitationcode"))?request.getParameter("Invitationcode"):"";
		//目标地址（募集时可设置此地址，系统向目标地址中转入SDC）
		String targetaddress = StringUtils.isNotBlank(request.getParameter("targetaddress"))?request.getParameter("targetaddress"):"";
		if(!"".equals(ratioid)&&!"".equals(txhash)&&!"".equals(from)&&!"".equals(to)&&!"".equals(quantity)&&!"".equals(currency)){
			//检查txhash是否存在
			Map recordMap = ML.sdcTxRecordMapper.getSdcTxRecordByTxHash(txhash);
			boolean exist = recordMap!=null && !recordMap.isEmpty()?true:false;
			if(!exist){//如果不存在txhash的交易记录
				//把参数记录到数据库中（待校验）
				Map<String,String> toCheckMap = new HashMap<String,String>();
				toCheckMap.put("ratioid", ratioid);
				toCheckMap.put("txhash", txhash);
				toCheckMap.put("from", from);
				toCheckMap.put("to", to);
				toCheckMap.put("quantity", quantity);
				toCheckMap.put("currency", currency);
				toCheckMap.put("Invitationcode", Invitationcode);
				toCheckMap.put("targetaddress", targetaddress);
				toCheckMap.put("txstatus", "1");
				toCheckMap.put("txtype", "2");
				toCheckMap.put("collectstatus", "1");
				//向交易表插入待校验的交易记录
				ML.sdcTxRecordMapper.addToCheckTxRecordByCollect(toCheckMap);
				JsonpUtils.print(AR.getSuccessObj(null, null), request, response);
			}else{
				JsonpUtils.print(AR.getSuccessObj(UT.getFailedRepByEc(ARC.DANHAO_IS_EXIST)), request, response);
			}
		}else{
			JsonpUtils.print(AR.getSuccessObj(UT.getFailedRepByEc(ARC.PARA_IS_ERROR)), request, response);
		}
	}
	
	/**
	 * 查询交易记录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/queryTransferRecord")
	public void queryTransferRecord(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String accAddress = StringUtils.isNotBlank(request.getParameter("accAddress"))?request.getParameter("accAddress"):"";
		Map map = new HashMap();
		map.put("accAddress", accAddress.toLowerCase());
		List<Map> records = ML.sdcTxRecordMapper.queryTransferRecord(map);
		
		
	}
	
	/**
	 * 查询当前全部募集信息（未登录可用）
	 */
	@RequestMapping("/getCollectInfo")
	public void getCollectInfo(HttpServletRequest request, HttpServletResponse response){
		Map map = ML.sdcTxRecordMapper.getCurrentCollectInfo();
		if(map!=null && !map.isEmpty()){
			//查询众筹记录
			List<Map> list = ML.sdcTxRecordMapper.queryCollectRecord(null);
			map.put("collectList", list);
		}
		JSONObject jsonObject = JSONObject.fromObject(map); 
		JsonpUtils.print(jsonObject.toString(), request, response);
	}
	/**
	 * 查询当前全部募集信息（登录后可用）
	 */
	@RequestMapping("/getCollectInfoLogin")
	public void getCollectInfoLogin(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		String Invitationcode = request.getParameter("Invitationcode");
		Map map = ML.sdcTxRecordMapper.getCurrentCollectInfo();
		if(map!=null && !map.isEmpty()){
			//查询众筹记录
			List<Map> list = ML.sdcTxRecordMapper.queryCollectRecord(Invitationcode);
			map.put("collectList", list);
		}
		JSONObject jsonObject = JSONObject.fromObject(map); 
		JsonpUtils.print(jsonObject.toString(), request, response);
	
	}
	
	/**
	 * 查询当前个人募集信息
	 */
	@RequestMapping("/getCollectInfoForUser")
	public void getCollectInfoForUser(HttpServletRequest request, HttpServletResponse response){
		String accAddress = StringUtils.isNotBlank(request.getParameter("accAddress"))?request.getParameter("accAddress"):"";
		if(StringUtils.isNotBlank(accAddress)){
			Map map = ML.sdcTxRecordMapper.getCurrentCollectInfo();
			if(map!=null && !map.isEmpty()){
				//查询众筹记录
				List<Map> list = ML.sdcTxRecordMapper.queryCollectRecordForUser(accAddress);
				map.put("collectList", list);
			}
			JSONObject jsonObject = JSONObject.fromObject(map); 
			JsonpUtils.print(jsonObject.toString(), request, response);
		}else{
			JsonpUtils.print(AR.getSuccessObj(UT.getFailedRepByEc(ARC.PARA_IS_ERROR)), request, response);
		}
	}
	/**
	 * 查询当前个人交易记录SDC/ETH
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/queryTransferRecordForUser")
	public void queryTransferRecordForUser(HttpServletRequest request, HttpServletResponse response){
		String accAddress = StringUtils.isNotBlank(request.getParameter("accAddress"))?request.getParameter("accAddress"):"";
		String currency = StringUtils.isNotBlank(request.getParameter("currency"))?request.getParameter("currency"):"";
		if(StringUtils.isNotBlank(accAddress)&&StringUtils.isNotBlank(currency)){
			Map paraMap = new HashMap();
			paraMap.put("accAddress", accAddress);
			paraMap.put("currency", currency);
			List<Map> list = ML.sdcTxRecordMapper.queryTransferRecordForUser(paraMap);

			JSONArray json = JSONArray.fromObject(list);
			JsonpUtils.print(json.toString(), request, response);
		}else{
			JsonpUtils.print(AR.getSuccessObj(UT.getFailedRepByEc(ARC.PARA_IS_ERROR)), request, response);
		}
	}
	/**
	 * 管理方查询SDC交易情况
	 * @param request
	 * @param response
	 */
	@RequestMapping("/querySdcTransferRecordForAdmin")
	public void querySdcTransferRecordForAdmin(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		List<Map> list = ML.sdcTxRecordMapper.querySdcTransferRecordForAdmin();
		JSONArray json = JSONArray.fromObject(list);
		JsonpUtils.print(json.toString(), request, response);
	}
	/**
	 * 管理方查询ETH交易情况
	 * @param request
	 * @param response
	 */
	@RequestMapping("/queryEthTransferRecordForAdmin")
	public void queryEthTransferRecordForAdmin(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		List<Map> list = ML.sdcTxRecordMapper.queryEthTransferRecordForAdmin();
		JSONArray json = JSONArray.fromObject(list);
		JsonpUtils.print(json.toString(), request, response);
	}
	
	
	/**
	 * 募集渠道管理（添加、修改、删除）
	 * @param request
	 * @param response
	 */
	@RequestMapping("/eidtchn")
	public void eidtDaBiaoGeInfo(HttpServletRequest request, HttpServletResponse response) {
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		Map paramMap = CommonTool.getMap(request);
		String oper = request.getParameter("oper");
		if ("add".equals(oper)) {
			paramMap.remove("id");
			paramMap.remove("oper");
			paramMap.remove("login_no");
			ML.sdcTxRecordMapper.addchn(paramMap);
		} 
		if ("del".equals(oper)) {
			Map map = new HashMap();
			map.put("id", MT.getMapstr("id", paramMap));
			ML.sdcTxRecordMapper.delchn(map);
		}
		if ("edit".equals(oper)) {
			paramMap.remove("oper");
			paramMap.remove("login_no");
			ML.sdcTxRecordMapper.updatechn(paramMap);
		}
		JsonpUtils.print(AR.getSuccessObj(null, null), request, response);
	}
	/**
	 * 查询渠道列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/querychnList")
	public void querychnList(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		List<Map> list = ML.sdcTxRecordMapper.querychnList();
		JSONArray json = JSONArray.fromObject(list);
		JsonpUtils.print(json.toString(), request, response);
	}
	/**
	 * 查询主账户交易记录，用于后台管理员批量转账
	 * 只查iscollect=1的记录（是否募集交易0：不是；1：是）
	 * 并且collectstatus=1（1：用户已转账；2：已向用户转账）
	 * 并且to=主账户的记录
	 * @param request
	 * @param response
	 */
	@RequestMapping("/queryTransMainList")
	public void queryTransMainList(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		List<Map> list = ML.sdcTxRecordMapper.queryTransMainList(SdcJob.mainAcc);
		JSONArray json = JSONArray.fromObject(list);
		JsonpUtils.print(json.toString(), request, response);
	}
	/**
	 * 管理员批量设置记录为不处理type=3
	 * @param request
	 * @param response
	 */
	@RequestMapping("/setTransMainNotCollect")
	public void setTransMainNotCollect(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		String ids = request.getParameter("ids");
		ML.sdcTxRecordMapper.setTransMainNotCollect(ids);
		JsonpUtils.print(AR.getSuccessObj(null, null), request, response);
	}
	/**
	 * 管理员手工设置sdc募集数量及解锁时间
	 * @param request
	 * @param response
	 */
	@RequestMapping("/setSdcquantityAndUnlockTimeById")
	public void setSdcquantityAndUnlockTimeById(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		Map paramMap = CommonTool.getMap(request);
		ML.sdcTxRecordMapper.setSdcquantityAndUnlockTimeById(paramMap);
		JsonpUtils.print(AR.getSuccessObj(null, null), request, response);
	}
	/**
	 * 管理员手动批量转账
	 * 根据ids查询主账户交易记录，比对hash值，
	 * 1、修改trans_main表中type=2（已处理）
	 * 2、向交易记录表trans_record批量插入记录，定时器查询状态后自动向用户转账
	 * @param request
	 * @param response
	 */
	@RequestMapping("/insetTransRecordFromMain")
	public void insetTransRecordFromMain(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		String ids = request.getParameter("ids");
		//管理员选定的记录
		List<Map> mainList = ML.sdcTxRecordMapper.getTransMainByids(ids);
		//修改trans_main表中type=2
		ML.sdcTxRecordMapper.setTransMainCollectstatus(ids);
		List<Map> insertList = new ArrayList<Map>();
		//查询当前在用兑换比率
		String ratioid = ML.sdcTxRecordMapper.getRatioIsUse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(Map main : mainList){
			//检查txhash是否存在
			String txhash = main.get("hash").toString();
			Map recordMap = ML.sdcTxRecordMapper.getSdcTxRecordByTxHashAndTxtype(txhash);
			boolean exist = recordMap!=null && !recordMap.isEmpty()?true:false;
			if(!exist){//如果不存在txhash的交易记录
				//把参数记录到数据库中
				Map<String,String> toCheckMap = new HashMap<String,String>();
				toCheckMap.put("ratioid", ratioid);
				toCheckMap.put("txhash", txhash);
				Long timeStamp = Long.valueOf(main.get("timeStamp").toString())*1000;
				toCheckMap.put("age", sdf.format(new Date(timeStamp)));
				toCheckMap.put("from", main.get("from").toString());//用户地址
				toCheckMap.put("to", main.get("to").toString());
//				toCheckMap.put("quantity", (new BigDecimal(main.get("value").toString()).divide(new BigDecimal("1000000000000000000"))).toString());//ETH数量
				toCheckMap.put("quantity", main.get("quantity").toString());//ETH数量
				toCheckMap.put("currency", "ETH");
				toCheckMap.put("Invitationcode", "");
				toCheckMap.put("targetaddress", main.get("from").toString());
				toCheckMap.put("txstatus", "0");//成功
				toCheckMap.put("txtype", "2");//募集
				toCheckMap.put("collectstatus", "1");//用户已转账
				//向交易表插入私募的交易记录
				ML.sdcTxRecordMapper.addToCheckTxRecordByCollect(toCheckMap);
			}
		}
		JsonpUtils.print(AR.getSuccessObj(null, null), request, response);
	}
	
	/**
	 * 查询sdc入账记录
	 * @param request
	 * @param response
	 */
	@RequestMapping("/querySdcRecordForUser")
	public void querySdcRecordForUser(HttpServletRequest request, HttpServletResponse response){
		String accAddress = StringUtils.isNotBlank(request.getParameter("accAddress"))?request.getParameter("accAddress"):"";
		List<Map> list = ML.sdcTxRecordMapper.querySdcRecordForUser(accAddress);
		JSONArray json = JSONArray.fromObject(list);
		JsonpUtils.print(json.toString(), request, response);
	}
	/**
	 * 添加非线上交易
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/addUnonlineRecord")
	public void addUnonlineRecord(HttpServletRequest request, HttpServletResponse response){
		String accAddress = StringUtils.isNotBlank(request.getParameter("accAddress"))?request.getParameter("accAddress"):"";
		if(StringUtils.isBlank(accAddress)) return;
		Map map = new HashMap();
		map.put("blockNumber", "");
		String date = String.valueOf(new Date().getTime());
		map.put("timeStamp", date.substring(0, date.length()-3));
		map.put("hash", "1x"+UUID.randomUUID()+UUID.randomUUID());
		map.put("nonce", "unonline");
		map.put("blockHash", "");
		map.put("transactionIndex", "");
		map.put("from", accAddress);
		map.put("to", SdcJob.mainAcc);
		map.put("value", "0");
		map.put("gas", "0");
		map.put("gasPrice", "0");
		map.put("isError", "0");
		map.put("txreceipt_status", "1");
		map.put("input", "");
		map.put("contractAddress", "");
		map.put("cumulativeGasUsed", "");
		map.put("gasUsed", "0");
		map.put("confirmations", "");
		List<Map> insertMap = new ArrayList<Map>();
		insertMap.add(map);
		ML.sdcTxRecordMapper.insertTransMain(insertMap);
		
		JsonpUtils.print(AR.getSuccessObj(null, null), request, response);
	}
	/**
	 * 查询当前兑换比例
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getIsuseRatio")
	public void getIsuseRatio(HttpServletRequest request, HttpServletResponse response){
		Map map = ML.sdcTxRecordMapper.getIsuseRatio();
		JSONObject jsonObject = JSONObject.fromObject(map); 
		JsonpUtils.print(jsonObject.toString(), request, response);
	}
	/**
	 * 更新当前兑换比例
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateIsuseRatio")
	public void updateIsuseRatio(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		String id = StringUtils.isNotBlank(request.getParameter("id"))?request.getParameter("id"):"";
		String ratioVal = StringUtils.isNotBlank(request.getParameter("ratioVal"))?request.getParameter("ratioVal"):"";
		if(!"".equals(id)&&!"".equals(ratioVal)){
			Map map = new HashMap();
			map.put("id", id);
			map.put("ratioVal", ratioVal);
			ML.sdcTxRecordMapper.updateIsuseRatio(map);
			JsonpUtils.print(AR.getSuccessObj(null, null), request, response);
		}else{
			JsonpUtils.print(AR.getSuccessObj(UT.getFailedRepByEc(ARC.PARA_IS_ERROR)), request, response);
		}
	}
	/**
	 * 管理员查询锁仓合约交易情况
	 */
	@RequestMapping("/queryLockContractTransaction")
	public void queryLockContractTransaction(HttpServletRequest request, HttpServletResponse response){
		boolean login = SecurityController.getUserLogin(request, response);
		if(!login) return;
		String txhash = StringUtils.isNotBlank(request.getParameter("txhash"))?request.getParameter("txhash"):"";
		if(!"".equals(txhash)){
			List<Map> list = ML.sdcTxRecordMapper.queryLockContractTransaction(txhash);
			JSONArray json = JSONArray.fromObject(list);
			JsonpUtils.print(json.toString(), request, response);
		}else{
			JsonpUtils.print(AR.getSuccessObj(UT.getFailedRepByEc(ARC.PARA_IS_ERROR)), request, response);
		}
	}
}
