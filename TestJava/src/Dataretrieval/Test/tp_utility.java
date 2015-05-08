package Dataretrieval.Test;

public class tp_utility {

	public static String substitute(
			String masterString,
			String lookupString,
			String replacementString
		) {
			String		  resultString;
			StringBuilder tmp;
			
			int			start;
			

			if (masterString == null) return null;
			
			resultString= masterString;
			if (replacementString.indexOf(lookupString) >= 0) return resultString;
			
			start= resultString.indexOf( lookupString );
			while (start >= 0) {
				
				if (start==0) tmp= new StringBuilder("");
				else tmp= new StringBuilder(resultString.substring(0,start));
				
				tmp.append(replacementString);
				if ( (start+lookupString.length()) < resultString.length() ){
					tmp.append(resultString.substring( start+lookupString.length()));
				}
				
				resultString= tmp.toString();
				start= resultString.indexOf( lookupString );
			}
			
			return( resultString );
		}

}
