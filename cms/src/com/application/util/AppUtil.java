package com.application.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.cms.common.master.bean.CommonMasterDO;

public class AppUtil {

	public static int getNullToInteger(String intStr ) {
		return getNullToInteger(intStr, 0);
	}

	public static int getNullToInteger(String strVal, int defaultVal ) {
		int retVal=defaultVal;
		try {
			if(strVal!=null && !strVal.isEmpty()) { retVal=Integer.parseInt(strVal.trim()); }
		} catch (Exception e) { System.out.println("Error While parse Integer"); }
		return retVal;
	}

	public static String getNullToEmpty(String str) {
		return getNullToEmpty(str, "");
	}
	public static String getNullToEmpty(String str, String defaultVal) {
		return (str==null || str.equalsIgnoreCase("null") || str.isEmpty())?defaultVal:str;
	}

	public static double getNullToDouble(String strVal) {
		return getNullToDouble(strVal, 0.0);

	}
	
	public static double getNullToDouble(String strVal, double defaultVal) {
		double retVal=defaultVal;
		try {
			if(strVal!=null && !strVal.isEmpty()) { retVal=Double.parseDouble(strVal.trim()); }
		} catch (Exception e) { System.out.println("Error While parse Double"); }
		return retVal;
	}
	
	public static String formOption(Map<String, String> valueMap, String selVal ) {
		StringBuffer option=new StringBuffer();
		if(valueMap==null) { valueMap=new HashMap<String, String>(); }
		selVal=AppUtil.getNullToEmpty(selVal);
		for (Entry<String, String> entry : valueMap.entrySet()) {
			String key=AppUtil.getNullToEmpty( entry.getKey() ), sel="";
			if(selVal.equalsIgnoreCase(key) ) { sel="selected='selected' "; }
			option.append("<option value='"+key+"' "+sel+"> "+entry.getValue()+" </option>");
		}
		return option.toString();
	}
	
	public static String dbToJavCase(String strVal, boolean needFirstCap) {
		strVal=getNullToEmpty(strVal).replace("_", " ");
		String retVal="";
		String[] strValArr=strVal.split(" ");
		for (String str : strValArr) {
			str=getNullToEmpty(str);
			if(retVal.isEmpty() && needFirstCap==false) retVal=str;
			else retVal+=(""+str.charAt(0)).toUpperCase()+str.substring(1);
		}
		return retVal;
		
	}
	
	public static String convertArrayToString( String[] array,  String delemeter) {
		String retVal="";
		delemeter=getNullToEmpty(delemeter, ",");
		if(array!=null) {
			for (String str : array) {
				str=getNullToEmpty(str).trim();
				if(!str.isEmpty()) {
					retVal+=delemeter+str;
				}
			}
			if( !retVal.isEmpty() ) { retVal=retVal.substring(1); }
		}
		return retVal;
	}
	
	public static String getCommonMasterValueById(int commonMasterId) {
		
		CommonMasterDO cmnDO =CommonData.commonMasterData.get(""+commonMasterId);
		if(cmnDO!=null) {
			return getNullToEmpty( cmnDO.getCmnMasterName() );
		}
		return "";
		
	}
	
}
