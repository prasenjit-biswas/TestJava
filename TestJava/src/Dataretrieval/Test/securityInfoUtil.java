package Dataretrieval.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;




public class securityInfoUtil {
	private boolean			includeTimer = false;
	private boolean			autosubmit = false;
	private boolean				preventMultipleSubmissions= false;
	private ArrayList<String> lookAsideUsers = null;
	private ArrayList  lookAsideIPs = null;
	private ArrayList<userEntry> internalUsers = null;
	private ArrayList<String> internalIPs = null;
	private ConcurrentHashMap internalManagers = null;
	private String				timeZone= "GMT";
	private boolean				perDayRequestLimit= false;
	private boolean				perDaySubmissionLimit= false;
	private boolean				includeMessage = false;
	private int					submissionLimit = 0;
	private int					requestLimit = 0;
	private int					timeWarning= 0;
	private long			startTime = 0;
	private long			endTime = 0;

	private long				recallTime = 0;
	private String				anonymous_password = "";
	private long				participantList = 0;

	private long				managerList = 0;

	private long			IPaddressList = 0;
	private boolean				permitSelfRegistration = false;
	private String				ownerName = "undefined";

	private String				ownerPassword = "undefined";

	private String				ownerEmail = "";
	private long			elapsedTimeLimit = 0;
	private ArrayList<String> IPExceptions ;
	private ArrayList<String> attemptExceptions;
	private ArrayList<String> timeExceptions;
	private boolean	 hasLookAsideUserList;
	private boolean	 hasLookAsideIPList;
	private boolean	 mayEmail;
	private String oldLookaside;
	
	public String getOldLookaside() {
		return oldLookaside;
	}

	public void setOldLookaside(String oldLookaside) {
		this.oldLookaside = oldLookaside;
	}

	public boolean isHasLookAsideUserList() {
		return hasLookAsideUserList;
	}

	public void setHasLookAsideUserList(boolean hasLookAsideUserList) {
		this.hasLookAsideUserList = hasLookAsideUserList;
	}

	public boolean isHasLookAsideIPList() {
		return hasLookAsideIPList;
	}

	public void setHasLookAsideIPList(boolean hasLookAsideIPList) {
		this.hasLookAsideIPList = hasLookAsideIPList;
	}

	public boolean isMayEmail() {
		return mayEmail;
	}

	public void setMayEmail(boolean mayEmail) {
		this.mayEmail = mayEmail;
	}

	

	public boolean isIncludeTimer() {
		return includeTimer;
	}

	public void setIncludeTimer(boolean includeTimer) {
		this.includeTimer = includeTimer;
	}

	public boolean isAutosubmit() {
		return autosubmit;
	}

	public void setAutosubmit(boolean autosubmit) {
		this.autosubmit = autosubmit;
	}

	public boolean isPreventMultipleSubmissions() {
		return preventMultipleSubmissions;
	}

	public void setPreventMultipleSubmissions(boolean preventMultipleSubmissions) {
		this.preventMultipleSubmissions = preventMultipleSubmissions;
	}

	public ArrayList getLookAsideUsers() {
		return lookAsideUsers;
	}

	public void setLookAsideUsers(ArrayList lookAsideUsers) {
		this.lookAsideUsers = lookAsideUsers;
	}

	public ArrayList getLookAsideIPs() {
		return lookAsideIPs;
	}

	public void setLookAsideIPs(ArrayList lookAsideIPs) {
		this.lookAsideIPs = lookAsideIPs;
	}

	public ArrayList<userEntry> getInternalUsers() {
		return internalUsers;
	}

	public void setInternalUsers(ArrayList<userEntry> internalUsers) {
		this.internalUsers = internalUsers;
	}

	public ArrayList getInternalIPs() {
		return internalIPs;
	}

