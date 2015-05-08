package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.StringTokenizer;



public class richMediaUtil {
	
	public static int		MEDIA_TYPE_undefined= 0;

	/** constant representing a gif or jpg image media type */
	public static int		MEDIA_TYPE_image = 1;

	/** constant representing a .mid or .au sound media type */
	public static int		MEDIA_TYPE_sound = 2;

	/** constant representing a .mpg or .mov video media type */
	public static int		MEDIA_TYPE_movie = 3;

	/** constant representing a Shockwave animation media type */
	public static int		MEDIA_TYPE_shockwave = 4;

	/** constant representing a Flash animation media type */
	public static int		MEDIA_TYPE_flash = 5;

	/** constant representing a RealPlayer media type */
	public static int		MEDIA_TYPE_realplayer = 5;

	/** constant representing a TeX media type */
	public static int		MEDIA_TYPE_tex = 6;

	/** constant representing a LaTeX media type */
	public static int		MEDIA_TYPE_latex = 7;

	/** constant representing a tcx media type */
	public static int		MEDIA_TYPE_tcx = 8;

	/** constant representing a MathML media type */
	public static int		MEDIA_TYPE_mml = 9;

	/** constant representing a MathML media type */
	public static int		MEDIA_TYPE_mp3 = 10;

	/** constant representing a Marvin media type */
	public static int		MEDIA_TYPE_marvin = 11;

	/** constant representing a Marvin media type */
	public static int		MEDIA_TYPE_flashvideo = 12;

	/** constant representing an HTML5 mp4 movie */
	public static int		MEDIA_TYPE_mp4video = 13;


	//static String	CUSTOM_AUDIO_PLAY_BUTTON = "/EZTestOnline/MediaPlayers/audio/sp_play.png";


	/** constant representing an externally defined media type */
	public static int		MEDIA_TYPE_EXTERNAL = 13;


	/** constant to indicate that the media should be positioned above the question stem */
	public static int		MEDIA_POSITION_above = 0;

	/** constant to indicate that the media should be positioned to the left of the question stem */
	public static int		MEDIA_POSITION_left = 1;

	/** constant to indicate that the media should be positioned inside the question stem
	 * being substituted for any occurrence of %% in the stem */
	public static int		MEDIA_POSITION_inline = 2;

	/** constant to indicate that the media should be positioned to the right of the question stem */
	public static int		MEDIA_POSITION_right = 3;

	/** constant to indicate that the media should be positioned below the question stem */
	public static int		MEDIA_POSITION_below = 4;

	/** constant to indicate that the media should be positioned indented above the question stem */
	public static int		MEDIA_POSITION_indentedabove = 5;

	/** constant to indicate that the media should be positioned indented below the question stem */
	public static int		MEDIA_POSITION_indentedbelow = 6;

	/** constant to indicate that the media should be positioned centered and above the question stem */
	public static int		MEDIA_POSITION_centeredabove = 7;

	/** constant to indicate that the media should be positioned centered and below the question stem */
	public static int		MEDIA_POSITION_centeredbelow = 8;


	/** true, if a URL has been specified as opposed to a simple filename */
	public boolean	isURL = false;

	/** true, if the media item is to be used as a response choice in a question */
	boolean			isChoice= false;

	static String	FLV_PLAYER = "/EZTestOnline/flowplayer-3.2.4.min.js";
	static String	FLOWPLAYER = "/EZTestOnline/flowplayer.commercial-3.2.4.swf";
	
	static String	FLOW_AUDIO_PLAYER = "/EZTestOnline/flowplayer.audio-connect-3.2.1.swf";
	
	static String	FLOW_CONTROLS = "/EZTestOnline/flowplayer.controls.connect.swf";

	public static String	FLOW_PLAYER_SCRIPTS = "<script type=\"text/javascript\" src=\"" + FLV_PLAYER + "\"></script>\r";

