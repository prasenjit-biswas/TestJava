package Dataretrieval.Question;

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
	
	/**
	* MIME 64-bit decoder for BASIC authentication password decoding.
	* @param encoded the string to decode
	*/
    public static String b64decode(String encoded)
    {
        StringBuffer sb=new StringBuffer();
        int maxturns;
        //work out how long to loop for.
        if(encoded.length()%3==0)
        maxturns=encoded.length();
        else
        maxturns=encoded.length()+(3-(encoded.length()%3));
        //tells us whether to include the char in the unencode
        boolean skip;
        //the unencode buffer
        byte[] unenc=new byte[4];
        byte b;
        for(int i=0,j=0; i<maxturns; i++)
        {
            skip=false;
            //get the byte to convert or 0
            if(i<encoded.length())
            b=(byte)encoded.charAt(i);
            else
            b=0;
            //test and convert first capital letters, lowercase, digits then '+' and '/'
            if(b>=65 && b<91)
            unenc[j]=(byte)(b-65);
            else if(b>=97 && b<123)
            unenc[j]=(byte)(b-71);
            else if(b>=48 && b<58)
            unenc[j]=(byte)(b+4);
            else if(b=='+')
            unenc[j]=62;
            else if(b=='/')
            unenc[j]=63;
            //if we find "=" then data has finished, we're not really dealing with this now
            else if(b=='=')
            unenc[j]=0;
            else
            {
                char c=(char)b;
                if(c=='\n' || c=='\r' || c==' ' || c=='\t')
                skip=true;
                else
                //could throw an exception here? it's input we don't understand.
                ;
            }
            //once the array has boiled convert the bytes back into chars
            if(!skip && ++j==4)
            {
                //shift the 6 bit bytes into a single 4 octet word
                int res=(unenc[0] << 18)+(unenc[1] << 12)+(unenc[2] << 6)+unenc[3];
                byte c;
                int k=16;
                //shift each octet down to read it as char and add to StringBuffer
                while(k>=0)
                {
                        c=(byte)(res >> k);
                        if ( c > 0 )
                        sb.append((char)c);
                        k-=8;
                }
                //reset j and the unencode buffer
                j=0;
                unenc[0]=0;unenc[1]=0;unenc[2]=0;unenc[3]=0;
            }
        }
        return sb.toString();
    }
    
    public static void main(String args[]){
    	String authHeader = "Basic d21kOmJhZFBlbm55";
    	System.out.println("name : " + b64decode(authHeader.substring(6)));
    }

}
