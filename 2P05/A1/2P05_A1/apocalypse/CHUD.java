package apocalypse;

public class CHUD implements Undead {
	
	int limbs = 4;
	
	public int getDistance() {
		return 1;
	}
	
	public String chomp(Survivor victim) {
		
	}
	
	public String advance() {
		
	}
	
	public int getLimbs() {
		return limbs;
	}
	
	public void deLimb(int force) {
		limbs -= force;
	}
	
	public boolean getHungry() {
		
	}
	
	public String getLabel() {
		
	}
	
	static public String render(Undead u) {
		String padding="     ";
		return u==null?
				"    "
				:
					padding.substring(0,4-u.getDistance())+u.toString()+padding.substring(0,u.getDistance());
	}
}
