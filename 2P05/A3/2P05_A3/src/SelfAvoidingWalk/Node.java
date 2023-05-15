package SelfAvoidingWalk;

public class Node implements Runnable {
	
	char dir;
	
	public Node(char dir) {
		this.dir = dir;
	}

	@Override
	public void run() {
		System.out.println("Node Thread");
	}
}