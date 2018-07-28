package com.irebane;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irebane.sdc.SdcTxRecordMapper;
import com.irebane.user.UserMapper;

@Component
public class ML {
	public static UserMapper userMapper;
	
	public static SdcTxRecordMapper sdcTxRecordMapper;
	

	@Autowired
	public UserMapper userMapper2;
	
	@Autowired
	public SdcTxRecordMapper sdcTxRecordMapper2;
	
	@Autowired
	@PostConstruct
	public void beforeInit() {
		userMapper = userMapper2;
		sdcTxRecordMapper = sdcTxRecordMapper2;
	}
}
