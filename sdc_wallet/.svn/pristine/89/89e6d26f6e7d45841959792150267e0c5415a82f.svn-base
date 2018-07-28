package com.irebane.sdc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface SdcTxRecordMapper {

	Map getSdcTxRecordByTxHash(String txhash);
	
	Map getSdcTxRecordByTxHashAndTxtype(String txhash);
	
	int addToCheckTxRecordByTransfer(Map map);
	
	int addToCheckTxRecordByCollect(@Param("map")Map map);
	
	List<Map> queryTxstatusNot0TxInfo();
	
	int updateTxstatusAndOtherTxInfo(Map map);
	
	List<Map> queryjobATxInfo();
	
	Map queryRatioById(String ratioid);
	
	int updateCollectstatusTxInfo(String txhash);
	
	int cancelUpdateCollectstatusTxInfo(String txhash);
	
	int insertCollectRecord(Map map);
	
	List<Map> queryjobBTxInfo();
	
	int updateTxstatusTxInfo(Map map);
	
	int saveLockhashByTxhash(Map map);
	
	List<Map> queryTransferRecord(Map map);
	
	Map getCurrentCollectInfo();
	
	List<Map> queryCollectRecord(String invitationcode);
	
	int updateSdcCollectInfo(String quantity);
	
	int subSdcCollectInfo(String quantity);
	
	List<Map> queryCollectRecordForUser(String accAddress);
	
	List<Map> queryTransferRecordForUser(Map map);
	
	int updateCollectstatusTxInfoByUserhash(String userhash);
	
	int addchn(@Param("map")Map map);
	
	int updatechn(@Param("map")Map map);
	
	int delchn(Map map);
	
	List<Map> querychnList();
	
	List<Map> queryTransMainList(String mainAcc);
	
	List<Map> querySdcTransferRecordForAdmin();
	
	List<Map> queryEthTransferRecordForAdmin();
	
	String getMaxBlock();
	
	Map isExistTxRecordByHash(String hash);
	
	int insertTransMain(@Param("list")List<Map> list);
	
	int setTransMainNotCollect(String ids);
	
	int setTransMainCollectstatus(String ids);
	
	List<Map> getTransMainByids(String ids);
	
	String getRatioIsUse();
	
	int setSdcquantityAndUnlockTimeById(Map map);
	
	List<Map> querySdcRecordForUser(String accAddress);
	
	Map getIsuseRatio();
	
	int updateIsuseRatio(Map map);
	
	int insertTransSdctx(@Param("list")List<Map> list);
	
	List<Map> queryNoTransaction();
	
	int updateNoTransaction(Map map);
	
	List<Map> queryAlreadyTransaction();
	
	int updateAlreadyTransaction(Map map);
	
	List<Map> queryLockContractTransaction(String txhash);
}
