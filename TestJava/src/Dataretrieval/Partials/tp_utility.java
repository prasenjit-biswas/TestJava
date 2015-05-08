package Dataretrieval.Partials;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class tp_utility {
	static String LONG_STRING		= "***UTF String Too Long for writeUTF***";
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
	
	public static String readString( DataInputStream input )
	throws IOException{
		String result= input.readUTF();
		if (result.equals(LONG_STRING))
		{
			//System.out.println("inputting " + LONG_STRING);
			int arrayLength= input.readInt();
			byte[] barray= new byte[ arrayLength ];
			input.read( barray, 0, arrayLength );
			result= new String( barray, "UTF-8" );
		}
		return(result);
	}
	public static String convertFromUTF8( String input )
	{
		if (input == null) return(null);
		if (input.length() == 0) return(input);
		
		byte[] utf8= new byte[input.length()];
		
		for (int i=0; i<input.length(); i++)
		{
			utf8[i]= (byte)input.charAt(i);
		}
		
		String result= input;
		try
		{
			//System.out.println( "cvt: " + toCharEntities( (new String(utf8, "UTF-8")), false ) );
			result= toCharEntities( (new String(utf8, "UTF-8")), false );
		}
		catch (UnsupportedEncodingException ignore) {}
		
		return(result);
	}
	
	public static String toCharEntities( String input, boolean formatNonRomanAsHTML )
	{
		return( toCharEntities( input, formatNonRomanAsHTML, true ) );
	}
	public static String toCharEntities( String input, boolean formatNonRomanAsHTML, boolean convertAmpersands )
	{
		return( toCharEntities( input, formatNonRomanAsHTML, convertAmpersands, true ) );
	}
	
	public static String toCharEntities( String input, boolean formatNonRomanAsHTML, boolean convertAmpersands, boolean convertQuotes )
	{
		if (input == null) return("");
		String result= input;
		
		//System.out.println("input: " + input);

		//result= substitute(result, "&", "!AMPERSAND!");
		//result= substitute(result, "!AMPERSAND!", "&amp;");
		if (convertAmpersands)
			result= fixAmpersands(input);

		result= substitute(result, (new Character((char)145)).toString(), "&#8216;");
		result= substitute(result, (new Character((char)146)).toString(), "&#8217;");
		result= substitute(result, (new Character((char)147)).toString(), "&#8220;");
		result= substitute(result, (new Character((char)148)).toString(), "&#8221;");

		result= substitute(result, (new Character((char)160)).toString(), "&nbsp;");
		result= substitute(result, (new Character((char)161)).toString(), "&iexcl;");
		result= substitute(result, (new Character((char)162)).toString(), "&cent;");
		result= substitute(result, (new Character((char)163)).toString(), "&pound;");
		result= substitute(result, (new Character((char)164)).toString(), "&curren;");
		result= substitute(result, (new Character((char)165)).toString(), "&yen;");
		result= substitute(result, (new Character((char)166)).toString(), "&brvbar;");
		result= substitute(result, (new Character((char)167)).toString(), "&sect;");
		result= substitute(result, (new Character((char)169)).toString(), "&copy;");
		result= substitute(result, (new Character((char)170)).toString(), "&ordf;");
		result= substitute(result, (new Character((char)171)).toString(), "&laquo;");
		result= substitute(result, (new Character((char)172)).toString(), "&not;");
		result= substitute(result, (new Character((char)173)).toString(), "&shy;");
		result= substitute(result, (new Character((char)174)).toString(), "&reg;");
		result= substitute(result, (new Character((char)175)).toString(), "&macr;");
		
		//result= substitute(result, (new Character((char)176)).toString(), "&deg;");
		result= substitute(result, (new Character((char)176)).toString(), "&#176;");
		
		result= substitute(result, (new Character((char)177)).toString(), "&plusmn;");
		result= substitute(result, (new Character((char)178)).toString(), "&sup2;");
		result= substitute(result, (new Character((char)179)).toString(), "&sup3;");
		result= substitute(result, (new Character((char)180)).toString(), "&acute;");
		result= substitute(result, (new Character((char)181)).toString(), "&micro;");
		result= substitute(result, (new Character((char)182)).toString(), "&para;");
		result= substitute(result, (new Character((char)183)).toString(), "&middot;");
		result= substitute(result, (new Character((char)184)).toString(), "&ccedil;");
		result= substitute(result, (new Character((char)185)).toString(), "&sup1;");
		result= substitute(result, (new Character((char)186)).toString(), "&ordm;");
		result= substitute(result, (new Character((char)187)).toString(), "&raquo;");
		result= substitute(result, (new Character((char)188)).toString(), "&frac14;");
		result= substitute(result, (new Character((char)189)).toString(), "&frac12;");
		result= substitute(result, (new Character((char)190)).toString(), "&frac34;");
		result= substitute(result, (new Character((char)191)).toString(), "&iquest;");
		result= substitute(result, (new Character((char)192)).toString(), "&Agrave;");
		result= substitute(result, (new Character((char)193)).toString(), "&Aacute;");
		result= substitute(result, (new Character((char)194)).toString(), "&Acirc;");
		result= substitute(result, (new Character((char)195)).toString(), "&Atilde;");
		result= substitute(result, (new Character((char)196)).toString(), "&Auml;");
		result= substitute(result, (new Character((char)197)).toString(), "&Aring;");
		result= substitute(result, (new Character((char)198)).toString(), "&AElig;");
		result= substitute(result, (new Character((char)199)).toString(), "&Ccedil;");
		result= substitute(result, (new Character((char)200)).toString(), "&Egrave;");
		result= substitute(result, (new Character((char)201)).toString(), "&Eacute;");
		result= substitute(result, (new Character((char)202)).toString(), "&Ecirc;");
		result= substitute(result, (new Character((char)203)).toString(), "&Euml;");
		result= substitute(result, (new Character((char)204)).toString(), "&Igrave;");
		result= substitute(result, (new Character((char)205)).toString(), "&Iacute;");
		result= substitute(result, (new Character((char)206)).toString(), "&Icirc;");
		result= substitute(result, (new Character((char)207)).toString(), "&Iuml;");
		result= substitute(result, (new Character((char)208)).toString(), "&ETH;");
		result= substitute(result, (new Character((char)209)).toString(), "&Ntilde;");
		result= substitute(result, (new Character((char)210)).toString(), "&Ograve;");
		result= substitute(result, (new Character((char)211)).toString(), "&Oacute;");
		result= substitute(result, (new Character((char)212)).toString(), "&Ocirc;");
		result= substitute(result, (new Character((char)213)).toString(), "&Otilde;");
		result= substitute(result, (new Character((char)214)).toString(), "&Ouml;");
		result= substitute(result, (new Character((char)215)).toString(), "&times;");
		result= substitute(result, (new Character((char)216)).toString(), "&Oslash;");
		result= substitute(result, (new Character((char)217)).toString(), "&Ugrave;");
		result= substitute(result, (new Character((char)218)).toString(), "&Uacute;");
		result= substitute(result, (new Character((char)219)).toString(), "&Ucirc;");
		result= substitute(result, (new Character((char)220)).toString(), "&Uuml;");
		result= substitute(result, (new Character((char)221)).toString(), "&Yacute;");
		result= substitute(result, (new Character((char)222)).toString(), "&THORN;");
		result= substitute(result, (new Character((char)223)).toString(), "&szlig;");
		result= substitute(result, (new Character((char)224)).toString(), "&agrave;");
		result= substitute(result, (new Character((char)225)).toString(), "&aacute;");
		result= substitute(result, (new Character((char)226)).toString(), "&acirc;");
		result= substitute(result, (new Character((char)227)).toString(), "&atilde;");
		result= substitute(result, (new Character((char)228)).toString(), "&auml;");
		result= substitute(result, (new Character((char)229)).toString(), "&aring;");
		result= substitute(result, (new Character((char)230)).toString(), "&aelig;");
		result= substitute(result, (new Character((char)231)).toString(), "&ccedil;");
		result= substitute(result, (new Character((char)232)).toString(), "&egrave;");
		result= substitute(result, (new Character((char)233)).toString(), "&eacute;");
		result= substitute(result, (new Character((char)234)).toString(), "&ecirc;");
		result= substitute(result, (new Character((char)235)).toString(), "&euml;");
		result= substitute(result, (new Character((char)236)).toString(), "&igrave;");
		result= substitute(result, (new Character((char)237)).toString(), "&iacute;");
		result= substitute(result, (new Character((char)238)).toString(), "&icirc;");
		result= substitute(result, (new Character((char)239)).toString(), "&iuml;");
		result= substitute(result, (new Character((char)240)).toString(), "&eth;");
		result= substitute(result, (new Character((char)241)).toString(), "&ntilde;");
		result= substitute(result, (new Character((char)242)).toString(), "&ograve;");
		result= substitute(result, (new Character((char)243)).toString(), "&oacute;");
		result= substitute(result, (new Character((char)244)).toString(), "&ocirc;");
		result= substitute(result, (new Character((char)245)).toString(), "&otilde;");
		result= substitute(result, (new Character((char)246)).toString(), "&ouml;");
		result= substitute(result, (new Character((char)247)).toString(), "&divide;");
		result= substitute(result, (new Character((char)248)).toString(), "&oslash;");
		result= substitute(result, (new Character((char)249)).toString(), "&ugrave;");
		result= substitute(result, (new Character((char)250)).toString(), "&uacute;");
		result= substitute(result, (new Character((char)251)).toString(), "&ucirc;");
		result= substitute(result, (new Character((char)252)).toString(), "&uuml;");
		result= substitute(result, (new Character((char)253)).toString(), "&yacute;");
		result= substitute(result, (new Character((char)254)).toString(), "&thorn;");
		result= substitute(result, (new Character((char)255)).toString(), "&yuml;");
		result= substitute(result, (new Character((char)338)).toString(), "&OElig;");
		result= substitute(result, (new Character((char)339)).toString(), "&oelig;");
		
		if (convertQuotes) result= substitute(result, (new Character((char)34)).toString(), "&quot;");
		
		result= substitute(result, (new Character((char)352)).toString(), "&Scaron;");
		result= substitute(result, (new Character((char)353)).toString(), "&scaron;");
		result= substitute(result, (new Character((char)376)).toString(), "&Yuml;");
		result= substitute(result, (new Character((char)402)).toString(), "&fnof;");
		result= substitute(result, (new Character((char)710)).toString(), "&circ;");
		result= substitute(result, (new Character((char)732)).toString(), "&tilde;");
		result= substitute(result, (new Character((char)8194)).toString(), "&ensp;");
		result= substitute(result, (new Character((char)8195)).toString(), "&emsp;");
		result= substitute(result, (new Character((char)8201)).toString(), "&thinsp;");
		result= substitute(result, (new Character((char)8204)).toString(), "&zwnj;");
		result= substitute(result, (new Character((char)8205)).toString(), "&zwl;");
		result= substitute(result, (new Character((char)8206)).toString(), "&lrm;");
		result= substitute(result, (new Character((char)8207)).toString(), "&rlm;");
		result= substitute(result, (new Character((char)8211)).toString(), "&ndash;");
		result= substitute(result, (new Character((char)8212)).toString(), "&mdash;");
		result= substitute(result, (new Character((char)8216)).toString(), "&lsquo;");
		result= substitute(result, (new Character((char)8217)).toString(), "&rsquo;");
		result= substitute(result, (new Character((char)8218)).toString(), "&sbquo;");
		result= substitute(result, (new Character((char)8220)).toString(), "&ldquo;");
		result= substitute(result, (new Character((char)8221)).toString(), "&rdquo;");
		result= substitute(result, (new Character((char)8222)).toString(), "&bdquo;");
		result= substitute(result, (new Character((char)8224)).toString(), "&dagger;");
		result= substitute(result, (new Character((char)8225)).toString(), "&Dagger;");
		result= substitute(result, (new Character((char)8226)).toString(), "&bull;");
		result= substitute(result, (new Character((char)8230)).toString(), "&hellip;");
		result= substitute(result, (new Character((char)8240)).toString(), "&permil;");
		result= substitute(result, (new Character((char)8242)).toString(), "&prime;");
		result= substitute(result, (new Character((char)8243)).toString(), "&Prime;");
		result= substitute(result, (new Character((char)8249)).toString(), "&lsaquo;");
		result= substitute(result, (new Character((char)8250)).toString(), "&rsaquo;");
		result= substitute(result, (new Character((char)8254)).toString(), "&oline;");
		result= substitute(result, (new Character((char)8260)).toString(), "&frasl;");
		result= substitute(result, (new Character((char)8364)).toString(), "&euro;");

		result= substitute(result, (new Character((char)8450)).toString(), "&#8450;");

		result= substitute(result, (new Character((char)8465)).toString(), "&image;");
		result= substitute(result, (new Character((char)8472)).toString(), "&weierp;");
		result= substitute(result, (new Character((char)8476)).toString(), "&real;");
		result= substitute(result, (new Character((char)8482)).toString(), "&trade;");
		result= substitute(result, (new Character((char)8501)).toString(), "&alefsym;");
		result= substitute(result, (new Character((char)8592)).toString(), "&larr;");
		result= substitute(result, (new Character((char)8593)).toString(), "&uarr;");
		result= substitute(result, (new Character((char)8594)).toString(), "&rarr;");
		result= substitute(result, (new Character((char)8595)).toString(), "&darr;");
		result= substitute(result, (new Character((char)8596)).toString(), "&harr;");
		result= substitute(result, (new Character((char)8629)).toString(), "&crarr;");
		result= substitute(result, (new Character((char)8656)).toString(), "&lArr;");
		result= substitute(result, (new Character((char)8657)).toString(), "&uArr;");
		result= substitute(result, (new Character((char)8658)).toString(), "&rArr;");
		result= substitute(result, (new Character((char)8658)).toString(), "&rarr;");
		result= substitute(result, (new Character((char)8660)).toString(), "&hArr;");
		result= substitute(result, (new Character((char)8704)).toString(), "&forall;");
		result= substitute(result, (new Character((char)8706)).toString(), "&part;");
		result= substitute(result, (new Character((char)8707)).toString(), "&exist;");
		result= substitute(result, (new Character((char)8709)).toString(), "&empty;");
		result= substitute(result, (new Character((char)8711)).toString(), "&nabla;");
		result= substitute(result, (new Character((char)8712)).toString(), "&isin;");
		result= substitute(result, (new Character((char)8713)).toString(), "&notin;");
		result= substitute(result, (new Character((char)8719)).toString(), "&prod;");
		result= substitute(result, (new Character((char)8721)).toString(), "&sum;");
		result= substitute(result, (new Character((char)8722)).toString(), "&minus;");
		
		result= substitute(result, (new Character((char)8726)).toString(), "&#8726;");

		result= substitute(result, (new Character((char)8727)).toString(), "&lowast;");
		result= substitute(result, (new Character((char)8730)).toString(), "&radic;");
		result= substitute(result, (new Character((char)8733)).toString(), "&prop;");
		result= substitute(result, (new Character((char)8734)).toString(), "&infin;");
		result= substitute(result, (new Character((char)8736)).toString(), "&ang;");
		result= substitute(result, (new Character((char)8743)).toString(), "&and;");
		result= substitute(result, (new Character((char)8744)).toString(), "&or;");
		result= substitute(result, (new Character((char)8745)).toString(), "&cap;");
		result= substitute(result, (new Character((char)8746)).toString(), "&cup;");
		result= substitute(result, (new Character((char)8747)).toString(), "&int;");
		result= substitute(result, (new Character((char)8756)).toString(), "&there4;");
		result= substitute(result, (new Character((char)8764)).toString(), "&sim;");
		result= substitute(result, (new Character((char)8773)).toString(), "&cong;");

		//result= substitute(result, (new Character((char)8776)).toString(), "&asymp;");
		result= substitute(result, (new Character((char)8776)).toString(), "&#8776;");
		
		result= substitute(result, (new Character((char)8800)).toString(), "&ne;");
		result= substitute(result, (new Character((char)8801)).toString(), "&equiv;");
		result= substitute(result, (new Character((char)8804)).toString(), "&le;");
		result= substitute(result, (new Character((char)8805)).toString(), "&ge;");
		result= substitute(result, (new Character((char)8834)).toString(), "&sub;");
		result= substitute(result, (new Character((char)8835)).toString(), "&sup;");
		result= substitute(result, (new Character((char)8836)).toString(), "&nsub;");
		result= substitute(result, (new Character((char)8838)).toString(), "&sube;");
		result= substitute(result, (new Character((char)8839)).toString(), "&supe;");
		result= substitute(result, (new Character((char)8853)).toString(), "&oplus;");
		result= substitute(result, (new Character((char)8855)).toString(), "&otimes;");
		result= substitute(result, (new Character((char)8869)).toString(), "&perp;");
		result= substitute(result, (new Character((char)8901)).toString(), "&sdot;");
		result= substitute(result, (new Character((char)913)).toString(), "&Alpha;");
		result= substitute(result, (new Character((char)914)).toString(), "&Beta;");
		result= substitute(result, (new Character((char)915)).toString(), "&Gamma;");
		result= substitute(result, (new Character((char)916)).toString(), "&Delta;");
		result= substitute(result, (new Character((char)917)).toString(), "&Epsilon;");
		result= substitute(result, (new Character((char)918)).toString(), "&Zeta;");
		result= substitute(result, (new Character((char)919)).toString(), "&Eta;");
		result= substitute(result, (new Character((char)920)).toString(), "&Theta;");
		result= substitute(result, (new Character((char)921)).toString(), "&Iota;");
		result= substitute(result, (new Character((char)922)).toString(), "&Kappa;");
		result= substitute(result, (new Character((char)923)).toString(), "&Lambda;");
		result= substitute(result, (new Character((char)924)).toString(), "&Mu;");
		result= substitute(result, (new Character((char)925)).toString(), "&Nu;");
		result= substitute(result, (new Character((char)926)).toString(), "&Xi;");
		result= substitute(result, (new Character((char)927)).toString(), "&Omicron;");
		result= substitute(result, (new Character((char)928)).toString(), "&Pi;");
		result= substitute(result, (new Character((char)929)).toString(), "&Rho;");
		result= substitute(result, (new Character((char)931)).toString(), "&Sigma;");
		result= substitute(result, (new Character((char)932)).toString(), "&Tau;");
		
		result= substitute(result, (new Character((char)933)).toString(), "&Upsilon;");
		
		result= substitute(result, (new Character((char)934)).toString(), "&Phi;");
		result= substitute(result, (new Character((char)935)).toString(), "&Chi;");
		result= substitute(result, (new Character((char)936)).toString(), "&Psi;");
		result= substitute(result, (new Character((char)937)).toString(), "&Omega;");
		result= substitute(result, (new Character((char)945)).toString(), "&alpha;");
		result= substitute(result, (new Character((char)946)).toString(), "&beta;");
		result= substitute(result, (new Character((char)947)).toString(), "&gamma;");
		result= substitute(result, (new Character((char)948)).toString(), "&delta;");
		result= substitute(result, (new Character((char)949)).toString(), "&epsilon;");

		result= substitute(result, (new Character((char)1013)).toString(), "&#1013;");

		result= substitute(result, (new Character((char)950)).toString(), "&zeta;");
		result= substitute(result, (new Character((char)951)).toString(), "&eta;");
		result= substitute(result, (new Character((char)952)).toString(), "&theta;");
		result= substitute(result, (new Character((char)953)).toString(), "&iota;");
		result= substitute(result, (new Character((char)954)).toString(), "&kappa;");
		result= substitute(result, (new Character((char)955)).toString(), "&lambda;");
		result= substitute(result, (new Character((char)956)).toString(), "&mu;");
		result= substitute(result, (new Character((char)957)).toString(), "&nu;");
		result= substitute(result, (new Character((char)958)).toString(), "&xi;");
		result= substitute(result, (new Character((char)959)).toString(), "&omicron;");
		result= substitute(result, (new Character((char)960)).toString(), "&pi;");
		result= substitute(result, (new Character((char)961)).toString(), "&rho;");
		result= substitute(result, (new Character((char)962)).toString(), "&sigmaf;");
		result= substitute(result, (new Character((char)963)).toString(), "&sigma;");
		result= substitute(result, (new Character((char)964)).toString(), "&tau;");
		result= substitute(result, (new Character((char)965)).toString(), "&upsilon;");
		result= substitute(result, (new Character((char)966)).toString(), "&phi;");
		result= substitute(result, (new Character((char)967)).toString(), "&chi;");
		result= substitute(result, (new Character((char)9674)).toString(), "&loz;");
		result= substitute(result, (new Character((char)968)).toString(), "&psi;");
		result= substitute(result, (new Character((char)969)).toString(), "&omega;");
		result= substitute(result, (new Character((char)977)).toString(), "&thetasym;");
		
		//result= substitute(result, (new Character((char)978)).toString(), "&upsih;");
		result= substitute(result, (new Character((char)978)).toString(), "&Upsilon;");
		
		result= substitute(result, (new Character((char)982)).toString(), "&piv;");
		result= substitute(result, (new Character((char)9824)).toString(), "&spades;");
		result= substitute(result, (new Character((char)9827)).toString(), "&clubs;");
		result= substitute(result, (new Character((char)9829)).toString(), "&hearts;");
		result= substitute(result, (new Character((char)9830)).toString(), "&diams;");

		result= substitute(result, (new Character((char)168)).toString(), "&#168;");
		result= substitute(result, (new Character((char)729)).toString(), "&#729;");
		result= substitute(result, (new Character((char)8214)).toString(), "&#8214;");
		result= substitute(result, (new Character((char)8289)).toString(), "&#8289;");
		result= substitute(result, (new Character((char)8290)).toString(), "&#8290;");

		result= substitute(result, (new Character((char)8304)).toString(), "&#8304;");
		result= substitute(result, (new Character((char)8308)).toString(), "&#8308;");
		result= substitute(result, (new Character((char)8309)).toString(), "&#8309;");
		result= substitute(result, (new Character((char)8310)).toString(), "&#8310;");
		result= substitute(result, (new Character((char)8311)).toString(), "&#8311;");
		result= substitute(result, (new Character((char)8312)).toString(), "&#8312;");
		result= substitute(result, (new Character((char)8313)).toString(), "&#8313;");

		result= substitute(result, (new Character((char)8320)).toString(), "&#8320;");
		result= substitute(result, (new Character((char)8321)).toString(), "&#8322;");
		result= substitute(result, (new Character((char)8322)).toString(), "&#8322;");
		result= substitute(result, (new Character((char)8323)).toString(), "&#8323;");
		result= substitute(result, (new Character((char)8324)).toString(), "&#8324;");
		result= substitute(result, (new Character((char)8325)).toString(), "&#8325;");
		result= substitute(result, (new Character((char)8326)).toString(), "&#8326;");
		result= substitute(result, (new Character((char)8327)).toString(), "&#8327;");
		result= substitute(result, (new Character((char)8328)).toString(), "&#8328;");
		result= substitute(result, (new Character((char)8329)).toString(), "&#8329;");

		result= substitute(result, (new Character((char)8484)).toString(), "&#8484;");
		result= substitute(result, (new Character((char)8477)).toString(), "&#8477;");

		result= substitute(result, (new Character((char)8518)).toString(), "&#8518;");
		result= substitute(result, (new Character((char)8597)).toString(), "&#8597;");
		result= substitute(result, (new Character((char)8598)).toString(), "&#8598;");
		result= substitute(result, (new Character((char)8599)).toString(), "&#8599;");
		result= substitute(result, (new Character((char)8600)).toString(), "&#8600;");
		result= substitute(result, (new Character((char)8601)).toString(), "&#8601;");
		result= substitute(result, (new Character((char)8614)).toString(), "&#8614;");
		result= substitute(result, (new Character((char)8617)).toString(), "&#8617;");
		result= substitute(result, (new Character((char)8618)).toString(), "&#8618;");
		result= substitute(result, (new Character((char)8636)).toString(), "&#8636;");
		result= substitute(result, (new Character((char)8640)).toString(), "&#8640;");
		result= substitute(result, (new Character((char)8708)).toString(), "&#8708;");
		result= substitute(result, (new Character((char)8728)).toString(), "&#8728;");
		result= substitute(result, (new Character((char)8737)).toString(), "&#8737;");
		result= substitute(result, (new Character((char)8738)).toString(), "&#8738;");
		result= substitute(result, (new Character((char)8741)).toString(), "&#8741;");
		result= substitute(result, (new Character((char)8742)).toString(), "&#8742;");
		result= substitute(result, (new Character((char)8748)).toString(), "&#8748;");
		result= substitute(result, (new Character((char)8749)).toString(), "&#8749;");
		result= substitute(result, (new Character((char)8750)).toString(), "&#8750;");
		result= substitute(result, (new Character((char)8769)).toString(), "&#8769;");
		result= substitute(result, (new Character((char)8775)).toString(), "&#8775;");
		result= substitute(result, (new Character((char)8777)).toString(), "&#8777;");
		result= substitute(result, (new Character((char)8802)).toString(), "&#8802;");

		result= substitute(result, (new Character((char)8808)).toString(), "&#8808;");
		result= substitute(result, (new Character((char)8809)).toString(), "&#8809;");
		
		result= substitute(result, (new Character((char)8814)).toString(), "&#8814;");
		result= substitute(result, (new Character((char)8815)).toString(), "&#8815;");
		result= substitute(result, (new Character((char)8837)).toString(), "&#8837;");
		result= substitute(result, (new Character((char)8840)).toString(), "&#8840;");
		result= substitute(result, (new Character((char)8841)).toString(), "&#8841;");
		result= substitute(result, (new Character((char)8968)).toString(), "&#8968;");
		result= substitute(result, (new Character((char)8969)).toString(), "&#8969;");
		result= substitute(result, (new Character((char)8970)).toString(), "&#8970;");
		result= substitute(result, (new Character((char)8971)).toString(), "&#8971;");
		result= substitute(result, (new Character((char)9001)).toString(), "&#9001;");
		result= substitute(result, (new Character((char)9002)).toString(), "&#9002;");

		result= substitute(result, (new Character((char)10815)).toString(), "&#10815;");
		result= substitute(result, (new Character((char)10887)).toString(), "&#10887;");
		result= substitute(result, (new Character((char)10888)).toString(), "&#10888;");

		result= substitute(result, (new Character((char)57968)).toString(), "&#57968;");
		result= substitute(result, (new Character((char)57969)).toString(), "&#57969;");

		//result= substitute(result, (new Character((char)58682)).toString(), "&#58682;");
		//result= substitute(result, (new Character((char)58697)).toString(), "&#58697;");
		//result= substitute(result, (new Character((char)58705)).toString(), "&#58705;");
		result= substitute(result, (new Character((char)58682)).toString(), "&#x2102;");
		result= substitute(result, (new Character((char)58697)).toString(), "&#x211d;");
		result= substitute(result, (new Character((char)58705)).toString(), "&#x2124;");
		
		result= substitute(result, (new Character((char)57878)).toString(), "&#57878;");
		
		result= substitute(result, (new Character((char)61218)).toString(), "&#61218;");
		result= substitute(result, (new Character((char)62338)).toString(), "&#62338;");
		
		result= substitute(result, (new Character((char)65079)).toString(), "&#65079;");
		
		result= substitute(result, (new Character((char)344)).toString(), "&#344;");
		result= substitute(result, (new Character((char)345)).toString(), "&#345;");
		
		result= convertNonRomanToEntities( result, formatNonRomanAsHTML );		
		
		return(result);
	}
	public static String convertNonRomanToEntities( String input, boolean formatNonRomanAsHTML )
	{
		char[] inputArray = input.toCharArray();
		StringBuilder result = new StringBuilder();
		boolean nonRomanPresent= false;
		boolean prevCharReplaced = false;
		
		for (int j=0; j<inputArray.length; j++)
		{
			nonRomanPresent = false;			
			if(inputArray[j]>=0 && inputArray[j]<=255){//Ascii
				if(prevCharReplaced){
					result.append("<!-- ac --></span>");					
				}
				result.append(inputArray[j]);
				prevCharReplaced = false;
				continue;
			}else if(inputArray[j]>=0x4e00 && inputArray[j]<=0x9fff){ //CJK Unified
				nonRomanPresent = true;
			}else if(inputArray[j]>=0x3000 && inputArray[j]<=0x31ff){//Japanese, Korean & Asian Punctuation 1
				nonRomanPresent = true;
			}else if(inputArray[j]>=0xff00 && inputArray[j]<=0xff3f){//Asian Punctuation 2
				nonRomanPresent = true;
			}else if(inputArray[j]>=0xac00 && inputArray[j]<=0xd79f){//Korean
				nonRomanPresent = true;
			}else if(inputArray[j]>=0x0900 && inputArray[j]<=0x0dff){//Indian 1
				nonRomanPresent = true;
			}else if(inputArray[j]>=0x0400 && inputArray[j]<=0x04ff){//Cyrillic
				nonRomanPresent = true;
			}else if(inputArray[j]>=0x1900 && inputArray[j]<=0x194f){//Indian 2
				nonRomanPresent = true;
			}else if(inputArray[j]>=0x0a800 && inputArray[j]<=0x0a82f){//Indian 3
				nonRomanPresent = true;
			}else{
				result.append(inputArray[j]);
			}
			if (nonRomanPresent)
			{
				if (formatNonRomanAsHTML && !prevCharReplaced){
						result.append("<span style=\"font-family: Arial Unicode MS\">&#" ).append((int)inputArray[j]).append(";");											
						if(j ==inputArray.length-1){
							result.append("<!-- ac --></span>");
						}
						prevCharReplaced = true;
				}
				else {
					result.append("&#" ).append((int)inputArray[j]).append(";");
					if(j ==inputArray.length-1 && prevCharReplaced){
						result.append("<!-- ac --></span>");
					}
				}
			}
		}
		
		return result.toString();
	}
	public static String fixAmpersands( String input )
	{
		//System.out.println("input:");
		//System.out.println(input);
		//System.out.println("");
		if (input.indexOf("&") < 0) return input;
		
		String result= "";
		boolean inURL= false;
		boolean inEntity= false;
		String thisEntity= "";
		
		for (int i=0 ; i<input.length() ; i++) 
		{
			if (inURL)
			{
				result += input.substring(i, i+1);
				if (input.charAt(i) == '"') inURL= false;
			}
			else if (inEntity) 
			{
				if (input.charAt(i) == '&')
				{
					//System.out.println("entity not terminated - new started " + thisEntity);
					
					thisEntity= substitute(thisEntity, "&", "!AMPERSAND!");
					result += substitute(thisEntity, "!AMPERSAND!", "&amp;");
					thisEntity= "&";
				}
				else if (input.charAt(i) == ';')
				{
					inEntity=false;
					thisEntity += ";";
					//System.out.println("entity terminated normally " + thisEntity);
					
					if (!validEntityCharacters(thisEntity))
					{
						thisEntity= substitute(thisEntity, "&", "!AMPERSAND!");
						result += substitute(thisEntity, "!AMPERSAND!", "&amp;");
					}
					else
						result += thisEntity;
				}
				else
				{
					thisEntity += input.substring(i, i+1);
					
					if (thisEntity.length() > 14)
					{
						inEntity= false;
						//System.out.println("entity too long " + thisEntity);

						thisEntity= substitute(thisEntity, "&", "!AMPERSAND!");
						result += substitute(thisEntity, "!AMPERSAND!", "&amp;");
					}
				}
			}
			else 
			{
				if (input.charAt(i) == '&')
				{
					inEntity=true;
					thisEntity= "&";
				}
				else if (input.substring(i).startsWith("\"http"))
				{
					if (inEntity)
					{
						thisEntity= substitute(thisEntity, "&", "!AMPERSAND!");
						result += substitute(thisEntity, "!AMPERSAND!", "&amp;");
						inEntity= false;
					}
					result += input.substring(i, i+1);
					inURL= true;
				}
				else 
					result += input.substring(i, i+1);
			}
		}
		
		if (inEntity)
		{
			//System.out.println("entity hanging " + thisEntity);
			
			thisEntity= substitute(thisEntity, "&", "!AMPERSAND!");
			result += substitute(thisEntity, "!AMPERSAND!", "&amp;");
		}
		
		//System.out.println("");
		//System.out.println("result: " + result);
		return(result);
	}
	public static boolean validEntityCharacters( String input )
	{
		String permittedCharacters= "0123456789";
		permittedCharacters += "&#;";
		permittedCharacters += "abcdefghijklmnopqrstuvwxyz";
		permittedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				
		for (int i=0 ; i< input.length() ; i++)
		{
			String theChar= input.substring(i, i+1);
			if (permittedCharacters.indexOf(theChar) < 0) return(false);
		}
		
		return(true);
	}
	
	public static ConcurrentHashMap hashFromStream(InputStream bStream) {
		ConcurrentHashMap result = new ConcurrentHashMap();

		if (bStream != null) {
			try {
				DataInputStream input = new DataInputStream(bStream);

				int count = input.readInt();
				for (int i = 0; i < count; i++) {
					String key = input.readUTF();
					String value = readString(input);
					result.put(key, value);
				}
				bStream.close();
			} catch (IOException i) {
				// System.out.println("IOException in tp_sql.hashFromStream()");
				// i.printStackTrace();
			}
		}

		return (result);
	}
	
	public static byte[] hashToArray( ConcurrentHashMap theHash )
	{
		byte result[] = null;

		try 
		{
			ByteArrayOutputStream bStream= new ByteArrayOutputStream();
			DataOutputStream out= new DataOutputStream( bStream );

			out.writeInt( theHash.size() );

			Enumeration theKeys= theHash.keys();
			for (int i=0 ; theKeys.hasMoreElements() ; i++) 
			{
				String key= (String)theKeys.nextElement();
				out.writeUTF( key );

				String value= (String)theHash.get(key);
				if (value == null) value= "";
				writeString( value, out );
			}

			out.flush();			
			result= bStream.toByteArray();

			out.close();
			bStream.close();
		} 
		catch (Exception i) 
		{
			System.out.println("Exception in tp_sql.hashToArray()");
			i.printStackTrace();
		}

		return( result );
	}
	
	/**
	* Writes UTF-8 text from the given String to the given output stream.
	* Handles very large text (&gt; 65,535 characters).
	*
	* @param theString
	* String containing the text to be written as UTF-8 text to the stream.
	*
	* @param out
	* DataOutputStream instance to which the UTF-8 text is to be written.
	*
	* @throws java.io.IOException
	*/
	public static void writeString( String theString, DataOutputStream out )
		throws IOException
	{
		if (theString.length() > 65535) 
		{
			//System.out.println("outputting " + LONG_STRING);
			out.writeUTF( LONG_STRING );
			byte[] barray= theString.getBytes("UTF-8");
			out.writeInt( barray.length );
			out.write( barray, 0, barray.length);
		}
		else
			out.writeUTF( theString );
	}
}
