package example;

import java.util.concurrent.*;

public class MyThread extends Thread {

	Semaphore sem;
	String threadName;
	
	public MyThread(Semaphore sem, String threadName) {
		super(threadName);
		this.sem = sem;
		this.threadName = threadName;
	}
	
	public void run() {
		if(this.getName().equals("A")) {
			System.out.println("Starting  " + threadName);
			try {
				System.out.println(threadName + " waiting for permit");
				sem.acquire();
				System.out.println(threadName + " gets a permit");
				
				for(int i = 0; i < 3; i++) {
					Shared.count++;
					System.out.println(threadName + ": " + Shared.count);
					Thread.sleep(10); //context switch for next thread to execute
				}
			}catch(InterruptedException exc){
				System.out.println(exc);
			}
			System.out.println(threadName + " release the permit");
			sem.release();
		}
		else {
			System.out.println("Starting  " + threadName);
			try {
				System.out.println(threadName + " waiting for permit");
				sem.acquire();
				System.out.println(threadName + " gets a permit");
				
				for(int i = 0; i < 3; i++) {
					Shared.count--;
					System.out.println(threadName + ": " + Shared.count);
					Thread.sleep(10); //context switch for next thread to execute
				}
			}catch(InterruptedException exc){
				System.out.println(exc);
			}
			System.out.println(threadName + " release the permit");
			sem.release();
		}
	}
}
