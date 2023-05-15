package apocalypse;

/**
 * Represents the basic functionality of being reanimated.
 * 
 * All Undead starts at a distance of 4, and they can only attack once they get
 * to a distance of 0. 
 * Instead of HP, they have 'limbs'. A zombie with no limbs isn't terribly
 * scary anymore (so you can treat it as re-deadened).
 * @see CHUD
 * @see Deadite
 * @see Trioxin
 * @see Horde
 */
public interface Undead {
    /**
     * @return distance (4 furthest; 0 closest)
     */
    public int getDistance();
    
    /**
     * The primary joy of the living dead: chowing down on the living
     * not-yet-dead!
     * 
     * Should only apply in cases where the Undeade has a distance of zero, and
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
    
    public int getLimbs();
    
    public void deLimb(int force);
    
    public boolean getHungry();
    
    /**
     *
     * @return
     */
    default public String speak(){
        return getHungry()?"braaainsss":"*groans*";
    }
}


