package com.irebane.model;

public enum ARC {
	SUCCESS("0000", "成功"),
	PARA_IS_ERROR("0001","参数不能为空或不完整" ),
	ALREADY_EXIST("0001","该条记录已经存在" ),
	DATA_IS_LOCK("0001","信息已经锁定，禁止更改！" ),
	NO_SUBSUMMARY_POWER("0001","只有经理和主管可以提交日总结" ),
	NO_EXIST_OPENID("0001","不存在的openid" ),
	NO_SETMT_POWER("0001","只有经理可以设置月目标" ),
	NO_SETMTPRO_POWER("0001","只有经理可以设置月目标单品" ),
	
	DANHAO_IS_EXIST("0001","单号已存在" ),
	
	
//	NO_RESULT("0001","没有信息" ),
	MT_NOT_INIT("0001","请先配置月目标！" ),
	NO_POWER("0002","商户没有产品授权" ),
	LOGIN_TIMEOUT("0003","登陆超时" ),
	LOGIN_NULL("0004","尚未登录" ),
	POWER_NULL("0005","没有权限" ),
	OTHER("9999","其他错误");
	
	// 成员变量
	private String errorCode;
	private String errorMsg;
	
	// jsonKey
	public static String codeKey="errorCode";
	public static String codeMsg="errorMsg";
	public static String codeObj="dataObj";
	public static String codeArr="dataArr";

	// 构造方法
	private ARC( String errorCode,String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


}
