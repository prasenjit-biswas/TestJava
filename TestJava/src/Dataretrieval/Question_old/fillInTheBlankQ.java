package Dataretrieval.Question;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
public class fillInTheBlankQ extends Question{
	public richPropertiesUtil	questionProperties = null;
	public static int		QUESTION_TYPE_undefined = 0;
	public int		type = QUESTION_TYPE_undefined;
	public static int		QUESTION_TYPE_fillBlank = 4;
	public static String		CASE_SENSITIVITY			= "caseSensitivity";
	public static String		CASE_SENSITIVITY_ON			= "on";
	public static String		CASE_SENSITIVITY_OFF		= "off";
	public static String		CASE_SENSITIVITY_OVERRIDE	= "caseOverride";

	public static String		IGNORE_ACCENTS				= "ignoreAccents";
	public static String		IGNORE_ACCENTS_OVERRIDE		= "ignoreAccentsOverride";

	public static String		IGNORE_SPACING				= "ignoreSpacing";
	public static String		IGNORE_SPACING_OVERRIDE		= "ignoreSpacingOverride";

	public static String		FIELD_HEIGHT				= "fieldHeight";
	public static String		FIELD_WIDTH					= "fieldWidth";

	public static String		RESPECT_UNITS				= "respectUnits";
	public static String		INCORRECT_WITHOUT_UNITS		= "incorrectWithoutUnits";
	public static String		RESPECT_SIGFIG				= "respectSignificantFigures";

	public static String		ENGINEERING_UNITS			= "engineeringUnits";
	public static String		FORMAT_STRING				= "formatString";

	public static String		UNIT_POINTS					= "unitPointAward";
	public static String		SIGNIFICANT_FIGURES			= "significantFigures";
	public static String		PRECISION_TYPE				= "precisionType";
	public static String		ABSOLUTE_PRECISION			= "absolutePrecision";

	public static String		DEFAULT_FORMAT				= "#";
	public static int			DEFAULT_WIDTH				= 40;
	public static int			DEFAULT_HEIGHT				= 1;
	public static int	PRECISION_PERCENT					= 0,
	PRECISION_ABSOLUTE					= 1;

	private	boolean		hasFormula= false;
	boolean 	checkUnits = false, 
	markIncorrectWithoutUnits = false, 
	caseSensitive= false, 
	useSignificantFigures= false;

	private	boolean		shortanswer= false;


	private	int			columns = DEFAULT_WIDTH;
	private	int			lines = DEFAULT_HEIGHT;
	int			unitPoints = 0, 
	precisionType= PRECISION_PERCENT, 
	branchCorrect = -1, 
	branchIncorrect = -1, 
	branchUnanswered = -1, 
	significantFigures= 0;
	private double		precision = 0;

	private	String		formula = "";
	private String 		engineeringUnits = "", 
	formatString = DEFAULT_FORMAT;


