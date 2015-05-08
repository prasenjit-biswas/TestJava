package Datafix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.utils.StringUtils;


//  /local/apps/weblogic/wls1033v9/wlserver_10.3/server/lib
public class MediaConsumerDestinationFix {
	
	/*
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = ewnvldb06-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb07-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb08-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = ertload)))";
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String orauser = "olcerlive";
	static String orapass = "ulcerlive2004";
    	
	
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = ewnvldb06-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb07-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb08-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = clssload)))";
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String orauser = "classware";
	static String orapass = "classware";
    */

	static final String HostURL = "http://ezto.mhhm.mcgraw-hill.com/";
	//static final String HostURL = "http://qaliveeztest.mhhe.com/";
	/*
	static final String oraurl      = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = nj09mhe0234-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = nj09mhe0235-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoqalv)))";
	static final String oradriver   = "oracle.jdbc.driver.OracleDriver";
	static final String orauser     = "eztoapp";
	static final String orapass     = "eztoapp123";
	static String siteid    = "muon:";
    */
	/** My SQL Dev */
	
	static String mysqlurl = "jdbc:mysql://nvldb06.eppg.com:3306/";
	static String mysqldb = "ezto_dev";
	static String mysqldriver = "com.mysql.jdbc.Driver";
	static String mysqluser = "ezto_dev";
	static String mysqlpass = "pixie";
	//static String siteid    = "muon:";

    /** My SQL Prod */
	/*
	static String mysqlurl = "jdbc:mysql://ewnvldb13.eppg.com:3306/";
	static String mysqldb = "aris_spark";
	static String mysqldriver = "com.mysql.jdbc.Driver";
	static String mysqluser = "aris_spark";
	static String mysqlpass = "pixie";
    static String siteid    = "eztestonline:";
	*/
	
