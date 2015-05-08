package utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestStringSubstring {
  public static void main(String[] args) {
	 String qtext = "The following events occurred last year at Dewhurst Company:<br><br>&nbsp;%media:3image011.png%&nbsp;<br><br>Based on the above information, the cash provided (used) by investing activities for the year on the statement of cash flows would net to:";
	 qtext.indexOf("%media:");
	 qtext.lastIndexOf("%");
	 String firstString = qtext.substring(qtext.indexOf("%media:"),qtext.lastIndexOf("%") );
	 System.out.println(firstString);
	 String second = firstString.substring(firstString.indexOf(":")+1);
	 System.out.println(second);
	  /*BigDecimal eztScore = (new BigDecimal(2000).divide(new BigDecimal(20000))).setScale(2);
	  System.out.println(eztScore);
	  if(new BigDecimal(0.2).compareTo(new BigDecimal(0.20))==0){
		  System.out.println("match");
		}else{
			System.out.println("not match");
		}*/
	  /*Float connectScore = new Float(".895"); 
	  BigDecimal conectscore = new BigDecimal(connectScore).setScale(2, RoundingMode.HALF_DOWN);
      System.out.println(" conectscore : "+conectscore);
      BigDecimal eztScore =(new BigDecimal(95000).divide(new BigDecimal(240000), 2, RoundingMode.HALF_DOWN ));
      System.out.println("eztScore : "+eztScore);*/
  }
}
