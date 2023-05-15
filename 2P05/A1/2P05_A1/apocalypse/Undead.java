package apocalypse;

public interface Undead {
	
	public int getDistance();
	
	public String chomp(Survivor victim);
	
	public String advance();
	
	public int getLimbs();
	
	public void deLimb(int force);
	
	public boolean getHungry();
	
	default public String speak() {
		return getHungry()?"braaainsss":"*groans*";
	}
	
	public String getLabel();
	
	static public String render(Undead u) {
		String padding="     ";
		return u==null?
				"    "
				:
					padding.substring(0,4-u.getDistance())+u.toString()+padding.substring(0,u.getDistance());
	}
}