	private	ArrayList<String>		correctAnswers;
	ArrayList 		validUnits;
	public String getFormatString() {
		return formatString;
	}

	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}

	public boolean isHasFormula() {
		return hasFormula;
	}

	public void setHasFormula(boolean hasFormula) {
		this.hasFormula = hasFormula;
	}

	public boolean isCheckUnits() {
		return checkUnits;
	}

	public void setCheckUnits(boolean checkUnits) {
		this.checkUnits = checkUnits;
	}

	public boolean isMarkIncorrectWithoutUnits() {
		return markIncorrectWithoutUnits;
	}

	public void setMarkIncorrectWithoutUnits(boolean markIncorrectWithoutUnits) {
		this.markIncorrectWithoutUnits = markIncorrectWithoutUnits;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public boolean isUseSignificantFigures() {
		return useSignificantFigures;
	}

	public void setUseSignificantFigures(boolean useSignificantFigures) {
		this.useSignificantFigures = useSignificantFigures;
	}

	public boolean isShortanswer() {
		return shortanswer;
	}

	public void setShortanswer(boolean shortanswer) {
		this.shortanswer = shortanswer;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public int getUnitPoints() {
		return unitPoints;
	}

	public void setUnitPoints(int unitPoints) {
		this.unitPoints = unitPoints;
	}

	public int getPrecisionType() {
		return precisionType;
	}

	public void setPrecisionType(int precisionType) {
		this.precisionType = precisionType;
	}

	public int getBranchCorrect() {
		return branchCorrect;
	}

	public void setBranchCorrect(int branchCorrect) {
		this.branchCorrect = branchCorrect;
	}

	public int getBranchIncorrect() {
		return branchIncorrect;
	}

	public void setBranchIncorrect(int branchIncorrect) {
		this.branchIncorrect = branchIncorrect;
	}

	public int getBranchUnanswered() {
		return branchUnanswered;
	}

	public void setBranchUnanswered(int branchUnanswered) {
		this.branchUnanswered = branchUnanswered;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getEngineeringUnits() {
		return engineeringUnits;
	}

	public void setEngineeringUnits(String engineeringUnits) {
		this.engineeringUnits = engineeringUnits;
	}

	public ArrayList<String> getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(ArrayList<String> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public ArrayList getValidUnits() {
		return validUnits;
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public int getSignificantFigures() {
		return significantFigures;
	}

	public void setSignificantFigures(int significantFigures) {
		this.significantFigures = significantFigures;
	}

	public void setValidUnits(ArrayList validUnits) {
		this.validUnits = validUnits;
	}

	public fillInTheBlankQ(){}

	public static fillInTheBlankQ getQuestionFillBlkQ(Question result, DataInputStream theInput, int format) {
		fillInTheBlankQ fillInTheBlankq = new fillInTheBlankQ();
		try {
			Question q = new Question();
			fillInTheBlankq = (fillInTheBlankQ)q.getQuestion(result, theInput, format);

			try {

				// read the booleans
				/*hasFormula= theInput.readBoolean();
				checkUnits= theInput.readBoolean();
				markIncorrectWithoutUnits= theInput.readBoolean();
				caseSensitive= theInput.readBoolean();*/
				fillInTheBlankq.setCheckUnits(theInput.readBoolean());
				fillInTheBlankq.setMarkIncorrectWithoutUnits(theInput.readBoolean());
				fillInTheBlankq.setCaseSensitive(theInput.readBoolean());

				if (format >= 32) {
					//useSignificantFigures= theInput.readBoolean();
					fillInTheBlankq.setUseSignificantFigures(theInput.readBoolean());
				}
				if (format >= 428){
					//shortanswer= theInput.readBoolean();
					fillInTheBlankq.setShortanswer(theInput.readBoolean());
				}

				// additional booleans go here


				// read the ints
				/*columns= theInput.readInt();
				lines= theInput.readInt();
				precisionType= theInput.readInt();
				unitPoints= theInput.readInt();*/
				fillInTheBlankq.setColumns(theInput.readInt());
				fillInTheBlankq.setLines(theInput.readInt());
				fillInTheBlankq.setPrecisionType(theInput.readInt());
				fillInTheBlankq.setUnitPoints(theInput.readInt());

				if (format >= 5) {
					/*branchCorrect= theInput.readInt();
					branchIncorrect= theInput.readInt();
					branchUnanswered= theInput.readInt();*/
					fillInTheBlankq.setBranchCorrect(theInput.readInt());
					fillInTheBlankq.setBranchIncorrect(theInput.readInt());
					fillInTheBlankq.setBranchUnanswered(theInput.readInt());
				}

				if (format >= 32) {
					//significantFigures= theInput.readInt();
					fillInTheBlankq.setSignificantFigures(theInput.readInt());
				}

				// additional ints here


				// read the doubles
				//precision= theInput.readDouble();
				fillInTheBlankq.setPrecision(theInput.readDouble());

				// additional doubles here


				// read the strings
				//formula= theInput.readUTF();
				fillInTheBlankq.setFormula(theInput.readUTF());
				if (format >= 42) {
					//engineeringUnits= theInput.readUTF();
					fillInTheBlankq.setEngineeringUnits(theInput.readUTF());
				}

				// additional strings here


				// read correctAnswers
				ArrayList<String> correctAnswers = new ArrayList<String>();
				int correctAnswersCount= theInput.readInt();
				for (int i=0 ; i<correctAnswersCount ; i++)
					correctAnswers.add( theInput.readUTF() );


				// read validUnits
				ArrayList<String> validUnits = new ArrayList<String>();
				int validUnitsCount= theInput.readInt();
				for (int i=0 ; i<validUnitsCount ; i++)
					validUnits.add( theInput.readUTF() );


				// read additional subclasses here

				if (format < 405) {
					// calculate maxPoints
					//maxPoints= 0;
					int maxPoints = fillInTheBlankq.getMaxPoints();
					ArrayList pointlList = new ArrayList();
					pointlList = fillInTheBlankq.getPoints();
					try {
						maxPoints= Math.max( Integer.parseInt( (String)pointlList.get(0) ), Integer.parseInt( (String)pointlList.get(1) ) );
						maxPoints += fillInTheBlankq.getUnitPoints();
					} catch (NumberFormatException e) {}
				}

				if (format >= 422)
				{
					//formatString= theInput.readUTF();
					fillInTheBlankq.setFormatString(theInput.readUTF());
				}

			} catch (IOException e) {
				e.printStackTrace();

			}
			richPropertiesUtil questionProperties = richPropertiesUtil.newInstance("questionProperties");
			boolean sensitivity= questionProperties.getBoolean( CASE_SENSITIVITY, fillInTheBlankq.caseSensitive );
			questionProperties.setBoolean( CASE_SENSITIVITY, sensitivity );

			int fieldWidth= questionProperties.getInt( FIELD_WIDTH, -1 );
			//if (fieldWidth < 0) fieldWidth= columns;
			if (fieldWidth < 0) fieldWidth=fillInTheBlankq.getColumns();
			questionProperties.setInt( FIELD_WIDTH, fieldWidth );

			int fieldHeight= questionProperties.getInt( FIELD_HEIGHT, -1 );
			//if (fieldHeight < 0) fieldHeight= lines;
			if (fieldHeight < 0) fieldHeight= fillInTheBlankq.getLines();
			questionProperties.setInt( FIELD_HEIGHT, fieldHeight );

			String unitRespect= questionProperties.getString( RESPECT_UNITS, "" );
			//if (unitRespect.length() == 0) unitRespect= (checkUnits ? "true" : "false");
			if (unitRespect.length() == 0) unitRespect= (fillInTheBlankq.checkUnits ? "true" : "false");
			questionProperties.setString( RESPECT_UNITS, unitRespect );

			String incUnits= questionProperties.getString( INCORRECT_WITHOUT_UNITS, "" );
			//if (incUnits.length() == 0) incUnits= (markIncorrectWithoutUnits ? "true" : "false");
			if (incUnits.length() == 0) incUnits= (fillInTheBlankq.markIncorrectWithoutUnits ? "true" : "false");
			questionProperties.setString( INCORRECT_WITHOUT_UNITS, incUnits );

			String useSigFig= questionProperties.getString( RESPECT_SIGFIG, "" );
			//if (useSigFig.length() == 0) useSigFig= (useSignificantFigures ? "true" : "false");
			if (useSigFig.length() == 0) useSigFig= (fillInTheBlankq.useSignificantFigures ? "true" : "false");
			questionProperties.setString( RESPECT_SIGFIG, useSigFig );

			String engUnits= questionProperties.getString( ENGINEERING_UNITS, "XXX" );
			//if (engUnits.equals("XXX")) engUnits= engineeringUnits;
			if (engUnits.equals("XXX")) engUnits= fillInTheBlankq.getEngineeringUnits();
			questionProperties.setString( ENGINEERING_UNITS, engUnits );

			String fmtString= questionProperties.getString( FORMAT_STRING, "XXX" );
			if (fmtString.equals("XXX")) fmtString= fillInTheBlankq.getFormatString();
			questionProperties.setString( FORMAT_STRING, fmtString );

			int upoints= questionProperties.getInt( UNIT_POINTS, -1 );
			if (upoints == -1) upoints= fillInTheBlankq.getUnitPoints();
			questionProperties.setInt( UNIT_POINTS, upoints );

			int ptype= questionProperties.getInt( PRECISION_TYPE, -1 );
			//if (ptype == -1) ptype= precisionType;
			if (ptype == -1) ptype= fillInTheBlankq.getPrecisionType();
			questionProperties.setInt( PRECISION_TYPE, ptype );

			int sigfig= questionProperties.getInt( SIGNIFICANT_FIGURES, -1 );
			//if (sigfig == -1) sigfig= significantFigures;
			if (sigfig == -1) sigfig= fillInTheBlankq.getSignificantFigures();
			questionProperties.setInt( SIGNIFICANT_FIGURES, sigfig );

			double absprec= questionProperties.getDouble( ABSOLUTE_PRECISION, (double)-1 );
			//if (absprec == -1) absprec= precision;
			if (absprec == -1) absprec= fillInTheBlankq.getPrecision();
			questionProperties.setDouble( ABSOLUTE_PRECISION, absprec );
			fillInTheBlankq.setQuestionProperties(questionProperties);
			//print_fillInTheBlankQ(fillInTheBlankq);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return fillInTheBlankq;

	}

	private static void print_fillInTheBlankQ(fillInTheBlankQ fillInTheBlankq) {
		System.out.println("########### Printing fillInTheBlankQ Start ################");
		System.out.println(" hasFormula : " + fillInTheBlankq.hasFormula);
		System.out.println(" checkUnits : " + fillInTheBlankq.checkUnits);
		System.out.println(" markIncorrectWithoutUnits : " + fillInTheBlankq.markIncorrectWithoutUnits);
		System.out.println(" caseSensitive : " + fillInTheBlankq.caseSensitive);
		System.out.println(" useSignificantFigures : " + fillInTheBlankq.useSignificantFigures);
		System.out.println(" shortanswer : " + fillInTheBlankq.shortanswer);
		System.out.println(" columns : " + fillInTheBlankq.getColumns());
		System.out.println(" lines : " + fillInTheBlankq.getLines());
		System.out.println(" precisionType : " + fillInTheBlankq.getPrecisionType());
		System.out.println(" unitPoints : " + fillInTheBlankq.getUnitPoints());
		System.out.println(" branchCorrect : " + fillInTheBlankq.getBranchCorrect());
		System.out.println(" branchIncorrect : " + fillInTheBlankq.getBranchIncorrect());
		System.out.println(" branchUnanswered : " + fillInTheBlankq.getBranchUnanswered());
		System.out.println(" significantFigures : " + fillInTheBlankq.getSignificantFigures());
		System.out.println(" precision : " + fillInTheBlankq.getPrecision());
		System.out.println(" formula : " + fillInTheBlankq.getFormula());
		System.out.println(" engineeringUnits : " + fillInTheBlankq.getEngineeringUnits());
		System.out.println(" formatString : " + fillInTheBlankq.getFormatString());
		System.out.println("..... printing Question Properties in FillInTheBlankQ....");

		richPropertiesUtil questionProperties = fillInTheBlankq.getQuestionProperties();
		Iterator questionPropItr = questionProperties.getList().keySet().iterator();
		String questionPropName = "";
		String questionPropVal = "";
		System.out.println("Title : " + questionProperties.getTitle());
		while(questionPropItr.hasNext()){
			questionPropName = (String)questionPropItr.next();
			questionPropVal = questionProperties.getString(questionPropName, "NA");
			System.out.println("Name : " + questionPropName + ", Value : " + questionPropVal);
		}
		System.out.println("########### Printing fillInTheBlankQ Start ################");

	}}
