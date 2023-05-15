package example;

import java.util.concurrent.*;

public class SemaphoreDemo {

	public static void main(String args[]) throws InterruptedException{
		
		//1 permit
		Semaphore sem = new Semaphore(1);
		
		MyThread mt1 = new MyThread(sem, "A");
		MyThread mt2 = new MyThread(sem, "B");
		
		mt1.start();
		mt2.start();
		
		mt1.join();
		mt2.join();
		
		System.out.println("Count: " + Shared.count);
	}
	
}