	public void setInternalIPs(ArrayList internalIPs) {
		this.internalIPs = internalIPs;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isPerDayRequestLimit() {
		return perDayRequestLimit;
	}

	public void setPerDayRequestLimit(boolean perDayRequestLimit) {
		this.perDayRequestLimit = perDayRequestLimit;
	}

	public boolean isPerDaySubmissionLimit() {
		return perDaySubmissionLimit;
	}

	public void setPerDaySubmissionLimit(boolean perDaySubmissionLimit) {
		this.perDaySubmissionLimit = perDaySubmissionLimit;
	}

	public boolean isIncludeMessage() {
		return includeMessage;
	}

	public void setIncludeMessage(boolean includeMessage) {
		this.includeMessage = includeMessage;
	}

	public int getSubmissionLimit() {
		return submissionLimit;
	}

	public void setSubmissionLimit(int submissionLimit) {
		this.submissionLimit = submissionLimit;
	}

	public int getRequestLimit() {
		return requestLimit;
	}

	public void setRequestLimit(int requestLimit) {
		this.requestLimit = requestLimit;
	}

	public int getTimeWarning() {
		return timeWarning;
	}

	public void setTimeWarning(int timeWarning) {
		this.timeWarning = timeWarning;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getRecallTime() {
		return recallTime;
	}

	public void setRecallTime(long recallTime) {
		this.recallTime = recallTime;
	}

	public String getAnonymous_password() {
		return anonymous_password;
	}

	public void setAnonymous_password(String anonymousPassword) {
		anonymous_password = anonymousPassword;
	}

	public long getParticipantList() {
		return participantList;
	}

	public void setParticipantList(long participantList) {
		this.participantList = participantList;
	}

	public long getManagerList() {
		return managerList;
	}

	public void setManagerList(long managerList) {
		this.managerList = managerList;
	}

	public long getIPaddressList() {
		return IPaddressList;
	}

	public void setIPaddressList(long iPaddressList) {
		IPaddressList = iPaddressList;
	}

	public boolean isPermitSelfRegistration() {
		return permitSelfRegistration;
	}

	public void setPermitSelfRegistration(boolean permitSelfRegistration) {
		this.permitSelfRegistration = permitSelfRegistration;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerPassword() {
		return ownerPassword;
	}

	public void setOwnerPassword(String ownerPassword) {
		this.ownerPassword = ownerPassword;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public long getElapsedTimeLimit() {
		return elapsedTimeLimit;
	}

	public void setElapsedTimeLimit(long elapsedTimeLimit) {
		this.elapsedTimeLimit = elapsedTimeLimit;
	}

	public ArrayList getIPExceptions() {
		return IPExceptions;
	}

	public void setIPExceptions(ArrayList iPExceptions) {
		IPExceptions = iPExceptions;
	}

	public ArrayList getAttemptExceptions() {
		return attemptExceptions;
	}

	public void setAttemptExceptions(ArrayList attemptExceptions) {
		this.attemptExceptions = attemptExceptions;
	}

	public ArrayList getTimeExceptions() {
		return timeExceptions;
	}

	public void setTimeExceptions(ArrayList timeExceptions) {
		this.timeExceptions = timeExceptions;
	}

	public securityInfoUtil(DataInputStream theInput, int format) throws IOException {

		lookAsideUsers= new ArrayList<String>();
		lookAsideIPs= new ArrayList<String>();

		internalUsers= new ArrayList<userEntry>();
		internalIPs= new ArrayList<String>();
		internalManagers= new ConcurrentHashMap();

		//System.out.println("ckpt#2a1");

		TimeZone theTZ= TimeZone.getTimeZone(timeZone);
		if ((timeZone.length()==0) || (theTZ == null))
			theTZ= TimeZone.getDefault();

		try {

			if (format < 400) {

				// read the booleans
				hasLookAsideUserList= theInput.readBoolean();
				hasLookAsideIPList= theInput.readBoolean();
				mayEmail= theInput.readBoolean();

				if (format >= 6) {
					includeTimer= theInput.readBoolean();
					autosubmit= theInput.readBoolean();
				}

				if (format >= 12) {
					perDayRequestLimit= theInput.readBoolean();
					preventMultipleSubmissions= theInput.readBoolean();
				}

				if (format >= 15) {
					perDaySubmissionLimit= theInput.readBoolean();
				}

				if (format >= 38)
					includeMessage= theInput.readBoolean();

				// read the ints
				submissionLimit= theInput.readInt();
				requestLimit= theInput.readInt();

				if (format >= 27) timeWarning= theInput.readInt();

				if (theInput.readBoolean()) {
					int yr= theInput.readInt();
					int mo= theInput.readInt();
					int dy= theInput.readInt();
					int hr= theInput.readInt();
					int mn= theInput.readInt();

					GregorianCalendar cal= new GregorianCalendar( yr, mo, dy, hr, mn );
					cal.setTimeZone(theTZ);

					startTime= cal.getTime().getTime();
				}

				if (theInput.readBoolean()) {
					int yr= theInput.readInt();
					int mo= theInput.readInt();
					int dy= theInput.readInt();
					int hr= theInput.readInt();
					int mn= theInput.readInt();

					GregorianCalendar cal= new GregorianCalendar( yr, mo, dy, hr, mn );
					cal.setTimeZone(theTZ);

					endTime= cal.getTime().getTime();
				}

				if (theInput.readBoolean()) {
					int yr= theInput.readInt();
					int mo= theInput.readInt();
					int dy= theInput.readInt();
					int hr= theInput.readInt();
					int mn= theInput.readInt();

					GregorianCalendar cal= new GregorianCalendar( yr, mo, dy, hr, mn );
					cal.setTimeZone(theTZ);

					recallTime= cal.getTime().getTime();
				}

				// additional ints here

				// read the longs
				elapsedTimeLimit= theInput.readLong();

				// additional longs here

				// read the strings
				ownerName= theInput.readUTF();
				ownerPassword= theInput.readUTF();
				ownerEmail= theInput.readUTF();

				// additional strings here

				// read users
				if (hasLookAsideUserList) {
					String tmp= "";
					if (format < 28) tmp= theInput.readUTF();
					//lookAsideUserFilename= theInput.readUTF();
					//parseUserFile();

					tmp= theInput.readUTF();
					System.out.println(" hasLookAsideUserList tmp securityInfoUtil: " + tmp);
					hasLookAsideUserList= false;
					internalUsers= new ArrayList();
				}
				else {
					int userCount= theInput.readInt();
					for (int i=0 ; i<userCount ; i++)
						internalUsers.add( new userEntry( theInput, format ) );
				}

				// insert into db	
				//	if (sqlRoutine != null) {}


				// read ip list
				if (hasLookAsideIPList) {
					String tmp= "";
					if (format < 28) tmp= theInput.readUTF();
					tmp= theInput.readUTF();
					//lookAsideIPFilename= theInput.readUTF();
					//parseIPFile();
					//System.out.println(" hasLookAsideIPList tmp securityInfoUtil: " + tmp);
					hasLookAsideIPList= false;
					internalIPs= new ArrayList<String>();
				}
				else {
					int ipCount= theInput.readInt();
					for (int i=0 ; i<ipCount ; i++) {
						if (format < 47) {
							int parts= theInput.readInt();

							String address= theInput.readUTF();

							String tmp= theInput.readUTF();
							tmp= theInput.readUTF();
							tmp= theInput.readUTF();
							tmp= theInput.readUTF();

							internalIPs.add( address );
						}
						else
							internalIPs.add( theInput.readUTF() );
					}
				}

				// insert into db	
				//	if (sqlRoutine != null) {}


				if (format >= 23) {
					int count= theInput.readInt();
					for (int i=0 ; i< count ; i++) {
						tp_manager theMgr= new tp_manager( theInput, format );
						internalManagers.put( (theMgr.id + "," + theMgr.password), theMgr );
					}

					// insert into db	
					//	if (sqlRoutine != null) {}
				}

				IPExceptions= new ArrayList<String>();
				attemptExceptions= new ArrayList<String>();
				timeExceptions= new ArrayList<String>();
				if (format >= 25) {

					int count= theInput.readInt();
					for (int i=0 ; i<count ; i++)
						IPExceptions.add( theInput.readUTF() );

					count= theInput.readInt();
					for (int i=0 ; i<count ; i++)
						attemptExceptions.add( theInput.readUTF() );

					count= theInput.readInt();
					for (int i=0 ; i<count ; i++)
						timeExceptions.add( theInput.readUTF() );

				}

			}
		else {

				//System.out.println("ckpt2a1");

				ownerName= theInput.readUTF();
				ownerPassword= theInput.readUTF();
				ownerEmail= theInput.readUTF();

				if (format >= 427)
					anonymous_password= theInput.readUTF();				

				//System.out.println("ckpt2a2");

				includeTimer= theInput.readBoolean();
				includeMessage= theInput.readBoolean();
				autosubmit= theInput.readBoolean();
				preventMultipleSubmissions= theInput.readBoolean();

				//System.out.println("ckpt2a3");

				submissionLimit= theInput.readInt();
				perDaySubmissionLimit= theInput.readBoolean();

				//System.out.println("ckpt2a4");

				requestLimit= theInput.readInt();
				perDayRequestLimit= theInput.readBoolean();

				//System.out.println("ckpt2a5");

				timeWarning= theInput.readInt();

				//System.out.println("ckpt2a6");

				startTime= theInput.readLong();
				endTime= theInput.readLong();
				recallTime= theInput.readLong();
				elapsedTimeLimit= theInput.readLong();

				//System.out.println("ckpt2a7");

				//lookAsideUserFilename= theInput.readUTF();
				oldLookaside= theInput.readUTF();

				participantList= theInput.readLong();
				if (format >= 408) permitSelfRegistration= theInput.readBoolean();

				//System.out.println("ckpt2a8");

				//lookAsideIPFilename= theInput.readUTF();
				oldLookaside= theInput.readUTF();

				IPaddressList = theInput.readLong();

				//System.out.println("ckpt2a9");

				managerList= theInput.readLong();

				IPExceptions= new ArrayList();
				attemptExceptions= new ArrayList();
				timeExceptions= new ArrayList();

				//System.out.println("ckpt2a10");

				int count= theInput.readInt();
				for (int i=0 ; i<count ; i++)
					IPExceptions.add( theInput.readUTF() );

				//System.out.println("ckpt2a11");

				count= theInput.readInt();
				for (int i=0 ; i<count ; i++)
					attemptExceptions.add( theInput.readUTF() );

				//System.out.println("ckpt2a12");

				count= theInput.readInt();
				for (int i=0 ; i<count ; i++)
					timeExceptions.add( theInput.readUTF() );

				//System.out.println("ckpt2a13");

				count= theInput.readInt();
				for (int i=0 ; i<count ; i++)
					internalUsers.add( new userEntry( theInput, format ) );

				//System.out.println("ckpt2a14");

				count= theInput.readInt();
				for (int i=0 ; i<count ; i++)
					internalIPs.add( theInput.readUTF() );			

				//System.out.println("ckpt2a15");

				count= theInput.readInt();
				for (int i=0 ; i< count ; i++) {
					tp_manager theMgr= new tp_manager( theInput, format );
					internalManagers.put( (theMgr.id + "," + theMgr.password), theMgr );
				}
		  }

		} catch (IOException e) {
			throw (new IOException( "IOException reading securityInfo" ) );
		}
		print_security_Info_Util();
	}

	private void print_security_Info_Util() {
		System.out.println("############## printing securityInfoUtil start ###############");
		System.out.println(" hasLookAsideUserList : " + hasLookAsideUserList);
		System.out.println(" hasLookAsideIPList : " + hasLookAsideIPList);
		System.out.println(" mayEmail : " + mayEmail);
		System.out.println(" includeTimer : " + includeTimer);
		System.out.println(" autosubmit : " + autosubmit);
		System.out.println(" perDayRequestLimit : " + perDayRequestLimit);
		System.out.println(" preventMultipleSubmissions : " + preventMultipleSubmissions);
		System.out.println(" perDaySubmissionLimit : " + perDaySubmissionLimit);
		System.out.println(" includeMessage : " + includeMessage);
		System.out.println(" submissionLimit : " + submissionLimit);
		System.out.println(" requestLimit : " + requestLimit);
		System.out.println(" timeWarning : " + timeWarning);
		System.out.println(" elapsedTimeLimit : " + elapsedTimeLimit);
		System.out.println(" ownerName : " + ownerName);
		System.out.println(" ownerPassword : " + ownerPassword);
		System.out.println(" ownerEmail : " + ownerEmail);
		System.out.println(" ownerName : " + ownerEmail);
		
		System.out.println(" printing IPExceptions....");
		for(String isIPExceptionsStr : IPExceptions)
		{
			System.out.println("IPExceptions : " + isIPExceptionsStr);
		}
		System.out.println(" printing attemptExceptions....");
		for(String isIPExceptionsStr : attemptExceptions)
		{
			System.out.println("timeExceptions : " + isIPExceptionsStr);
		}
		for(String isIPExceptionsStr : timeExceptions)
		{
			System.out.println("timeExceptions : " + isIPExceptionsStr);
		}
		
		System.out.println(" printing internalUsers...");
		for(userEntry UserEntry : internalUsers)
		{
			UserEntry.printUserEntry();
		}
		for(String isIPExceptionsStr : timeExceptions)
		{
			System.out.println("timeExceptions : " + isIPExceptionsStr);
		}
		
		for(String isIPExceptionsStr : internalIPs)
		{
			System.out.println("internalIPs : " + isIPExceptionsStr);
		}
		System.out.println(" participantList : " + participantList);
		System.out.println(" IPaddressList : " + IPaddressList);
		System.out.println(" managerList : " + managerList);
		System.out.println(" oldLookaside : " + oldLookaside);
		System.out.println(" permitSelfRegistration : " + permitSelfRegistration);
		System.out.println("############## printing securityInfoUtil end ###############");
	}

}
