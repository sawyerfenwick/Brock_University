package SelfAvoidingWalk;

public class Direction implements Runnable {
	
	boolean visited, north, south, west, east;
	
	public Direction(boolean visited, boolean north, boolean south, boolean east, boolean west) {
		this.visited = visited;
		this.north = north;
		this.south = south;
		this.west = west;
		this.east = east;
	}
	
	@Override
	public void run() {
		System.out.println("Direction Thread");
	}
}
