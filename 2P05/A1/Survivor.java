package apocalypse;

/**
 * Abstract class representing the basic functionality of a Survivor.
 * Your job is to build upon this in myriad concrete classes.
 * 
 * You *need* to provide implementations of anything 'abstract' in the concrete
 * types, but you're also welcome to override anything already provided.
 * 
 * @see Plinker
 * @see Medic
 * @see Flamer
 * @see Sniper
 */
public abstract class Survivor {
	final static int maxHP=5;
	final static String victory="YAY!"; //Probably not necessary; just used if attempting to act after victory
	private int hitpoints=Survivor.maxHP; //?package-private to allow for healing?
	String name;
	
	//For at least the Medic, I'd suggest adding an instance variable to keep
	//track of the List of Survivors
	
	/**
	 * @return	What happened
	 */
	abstract public String act();
	
	/**
	 * @return Duh.
	 */
	public int getHP() {
		return hitpoints;
	}
	
	/**
	 * Fun fact: if you try to 'injure' by a negative value, it'll heal!
	 * Wonder if that could be useful...
	 * @param HP delta value
	 */
	public void injure(int amt) {
		this.hitpoints-=amt;
		this.hitpoints=this.hitpoints<0?0:this.hitpoints>Survivor.maxHP?Survivor.maxHP:this.hitpoints;
	}
	
	/**
	 * Just return "Sniper", "Medic", etc.
	 * It isn't unheard of to have something like this when there are multiple
	 * possible implementations, to easily discern between the types (without
	 * needing to resort to reflection)
	 */
	abstract public String getRole();
	
	/**
	 * Does what it says on the box
	 * @return A spaghetti dinner (whaddaya think?!?)
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Overridden String representation of a Survivor.
	 * (Feel free to override this further, though I don't know why you would
	 */
	public String toString() {
		return '['+getName()+':'+getRole()+'|'+getHP()+']';
	}
}
