package com.irebane.yt.organisation.name;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ChainId;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.irebane.job.SdcJob;

public class ColdWallet {
	
	private static Map<String,BigInteger> nonceMap= new HashMap<String, BigInteger>(); 
	
	private static Web3j web3j = Web3j.build(new HttpService(SdcJob.web3jurl));
	
	static{
		nonceMap.put("nonce",  BigInteger.valueOf(0));
	}
	
	public static void main(String[] args) throws Exception {
		//合约代币转帐
		/*String hash=toSDC("0xe7DbCcA8183cb7d67bCFb3DA687Ce7325779c02f","0x6bFC4c2a619Ef139a72C6FcF5e86a443166695E9","1","1");
		System.out.println(hash);*/
		
		
		
/*		//web3j 初始化
		Web3j web3j = Web3j.build(new HttpService("http://47.104.22.35:8545/"));
		BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(3), Convert.Unit.GWEI).toBigInteger();
		BigInteger gasLimit = BigInteger.valueOf(60000);
		Credentials credentials = Credentials.create("8ce65f05643a0cb00b1b26ee29b85124003526a2e3693fdb1f074ba2d3066b7c");
		
		//初始化合约
		SDC_sol_locksdc2 h=SDC_sol_locksdc2.load("0x8ecd747930a2cc74d110145a6b2f16d598edf40a", web3j, credentials, gasPrice, gasLimit);
		
		//查询转账人地址
		String toAddress="0x89618c659bb408f3e3d0ca02d46d527a57db79f8";
		//调用合约查询用户锁定代币函数
		System.out.println(h.querySdcForUser(toAddress).send()); 
		*/
		
		
		//调用合约转账人代币锁定时间
		/*for(int i=0;i<=5;i++){
			String hash=toSDCLocktime("0xe7DbCcA8183cb7d67bCFb3DA687Ce7325779c02f","0x6bFC4c2a619Ef139a72C6FcF5e86a443166695E9",1543680000,"1","1");
			//返回交易hash
			System.out.println(hash);
		}*/
//		for(int i=0;i<=5;i++){
//			BigInteger	nonce = ColdWallet.getTransactionNonce("0xAD7B07322A7e144a0a99e027F9cb173006668D17");
//			System.out.println("当前："+nonce);
//			String hash=toSDC("0xAD7B07322A7e144a0a99e027F9cb173006668D17","0x89618c659bb408f3e3d0ca02d46d527a57db79f8","10","10");
//			System.out.println(hash);
//		}
		System.out.println(SdcJob.web3jurl);
		BigInteger nonce = ColdWallet.getTransactionNonce("0x445d7bb16fe3aada6a89673aa15919306af07c9f");
		System.out.println(nonce);
		
	}
	
	public static String toSDC(String fromAddress, String toAddress, String amount, String decimals,BigInteger gasPrice) {
		BigInteger bi1 = new BigInteger("1");
		Web3j web3j = Web3j.build(new HttpService(SdcJob.web3jurl));

		BigInteger nonce = ColdWallet.getNewNonce(fromAddress);
		
//		BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(7), Convert.Unit.GWEI).toBigInteger();
		BigInteger gasLimit = BigInteger.valueOf(60000);
		BigInteger value = BigInteger.ZERO;
		//token转账参数
		String methodName = "transfer";
		List<Type> inputParameters = new ArrayList<Type>();
		List<TypeReference<?>> outputParameters = new ArrayList<TypeReference<?>>();
		Address tAddress = new Address(toAddress);
		BigInteger value1 = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();
		Uint256 tokenValue = new Uint256(value1);
		inputParameters.add(tAddress);
		inputParameters.add(tokenValue);
		TypeReference<Bool> typeReference = new TypeReference<Bool>() {
		};
		outputParameters.add(typeReference);
		Function function = new Function(methodName, inputParameters, outputParameters);
		String data = FunctionEncoder.encode(function);

		byte chainId = ChainId.NONE;
		String signedData;
		try {
			signedData = ColdWallet.signTransaction(nonce, gasPrice, gasLimit, SdcJob.sdccontractAdd, value, data, chainId, SdcJob.mainprivateKey);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				String txHash=ethSendTransaction.getTransactionHash();
				if(StringUtils.isNotBlank(txHash)){//如果返回交易hash不为空map中的nonce才加1
					//nonce 加一
					nonceMap.put("nonce", nonce.add(bi1));
				}
				return txHash;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	/**
	 * 签名交易
	 */
	public static String signTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to,
										 BigInteger value, String data, byte chainId, String privateKey) throws IOException {
		byte[] signedMessage;
		RawTransaction rawTransaction = RawTransaction.createTransaction(
				nonce,
				gasPrice,
				gasLimit,
				to,
				value,
				data);

		if (privateKey.startsWith("0x")) {
			privateKey = privateKey.substring(2);
		}
		ECKeyPair ecKeyPair = ECKeyPair.create(new BigInteger(privateKey, 16));
		Credentials credentials = Credentials.create(ecKeyPair);

		if (chainId > ChainId.NONE) {
			signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, credentials);
		} else {
			signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		}

		String hexValue = Numeric.toHexString(signedMessage);
		return hexValue;
	}
	
