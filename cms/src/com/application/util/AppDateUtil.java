package com.application.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppDateUtil {

	public static final String DIAPLAY_DATE_TIME="dd/MM/yyyy HH:mm:ss";
	public static final String DIAPLAY_DATE="dd/MM/yyyy";
	public static final String DB_DATE_TIME="yyyy-MM-dd HH:mm:ss";
	public static final String DB_DATE="yyyy-MM-dd";
	
	public static String currentDate(String format) {
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		return dateFormat.format(calendar.getTime());
	}
	
	public static String currentDate(boolean needTime) {
		return currentDate((needTime?DB_DATE_TIME:DB_DATE));
	}
	
	public static String currentDateDisplay(boolean needTime) {
		return currentDate((needTime?DIAPLAY_DATE_TIME:DIAPLAY_DATE));
	}
	
	public static String convertDate(String dateVal, String srcFormat, String destFormat) {
		String retVal="";
		dateVal=AppUtil.getNullToEmpty(dateVal);
		try {
			if(!dateVal.isEmpty()) {
				SimpleDateFormat dateFormat=new SimpleDateFormat(srcFormat); 
				Calendar cal=Calendar.getInstance(); cal.setTime(dateFormat.parse(dateVal));
				dateFormat=new SimpleDateFormat(destFormat); retVal=dateFormat.format(cal.getTime());
			}
		} catch (Exception e) { e.printStackTrace(); }
		return retVal;
	}
	
	public static String convertToDBDate( String dateVal, boolean needTime, boolean needDefaultVal) {
		return convertDate( dateVal,  (needTime?DIAPLAY_DATE_TIME:DIAPLAY_DATE),  (needTime?DB_DATE_TIME:DB_DATE));
	}
	public static String convertToAppDate( String dateVal, boolean needTime, boolean needDefaultVal) {
		return convertDate( dateVal,  (needTime?DB_DATE_TIME:DB_DATE),  (needTime?DIAPLAY_DATE_TIME:DIAPLAY_DATE));
	}
	
}
