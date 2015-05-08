package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


public class randomVariableUtil {

	private double startValue;
	private double endValue;
	private double incrementValue;
	private double incrementAmount;
	private String		name = "undefined";
	private boolean usePool;
	private int count;
	private String inValue;
	private boolean arrayed;
	private boolean		calculated = false;
	private ArrayList		pool;

	private ArrayList<String> dependencies;



	private String calculation ;
	private Object poolObj;

	public randomVariableUtil(DataInputStream theInput, int format) {

		try {

			// read the doubles
			startValue= theInput.readDouble();
			endValue= theInput.readDouble();
			incrementValue= theInput.readDouble();
			incrementAmount= theInput.readDouble();

			// additional doubles here


			// read the strings
			name= theInput.readUTF();

			// additional strings here


			if (format >= 26) {
				usePool= theInput.readBoolean();
				if (usePool) {
					int count= theInput.readInt();
					pool= new ArrayList();
					for (int i=0 ; i<count ; i++)
					{
						String inValue= theInput.readUTF();
						if (inValue.indexOf(",") >= 0)
						{
							StringTokenizer theTokens= new StringTokenizer(inValue, ",");
							ArrayList<String> thisArray= new ArrayList<String>();
							while (theTokens.hasMoreTokens())
								thisArray.add( rv_recall(theTokens.nextToken().trim()) );
							pool.add(thisArray);
						}
						else
							pool.add( rv_recall(inValue) );
					}
				}
			}

			if (format >= 426) arrayed= theInput.readBoolean();

			dependencies= new ArrayList<String>();
			if (format >= 429)
			{
				int count= theInput.readInt();
				for (int i=0 ; i<count ; i++)
					dependencies.add(theInput.readUTF());
			}

			// read additional subclasses here
			if (format >= 430)
			{
				calculated= theInput.readBoolean();
				if (calculated)
					calculation= theInput.readUTF();
			}
			//print_randomVariableUtil();
		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public String getPoolString()
	{
		long start = new java.util.Date().getTime();
		if (!usePool) return("");
		
		StringBuilder result= new StringBuilder();
		System.out.println(" inside getPoolString() pool.size : " + pool.size());
		int poolSize = pool.size();
		int rowSize = 0;
		for (int i=0; i< poolSize; i++)
		{
			Object thisOne= pool.get(i);
			
			if (arrayed && (thisOne instanceof ArrayList))
			{
				ArrayList thisRow= (ArrayList)thisOne;
				rowSize = thisRow.size();
				for (int j=0; j< rowSize; j++)
				{
					if (j==0)
					{
						if (result.length() > 0){
							//result += ";";
							result.append(";");
						}
					}
					else{
						//result += ",";
						result.append(",");
					}
					//result += (String)thisRow.get(j);
					result.append((String)thisRow.get(j));
				}
			}
			else
			{
				if (result.length() > 0){
					//result += ",";
					result.append(",");
				}
				//result += (String)pool.get(i);
				result.append((String)pool.get(i));
			}
		}
		long end = new java.util.Date().getTime();
        System.out.println(" Time take getPoolString() " + (end - start) + "ms");

		return(result.toString());
	}
	
	private static String rv_recall( String input ) {
		String result= tp_utility.substitute(input, "XXXcommaXXX", ",");
		return result;
	}


	public void print_randomVariableUtil() {
		System.out.println("############ Inside randomVariableUtil ################");
		System.out.println(" startValue : " + startValue);
		System.out.println(" endValue : " + endValue);
		System.out.println(" incrementValue : " + incrementValue);
		System.out.println(" incrementAmount : " + incrementAmount);
		System.out.println(" name : " + name);
		System.out.println(" usePool : " + usePool);
		System.out.println(" count : " + count);
		System.out.println(" inValue : " + inValue);
		System.out.println(" arrayed : " + arrayed);
		System.out.println(" inValue : " + inValue);
		System.out.println("..... printing dependencies ....");
		for(String dependencieStr : dependencies)
		{
			System.out.println(" dependencies : " + dependencieStr);
		}
		
		System.out.println("..... printing pool ....");
		if(pool != null){
		  for(Object poolObj : pool)
		  {
			//System.out.println(" dependencies : " + dependencieStr);
			if(poolObj instanceof String){
				System.out.println(" pool String : " + poolObj.toString());
			} else if(poolObj instanceof ArrayList){
				Iterator itr = ((ArrayList) poolObj).iterator();
				while(itr.hasNext()){
					System.out.println(" pool ArrayList : " + itr.next());
				}
			}
		  }
		}
		System.out.println("..... printing getPoolString method ....");
		System.out.println(" getPoolString : " + getPoolString());
		
		System.out.println(" calculation : " + calculation);
		System.out.println("############ End randomVariableUtil ################");
	}


	public boolean isUsePool() {
		return usePool;
	}


	public void setUsePool(boolean usePool) {
		this.usePool = usePool;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getInValue() {
		return inValue;
	}


	public void setInValue(String inValue) {
		this.inValue = inValue;
	}


	public ArrayList getPool() {
		return pool;
	}


	public void setPool(ArrayList pool) {
		this.pool = pool;
	}

	public String getCalculation() {
		return calculation;
	}


	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}


	public double getStartValue() {
		return startValue;
	}


	public void setStartValue(double startValue) {
		this.startValue = startValue;
	}


	public double getEndValue() {
		return endValue;
	}


	public void setEndValue(double endValue) {
		this.endValue = endValue;
	}


	public double getIncrementValue() {
		return incrementValue;
	}


	public void setIncrementValue(double incrementValue) {
		this.incrementValue = incrementValue;
	}


	public double getIncrementAmount() {
		return incrementAmount;
	}


	public void setIncrementAmount(double incrementAmount) {
		this.incrementAmount = incrementAmount;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public boolean isArrayed() {
		return arrayed;
	}


	public void setArrayed(boolean arrayed) {
		this.arrayed = arrayed;
	}

	public ArrayList getDependencies() {
		return dependencies;
	}


	public void setDependencies(ArrayList dependencies) {
		this.dependencies = dependencies;
	}
	public boolean isCalculated() {
		return calculated;
	}


	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}

}
