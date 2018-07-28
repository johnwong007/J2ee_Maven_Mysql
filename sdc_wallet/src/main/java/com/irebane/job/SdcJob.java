package com.irebane.job;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.irebane.yt.organisation.name.ColdWallet;
import com.irebane.ML;
import com.utils.http.HttpsClientUtil;

@Component
public class SdcJob {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(SdcJob.class);
	private static final String apikey = "INPIXQYXYN5GCEKU9WRGZEBBQETQ2IBKJC";
	
	//main网【发布生产环境时注意切换】
	public static final String mainAcc = "0xe7DbCcA8183cb7d67bCFb3DA687Ce7325779c02f";
	public static final String sdclockcontractAdd = "0x445d7bb16fe3aada6a89673aa15919306af07c9f";
	private static final String statusUrlPrefix = "https://api.etherscan.io/api?module=proxy&action=eth_getTransactionReceipt";
	private static final String transUrlPrefix = "https://api.etherscan.io/api?module=proxy&action=eth_getTransactionByHash";
	private static final String recordUrlPrefix = "http://api.etherscan.io/api?module=account&action=txlist";
	private static final String gasPriceDefault = "8000000000"; 
	private static final String gasPrice16Url = "https://api.etherscan.io/api?module=proxy&action=eth_gasPrice&apikey="+apikey;
//	public static final String web3jurl = "http://54.169.251.215:9656/";//产生国外服务器
//	public static final String web3jurl = "http://172.31.23.37:9656/";//产生国外服务器
	public static final String web3jurl = "https://mainnet.infura.io/dY2Ew9eYZspcnsYp8oL1";//Main Ethereum Network
	public static final String mainprivateKey = "22d21692cad7ed6cce51e02da71fb5dd64b20428a8c0ca6564106f47d3db467d";
	public static final String sdccontractAdd = "0xe85ed250e3d91fde61bf32e22c54f04754e695c5";
	//rop测试网【发布测试环境时注意切换】
//	public static final String mainAcc = "0xAD7B07322A7e144a0a99e027F9cb173006668D17";
//	public static final String sdclockcontractAdd = "0x8ecd747930a2cc74d110145a6b2f16d598edf40a";
//	private static final String statusUrlPrefix = "https://api-ropsten.etherscan.io/api?module=proxy&action=eth_getTransactionReceipt";
//	private static final String transUrlPrefix = "https://api-ropsten.etherscan.io/api?module=proxy&action=eth_getTransactionByHash";
//	private static final String recordUrlPrefix = "http://api-ropsten.etherscan.io/api?module=account&action=txlist";
//	private static final String gasPriceDefault = "8000000000";
//	private static final String gasPrice16Url = "https://api-ropsten.etherscan.io/api?module=proxy&action=eth_gasPrice&apikey="+apikey;
////	public static final String web3jurl = "http://47.104.22.35:9656/";//测试阿里云服务器
//	public static final String web3jurl = "https://ropsten.infura.io/dY2Ew9eYZspcnsYp8oL1";//Test Ethereum Network (Ropsten)
//	public static final String mainprivateKey = "8ce65f05643a0cb00b1b26ee29b85124003526a2e3693fdb1f074ba2d3066b7c";
//	public static final String sdccontractAdd = "0x787b1aa8e62ff2a45b61d9c975ce4f01e8292f30";
	
	
//	public static String mainAcc;
//	@Value("${sdc.mainAcc}")
//	public  void setMainAcc(String _mainAcc) {
//		SdcJob.mainAcc = _mainAcc;
//	}
//	public static String sdclockcontractAdd;
//	@Value("${sdc.sdclockcontractAdd}")
//	public  void setSdclockcontractAdd(String _sdclockcontractAdd) {
//		SdcJob.sdclockcontractAdd = _sdclockcontractAdd;
//	}
//	private static String statusUrlPrefix;
//	@Value("${sdc.statusUrlPrefix}")
//	public  void setStatusUrlPrefix(String _statusUrlPrefix) {
//		SdcJob.statusUrlPrefix = _statusUrlPrefix;
//	}
//	private static String transUrlPrefix;
//	@Value("${sdc.transUrlPrefix}")
//	public  void setTransUrlPrefix(String _transUrlPrefix) {
//		SdcJob.transUrlPrefix = _transUrlPrefix;
//	}
//	private static String recordUrlPrefix;
//	@Value("${sdc.recordUrlPrefix}")
//	public  void setRecordUrlPrefix(String _recordUrlPrefix) {
//		SdcJob.recordUrlPrefix = _recordUrlPrefix;
//	}
//	public static String web3jurl;
//	@Value("${sdc.web3jurl}")
//	public  void setWeb3jurl(String _web3jurl) {
//		SdcJob.web3jurl = _web3jurl;
//	}
//	public static String mainprivateKey;
//	@Value("${sdc.mainprivateKey}")
//	public  void setMainprivateKey(String _mainprivateKey) {
//		SdcJob.mainprivateKey = _mainprivateKey;
//	}
//	public static String sdccontractAdd;
//	@Value("${sdc.sdccontractAdd}")
//	public  void setSdccontractAdd(String _sdccontractAdd) {
//		SdcJob.sdccontractAdd = _sdccontractAdd;
//	}

