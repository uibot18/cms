package com.application.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class AppDateUtil {

	public static final String DISPLAY_DATE_TIME="dd/MM/yyyy HH:mm:ss";
	public static final String DISPLAY_DATE="dd/MM/yyyy";
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
		return currentDate((needTime?DISPLAY_DATE_TIME:DISPLAY_DATE));
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
	public static String convertDate(String dateVal, String srcFormat, String destFormat, String defaultVal) {
		String retVal=defaultVal;
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
		return convertDate( dateVal,  (needTime?DISPLAY_DATE_TIME:DISPLAY_DATE),  (needTime?DB_DATE_TIME:DB_DATE), "1000-01-01"+(needTime?" 00:00:00":""));
	}
	public static String convertToAppDate( String dateVal, boolean needTime, boolean needDefaultVal) {
		return convertDate( dateVal,  (needTime?DB_DATE_TIME:DB_DATE),  (needTime?DISPLAY_DATE_TIME:DISPLAY_DATE), "01/01/1000"+(needTime?" 00:00:00":""));
	}

	public static List<String> getWeekDaysDateList(String fromDate, String toDate){
		List<String> dateList=new ArrayList<String>();

		try {
			SimpleDateFormat format=new SimpleDateFormat(DISPLAY_DATE);
			format.setTimeZone( TimeZone.getTimeZone("GMT"));

			Calendar c1=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			Calendar c2=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			c1.setTime(format.parse(fromDate));
			c2.setTime(format.parse(toDate));
			c2.add(Calendar.DATE, 1);

			while(c1.before(c2)) {
				if(c1.get(Calendar.DAY_OF_WEEK)!=1 && c1.get(Calendar.DAY_OF_WEEK)!=7) {
					dateList.add( format.format( c1.getTime() ) );
				}
				c1.add(Calendar.DATE, 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateList;
	}

	public static List<String> getDateList(String fromDate, String toDate, int days) {
		List<String> dateList=new ArrayList<String>();
		try {
			if(days<=0) {
				return dateList;
			}
			SimpleDateFormat format=new SimpleDateFormat(DISPLAY_DATE);
			format.setTimeZone( TimeZone.getTimeZone("GMT"));

			Calendar c1=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			Calendar c2=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			c1.setTime(format.parse(fromDate));
			c2.setTime(format.parse(toDate));
			c2.add(Calendar.DATE, 1);

			while(c1.before(c2)) {
				dateList.add( format.format( c1.getTime() ) );
				c1.add(Calendar.DATE, days);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateList;
	}

	public static List<String> getDateList(String fromDate, String toDate, int days, List<Integer> dayList ) {
		List<String> dateList=new ArrayList<String>();
		try {
			
			if(days<=0 || dayList==null || dayList.size()==0) {
				return dateList;
			}
			
			SimpleDateFormat format=new SimpleDateFormat(DISPLAY_DATE);
			format.setTimeZone( TimeZone.getTimeZone("GMT"));

			Calendar c1=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			Calendar c2=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			c1.setTime(format.parse(fromDate));
			c2.setTime(format.parse(toDate));
			c2.add(Calendar.DATE, 1);

			while(c1.before(c2)) {
					
				if( dayList.contains(c1.get(Calendar.DAY_OF_WEEK)) ) {
					dateList.add( format.format( c1.getTime() ) );
				}
				if(c1.get(Calendar.DAY_OF_WEEK)==7) {
					c1.add(Calendar.DATE, days);
				}else {
					c1.add(Calendar.DATE, 1);
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateList;
	}

	public static int getDayNo(String day) {
		//'sunday','monday','tuesday','wednesday','thursday','friday','saturday'
		switch (day) {
		case "sunday":
			return 1;
		case "monday":
			return 2;
		case "tuesday":
			return 3;
		case "wednesday":
			return 4;
		case "thursday":
			return 5;
		case "friday":
			return 6;
		case "saturday":
			return 7;
		default:
			return 0;
		}
	}

}
