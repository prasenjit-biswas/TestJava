package utility;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUtility{
	public static void main(String[] v) {
	  try{
		  String url = "http://dev3.mhhe.com/hm_copyx.tpx";
		  String postData = "todo=copyx&transaction_id=164667388&key=a292c198619f47548331ad8758f76e9a&xml=<transaction name=\"copyx\" version=\"3\" transaction_id=\"164667388\"><assignment id=\"164667388\" original_nativeid=\"13140034982093200\" new_title=\"Copyx of Practice Exam 1\" estination_owner=\"2850898\" /></transaction>";
		  int timeout = 3 * 60 * 1000;
		  String xmlString = postDataToURL(url, postData, timeout);
		  System.out.println("result : " + xmlString);
		  XmlUtils.buildDOMTree(xmlString);
	  }catch(Exception ex){
		  ex.printStackTrace();
	  }
	}
	
	  public static String postDataToURL(String url, String postData, int timeout) throws Exception {
	        URL scoringURL = new URL(url);
	        HttpURLConnection conn = (HttpURLConnection) scoringURL.openConnection();
	        StringBuffer sb2 = new StringBuffer();
	        try {
	            // Tell browser to allow me to send data to server.
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setConnectTimeout(timeout);
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            //conn.setRequestProperty("Referer", GenUtil.getServerURL());

	            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);

	            // Stream that writes into buffer
	            PrintWriter out = new PrintWriter(byteStream, true);

	            // Write POST data into local buffer
	            out.print(postData);
	            out.flush(); // Flush since above used print, not println
	            // POST requests are required to have Content-Length
	            String lengthString = String.valueOf(byteStream.size());
	            conn.setRequestProperty("Content-Length", lengthString);

	            // Write POST data to real output stream
	            byteStream.writeTo(conn.getOutputStream());
	            int responseCode = conn.getResponseCode();
	            if(responseCode == 606 && postData.indexOf("connectForceSubmission") != -1){
	            	sb2.append("WID_NOT_FOUND");
	            	return sb2.toString();
	            }
	             InputStream in = conn.getInputStream();
	             int x = 0;
	             byte[] read = new byte[1024 * 8];
	             while ((x = in.read(read)) != -1) {
	                 sb2.append(new String(read, 0, x));
	             }
	        }
	        catch(Exception ex)
	        {
				ex.printStackTrace();
	        }
	        finally {
	            conn.disconnect();
	            long endtime = System.currentTimeMillis();
	    	}
	        return sb2.toString();
	    }
}