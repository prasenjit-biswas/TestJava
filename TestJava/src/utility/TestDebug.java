package utility;

public class TestDebug {
	
	private static String getDebug(String baseServer, String inputDebug) throws Exception {
		if(inputDebug == null ){
			throw new Exception(" inputDebug is coming as Null");
		}
		String debug = inputDebug;
		if(baseServer!= null && baseServer.indexOf("-")>0){
			String[] baseServerArr = baseServer.split("-");
			String baseServerSplit = baseServerArr[1];
			if(baseServerSplit != null && baseServerSplit.indexOf(".")>0){
				baseServer = baseServerSplit.substring(0,baseServerSplit.indexOf("."));
				debug = inputDebug+baseServer;
			}
		}
		return debug;
	}
	
	public static void main(String[] args) {
		System.out.println(getDebug("http://ezto-dev-dev2.mhecloud.mhhe.com/", "testStrudentMapView"));
	}
}