	/**
	 * job1：查询交易状态为不成功的交易记录，通过以太坊api验证该条记录，修改记录状态
	 */
	@SuppressWarnings("rawtypes")
	@Scheduled(cron = "*/10 * * * * ?")
	public void job1(){
		System.out.println("-----------job1开始-----------");
		//查询交易状态为不成功且不是募集回执的记录集
		List<Map> txInfoList = ML.sdcTxRecordMapper.queryTxstatusNot0TxInfo();
		if(txInfoList!=null&&!txInfoList.isEmpty()){
			for(Map map : txInfoList){
				String txhash = map.get("txhash").toString();
				Map<String,String> queryMap = this.queryTxInfo(txhash);
				if(!queryMap.isEmpty()&&"0".equals(queryMap.get("txStatus"))){
					String from = map.get("from").toString().toLowerCase();//未校验的from
					String to = map.get("to").toString().toLowerCase();//未校验的to
					String currency = map.get("currency").toString();//未校验的币种
					//String quantity = map.get("quantity").toString();//未校验的交易数量（这个暂时校验不了）
					String from1 = queryMap.get("from").toString().toLowerCase();//校验的from
					String to1 = queryMap.get("to").toString().toLowerCase();//校验的to
					String currency1 = queryMap.get("currency").toString();//校验的币种
					if(!from.equals(from1)||!currency.equals(currency1)){//to也不校验了
						queryMap.put("txStatus", "2");//上传参数和校验参数不相同，修改记录的交易状态为异常
					}
					//修改txStatus及from1、to1、currency1、quantity1的值
					ML.sdcTxRecordMapper.updateTxstatusAndOtherTxInfo(queryMap);
				}
			}
		}
		System.out.println("-----------job1结束-----------");
	}
	
//	@Scheduled(cron = "*/30 * * * * ?")
	public void job2(){
		job1();
	}
	