	static final String  oraurl        = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb01-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb02-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb03-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb04-vip.eppg.com)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb05-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoprod)) )";
	static final String oradriver      = "oracle.jdbc.driver.OracleDriver";
    static final String orauser        = "ezto";
	static final String orapass        = "eztoprod";
	static final String siteid         = "eztestonline:";
	
	/*
	public static final String mySqlJDBCURL = "jdbc:mysql://ewnvldb15.eppg.com/aris_spark_pqa";
	public static final String mySqlusername = "aris_spark_pqa";
	public static final String mySqlpassword = "aris_spark_pqa";
	*/
	static ArrayList mediaConsumerList = new ArrayList();
	static ArrayList missingMediaList = new ArrayList();
	static Map mediaConsumerMap = new HashMap();
		
	/*
	static String oraurl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST = ewnvldb06-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb07-vip.eppg.com)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = ewnvldb08-vip.eppg.com)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = eztoload)))";
	static String oradriver = "oracle.jdbc.driver.OracleDriver";
	static String orauser = "ezto";
	static String orapass = "eztoload";
    */
	
	static String ORACLE_DB = "oracle";
	static String MYSQL_DB = "mysql";
	static String database = ORACLE_DB;
	static String GET_DISTINCT_DESTINATION_TID = "SELECT DISTINCT tb1.destinationtid FROM trackback tb,trackback tb1 " +
												 "WHERE tb.sourcetid = tb1.sourcetid AND tb.destinationtid = ?"; 

	static FileWriter fstream = null;
	static BufferedWriter out = null;
	
	public static void main(String argv[]) throws Exception{
    
	Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;       
    
    try {
    	con = getConnection();
    	String testId = argv[0];
    	if(testId == null || StringUtils.isEmpty(testId)){
    		throw new Exception("testId is blank or null : " + testId);
    	}
    	fstream = new FileWriter("src/Datafix/" + testId + "_output.txt");
    	out = new BufferedWriter(fstream);
    	out.write("\n********************************** Updating target media ***************************** ");
    	/*
    	String finalpath = printPath(con,"13041049905737343");
    	out.write("\nfinalpath " + finalpath);
    	*/
    	pst = con.prepareStatement(GET_DISTINCT_DESTINATION_TID);
    	pst.setString(1,testId);
	    rs = pst.executeQuery();
	      
	    while(rs.next()){
	      String destinationTid = rs.getString("destinationtid"); 
	      fixDestinationQuestionsMedia(con,destinationTid);
	    }
    } catch(SQLException exSQLException){
	    exSQLException.printStackTrace();
	} catch(Exception exException){
	    exException.printStackTrace();
    }finally{
    	if(rs != null){
    	  rs.close();
    	}
		if(pst != null){
    	  pst.close();
		}
		if(con != null){
		  con.close();
		}
		if(out != null){
		  out.close();	
		}
		if(fstream != null){
		  fstream.close();	
		}
    }
  }
	
	public static Connection getConnection() throws SQLException, Exception{
		  Connection con = null;
		  System.out.println("database : " + database);
		  if(database.equals(ORACLE_DB)){
	    	Class.forName(oradriver);
	        con = DriverManager.getConnection(oraurl, orauser, orapass);
	      } else if(database.equals(MYSQL_DB)){
	    	Class.forName(mysqldriver);
	        con = DriverManager.getConnection(mysqlurl + mysqldb, mysqluser, mysqlpass);
	      }
		  return con;
	}
	
	/*static String UPDATE_MEDIACONSUMERS = "UPDATE mediaconsumers mc SET mc.mediaid = ? " +
										  "WHERE mc.testID = ? AND mc.consumerid IN ('0',?) AND mc.NAME = ?";
    
	static String GET_DISTINCT_SOURCE_TID = "SELECT DISTINCT t1.sourcetid FROM trackback t1 WHERE t1.destinationtid = ?"; 
	
	static String GET_MEDIAID_DTLS_FOR_SOURDETID_CONSUMERID_0 = "SELECT DISTINCT mc.mediaid , mc.NAME FROM mediaconsumers mc " +
    													 "WHERE mc.testid = ? AND mc.consumerid = '0'"; 
    
	static String GET_CONSUMERID_FOR_SOURDETID_CONSUMERID_NONZERO = "SELECT DISTINCT mc.consumerid FROM " +
    														"mediaconsumers mc WHERE mc.testid = ? AND mc.NAME = ? " +
    														"AND mc.consumerid <> '0' AND mc.mediaid = ? ";
    
	static String GET_DESTINATIONTID_QID = "SELECT t1.destinationtid,t1.destinationqid FROM trackback t1 " +
    								"WHERE t1.sourcetid = ? AND t1.sourceqid = ? ";
    
	
	private static void fixDestinationQuestions(Connection con,String destinationTID) throws Exception{
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    PreparedStatement ps1 = null;
	    ResultSet rs1 = null;
	    PreparedStatement ps2 = null;
	    ResultSet rs2 = null;
	    PreparedStatement ps3 = null;
	    ResultSet rs3 = null;
	    PreparedStatement update = null;
	    int count = 0;
	    try{
	    	out.write("\n********************************** Updating target media ***************************** ");
	    	con.setAutoCommit(false);
	    	
	    	ps = con.prepareStatement(GET_MEDIAID_DTLS_FOR_SOURDETID_CONSUMERID_0);
	    	ps1 = con.prepareStatement(GET_CONSUMERID_FOR_SOURDETID_CONSUMERID_NONZERO);
	    	ps2 = con.prepareStatement(GET_DESTINATIONTID_QID);
	    	update = con.prepareStatement(UPDATE_MEDIACONSUMERS);
	    	
	    	ps3 = con.prepareStatement(GET_DISTINCT_SOURCE_TID);
		    ps3.setString(1,destinationTID);
		    rs3 = ps3.executeQuery();
	    	
		    while (rs3.next()){
		      String sourceTestId = rs3.getString("sourcetid");
		      
		      ps.setString(1,sourceTestId);
		      rs = ps.executeQuery();
		      
		      while(rs.next()){
		        long sourceMediaId = rs.getLong("mediaid");
		        String sourceMediaName = rs.getString("NAME");
		    	
		        ps1.setString(1,sourceTestId);
		        ps1.setString(2,sourceMediaName);
		        ps1.setLong(3,sourceMediaId);
			    rs1 = ps1.executeQuery();
			    
		        while(rs1.next()){
		    	  String sourceQId = rs1.getString("consumerid");
		    		
		    	  ps2.setString(1,sourceTestId);
			      ps2.setString(2,sourceQId);
			    	
				  rs2 = ps2.executeQuery();
				  while(rs2.next()){
				    String destinationTestID = rs2.getString("destinationtid");
				    String destinationQID = rs2.getString("destinationqid");
				      
				    update.setLong(1, sourceMediaId);
				    update.setString(2,destinationTestID);
				    update.setString(3,destinationQID);
				    update.setString(4,sourceMediaName);
				  	
				    out.write("\n UPDATE mediaconsumers mc SET mc.mediaid = "+ sourceMediaId + " WHERE mc.testID = '"+ destinationTestID + "' AND mc.consumerid IN ('0','" + destinationQID + "') AND mc.NAME = '" + sourceMediaName +"')");   
				    
				    update.addBatch();
				    count ++;
				    if(count%500 == 0){
					  update.executeBatch();
				    }
				    
				  }//end of while(rs2.next())
				  if(rs2 != null){
				    rs2.close();
				  }
		        }// end of  while(rs1.next())
		        if(rs1 != null){
				  rs1.close();
				}
		      }// end of while(rs.next())
		      if(rs != null){
				rs.close();
			  }
		    }// end of while (rs3.next())
		    
		    update.executeBatch();
		    
		    con.commit();
		}
	    catch(Exception ex){
	      con.rollback();
	      ex.printStackTrace();	
	    }
	    finally{
		   	if(update != null){
			    update.close();
			}
			if(rs2 != null){
			    rs2.close();
			}
			if(ps2 != null){
			    ps2.close();
			}
			if(rs1 != null){
			    rs1.close();
			}
			if(ps1 != null){
			    ps1.close();
			}
			if(rs != null){
			    rs.close();
			}
			if(ps != null){
			    ps.close();
			}
			if(rs3 != null){
			    rs3.close();
			}
			if(ps3 != null){
			    ps3.close();
			}
		}
	}*/
	
	static String GET_CORRUPTED_RECORDS = " SELECT mc.consumerid,mc.NAME FROM mediaconsumers mc " +
										  " WHERE mc.testid = ? AND NOT EXISTS ( SELECT 1 FROM media m WHERE m.mediaid = mc.mediaid)";
	
	static String GET_SOURCE_QID = " SELECT t1.sourcetid,t1.sourceqid FROM trackback t1 " +
								   " WHERE t1.destinationtid = ? AND t1.destinationqid = ? ";

	static String GET_MEDIA_QID = " SELECT mc.mediaID FROM mediaconsumers mc WHERE mc.testid = ? " +
								  " AND mc.consumerid = ? AND NAME = ? " +
								  " AND EXISTS ( SELECT 1 FROM media m WHERE m.mediaID = mc.mediaID) ";
	
	static String UPDATE_MEDIACONSUMER = " UPDATE mediaconsumers mc SET mc.mediaid = ? " +
	  									  " WHERE mc.testID = ? AND mc.consumerid = ? AND mc.NAME = ?";
	
	static String GET_MEDIAID = " SELECT mc.mediaID FROM mediaconsumers mc ,trackback t1 	 " +
									     " WHERE mc.testid = t1.sourceTid AND mc.consumerID = '0' AND mc.NAME = ? " +
									     " AND t1.destinationqid = ? AND t1.destinationtid = ? " +
									     " AND EXISTS ( SELECT 1 FROM media m WHERE m.mediaID = mc.mediaID)";
	
	//
	private static void fixDestinationQuestionsMedia(Connection con,String destinationTID) throws Exception{
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    PreparedStatement ps1 = null;
	    ResultSet rs1 = null;
	    PreparedStatement ps2 = null;
	    ResultSet rs2 = null;
	    PreparedStatement ps3 = null;
	    ResultSet rs3 = null;
	    int updateCounter = 0;
	    PreparedStatement updateConsumer = null;
        try{
          
          con.setAutoCommit(false);
      	  
          ps = con.prepareStatement(GET_CORRUPTED_RECORDS);
      	  ps1 = con.prepareStatement(GET_SOURCE_QID);
      	  ps2 = con.prepareStatement(GET_MEDIA_QID);
      	  ps3 = con.prepareStatement(GET_MEDIAID);
      	  updateConsumer = con.prepareStatement(UPDATE_MEDIACONSUMER);
      	  
      	  ps.setString(1, destinationTID);
      	  rs = ps.executeQuery();
      	  
      	  while(rs.next()){
      		/** Get Source Question ID */
      		String destinationQuestionID = rs.getString("consumerid");  
      		String destinationFileName = rs.getString("name");
      		
      		if(!destinationQuestionID.equalsIgnoreCase("0") && !StringUtils.isEmpty(destinationQuestionID)){
      		   /** Find the source Question ID */
      		   ps1.setString(1, destinationTID);
      		   ps1.setString(2, destinationQuestionID);
      		   rs1 = ps1.executeQuery();
      		  
      		   String sourceTID = "";
      		   String sourceQID = "";
      		 
      		   if(rs1.next()){
      		     sourceTID = rs1.getString("sourcetid");
      		     sourceQID = rs1.getString("sourceqid");
      		   }
      		   rs1.close();
      		   
      		   ps2.setString(1, sourceTID);
      		   ps2.setString(2, sourceQID);
      		   ps2.setString(3, destinationFileName);
      		   rs2 = ps2.executeQuery();
      		 
      		   String mediaID = "";        			 
      		   if(rs2.next()){
      		     mediaID = rs2.getString("mediaID");  	   
      		   }      
      		   rs2.close();
      		   if(StringUtils.isEmpty(mediaID)){
        		  ps2.setString(1, sourceTID);
        		  ps2.setString(2, "0");
          		  ps2.setString(3, destinationFileName);
          		  rs2 = ps2.executeQuery();
          		  if(rs2.next()){
          		    mediaID = rs2.getString("mediaID");  	   
          		  }
          		  rs2.close();
      		   }
      		   if(!StringUtils.isEmpty(mediaID)){
  		         updateConsumer.setString(1, mediaID);
  		         updateConsumer.setString(2, destinationTID);
  		         updateConsumer.setString(3, destinationQuestionID);
  		         updateConsumer.setString(4, destinationFileName);
  		         out.write("\n UPDATE mediaconsumers mc SET mc.mediaid = "+ mediaID + " WHERE mc.testID = '"+ destinationTID + "' AND mc.consumerid = '" + destinationQuestionID + "' AND mc.NAME = '" + destinationFileName +"'");
  		         updateConsumer.addBatch();
  		         updateCounter ++;
  		         
  		         if(updateCounter%500 == 0){
  		          updateConsumer.executeBatch();
  		         }
  		       }
      		}
      		else{
      		  ps3.setString(1, destinationFileName);
      		  ps3.setString(2, destinationQuestionID);
      		  ps3.setString(3, destinationTID);      		  
      		  rs3 = ps3.executeQuery();
      		  
      		  String mediaID = "";
      		  
      		  if(rs3.next()){
      			mediaID = rs3.getString("mediaID");  	        			  
      		  }
      		  
      		  rs3.close();
      		  
      		  if(!StringUtils.isEmpty(mediaID)){
	            updateConsumer.setString(1, mediaID);
  		        updateConsumer.setString(2, destinationTID);
  		        updateConsumer.setString(3, destinationQuestionID);
  		        updateConsumer.setString(4, destinationFileName);
  		        out.write("\n UPDATE mediaconsumers mc SET mc.mediaid = "+ mediaID + " WHERE mc.testID = '"+ destinationTID + "' AND mc.consumerid = '" + destinationQuestionID + "' AND mc.NAME = '" + destinationFileName +"'");
  		        updateConsumer.addBatch();
 		        updateCounter ++;
 		         
 		        if(updateCounter%500 == 0){
 		         updateConsumer.executeBatch();
 		        }     		   
      		  }
      		}
      	  }
      	  if(updateCounter > 0){
      	    updateConsumer.executeBatch();
      	  }
      	  
      	  con.commit();	
        }
        catch(Exception ex){
  	      con.rollback();
	      ex.printStackTrace();	            	
        }
        finally{
			if(updateConsumer != null){
			  updateConsumer.close();
			}
			if(rs2 != null){
			    rs2.close();
			}
			if(ps2 != null){
			    ps2.close();
			}
			if(rs1 != null){
			    rs1.close();
			}
			if(ps1 != null){
			    ps1.close();
			}
			if(rs != null){
			    rs.close();
			}
			if(ps != null){
			    ps.close();
			}
			if(rs3 != null){
			    rs3.close();
			}
			if(ps3 != null){
			    ps3.close();
			}
        }
	}
	
}

