package apocalypse;

/**
 * Horde class
 * Note that we don't bother instantiating this one. Everything's static, so
 * we can just use everything directly.
 *
 *Before use, remember to prime it with 'populate'.
 * @see CHUD
 * @see Deadite
 * @see Trioxin
 */
public class Horde {
	private static int remaining=0;
	static Undead[] boo;
	
	public static int getRemaining() { return remaining; }
	
	/**
	 * For initializing the horde.
	 * 
	 * @param remaining How many Undeadies can be summoned
	 * @param breadth How many can be on the field at any given time
	 */
	static void populate(int remaining, int breadth) {
		Horde.remaining=remaining;
		Horde.boo=new Undead[breadth];
		for (int i=0;i<Horde.boo.length;i++) {
			Horde.replenish(i);
		}
	}
	
	/**
	 * Convenience operator added to easily replenish the horde
	 * after a chompy-boi is eliminated.
	 * You don't *have* to use this, but it'll probably help.
	 * 
	 * @param pos Position of recently receased. (Re-deceased? ... splattered)
	 */
	public static void replenish(int pos) {
		if (Horde.remaining<=0) {
			if (Horde.boo.length<=1) {
				Horde.boo=new Undead[0];
			}
			else {
				Undead[] temp=new Undead[Horde.boo.length-1];
				for (int i=0;i<pos;i++)
					temp[i]=Horde.boo[i];
				for (int i=pos+1;i<Horde.boo.length;i++)
					temp[i-1]=Horde.boo[i];
				Horde.boo=temp;
			}
		}
		else {
			double r=Math.random();
			if (r<0.25) { //25% chance of a CHUD
				Horde.boo[pos]=new CHUD();
			}
			else if (r<0.75) { //50% chance of a Deadite
				Horde.boo[pos]=new Deadite();
			}
			else { //25% chance of a Trioxin zombie
				Horde.boo[pos]=new Trioxin();
			}
			Horde.remaining--;
		}
	}
	
}
