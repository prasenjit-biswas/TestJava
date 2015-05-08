package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TestCSVFile {
	static String  fileName = "./src/utility/MediaCount_Test4.csv";
	public static void main(String[] args) {
		
		String ownerID = "";
		 int index =  ("13634|32334616|").indexOf("|");
		 if(index != -1){
			 ownerID = ("13634|32334616|").substring(0, index);
		 }else{
			 ownerID = ("13634|32334616|");
		 }
		System.out.println("ownerID : "+ownerID);
		
		/* try{  
		  File f = new File(fileName);
		  if(!f.exists()){
			  f.createNewFile();
		  }
		  FileWriter writer = new FileWriter(f);
		  writer.append("      TESTID     "); 
		  writer.append(',');
		  writer.append("      QUESTIONID ");
		  writer.append(',');
		  writer.append("      MEDIAID   ");
		  writer.append(',');
		  writer.append(String.valueOf("   MEDIACOUNT    "));
		  writer.append('\n');
		  writer.flush();
		  writer.close();
		  
		  callAnotherMethod();
		  
		 }catch(Exception e){
			 e.printStackTrace();
		 }*/
	}

   public static void callAnotherMethod() throws Exception
   {
	   File f = new File(fileName);
	   for (int i =0; i<2; i++){
		   System.out.println("value of I : "+i);
		      
		      if(!f.exists()){
				  f.createNewFile();
			  }
		      /*BufferedReader br = new BufferedReader(new FileReader(f));
		      */BufferedWriter out = new BufferedWriter( new FileWriter(f, true));
			  
			  
			  /*String line = "";
			  while((line = br.readLine()) != null){
				  System.out.println(" line "+line );
			  }*/
			  
			  StringBuilder str = new StringBuilder("Testid"+i);
			  str.append(',');
			  str.append("QUESTIONID"+i);
			  str.append(',');
			  str.append("MEDIAID"+i);
			  str.append(',');
			  str.append(String.valueOf("MEDIACOUNT"+i));
			  str.append('\n');
			  out.append(str.toString());
			  out.close();
			  /*writer.append("Testid"+i); 
			  writer.append(',');
			  writer.append("QUESTIONID"+i);
			  writer.append(',');
			  writer.append("MEDIAID"+i);
			  writer.append(',');
			  writer.append(String.valueOf("MEDIACOUNT"+i));
			  writer.append('\n');
			  writer.flush();
			  writer.close();*/
	   }
   }

}
