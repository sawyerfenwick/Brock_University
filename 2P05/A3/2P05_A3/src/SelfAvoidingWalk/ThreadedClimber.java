package SelfAvoidingWalk;

import java.util.concurrent.*;

/**
 * This class Defines a Thread in that runs the Hill Climber
 * 
 * @author Sawyer Fenwick | 6005011 | sf15zx
 *
 */
public class ThreadedClimber extends Thread {

	Semaphore sem;
	String threadName;
	ThreadGroup group;
	
	public ThreadedClimber(ThreadGroup group, Semaphore sem, String threadName) {
		super(group, threadName);
		this.sem = sem;
		this.threadName = threadName;
		this.group = group;
		start();
	}//threadClimber
	
	/**
	 * Show which thread is currently running. Semaphore operates over the hill climber program. 
	 * Runs on Thread.start()
	 */
	public void run() {
		try {
			System.out.println("Thread " + threadName + " waiting for permit");
			sem.acquire();
			System.out.println("Thread " + threadName + " acquires permit");
			if(HillClimber.interrupt) {
				System.out.println("Thread " +threadName + " Working");
			}
			else {
				System.out.println("Thread " + threadName + " Interrupted");
			}
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("Thread " + threadName + " releases the permit");
		sem.release();
	}//run
}//ThreadedClimber
