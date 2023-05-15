package apocalypse;

/**
 * 
 * @author Sawyer Fenwick | sf15zx | 6005011
 * @see Plinker
 * @see Medic
 * @see Flamer
 * @see Sniper
 */
public abstract class Survivor {
	final static int maxHP = 5;
	final static String victory = "YAY";
	private int hitpoints = Survivor.maxHP;
	String name;
	
	abstract public String act();
	
	public int getHP() {
		return hitpoints;
	}
	
	public void injure(int amt) {
		this.hitpoints-=amt;
		this.hitpoints=this.hitpoints<0?0:this.hitpoints>Survivor.maxHP?Survivor.maxHP:this.hitpoints;
	}
	
	abstract public String getRole();
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return '['+getName()+':'+getRole()+'|'+getHP()+']';
	}
}
