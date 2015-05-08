package utility;

import java.util.Date;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {		
		String attacheMedia =  "baltzan_chart1.jpg,baltzan_ch3v1q3and4.png,%media:baltzan_ch3v1q3and4.png%,%media:2baltzan_chart1.jpg%";
		attacheMedia = "combined.flv,%media:combined.flv%";
		attacheMedia = "combined1.flv,combined2.flv";
		attacheMedia = ",%media:combined3.flv%, combined4.flv,";
		//attacheMedia = ",,combined4.flv, ";
		StringTokenizer tokens = new StringTokenizer(attacheMedia,",");
		
		
		while(tokens.hasMoreTokens()){
			//System.out.println(tokens.nextToken());
		    String medianame = tokens.nextToken();
			if(medianame.indexOf("%media:") != -1){
				int begin =  medianame.indexOf("%media:");
				int end= medianame.substring(begin+1).indexOf("%");
				medianame =  medianame.substring(begin+7,end+1);
			}
			if(medianame != null && !("").equals(medianame)){
				medianame = medianame.trim();
			}
		 System.out.println(medianame);
		}
		
	}
}
