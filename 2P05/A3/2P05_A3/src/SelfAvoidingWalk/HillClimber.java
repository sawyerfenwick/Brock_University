package SelfAvoidingWalk;

import javax.swing.*;
import java.util.concurrent.*;
import java.awt.*;
import java.util.*;

/**
 * Main class, constructs the JFrame, executes threads, finds longest path
 * Could not finish, commented out the thread call in the Display class since
 * it was not working correctly, uncomment to see, threads executing one at a time instead of parallel.
 * Must click start continuously to see next longest path since threads are not working.
 * 
 * @author Sawyer Fenwick | 6005011 | sf15zx 
 *
 */
public class HillClimber {
	
	public static LinkedList<Node> walk = new LinkedList<Node>();
	static JFrame frame = new JFrame("Self Avoiding Walk");
	static Direction[][] array;
	static Semaphore sem = new Semaphore(1); //1 'permit'
	static ThreadGroup g = new ThreadGroup("Climber");
	
	public static final int INITIAL_HEIGHT = 3;
	public static final int INITIAL_WIDTH = 4;
	public static final int MAX_HEIGHT = 10;
	public static final int MAX_WIDTH = 16;
	public static final int MIN = 2;
	
	static boolean newBest = false;
	static boolean interrupt = false;
	static int change = 1;
	static int bestCount = 1;
	static int count = 1;
	static int currHeight = INITIAL_HEIGHT;
	static int currWidth = INITIAL_WIDTH;
	
	static String path = "ESSENENWWEESWN";
	static char pathArray[] = path.toCharArray();
	
	public HillClimber() {
		buildFrame();
	}//constructor
	
	/**
	 * Creates main JFrame
	 */
	public void buildFrame() {
		frame.setSize(new Dimension(900,500));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(new Display(), BorderLayout.EAST);
		frame.add(new Graph(), BorderLayout.CENTER);
		frame.setVisible(true);
	}//buildFrame
	
	/**
	 * Resizes the graph based on user input
	 * @param graph
	 */
	public static void resize(JPanel graph) {
		frame.add(graph);
		frame.validate();
	}//resize
	
	/**
	 * Calls build array, begins traversal
	 */
	public static void begin() {
		walk.clear();
		buildArray();
		traverse();
		resize(new Graph());
		change();
	}//begin
	
	/**
	 * If you reach a dead end, change that node to a new direction. 33% chance per direction change.
	 */
	public static void change() {
		System.out.println();
		double r = Math.random();
		
		int i = change;
		if(i == 0) {
			i++;
		}
		i--;
		if(pathArray[i] == 'N') {
			if(r<0.33) {
				pathArray[i] = 'E';
			}
			else if(r<0.66) {
				pathArray[i] = 'S';
			}
			else {
				pathArray[i] = 'W';
			}
		}
		else if(pathArray[i] == 'E') {
			if(r<0.33) {
				pathArray[i] = 'N';
			}
			else if(r<0.66) {
				pathArray[i] = 'S';
			}
			else {
				pathArray[i] = 'W';
			}
		}
		else if(pathArray[i] == 'S') {
			if(r<0.33) {
				pathArray[i] = 'E';
			}
			else if(r<0.66) {
				pathArray[i] = 'N';
			}
			else {
				pathArray[i] = 'W';
			}
		}
		else if(pathArray[i] == 'W') {
			if(r<0.33) {
				pathArray[i] = 'E';
			}
			else if(r<0.66) {
				pathArray[i] = 'S';
			}
			else {
				pathArray[i] = 'N';
			}
		}
		count = 1;
	}//change

	/**
	 * Builds the array
	 */
	public static void buildArray() {
		array = new Direction[currHeight][currWidth];
		for(int i = 0; i < currHeight; i++) {
			for(int j = 0; j < currWidth; j++) {
				if(i==0 & j==0) {
					array[i][j] = new Direction(false, false, true, true, false);//top left corner case
				}
				else if(i==0 & j==currWidth-1) {
					array[i][j] = new Direction(false, false, true, false, true);//top right corner case
				}
				else if(i==currHeight-1 & j==0) {
					array[i][j] = new Direction(false, true, false, true, false);//bottom left corner case
				}
				else if(i==currHeight-1 & j==currWidth-1) {
					array[i][j] = new Direction(false, true, false, false, true);//bottom right corner case
				}
				else if(i==0) {
					array[i][j] = new Direction(false, false, true, true, true);//top row
				}
				else if(i==currHeight-1) {
					array[i][j] = new Direction(false, true, false, true, true);//bottom row
				}
				else if(j==currWidth-1) {
					array[i][j] = new Direction(false, true, true, false, true);//right most column
				}
				else if(j==0) {
					array[i][j] = new Direction(false, true, true, true, false);//left most column
				}
				else{
					array[i][j] = new Direction(false, true, true, true, true);//graph points in all directions
				}
			}
		}
	}//buildArray
	
	/**
	 * Traverse the array and builds the path
	 */
	public static void traverse() {
		int i = 0;
		int j = 0;
		Double r = Math.random();
		array[0][0].visited = true; //visit number one automatically
		if(pathArray[0] == 'N') { //always starts in top left corner, can't go up
			if(r<0.5) {
				pathArray[0] = 'E';
			}
			else {
				pathArray[0] = 'S';
			}
		}else if(pathArray[0] == 'E') {
			j++;
		}else if(pathArray[0] == 'S') {
			i++;
		}else if(pathArray[0] == 'W') { //always starts in top left corner, can't go left
			if(r<0.5) {
				pathArray[0] = 'E';
			}
			else {
				pathArray[0] = 'S';
			}
		}
		
		for(int k = 0; k < pathArray.length; k++) {
			if(i < currHeight & j < currWidth) {
				if(array[i][j].visited == false) {
					array[i][j].visited = true;
					count++;
					if(count > bestCount) {
						newBest = true;
						bestCount = count;
					}
					walk.add(new Node(pathArray[k]));
					if(k+1 < pathArray.length) { //"I will make it legal" - Chancellor P
						change = k+1;
						if(pathArray[k+1] == 'N') {
							if(array[i][j].north == true) {
								i--;
							}
						}
						else if(pathArray[k+1] == 'E'){
							if(array[i][j].east = true) {
								j++;
							}
						}
						else if(pathArray[k+1] == 'S') {
							if(array[i][j].south == true) {
								i++;
							}
						}
						else if(pathArray[k+1] == 'W'){
							if(array[i][j].west == true) {
								j--;
							}
						}
					}		
				}
			}
		}//for
	}//traverse
	
	/**
	 * Starts threads
	 * @throws InterruptedException
	 */
	public static void start() throws InterruptedException {
		for(int i = 0; i < 4; i++) {
			ThreadedClimber t = new ThreadedClimber(g,sem, Integer.toString(i)); //create 4 threads
			t.start(); //start thread 
			t.join(); //wait for thread to terminate
		}
	}//start
	
	/**
	 * Interrupts the Thread Group
	 */
	public static void stop() {
		g.interrupt();
		interrupt = true;
	}//stop
	
	public static void main(String[] args) {
		new HillClimber();
	}//main
	
}//HillClimber