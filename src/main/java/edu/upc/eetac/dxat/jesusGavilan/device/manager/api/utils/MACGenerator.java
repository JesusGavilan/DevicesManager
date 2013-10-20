package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MACGenerator {
	public static String MD5toHex(String s) {
		s =s.concat("dxat");
		StringBuilder sb = null;
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] digest = md5.digest(s.getBytes());
			sb = new StringBuilder(digest.length * 2);
			for(int i=0; i < digest.length;i++){
				sb.append(Character.forDigit((digest[i] & 0xf0) >> 4,16));
				sb.append(Character.forDigit(digest[i] & 0x0f, 16));
			}
		} catch (NoSuchAlgorithmException e) {
			// this win't happen, Java has MD5
			e.printStackTrace();
		}
		return sb.toString().toUpperCase();
	}
	
	public static String create(String deviceInventoryId){
		String tmp = MD5toHex(deviceInventoryId);
		String result = "00:16:3E";
		while (result.length()<17){
			result = result.concat(":");
			result = result.concat(tmp.substring(0,2));
			tmp = tmp.substring(2);
		}
		return result;
	}


}
