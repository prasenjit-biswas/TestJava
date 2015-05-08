package Dataretrieval.Submission;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import Dataretrieval.util.OracleConnection;





public class ReadFile {
	//final static String ATTEMPTDATA_QUERY_UPDATE = "UPDATE attemptdata SET params = ? WHERE ATTEMPTDATA_PK = ?";
	//final static String ATTEMPTDATA_QUERY = "SELECT params FROM attemptdata WHERE ATTEMPTDATA_PK = ?";
	
	
	//final static String ATTEMPTDATA_QUERY_UPDATE = "UPDATE attemptdata SET params = ? WHERE ATTEMPT_PK = ? AND QUESTIONID = ?";
	//final static String ATTEMPTDATA_QUERY = "SELECT params FROM attemptdata WHERE ATTEMPT_PK = ? AND QUESTIONID = ?";
	
	final static String ATTEMPT_QUERY_UPDATE = "UPDATE attempt SET params = ? WHERE ATTEMPT_PK = ?";
	final static String ATTEMPT_QUERY = "SELECT * FROM attempt WHERE ATTEMPT_PK = ?";	
	
	//final static String TEST_QUERY = "SELECT data from tests where testid = ?";


	public static void main(String[] args) throws Exception {

		Connection con = null;
		try {
			// get DB connection
			con = OracleConnection.getConnection();
			con.setAutoCommit(false);

			updateBlob(con);
			con.commit();			
		} catch (Exception exException) {
			System.out.print(exException);
			exException.printStackTrace();
			if (con != null) {
				con.rollback();
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public static void updateBlob(Connection con) throws Exception {
		String attempt_pk = "13252698045595774";
		//String questionid = "13252699828206322";
		
		//String attemptdata_pk = "13252700235122212";
		//int eval = 100;
		
		//String random = "a(6)=35030.00;a(5)=5.650;a=23;a(9)=7970;a(8)=43000;a(1)=43000;a(4)=12;a(11)=19000;a(7)=1.000;a(2)=6200;a(10)=62000;a(3)=10";
		
		PreparedStatement pst = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//String response = "removed by McGraw-Hill Education, contact Connect Support if you have any questions";
		
		//String in = "CF0=0,0,251,601,,,,f;CF1=248,0,555,601,,,R|15|562%d%Z|74.65|562%d%H|134.4|562%d%S|185.8|562,f;GFS=14;HGL=555;VGL=373;SLELD=219,48.1;DCKLD=300,80;ET=PRG;ZHP=200,200,425,75,4,100,220,686,30;PRGS0=When viewing a dominant phenotype%c% the __genotype__ can be unknown.,Review section 11.2 and figure 11.7. Read the description of a one-trait testcross and understand how a testcross can be used to determine the genotype of a dominant phenotype organism.,373,31;PRGS1=A dominant phenotype can be either homozygous dominant or __heterozygous__.,,373,117;PRGS2=By mating the unknown dominant organism with a homozygous __recessive__ organism%c% the genotype can be determined from the offspring.,,373,208;PRGS3=If the unknown organism is homozygous dominant%c% all offspring will be __dominant__.,,373,295;PRGS4=If the unknown is a heterozygote%c% there can be phenotypically __recessive__ individuals in the offspring.,,373,383;PRGT0=alleles,15,15;PRGT1=dominant,15,73.1;PRGT2=equal,15,131.2;PRGT3=genotype,15,189.25;PRGT4=heterozygous,15,247.4;PRGT5=homologous,15,305.5;PRGT6=mutant,15,363.6;PRGT7=recessive,15,421.7;PRGT8=square,15,479.8;PRGGV=;PRGRQ=;PRGRN=0,1,2,3,4,5,6,7,8,9;PRGPS=10,N,N,0,P,T;VST=0;";
		//String out = "CF0=0,501,249,109,,,R|19|10%d%Z|79|9%d%H|20|40%d%S|77|40,f;CF1=361,0,457,285,mad03482_06_11_unlabeled.jpg|0.25|3.8|-5.4, Copyright Brand X Pictures/PunchStock|200|10|F|183|247,,f;CF2=0,4,368,294,,,,f;CF3=249,277,515,330,,,,f;SLELD=160.5,79;DCKLD=300,80;ET=PRG;ZHP=200,200,425,75,3,200,100,425,375;PRGS0=C<sb>3</sb> photosynthesis takes place in regions with __moderate__ temperature and rainfall.,,371,306;PRGS1=This type of photosynthesis is named for the first __detectable__ molecule following __CO<sb>2</sb> fixation__: 3PG.,,371,393;PRGS2=In C<sb>3</sb> photosynthesis%c% the Calvin cycle reactions occur in the __mesophyll cells__.,,371,480;PRGT0=CO<sb>2</sb> fixation,15,19;PRGT1=mesophyll cells,185.5,19;PRGT2=traceable,15,108;PRGT3=detectable,185.5,108;PRGT4=moderate,15,197;PRGT5=bundle sheath,185.5,197;PRGGV=;PRGRQ=;PRGRN=0,1,2,3,4,5;PRGPS=6,N,N,0,P,T;VST=1;PRGDS=0,1,2,5,3,4;PRGSD=0|5,1|n|n,2|n;studentScore=25";
		//String userId = "9698805";
		//String passthrough = "userID:9698805;assignmentID:459832322;gradebookid:null;sectionid:6466334;role:S";
		//String pid = "9698805_459832323_1_6466334";
		//String qlist = "13112699147600431;13112699149033031;13112699147069431;13112699151056331;13112699148482031;13112699149644131;13112699147289831;13112699146959231;13112699149413731;13112699148311731;13112699149934631;13112699151186531;13112699150725731;13112699147880931;13112699148652331;13112699151116431;13112699150535331;13112699149203331;13112699148932831;13112699147339931";
		try{
			/*File file = new File("D:/biplab/state.txt");
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			StringBuilder state = new StringBuilder();
			while ((strLine = br.readLine()) != null)   {
				//System.out.println (strLine);
				state.append(strLine);
			}
			in.close();*/
			
			/*file = new File("D:/biplab/state1.txt");
			fstream = new FileInputStream(file);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			StringBuilder state1 = new StringBuilder();
			while ((strLine = br.readLine()) != null)   {
				//System.out.println (strLine);
				state1.append(strLine);
			}
			in.close();*/
			
			/*TestsTO testsTO = Test.getTestsTO(con, testid);
			Test test = new Test();*/
			
			/*if(testsTO != null){
			//	test = new tp_sql().get
			}*/
		//	test.getSecurityInfo().ownerName
			pst = con.prepareStatement(ATTEMPT_QUERY);
			pst.setLong(1, Long.valueOf(attempt_pk));//attemptpk
			//pst.setString(2, questionid);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				
				byte[] barray = rs.getBytes("params");
				ByteArrayInputStream bos = new ByteArrayInputStream(barray);
				ConcurrentHashMap<String, String> nameValueMap = Dataretrieval.Partials.tp_utility.hashFromStream(bos);
				Iterator it = nameValueMap.keySet().iterator();
				while(it.hasNext()){
					String key = (String)it.next();
					System.out.println("KEY :: "+key+" : VALUE :: "+nameValueMap.get(key));
				}


				nameValueMap.remove("p_instructions");
				
				it = nameValueMap.keySet().iterator();
				System.out.println("------------------------------------------------------");
				while(it.hasNext()){
					String key = (String)it.next();
					System.out.println("KEY :: "+key+" : VALUE :: "+nameValueMap.get(key));
				}
				
				/*PreparedStatement ps1 = con.prepareStatement(ATTEMPT_QUERY_UPDATE);		
				ps1.setBytes(1, Dataretrieval.Partials.tp_utility.hashToArray(nameValueMap));
				ps1.setLong(2, Long.valueOf(attempt_pk));//ATTEMPT_PK		
				ps1.executeUpdate();
				ps1.close();*/
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
	}
}

			// Get the object of DataInputStream
			/*DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		//Read File Line By Line
		boolean readKeyVal = false;

		//attemptDataPK = br.readLine().trim();
		ConcurrentHashMap nameValueMap = new ConcurrentHashMap();
		while ((strLine = br.readLine()) != null)   {
		//	System.out.println (strLine);
			String[] keys = strLine.split("-->");
			String key = keys[0].trim();
			String value = "";
			if(keys.length > 1 && keys[1] != null && !("").equals(keys[1]))
				value = keys[1].trim();
			nameValueMap.put(key, value);
			//System.out.println("key : " + key + " value : " + value);

		}*/
			/*in.close();

		Iterator nameValueMapItr = nameValueMap.keySet().iterator();
		while(nameValueMapItr.hasNext()){
			String key = (String)nameValueMapItr.next();
			System.out.println(key + "-->" + nameValueMap.get(key));
		}

		ByteArrayOutputStream bStream= new ByteArrayOutputStream();
		DataOutputStream out= new DataOutputStream( bStream );*/

			//System.out.println("############ attemptPK : " + attemptPK);


			/*PreparedStatement ps = con.prepareStatement(ATTEMPT_QUERY_UPDATE);		
		ps.setBytes(1, tp_utility.hashToArray(nameValueMap));
		ps.setLong(2, Long.valueOf(attemptPK));//ATTEMPT_PK		
		ps.executeUpdate();

		ps.close();*/
		
