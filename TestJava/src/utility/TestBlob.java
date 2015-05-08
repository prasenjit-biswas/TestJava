package utility;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import Dataretrieval.util.DBConnection;
import Dataretrieval.util.OracleConnection;


public class TestBlob {
  public static void main(String[] args) {
	Connection con = null;
	PreparedStatement pst = null;
    ResultSet rs = null;	    
    InputStream is = null;
    try{
      con = OracleConnection.getConnection();
	  
      pst = con.prepareStatement("SELECT * FROM tests WHERE testid = ?");
      pst.setString(1, "12485782279706112");
      rs = pst.executeQuery();
      if(rs.next()){
    	is = rs.getBinaryStream("data");
      }
      
      DBConnection.releaseResources(con, pst, rs);
      
      if(is != null){
        System.out.println("#### before byte operation : " + new Timestamp(System.currentTimeMillis()));
        System.out.println("#### is.available : " + is.available());
        InputStream dbin= new DataInputStream(is);
		ByteArrayOutputStream bout= new ByteArrayOutputStream();
 		byte[] buffer = new byte[32768];
		for(;;) {	
			int size = dbin.read(buffer);
			if (size == -1) break;
			bout.write(buffer,0,size);
		}
		dbin.close();
		bout.flush();
		byte barray[]= bout.toByteArray();
		bout.close();
		
		InputStream bin= new ByteArrayInputStream(barray);
		DataInputStream input= new DataInputStream(bin);
		System.out.println("#### after byte operation : " + new Timestamp(System.currentTimeMillis()));
      }	
	}catch(Exception ex){
		ex.printStackTrace();
	}
  }
}