	public static String toSDCLocktime(String fromAddress, String toAddress,long locktime, String amount, String decimals,BigInteger GAS_PRICE) throws Exception {
		System.out.println("fromAddress="+fromAddress+"toAddress="+toAddress+"locktime="+locktime+"amount="+amount+"decimals="+decimals);
		BigInteger bi1 = new BigInteger("1");
		Web3j web3j = Web3j.build(new HttpService(SdcJob.web3jurl));
		
		BigInteger nonce = ColdWallet.getNewNonce(fromAddress);
		
//		BigInteger GAS_PRICE = Convert.toWei(BigDecimal.valueOf(7), Convert.Unit.GWEI).toBigInteger();
		BigInteger GAS_LIMIT = BigInteger.valueOf(600000);
		BigInteger value = BigInteger.ZERO;
		//token转账参数
		
		BigInteger value1 = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();
		
		 final Function function = new Function(
	                "inSdcForAdmin", 
	                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(toAddress), 
	                new org.web3j.abi.datatypes.generated.Uint256(value1), 
	                new org.web3j.abi.datatypes.generated.Uint256(BigInteger.valueOf(locktime))), 
	                Collections.<TypeReference<?>>emptyList());
		String data = FunctionEncoder.encode(function);

		byte chainId = ChainId.NONE;
		String signedData;
		try {
			signedData = ColdWallet.signTransaction(nonce, GAS_PRICE, GAS_LIMIT, SdcJob.sdclockcontractAdd, value, data, chainId, SdcJob.mainprivateKey);
			System.out.println(signedData);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				String txhash = ethSendTransaction.getTransactionHash();
				System.out.println("txhash1---->"+txhash);
				if(StringUtils.isNotBlank(txhash)){//如果返回交易hash不为空map中的nonce才加1
					//nonce 加一
					nonceMap.put("nonce", nonce.add(bi1));
				}
				return txhash;
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("txhash2---->null");
			return null;
		}
		System.out.println("txhash3---->null");
		return null;
	}

	/**
	 * 当 static 初始化 nonce 等于 0 时 查询 geth 节点 nonce 值
	 * 
	 * @param forAddress
	 * @return
	 */
	public static BigInteger  getNewNonce(String forAddress){
		BigInteger nonce = null;
		//获取geth 节点 nonce 值 
		BigInteger currentNonce=ColdWallet.getTransactionNonce(forAddress);
		System.out.println("获取 geth nonce 值====》》:"+currentNonce);
		//获取 map nonce 值
		BigInteger tempNonce=nonceMap.get("nonce");
		
		System.out.println("获取 map 值====》》:"+tempNonce);
		if(Long.parseLong(tempNonce.toString())==0){
			nonce = currentNonce;
		}else if(tempNonce.compareTo(currentNonce)<0){
			nonce=currentNonce;
		}else {
			nonce=nonceMap.get("nonce");
		}
		System.out.println("发送的 nonce 值====》》:"+nonce);
		return nonce;
	}
	
	/**
	 * 获取账号交易次数 nonce
	 *
	 * @param address 钱包地址
	 * @return nonce
	 */
	private static BigInteger getTransactionNonce(String address) {
		BigInteger nonce = BigInteger.ZERO;
		try {
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(address, DefaultBlockParameterName.PENDING).send();
			nonce = ethGetTransactionCount.getTransactionCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nonce;
	}
}