	/**
	 * 调用以太坊api查询交易信息，包括交易状态、from、to、quantity、currency
	 * @param txhash
	 * @return
	 */
	private static Map<String,String> queryTxInfo(String txhash){
		String statusUrl = statusUrlPrefix + "&txhash="+txhash+"&apikey="+apikey;
		System.out.println("statusUrl--->"+statusUrl);
		Map<String,String> txInfo = new HashMap<String,String>();
		String txStatus = "";
		String from = "";
		String to = "";
		String value = "";//交易数量（以太币）
		String quantity = "";//交易数量（代币）
		String currency = "";//币种
		txInfo = queryTxStatus(txhash);
		txStatus = txInfo.get("txStatus");
		if("0".equals(txStatus)){
			String transUrl = transUrlPrefix + "&txhash="+txhash+"&apikey="+apikey;
			String transRet = HttpsClientUtil.doPost(transUrl, "", "utf-8");
			if(StringUtils.isNotBlank(transRet)){
				System.out.println("transRet---->"+transRet);
				JSONObject jsonTrans = JSONObject.fromObject(transRet);
				if(jsonTrans.containsKey("result")&&jsonTrans.getString("result")!="null"){
					String resultTrans = jsonTrans.getString("result");
					JSONObject resultTransObj = JSONObject.fromObject(resultTrans);
					from = resultTransObj.getString("from");
					to = resultTransObj.getString("to");
					value = resultTransObj.getString("value");
					if("".equals(value)||"0x0".equals(value)){//表示ETH交易为零，那么就是代币的交易
						quantity = "0";//代币的交易量暂时获取不到
						currency = "SDC";
					}else{
						BigDecimal bigDec = BigDecimal.valueOf(new BigInteger(value.substring(2), 16).longValue()/Math.pow(10, 18));
						quantity = bigDec.toString();
						currency = "ETH";
					}
					
					txInfo.put("txhash", txhash);
					txInfo.put("txStatus", txStatus);
					txInfo.put("from", from);
					txInfo.put("to", to);
					txInfo.put("quantity", quantity);
					txInfo.put("currency", currency);
				}
			}
		}
		return txInfo;
	}
	
	/**
	 * 调用以太坊api查询交易状态
	 * @param txhash
	 * @return
	 */
	private static Map<String,String> queryTxStatus(String txhash){
		String statusUrl = statusUrlPrefix + "&txhash="+txhash+"&apikey="+apikey;
		Map<String,String> txstatus = new HashMap<String,String>();
		String txStatus = "";
		String statusRet = HttpsClientUtil.doPost(statusUrl, txstatus, "utf-8");
		if(StringUtils.isNotBlank(statusRet)){
			System.out.println("statusRet--->"+statusRet);
			JSONObject jsonStatus = JSONObject.fromObject(statusRet);
			if(jsonStatus.containsKey("error")||jsonStatus.getString("result").equals("null")){//查询参数异常
				//{"jsonrpc":"2.0","id":1,"result":null}
				txStatus = "1";
			}else if(jsonStatus.containsKey("result")&&jsonStatus.getString("result")!=null){
				String resultStatus = jsonStatus.getString("result");
				JSONObject resultStatusObj = JSONObject.fromObject(resultStatus);
				if(resultStatusObj.getString("status").equals("0x1")||resultStatusObj.getString("status").equals("1")){
					txStatus = "0";//成功
				}else{
					txStatus = "1";//失败
				}
			}else{
				txStatus = "1";//api未返回结果
			}
		}else{
			txStatus = "1";//连接超时
		}
		txstatus.put("txhash", txhash);
		txstatus.put("txStatus", txStatus);

		return txstatus;
	}
	/**
	 * 调用以太坊api查询当前的gasPrice的推荐值
	 * @return
	 */
	private static BigInteger getCurrentGasPrice(){
		//{"jsonrpc":"2.0","id":73,"result":"0x2540be400"}
		String gasPriceJson = HttpsClientUtil.doPost(gasPrice16Url, "", "utf-8");
		BigInteger gasPrice = new BigInteger(gasPriceDefault);
		if(StringUtils.isNotBlank(gasPriceJson)){
			JSONObject gpjson = JSONObject.fromObject(gasPriceJson);
			if(gpjson.containsKey("result")&&"0x".equals(gpjson.getString("result").substring(0,2))){
				String str = gpjson.getString("result").substring(2);
				gasPrice = BigInteger.valueOf(Long.parseLong(str,16));
			}
		}
		System.out.println("推荐的gasPrice=>"+gasPrice);
		return gasPrice;
	}
	
