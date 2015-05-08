package utility;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtil {
	public static void main(String[] v) {
	try{
		
		/*String dateString = "2012/12/31";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
		TimeZone tz = TimeZone.getTimeZone("ESTEDT");
		simpleDateFormat.setTimeZone(tz);
		Date date = simpleDateFormat.parse(dateString);
		//System.out.println("date : " + simpleDateFormat.format(date));
		//System.out.println("date : " + date);
		
		date = new Date(111, 11, 31);
		long dateTime = date.getTime();
		dateTime = dateTime * 100;
		//dateTime += 100;
		dateTime = dateTime * 100;
		System.out.println("dateTime : " + dateTime);*/
		
		List<Long> timeList = new ArrayList<Long>();
		//timeList.add(1377765618311l);
		timeList.add(1423565100000l);// 1423563900000
		//timeList.add(1374774715509l);
		//timeList.add(1375527578071l);
		
		for(long time : timeList){
			DateFormat firstFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS aaa");
			TimeZone firstTime = TimeZone.getTimeZone("EST5EDT");
			firstFormat.setTimeZone(firstTime); 
			
			Date date = new Date(time);
			System.out.println(time + " : " + firstFormat.format(new Date(time)));
		}
		///*
		//13252698283780022 creation Fri Aug 17 22:41:09 IST 2012
		//13252698283780022 modified Fri Aug 17 23:44:44 IST 2012
		
		//13252698287887890 creation Thu Aug 23 01:39:39 IST 2012
		//13252698287887890 creation Fri Aug 24 07:31:28 IST 2012
		
		/*String thisPair= "13364721485385500:99.99";
		int colonIndex= thisPair.indexOf(":");
		
		//BigDecimal newScore = new BigDecimal("100.00");
		BigDecimal newScore = (new BigDecimal( thisPair.substring(colonIndex+1).trim())).setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(" newScore : "+newScore);
		System.out.println(" check status : "+ newScore.compareTo(new BigDecimal(99.99))); 
		System.out.println(" check status : "+ newScore.compareTo(new BigDecimal(0))); */
		
		
		/*long timeInMillis = 0;
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		formatter.setTimeZone(TimeZone.getTimeZone("EST"));
		Date date1 = formatter.parse("31-DEC-2012");
		timeInMillis = date1.getTime();
		System.out.println(timeInMillis);*/
		
	//System.out.println(date);
	}catch (Exception ex){
		ex.printStackTrace();
	}
  }
}
