package Datafix;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueThreadIDGenerator extends Thread{

	/**
	 * @param args
	 */
	private static final AtomicInteger uniqueId = new AtomicInteger(0);
	
	public static void main(String[] args) {
	  
	  //System.out.println(" Serial Number   " + SerialNum.get() );
	  for(int i=0;i<2;i++){
		  UniqueThreadIDGenerator serialNum = new UniqueThreadIDGenerator();
		  serialNum.start();
	  }
	  
	}
	
	public void run() {
		for(int i=0;i<3;i++)
		  System.out.println(" Serial Number   " + UniqueThreadIDGenerator.getCurrentThreadId());
	}
	
	
	private static final ThreadLocal <Integer> uniqueNum = new ThreadLocal <Integer> () {
		@Override protected Integer initialValue() {
        	System.out.println("Initialization");
            return uniqueId.getAndIncrement();
        }
    };

    public static int getCurrentThreadId() {
        return uniqueNum.get();
    }
}
