package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class StandAloneConnectionProvider {
    protected static DataSource theSource = null;
    protected static Context initContext = null;
    final static String datasource = "jdbc/EZTESTDS";
    static {
         Hashtable props = new Hashtable();
	      props.put(Context.PROVIDER_URL, "t3://localhost:7001/");
	      props.put(Context.SECURITY_PRINCIPAL, "weblogic");
	      props.put(Context.SECURITY_CREDENTIALS, "weblogic11g");
	      props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
	      props.put(Context.SECURITY_AUTHENTICATION, "simple");
	       try {
	            initContext = new InitialContext(props);
	            theSource = (DataSource)initContext.lookup(datasource);
	    } catch (NamingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }

    }
        public static Connection getConnection() throws SQLException{
           Connection con = null;
        //   try
        //   {
                 //long start= (new java.util.Date()).getTime();
                 con= theSource.getConnection();
                 //long elapsed= (new java.util.Date()).getTime() - start;
                 //if(elapsed > 200)
                   //System.err.println("  Connection creation Time " + Long.toString(elapsed) + "ms");
                 if (con == null)
                 {
                   System.out.println("tp_sql().getConnection(): no connection");
                   throw new SQLException("connection");
                 }
//         }


           return(con);
         }
        
        public static void releaseResources(Connection jConnection){
    		if (jConnection != null) {
                try {
                		jConnection.close();
                } catch (SQLException e) {
                	
                      e.printStackTrace();
                      System.out.println(" Exception while closing connection  " + e.getMessage());
                	
                }
                jConnection = null;
            }
    	 }

    	public static void releaseResources(Statement stmt){
    		if (stmt != null) {
                try {
                		stmt.close();
                } catch (SQLException e) {
                	
                	  e.printStackTrace();
                      System.out.println(" Exception while closing statement " + e.getMessage());
                	
                }
                stmt = null;
            }
    	 }
    	
    	public static void releaseResources(ResultSet rst){
    		if (rst != null) {
                try {
                	rst.close();
                } catch (SQLException e) {
                	
                	  e.printStackTrace();
                      System.out.println(" Exception while closing ResultSet " + e.getMessage());
                	
                }
                rst = null;
            }
    	 }
    	
    	public static void releaseResources(Connection con, PreparedStatement pst, ResultSet rs){
    		releaseResources(rs);
    		releaseResources(pst);
    		releaseResources(con);
    	}
    	
    	public static void releaseResources(PreparedStatement pst, ResultSet rs){
    		releaseResources(rs);
    		releaseResources(pst);
    	}
        
        public static void main(String[] v){
                ExecutorService service = Executors.newFixedThreadPool(Integer.parseInt(v[0]));
                //ThreadTask task = new ThreadTask();
                for(int i=0;i<Integer.parseInt(v[0]);i++){
                        service.execute(new Task());
                        //task.run();
                }
                service.shutdown();
                try {
                        service.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS );
                } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }

}

class Task implements Runnable{

        public void run() {
                StandAloneConnectionProvider provider = new StandAloneConnectionProvider();
                PreparedStatement ps = null;
                Connection con = null;
                ResultSet rs = null;
                try {
                        long startTime = System.currentTimeMillis();
                        con = provider.getConnection();
                        ps = con.prepareStatement("Select 1 from dual");
                        rs = ps.executeQuery();
                        while(rs.next()){
                                rs.getString(1);//Do nothing
                        }
                        System.out.println("Thread : "+Thread.currentThread().getName() +" Time Taken : " + (System.currentTimeMillis()- startTime));
                        for(long i=0;i<1000000;i++){
                                //Do nothing
                        }
                        //System.out.println("Time Taken after loop Thread:"+Thread.currentThread().getName() +(System.currentTimeMillis()- startTime));

                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }finally{
                        try{
                                rs.close();
                                ps.close();
                                con.close();
                        }catch(Exception ex){
                                ex.printStackTrace();
                        }
                }

        }

}
