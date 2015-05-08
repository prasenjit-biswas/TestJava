package utility;

import java.util.Date;

public class TestJava1{
	public static void main(String[] v) {
	  try{
		  //Calendar cal = GregorianCalendar.getInstance();
			//cal.set(2005, GregorianCalendar.DECEMBER, 31);
			//System.out.println("java.endorsed.dirs : " + cal.getTimeInMillis());
		  Date date = new Date(110866556227525L);
		  System.out.println("java.endorsed.dirs : " + date );
		}catch(Exception ex){
		  ex.printStackTrace();
		}
	}	
}