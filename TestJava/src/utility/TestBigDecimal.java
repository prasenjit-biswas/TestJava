package utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.mcgrawhill.ezto.integration.classware_hm;



public class TestBigDecimal {

	static String HM_POINTS = "55.55";
	static String DEFAULT_INTERNAL_POINTS = "10000.00";
	public static String       MAX_BD_PTS           = "100.00";
	public static String			MIN_BD_PTS				= "0.00";

	/*public static void main(String[] args) {
		 String ptsString = "0.01";
		 BigDecimal pts = new BigDecimal(ptsString.trim());
		 if(pts.compareTo(new BigDecimal(MIN_BD_PTS)) >= 0 && pts.compareTo(new BigDecimal(MAX_BD_PTS)) <= 0){
			System.out.println(" pts in the interval "+pts);
		 }else{
			System.out.println(" pts not in the interval "+pts);
		 }
	}*/

	public static void main(String[] args) {
		/*int totalChoiceCount =4;
		int answeredCount = 4;
		BigDecimal a =  new BigDecimal(answeredCount*100).divide(new BigDecimal(totalChoiceCount),2, RoundingMode.HALF_UP);
		System.out.println(" A : "+a);*/
		BigDecimal eval = new BigDecimal(0);
		BigDecimal ezmaxScore = new BigDecimal(3.00);
		int thePoints = 6666;
		BigDecimal ezscore = new BigDecimal(Integer.toString(thePoints) + ".00");
		ezscore = ezscore.multiply(ezmaxScore);
		ezscore = ezscore.divide(new BigDecimal(Integer.toString(classware_hm.DEFAULT_INTERNAL_POINTS) + ".00"), 2, BigDecimal.ROUND_UP);
		//calculate the percentage of user score related to the question point
		if(ezscore != null && !ezscore.equals(new BigDecimal("0.00")) &&  ezmaxScore != null && !ezmaxScore.equals(new BigDecimal("0.00")) ){
			eval = ezscore.multiply(new BigDecimal("100.00"));
			eval = eval.divide(ezmaxScore,2,BigDecimal.ROUND_UP);
		}
		System.out.println(eval);
	}


	/*public static void main(String[] args) {

		String scoreEntry = "5.0";
		BigDecimal newScore = new BigDecimal(scoreEntry);
		BigDecimal displayScore = newScore.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("newScore : "+newScore);
		System.out.println("displayScore : "+displayScore);

		//OldFlow();
		//OldOppFlow();
	try{

			File f = new File("C:\\ScoreMismatch.csv");

			f.createNewFile();
			if(f.exists()){
				f.delete();
			}
			PrintWriter writer = new PrintWriter(f);
			writer.print("      max_score     "); 
			writer.print(',');
			writer.print("      student_Score     "); 
			writer.print(',');
			writer.print("      scoreString     "); 
			writer.print('\n');

			BigDecimal max_score = new BigDecimal("99.99");
			while(max_score.compareTo(new BigDecimal(89.00)) >= 0){

				float student_Score = 0.01f;
				while(new BigDecimal(student_Score).compareTo(max_score) <= 0){
					BigDecimal newScore = new BigDecimal(student_Score).setScale(2, BigDecimal.ROUND_HALF_UP);
					String newScoreStr = newScore.toString();
					BigDecimal displayScore = newScore;
					newScore = displayScore.multiply(new BigDecimal(Integer.toString(10000)));
					newScore = newScore.divide(max_score, 3);
					int thePoints = newScore.intValue();
					BigDecimal proRataPoints = new BigDecimal(Integer.toString(thePoints) + ".00");
					proRataPoints = proRataPoints.multiply( max_score );
					proRataPoints = proRataPoints.divide(new BigDecimal(Integer.toString(10000) + ".00"), 2, RoundingMode.UP);
					if(!newScoreStr.equalsIgnoreCase(proRataPoints.toString())){
						writer.print(max_score);
						writer.print(',');
						writer.print("'" + newScoreStr + "'");
						writer.print(',');
						writer.print("'" + proRataPoints.toString() + "'");
						writer.println();
					}

					student_Score = student_Score + .01f;
				}
				max_score = max_score.subtract(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			writer.flush();
			writer.close();

			System.out.println("Completed");

		}catch(Exception exException){
		  exException.printStackTrace();
	  }
	}*/














	public static void OldFlow()
	{

		BigDecimal newScore = new BigDecimal("").setScale(2, BigDecimal.ROUND_HALF_UP);
		String newScoreStr = newScore.toString();
		BigDecimal displayScore = newScore;
		newScore = displayScore.multiply(new BigDecimal(Integer.toString(10000)));
		newScore = newScore.divide(new BigDecimal(HM_POINTS),3);
		System.out.println(" newScore : "+newScore);
		BigDecimal proRataPoints = new BigDecimal(Integer.toString(newScore.intValue()) + ".00");//newScore;//new BigDecimal("72.00");
		proRataPoints = proRataPoints.multiply( new BigDecimal(HM_POINTS) );
		BigDecimal proRataPoints1 = proRataPoints.divide(new BigDecimal(Integer.toString(10000)), 2, BigDecimal.ROUND_FLOOR);
		System.out.println(" proRataPoints1 ROUND_FLOOR : "+proRataPoints1);
		BigDecimal proRataPoints2 = proRataPoints.divide(new BigDecimal(Integer.toString(10000)), 2, BigDecimal.ROUND_UP);
		System.out.println(" proRataPoints2 ROUND_UP : "+proRataPoints2);
		BigDecimal proRataPoints3 = proRataPoints.divide(new BigDecimal(Integer.toString(10000)), 2, BigDecimal.ROUND_DOWN);
		System.out.println(" proRataPoints3 ROUND_DOWN : "+proRataPoints3);
		BigDecimal proRataPoints4 = proRataPoints.divide(new BigDecimal(Integer.toString(10000)), 2, BigDecimal.ROUND_HALF_UP);
		System.out.println(" proRataPoints4 ROUND_HALF_UP : "+proRataPoints4);
	}

	public static void OldOppFlow()
	{
		BigDecimal newScore = new BigDecimal("0");
		BigDecimal displayScore = newScore.setScale(2, BigDecimal.ROUND_HALF_UP);
		newScore = displayScore.multiply(new BigDecimal(DEFAULT_INTERNAL_POINTS));
		newScore = newScore.divide(new BigDecimal(HM_POINTS), BigDecimal.ROUND_FLOOR);

		int point = newScore.intValue();
		BigDecimal qPts = new BigDecimal(HM_POINTS);
		BigDecimal qAwd = new BigDecimal(point);
		qAwd = qAwd.multiply(qPts);
		//System.out.println(" qAwd : "+ qAwd);
		BigDecimal studentScore = qAwd.divide(new BigDecimal(DEFAULT_INTERNAL_POINTS), 2, BigDecimal.ROUND_UP);
		System.out.println(" studentScore : "+ studentScore);
	}

}