	public String convert16To10(String s){
	    return String.valueOf(Integer.parseInt(s.replaceAll("^0[x|X]", ""), 16));
	}
	
	/**
	 * jobA：查询主账户交易记录表与募集交易记录表hash相同，且交易状态为成功，且交易类型为募集，且募集状态为用户已转账的记录
	 * java调用geth给从公司主账户向锁仓账户转sdc并返回txhash
	 * 转账之后添加募集回执记录：其中交易状态为不成功，募集状态为2：已向锁仓账户转账
	 * 修改募集表已募集数量、剩余数量
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Scheduled(cron = "*/20 * * * * ?")
	public void jobA(){
		System.out.println("-----------jobA开始-----------");
		List<Map> jobAList = ML.sdcTxRecordMapper.queryjobATxInfo();
		if(jobAList!=null&&!jobAList.isEmpty()){
			for(Map aMap : jobAList){
				String txhash_user = aMap.get("txhash").toString();
				String from = aMap.get("from").toString();
				String targetaddress = aMap.get("targetaddress").toString();
				targetaddress = StringUtils.isNotBlank(targetaddress)?targetaddress:from;
//				String to = aMap.get("to").toString();
				String currency = aMap.get("currency").toString();
				String ratioid = aMap.get("ratioid").toString();
				String sdcquantity = aMap.get("sdcquantity").toString();
				String unlocktime = aMap.get("unlocktime_second").toString();
//				BigDecimal bigDec = new BigDecimal(sdcquantity).multiply(new BigDecimal(Math.pow(10, 18)));
//				String value = String.valueOf(new BigInteger(bigDec.toString()));
				//根据ratioid查询兑换比例
//				Map<String,String> ratioMap = ML.sdcTxRecordMapper.queryRatioById(ratioid);
//				String value = "0";//兑换代币的数量
//				if(ratioMap!=null&&!ratioMap.isEmpty()){
//					String quantity = aMap.get("quantity").toString();
//					String ratioVal = ratioMap.get("ratioVal").toString();
//					value = String.valueOf(Double.valueOf(new BigDecimal(quantity).doubleValue()*Integer.parseInt(ratioVal)).intValue());
//				}
				//修改记录募集状态为2：已向用户转账，当向用转账失败时再修改回1：用户已转账
				ML.sdcTxRecordMapper.updateCollectstatusTxInfo(txhash_user);
				
				//修改募集表已募集数量、剩余数量
				ML.sdcTxRecordMapper.updateSdcCollectInfo(aMap.get("quantity").toString());
				String txhash_sys = null;
				try {
					//调用java从公司主账户向锁合约户转sdc并返回txhash
					BigInteger gasPrice = getCurrentGasPrice();
					txhash_sys = ColdWallet.toSDC(mainAcc,sdclockcontractAdd,sdcquantity,sdcquantity,gasPrice);
					System.out.println("txhash_sys===="+txhash_sys);
					if(StringUtils.isNotBlank(txhash_sys)){
						Map<String,String> insertMap = new HashMap<String,String>();
						insertMap.put("txhash", txhash_sys);
						insertMap.put("from", mainAcc);
						insertMap.put("to", from);//用户地址
						insertMap.put("currency", "SDC");//币种SDC
						insertMap.put("quantity", sdcquantity);//兑换SDC数量
						insertMap.put("txstatus", "1");//默认插入失败
						insertMap.put("txtype", "3");//3：募集回执
						insertMap.put("collectstatus", "2");//2：已向用户转账
						insertMap.put("userhash", txhash_user);//用户转ETH的txhash
						insertMap.put("targetaddress", targetaddress);//募集目标地址
						ML.sdcTxRecordMapper.insertCollectRecord(insertMap);
					}else{
						System.out.println("调用转代币后返回的hash值为空，但也没进入到catch中去。txhash_sys==="+txhash_sys);
						//如果调用报错，修改募集记录为调用前，等待下次调用
						ML.sdcTxRecordMapper.cancelUpdateCollectstatusTxInfo(txhash_user);
						//修改募集表已募集数量、剩余数量
						ML.sdcTxRecordMapper.subSdcCollectInfo(aMap.get("quantity").toString());
					}
				} catch (Exception e) {
					//如果调用报错，修改募集记录为调用前，等待下次调用
					ML.sdcTxRecordMapper.cancelUpdateCollectstatusTxInfo(txhash_user);
					//修改募集表已募集数量、剩余数量
					ML.sdcTxRecordMapper.subSdcCollectInfo(aMap.get("quantity").toString());
					e.printStackTrace();
				}
				
			}
		}
		System.out.println("-----------jobA结束-----------");
	}
	/**
	 * jobB：查询交易状态为不成功且交易类型为募集回执且募集状态为已向用户转账的记录。
	 * 调用以太坊api查询该记录的交易状态，当交易状态为成功时
	 * 修改该条募集回执记录的交易状态
	 * 向trans_collect_sdcsend表中插入记录，记录SDC分期锁仓交易
	 */
	@SuppressWarnings("rawtypes")
	@Scheduled(cron = "*/12 * * * * ?")
	public void jobB(){
		System.out.println("-----------jobB开始-----------");
		//查询交易状态为不成功且交易类型为募集回执且募集状态为已向用户转账的记录集
		List<Map> jobBList = ML.sdcTxRecordMapper.queryjobBTxInfo();
		if(jobBList!=null&&!jobBList.isEmpty()){
			for(Map bMap : jobBList){
				String txhash = bMap.get("txhash").toString();
				Map<String,String> queryMap = this.queryTxStatus(txhash);
				if("0".equals(queryMap.get("txStatus"))){//如果募集回执交易成功
					//修改募集回执的txStatus
					ML.sdcTxRecordMapper.updateTxstatusTxInfo(queryMap);
					
					String userAcc = bMap.get("userAcc").toString();
					Long unlocktime = Long.valueOf(bMap.get("unlocktime_s").toString());
					String sdcquantity = bMap.get("sdcquantity").toString();
//					BigDecimal bigDec = new BigDecimal(sdcquantity).multiply(new BigDecimal(Math.pow(10, 18)));
//					String value = String.valueOf(new BigInteger(bigDec.toString()));
					//锁仓分期现写死6期。第一期锁仓四个月，以后五期每期加一个月，锁仓金额第一次为传过来的50%，以后每期为10%
					//首次锁仓释放时间为传过来的值
//					try {
					String value = "";
					Long locktime = 0L;
					Integer[] spiltValue = spiltValuefun(sdcquantity);
					Long[] spiltlocktime = spiltlocktimefun(unlocktime);
					List<Map> insertList = new ArrayList<Map>();
					for(int i=0;i<6;i++){
						value = String.valueOf(spiltValue[i]);
						System.out.println("value--->"+value+"--->");
						locktime = spiltlocktime[i];
						Map map = new HashMap();
						map.put("lockNum", i+1);
						map.put("receipthash", txhash);//募集回执hash
						map.put("useraddress", userAcc);
						map.put("locktime", locktime);
						map.put("value", value);
//						map.put("proStatus", "0");//未处理
//						map.put("txStatus", "3");//未核验
						insertList.add(map);
						//使用web3j调用锁仓合约记录给谁、多少钱、解锁时间
//							String lockhash = ColdWallet.toSDCLocktime(mainAcc, userAcc, locktime, value, value);
						//调用锁仓合约后返回的lockhash值要存到募集回执的记录里
//							queryMap.put("lockhash", lockhash);
//							ML.sdcTxRecordMapper.saveLockhashByTxhash(queryMap);
					}
					ML.sdcTxRecordMapper.insertTransSdctx(insertList);
//					} catch (Exception e) {
//						//修改募集回执的txStatus为失败
//						queryMap.put("txStatus", "1");
//						ML.sdcTxRecordMapper.updateTxstatusTxInfo(queryMap);
//						e.printStackTrace();
//					}
				}
				//如果交易状态不成功，修改用户向平台转ETH记录的募集状态为1:用户已转账
//				else{
//					String userhash = bMap.get("userhash").toString();
//					ML.sdcTxRecordMapper.updateCollectstatusTxInfoByUserhash(userhash);
//				}
			}
		}
		System.out.println("-----------jobB结束-----------");
	}
	/**
	 * jobC：通过api查询主账户从哪个块到哪个块中的交易记录，插入到trans_main表中
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Scheduled(cron = "*/30 * * * * ?")
	public void jobC(){
		System.out.println("-----------jobC开始-----------");
		//查询主账户交易表中最大的块设置为开始查询的块
		String startblock = ML.sdcTxRecordMapper.getMaxBlock();
//		startblock = "2000000";
		
		String recordUrl = recordUrlPrefix + "&address="+mainAcc+"&startblock="+startblock+"&endblock=99999999&sort=asc&apikey="+apikey;
		String recordRet = HttpsClientUtil.doPost(recordUrl, "", "utf-8");
		List<Map> insertMap = new ArrayList<Map>();
		if(StringUtils.isNotBlank(recordRet)){
			System.out.println("recordRet--->"+recordRet);
			JSONObject jsonRecord = JSONObject.fromObject(recordRet);
			String resultRecord = jsonRecord.getString("result");
			JSONArray recordArr = JSONArray.fromObject(resultRecord);
			for(Object record : recordArr){
				JSONObject rec = JSONObject.fromObject(record);
				Map map = new HashMap();
				String hash = rec.get("hash").toString();
				Map exist = ML.sdcTxRecordMapper.isExistTxRecordByHash(hash);
				if(exist==null||exist.isEmpty()){
					map.put("blockNumber", rec.get("blockNumber").toString());
					map.put("timeStamp", rec.get("timeStamp").toString());
					map.put("hash", rec.get("hash").toString());
					map.put("nonce", rec.get("nonce").toString());
					map.put("blockHash", rec.get("blockHash").toString());
					map.put("transactionIndex", rec.get("transactionIndex").toString());
					map.put("from", rec.get("from").toString());
					map.put("to", rec.get("to").toString());
					map.put("value", rec.get("value").toString());
					map.put("gas", rec.get("gas").toString());
					map.put("gasPrice", rec.get("gasPrice").toString());
					map.put("isError", rec.get("isError").toString());
					map.put("txreceipt_status", rec.get("txreceipt_status").toString());
					map.put("input", rec.get("input").toString());
					map.put("contractAddress", rec.get("contractAddress").toString());
					map.put("cumulativeGasUsed", rec.get("cumulativeGasUsed").toString());
					map.put("gasUsed", rec.get("gasUsed").toString());
					map.put("confirmations", rec.get("confirmations").toString());
					insertMap.add(map);
				}
			}
			if(!insertMap.isEmpty()){
				ML.sdcTxRecordMapper.insertTransMain(insertMap);
			}
		}
		System.out.println("-----------jobC结束-----------");
		
	}
	/**
	 * 扫描trans_collect_sdcsend表中处理状态为未处理记录集，
	 * 使用web3j调用锁仓合约记录给谁、多少钱、解锁时间
	 * 返回的交易hash记录到该条记录中，并记录交易时间、修改处理状态为已处理
	 */
	@Scheduled(cron = "*/15 * * * * ?")
	public void jobD(){
		System.out.println("-----------jobD开始-----------");
		List<Map> jobDList = ML.sdcTxRecordMapper.queryNoTransaction();
		if(jobDList!=null&&!jobDList.isEmpty()){
			for(Map dMap : jobDList){
				String useraddress = dMap.get("useraddress").toString();
				String locktime = dMap.get("locktime").toString();
				String value = dMap.get("value").toString();
				//使用web3j调用锁仓合约记录给谁、多少钱、解锁时间
				try {
					BigInteger gasPrice = getCurrentGasPrice();
					String lockhash = ColdWallet.toSDCLocktime(mainAcc, useraddress, Long.valueOf(locktime), value, value,gasPrice);
					lockhash = lockhash==null||"".equals(lockhash)||"null".equals(lockhash)?"":lockhash;
					if(StringUtils.isNotBlank(lockhash)){
						//返回的交易hash记录到该条记录中，并记录交易时间、修改处理状态为已处理
						Map map = new HashMap();
						map.put("id", dMap.get("id").toString());
						map.put("lockhash", lockhash);
						ML.sdcTxRecordMapper.updateNoTransaction(map);
					}
				}  catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("-----------jobD结束-----------");
	}
	
	/**
	 * 扫描trans_collect_sdcsend表中处理状态为已处理并交易状态不为成功并lockhash不为空记录集
	 * 调用以太坊api查询交易状态后更新该记录的交易状态
	 */
	@Scheduled(cron = "*/18 * * * * ?")
	public void jobE(){
		System.out.println("-----------jobE开始-----------");
		List<Map> jobEList = ML.sdcTxRecordMapper.queryAlreadyTransaction();
		if(jobEList!=null&&!jobEList.isEmpty()){
			for(Map eMap : jobEList){
				String lockhash = eMap.get("lockhash").toString();
				Map<String,String> queryMap = this.queryTxStatus(lockhash);
				String txStatus = queryMap.get("txStatus");//锁仓交易状态
				Map map = new HashMap();
				map.put("id", eMap.get("id").toString());
				map.put("txStatus", txStatus);
				ML.sdcTxRecordMapper.updateAlreadyTransaction(map);
			}
		}
		System.out.println("-----------jobE结束-----------");
	}
	
	
	
	public Long[] spiltlocktimefun(Long unlocktime){
		Long[] longArr = new Long[6];
		for(int a=0;a<6;a++){
			if(a==0){
				longArr[a] = unlocktime;
			}else{
				longArr[a] = longArr[a-1] + 30*24*60*60;
			}
		}
		return longArr;
	}
	
	public Integer[] spiltValuefun(String sdcquantity){
		Integer[] intArr = new Integer[6];
		int[] init = new int[] {50,10,10,10,10,10};
		Integer temp=0;
		Integer value=Integer.valueOf(sdcquantity);
		for(int i=0;i<init.length;i++){
			if(init[i]==50){
				intArr[i] = Integer.valueOf(String.valueOf(Math.round(value*0.5)));
				temp=temp+Integer.valueOf(String.valueOf(Math.round(value*0.5)));
			}else if(init[i]==10){
				if(i==5){
					intArr[i] = (value-temp);
					break;
				}
				intArr[i] =  Integer.valueOf(String.valueOf(Math.round(value*0.1)));
				temp=temp+Integer.valueOf(String.valueOf(Math.round(value*0.1)));
			}
		}
		return intArr;
	}
	public static void main(String[] args) {
//		SdcJob sj = new SdcJob();
//		String hash = "0x92012892de8729363a1ccb0be7da2801cb1fa5272096a011a733dd6e80e9ae1e";
////		Map<String,String> queryMap = sj.queryTxStatus(hash);
//		Map<String,String> queryMap = sj.queryTxInfo(hash);
//		System.out.println(queryMap);
		getCurrentGasPrice();
//		String txhash = "0x8cb97d68594ab4a050c3271e488c6528d2807be21564207e6cb5765802d45940";
//		Map<String,String> queryMap = queryTxInfo(txhash);
//		System.out.println(queryMap.toString());
	}
}
