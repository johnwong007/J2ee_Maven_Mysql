package com.irebane;

import java.math.BigInteger;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String money = "00000000000000000000000000000000000000000000000ad78ebc5ac620000";
		BigInteger bi = new BigInteger(money, 16);
		bi = bi.divide(new BigInteger("1000000000000000000"));
		money = String.valueOf(bi);
		System.out.println(money);
	}

}
