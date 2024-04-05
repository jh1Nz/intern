package com.summitthai.cr.apprentice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.primefaces.PrimeFaces;

import com.summitthai.sdd.sys.util.NumberUtils;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class XspUtils {
	public static String getDate(){
        // สร้างวันที่ปัจจุบัน
        Calendar calendar = Calendar.getInstance();

        // ดึงปีในรูปแบบพุทธศักราช
        int buddhistYear = calendar.get(Calendar.YEAR) + 543;

        // ดึงเดือนและวันที่
        int month = calendar.get(Calendar.MONTH) + 1; // เดือนเริ่มจาก 0 ถึง 11 จึงต้องเพิ่ม 1
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // สร้างวันที่ในรูปแบบพุทธศักราช
        String buddhistDate = String.format("%d%02d%02d", buddhistYear ,month, day);

        return buddhistDate;

    }
	public static String getYear() {
	        log.debug("Begin getYear...");
	        Calendar calendar = Calendar.getInstance(new Locale("th", "TH"));
	        calendar.setTime(new Date());
	        int year = calendar.get(Calendar.YEAR);

	        return Integer.toString(year);
	}
	public static Date convertStringToDate(String arg0) throws ParseException {
        log.debug("Begin convertStringToDate...");
        Integer resultYear;
        if (arg0 == null) {
            return null;
        }
        Date date = new Date();
        if (!StringUtils.isNullOrEmpty(arg0)) {
            int day = NumberUtils.toInteger(arg0.substring(6, 8));
            int month = NumberUtils.toInteger(arg0.substring(4, 6)) - 1;
            int year = NumberUtils.toInteger(arg0.substring(0, 4));
//            if((year - 543) < 1900) {
//                year = year + 543;
//            } 
            Calendar calendar = Calendar.getInstance(Locale.US);        
            calendar.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
            calendar.set(year, month, day, 0, 0, 0);
            date = calendar.getTime();
            
        }

        return date;
    }
	// แปลง Date เป็น String
//    public static String convertDateToString(Date arg0) {
//        log.debug("Begin convertDateToString...");
//        if (arg0 == null) {
//            return null;
//        }
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//        log.debug("End convertDateToString...");
//        
//        String dateRetrun = null;
//        Calendar calendar = Calendar.getInstance(new Locale("th", "TH"));
//        calendar.setTime(new Date());
//        int year = calendar.get(Calendar.YEAR);
//        
//        String date = formatter.format(arg0);
//        int yearArg0 = Integer.parseInt(date.substring(0,4));
//        
//        if (yearArg0 < year) {
//			String newDate = date.substring(4);
//			dateRetrun = Integer.toString(year).concat(newDate);
//		} else {
//			dateRetrun = date;
//		}
//
//        return dateRetrun;
//    }
	// แปลง Date เป็น String
    public static String convertDateToString(Date arg0) {
    	  String dateRetrun = null;
    	  if (arg0 == null) {
    	   return null;
    	  }
    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    	  String date = formatter.format(arg0);
    	  int yearArg0 = Integer.parseInt(date.substring(0,4)) + 543; 
    	  String newDate = date.substring(4);
		  dateRetrun = Integer.toString(yearArg0).concat(newDate);
    	  return dateRetrun;
    	 }
    
    public static void scrollToTop() {
        PrimeFaces.current().executeScript("scrollToTop();");
    }

}

