package apocalypse;

/**
 * Represents the basic functionality of being reanimated.
 * 
 * All Undead starts at a distance of 4, and they can only attack once they get
 * to a distance of 0.
 * Instead of HP, they have 'limbs'. A zombie with no limbs isn't terribly
 * scary anymore (so you can treat it as re-deadened).
 * 
 * @see CHUD
 * @see Deadite
 * @see Trioxin
 * @see Horde
 */
public interface Undead {
	/**
	 * @return distance (4 at furthest; 0 at closest)
	 */
	public int getDistance();
	
	/**
	 * The primary joy of the living dead: chowing down on the living
	 * not-yet-dead!
	 *
	 * Should only apply in cases where the Undead has a distance of zero, and
	 * attacks the Survivor in the front, but that's up to the main program
	 * to enforce.
	 *
	 * @param victim Owner of tasty, tasty brains
	 * @return Something descriptive of how the attack went
	 */
	public String chomp(Survivor victim);
	
	/**
	 * Closes the distance.
	 * 
	 * @return Whatever sound it makes while shambling
	 */
	public String advance();
	
	/**
	 * Accessor for pseudo-hitpoints
	 * @return Remaining unhealth
	 */
	public int getLimbs();
	
	/**
	 * Equivalent of injuring. Subtracts 'force' from number of limbs.
	 * No, a zombie can't have 'negative limbs' left.
	 *
	 * @param force How many limbs to 'liberate'
	 */
	public void deLimb(int force);
	
	/**
	 * Zombies only attack when hungry. Everyone knows that.
	 * @return Hunger state
	 */
	public boolean getHungry();
	
	/**
	 * Just flavour text.
	 * @see Trioxin
	 * @return Silliness
	 */
	default public String speak() {
		return getHungry()?"braaainsss":"*groans*";
	}
	
	/**
	 * Lets you identify the 'type' easily.
	 * @return Type (of Undead)
	 */
	public String getLabel();
	
	/**
	 * Convenience for rendering the battlefield
	 * @return Single letter to reflect type (C/D/T)
	 */
	static public String render(Undead u) {
		String padding="     ";
		return u==null?
			"     "
		:
			padding.substring(0,4-u.getDistance())+u.toString()+padding.substring(0,u.getDistance())
		;
	}
}
