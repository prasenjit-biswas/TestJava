package utility;

import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		/*Console console = System.console();
		if(console == null){
			System.err.println("No console.");
            System.exit(1);
		}*/
		//String reg = "(?:accesslog ([\d\.]+)).*(?=Mozilla|Jakarta|PATROL|ELB-HealthChecker)(.*)(?=\\" "Host)";
        String one = "(?:accesslog ([\\d\\.]+)).*(?=Mozilla|Jakarta|PATROL|ELB-HealthChecker)(.*)(?=\" \"Host)";
        //String two = "\\d*,\\d*";
        //two ="([^;]*(,\\d*,\\d*)(?=;|$))";
        String two = "(?<=;|^)([0-9]+(,[0-9]+)+)(?=;|$)";
        String three = "^[0-9a-zA-Z_=]+(?:&)[0-9a-zA-Z_=]+$|(?<!=)+$";
        
	/*	String regex = args[0];
        String stringToSearch = args[1];
	
			Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(stringToSearch);
	        boolean found = false;
	        while (matcher.find()) {
	            	System.out.println("I found the text" +
		                    " \"%s\" starting at " +
		                    "index %d and ending at index %d.%n "+
		                    matcher.group()+" "+
		                    matcher.start()+
		                    matcher.end()
	            	        );
	                found = true;
	            }
	            if(!found){
	                System.out.println("No match found.%n");
	        }*/
	
        //String stringToSearch = "4600,47000000,490000000,100000000;33333;4444444444444;46000001,47000001,490000001,100000001";
        String stringToSearch = "isbn=9999999showAnchor=fnjdndfnkn";
        String searchString = "<p><a target=\"_blank\" href=\"%media:viddler_d6151b45.ext% width=&amp;quot545&amp;quot height=&amp;quot451&amp;quot frameborder=&amp;quot0&amp;quot\">Guided Example</a></p>";
        Pattern pattern = Pattern.compile(three);
        
        Matcher matcher = pattern.matcher(stringToSearch);
        if (matcher.find()) {
        	System.out.println(matcher.group());
        	System.out.println("true");
        }else{
        	System.out.println("false");
        }
	}
}
