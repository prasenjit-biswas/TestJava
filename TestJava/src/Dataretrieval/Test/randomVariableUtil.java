package Dataretrieval.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;


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

	private ArrayList dependencies;



	private String calculation ;

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
							Vector thisArray= new Vector();
							while (theTokens.hasMoreTokens())
								thisArray.addElement( rv_recall(theTokens.nextToken().trim()) );
							pool.add(thisArray);
						}
						else
							pool.add( rv_recall(inValue) );
					}
				}
			}

			if (format >= 426) arrayed= theInput.readBoolean();

			dependencies= new ArrayList();
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
			print_randomVariableUtil();
		} catch (IOException e) {

			e.printStackTrace();

		}

	}


	private static String rv_recall( String input ) {
		String result= tp_utility.substitute(input, "XXXcommaXXX", ",");
		return result;
	}


	private void print_randomVariableUtil() {
		System.out.println("############ Inside randomVariableUtil ################");
		System.out.println(" startValue randomVariableUtil " + startValue);
		System.out.println(" endValue randomVariableUtil " + endValue);
		System.out.println(" incrementValue randomVariableUtil " + incrementValue);
		System.out.println(" incrementAmount randomVariableUtil " + incrementAmount);
		System.out.println(" name randomVariableUtil " + name);
		System.out.println(" usePool randomVariableUtil " + usePool);
		System.out.println(" count randomVariableUtil " + count);
		System.out.println(" inValue randomVariableUtil " + inValue);
		System.out.println(" arrayed randomVariableUtil " + arrayed);
		System.out.println(" inValue randomVariableUtil " + inValue);
		System.out.println(" dependencies randomVariableUtil " + dependencies);
		System.out.println(" calculation randomVariableUtil " + calculation);
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