	/** the type of this media item - default: undefined */
	int				type = MEDIA_TYPE_undefined;

	/** width of screen region to be allocated to this media item's player - default: 320 */
	int				movieWidth= 320;

	/** height of screen region to be allocated to this media item's player - default: 240 */
	int				movieHeight= 240;

	/** position where this media item should be displayed, relative to the question stem - default: to left of stem */
	int				position = MEDIA_POSITION_left;


	/** the String representing this media item */
	public String	mediaString;
	
	public boolean isChoice() {
		return isChoice;
	}
	public void setChoice(boolean isChoice) {
		this.isChoice = isChoice;
	}
	
	public richMediaUtil( String media ) throws Exception
	{
		// v4.1.3a15 handle extreme cases
		if (media == null)
			throw (new Exception( "empty media" ) );

		if (media.length() == 0)
			throw (new Exception( "empty media" ) );

		if (media.length() > 512)
			throw (new Exception( "media too long" ) );

		StringTokenizer mediaTokens= new StringTokenizer( media, "," );
		
		mediaString= mediaTokens.nextToken();
		
		// v4.1.3a15 check for valid media type first
		if ( mediaString.toLowerCase().endsWith( ".jpg" ) ) type= MEDIA_TYPE_image;
		if ( mediaString.toLowerCase().endsWith( ".gif" ) ) type= MEDIA_TYPE_image;
		if ( mediaString.toLowerCase().endsWith( ".png" ) ) type= MEDIA_TYPE_image;
		
		if ( mediaString.toLowerCase().endsWith( ".mp3" ) ) type= MEDIA_TYPE_mp3;
		
		if ( mediaString.toLowerCase().endsWith( ".mp4" ) ) type= MEDIA_TYPE_mp4video;
		if ( mediaString.toLowerCase().endsWith( ".m4v" ) ) type= MEDIA_TYPE_mp4video;

		if ( mediaString.toLowerCase().endsWith( ".au" ) ) type= MEDIA_TYPE_sound;
		if ( mediaString.toLowerCase().endsWith( ".mid" ) ) type= MEDIA_TYPE_sound;
		if ( mediaString.toLowerCase().endsWith( ".midi" ) ) type= MEDIA_TYPE_sound;
		
		if ( mediaString.toLowerCase().endsWith( ".mov" ) ) type= MEDIA_TYPE_movie;
		if ( mediaString.toLowerCase().endsWith( ".avi" ) ) type= MEDIA_TYPE_movie;
		if ( mediaString.toLowerCase().endsWith( ".mpg" ) ) type= MEDIA_TYPE_movie;
		if ( mediaString.toLowerCase().endsWith( ".mpeg" ) ) type= MEDIA_TYPE_movie;
		
		if ( mediaString.toLowerCase().endsWith( ".dcr" ) ) type= MEDIA_TYPE_shockwave;
		
		if ( mediaString.toLowerCase().endsWith( ".swf" ) ) type= MEDIA_TYPE_flash;
		if ( mediaString.toLowerCase().endsWith( ".flv" ) ) type= MEDIA_TYPE_flashvideo;

		if ( mediaString.toLowerCase().endsWith( ".ra" ) ) type= MEDIA_TYPE_realplayer;
		if ( mediaString.toLowerCase().endsWith( ".ram" ) ) type= MEDIA_TYPE_realplayer;
		if ( mediaString.toLowerCase().endsWith( ".rpm" ) ) type= MEDIA_TYPE_realplayer;
		if ( mediaString.toLowerCase().endsWith( ".rm" ) ) type= MEDIA_TYPE_realplayer;

		if ( mediaString.toLowerCase().endsWith( ".tex" ) ) type= MEDIA_TYPE_tex;
		if ( mediaString.toLowerCase().endsWith( ".bbl" ) ) type= MEDIA_TYPE_tex;

		if ( mediaString.toLowerCase().endsWith( ".latex" ) ) type= MEDIA_TYPE_latex;
		if ( mediaString.toLowerCase().endsWith( ".ltx" ) ) type= MEDIA_TYPE_latex;

		if ( mediaString.toLowerCase().endsWith( ".tcx" ) ) type= MEDIA_TYPE_tcx;

		if ( mediaString.toLowerCase().endsWith( ".mml" ) ) type= MEDIA_TYPE_mml;

		if ( mediaString.toLowerCase().endsWith( ".mvn" ) ) type= MEDIA_TYPE_marvin;

		if ( mediaString.toLowerCase().endsWith( ".ext" ) ) type= MEDIA_TYPE_EXTERNAL;

		if ( mediaString.toLowerCase().startsWith( "http" ) ) isURL= true;
		
		if (type == MEDIA_TYPE_undefined)
			throw (new Exception( "undefined media type for: " + mediaString ) );

		// v4.1.3a15 then look for special formatting options
		if (mediaTokens.hasMoreTokens()) 
		{
			String pos= mediaTokens.nextToken();
			if (pos.startsWith( "above" )) position= MEDIA_POSITION_above;
			else if (pos.startsWith( "before" )) position= MEDIA_POSITION_left;
			else if (pos.startsWith( "inside" )) position= MEDIA_POSITION_inline;
			else if (pos.startsWith( "after" )) position= MEDIA_POSITION_right;
			else if (pos.startsWith( "below" )) position= MEDIA_POSITION_below;
			else if (pos.startsWith( "indented below" )) position= MEDIA_POSITION_indentedbelow;
			else if (pos.startsWith( "indented above" )) position= MEDIA_POSITION_indentedabove;
			else if (pos.startsWith( "centered above" )) position= MEDIA_POSITION_centeredabove;
			else if (pos.startsWith( "centered below" )) position= MEDIA_POSITION_centeredbelow;
		}
		
		if (mediaTokens.hasMoreTokens()) 
		{

			String movieFormat= mediaTokens.nextToken();
			if (movieFormat.equals("160 x 120")) {
				movieWidth= 160;
				movieHeight= 120;
			}
			else if (movieFormat.equals("320 x 240")) {
				movieWidth= 320;
				movieHeight= 240;
			}
			else if (movieFormat.equals("640 x 480")) {
				movieWidth= 640;
				movieHeight= 480;
			}
			else {
				StringTokenizer theTokens= new StringTokenizer( movieFormat, " x" );
				if (theTokens.hasMoreTokens()) {
					try {
						movieWidth= Integer.parseInt( theTokens.nextToken() );
					} catch (NumberFormatException e) {}
				}
				if (theTokens.hasMoreTokens()) {
					try {
						movieHeight= Integer.parseInt( theTokens.nextToken() );
					} catch (NumberFormatException e) {}
				}
			}
			
		}
		
	}
	public richMediaUtil(DataInputStream theInput, int format) {


		try {
			
			// read the booleans
			isURL= theInput.readBoolean();
			if (format >= 3) {
				isChoice= theInput.readBoolean();
				System.out.println(" isChoice : "+isChoice);
			}
			System.out.println(" isURL : "+isURL);
			// additional booleans go here
			
			
			// read the ints
			type= theInput.readInt();
			System.out.println(" type : "+type);
			movieWidth= theInput.readInt();
			System.out.println(" movieWidth : "+movieWidth);
			movieHeight= theInput.readInt();
			System.out.println(" movieHeight : "+movieHeight);
			if (format>=2) {
				position= theInput.readInt();
				System.out.println(" position : "+position);
			}
	
			// additional ints here
			
			
			// read the strings
			mediaString= theInput.readUTF();
			System.out.println(" mediaString : "+mediaString); 
			// additional strings here
			
			
			// read additional subclasses here
		
		} catch (IOException e) {
			e.printStackTrace();
					
		}
		
	
	}

}
