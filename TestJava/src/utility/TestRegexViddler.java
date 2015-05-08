package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegexViddler {
	/*public static void main(String[] args) {
	
		String regex = ".[^<]*(%media:viddler_3bd2c340.ext%)[\\S\\s]*?(\\/>|<\\/a>|<\\/iframe>)";
		String stringToSearch = "<iframe id=\"viddler-3bd2c340\" src=\"http://viddler.com\" mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" frameborder=\"0\" " +
    		"height=\"361\" width=\"545\">&#160;</iframe><iframe id=\"viddler-3bd2c340\" src=\"%media:viddler_3bd2c340.ext%\" mozallowfullscreen=\"true\" " +
    		"webkitallowfullscreen=\"true\" frameborder=\"0\" height=\"361\" width=\"545\">&#160;</iframe><iframe id=\"viddler-3bd2c340\" src=\"http://viddler.com\"" +
    		" mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" frameborder=\"0\" height=\"361\" width=\"545\">&#160;</iframe>" +
    		"<a href=\"http://youtube.com\"></a><a href=\"%media:viddler_3bd2c341.ext%\"></a><a href=\"%media:viddler_3bd2c342.ext%\"/><a href=\"http://viddler.com\"></a>";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(stringToSearch);
		boolean found = false;
		while (matcher.find()) {
        	System.out.println(matcher.group());
            found = true;
        }
        if(!found){
            System.out.println("No match found.%n");
        }
	}*/

	
	/*public static void main(String[] args) {
		String regex = ".[^<]*(%media:viddlerPlaceHoler%)[\\S\\s]*?(\\/>|<\\/a>|<\\/iframe>)";
		String replacedRegex =  regex.replace("viddlerPlaceHoler", "viddler_3bd2c340.ext");
		System.out.println(" regex : "+regex);
		System.out.println(" replacedRegex : "+replacedRegex);
	}*/
	
	public static void main(String[] args) {
		String inputStr = "ISBN=XXXX&showAnchor=YYYYY";
		inputStr = "showAnchor=1234&iSbN=XXXX";
		String regex = "isbn=([0-9a-zA-Z]*)(?=&)|isbn=([0-9a-zA-Z]*)";
		regex = "(?i)isbn=[0-9a-zA-Z]*(?=&)|isbn=[0-9a-zA-Z]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.find()) {
        	System.out.println(matcher.group());
        } 
		
	}
}
