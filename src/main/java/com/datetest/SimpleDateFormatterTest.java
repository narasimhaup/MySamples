package com.datetest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

public class SimpleDateFormatterTest {

	public static void main(String[] args) {
		System.out.println(isOldDoc(new Date()));
		if(true)
			return;

		String strDate="Tue Apr 12 12:05:51 PDT 2016";
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
		
		String pubDate="2017-01-05 18:13:14";
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String conDate="01-05-2017 18:13:14";
		SimpleDateFormat contentdateFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss"); // for content Last modified new date format needed
		
		String dStr = "2016-05-17T07:04:53.949Z";
		SimpleDateFormat dFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		try {
			
			System.out.println(dFormatter.parse(dStr));
			System.out.println(sdf.format(dFormatter.parse(dStr)));
			if(true)
				return;
			
			Date todayStrMMDD = sdf.parse(strDate);
			
			
			
			System.out.println(todayStrMMDD);
			Date pubDatedate = dateFormatter.parse(pubDate);
			System.out.println(" Publish date Str: "+ pubDate);
			System.out.println(" Converted Publish date: "+ sdf.format(pubDatedate));
			
			Date conDatedate = contentdateFormatter.parse(conDate);
			System.out.println(" ConModi date Str: "+ conDate);
			System.out.println(" Converted Con Modified date: "+ sdf.format(conDatedate));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getDate( String date, String currentFormat, String expectedFormat) throws ParseException { 
		// Validating if the supplied parameters is null 
		if (date == null || currentFormat == null || expectedFormat == null ) { return null; } 
		// Create SimpleDateFormat object with source string date format 
		SimpleDateFormat sourceDateFormat = new SimpleDateFormat(currentFormat); 
		// Parse the string into Date object 
		Date dateObj = sourceDateFormat.parse(date); 
		// Create SimpleDateFormat object with desired date format 
		SimpleDateFormat desiredDateFormat = new SimpleDateFormat(expectedFormat); 
		// Parse the date into another format 
		return desiredDateFormat.format(dateObj).toString();
		
	}
 

	
	public static  boolean isOldDoc(Date modifyDate){
		String cutOffDtStr = "12/15/2017";
		Date cutOffDate = null;
		String modifiedDateStr = "Fri Dec 01 08:17:13 EST 2017";
		DateFormat cutOffdateFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat dtformat = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
		Date expectedFromat = null;
		String modifiedStr = cutOffdateFormat.format(modifyDate);
		//System.out.println(dtformat.format(date));
		
		
		try {
			System.out.println(getDate(modifyDate.toString(), "EEE MMM d HH:mm:ss zzz yyyy", "MM/dd/YYYY"));
			cutOffDate = cutOffdateFormat.parse(cutOffDtStr);
			expectedFromat = cutOffdateFormat.parse(modifiedStr);
			//dateFormat.format(modifyDate);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(dtformat.format(cutOffDate));
		System.out.println("CutOffDate " + cutOffDate.toString());
		System.out.println("ExpDate" + expectedFromat.toString());
		
		return expectedFromat.before(cutOffDate);
	}
	
	public static void dateTest(){
		
		Date cutof = new Date("12/15/2017");
		Date modified1 = new Date ("11/15/2017");
		Date modified2 = new Date("01/01/2018");
		
		System.out.println(modified1.after(cutof));
		System.out.println(modified2.after(cutof));
		
		
		//DateUtils.
	}
	
	public static void dateAfterBeforeTest(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dtformat = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");								
		Date maxDate = org.apache.commons.lang3.time.DateUtils.addMonths(cal.getTime(), 6);
		
		try {
			cal.setTime(new Date("12/04/2017"));
			// Check if the date is greater than next 6 months
			if(cal.getTime().after(maxDate)){  // User entered date is greater than 6 months
				System.out.println("Date is Greater than 6 months");
			}
			} catch(Exception e){
				e.printStackTrace();
			}
		
		Date today = new Date();
		Date date = new Date(today.getYear(),
				today.getMonth() + 6 , today.getDate());
		System.out.println(date.toString());
	}
}
