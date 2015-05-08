package utility;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.Writer;

public class AppletTest extends Applet {

	public static void main( String[] args){
		Graphics g = null;
		AppletTest at = new AppletTest();
		at.paint(g);
	}
	  
	public void paint(Graphics g)
	{
	  g.drawString("Hello world!", 50, 25);	
	}
}
