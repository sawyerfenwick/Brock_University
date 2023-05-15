package apocalypse;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Main class of the package.
 * This is only a minimally functional skeelton. It is NOT complete!
 * 
 * @author (YOUR name | your username | your student number)
 * 
 * @see Survivor
 * @see Undead
 * @see Horde
 */
public class Apocalypse {
	
	/**
	 * Constructor
	 */
	public Apocalypse() {
		//You might want to put your menu here?
	}
	
	/**
	 * This is just a temporary method.
	 * It's the bare minimum to help you test your Undead/Survivors, but isn't
	 * a complete program.
	 * You are, however, welcome to repurpose as much of it as you like for
	 * your real program.
	 */
	private void tempDemo() {
		LinkedList<Survivor> heroes=new LinkedList<>();
		Horde.populate(60,5); //Pretty doomed
		Horde.populate(20,5); //Survivors will probably win
		//Horde.populate(10,10); //Very doomed
		
		
		heroes.add(new Flamer("Charles",heroes));
		heroes.add(new Sniper("Chuck",heroes));
		heroes.add(new Medic("Charlie",heroes));
		heroes.add(new Plinker("Chaz",heroes));
		
		displayField(heroes);
		System.out.println();
		while (Horde.boo.length>0 && heroes.size()>0)
			iterate(heroes);
		displayField(heroes);
		
		System.out.println(Horde.boo.length+"; "+Horde.getRemaining()); //(This was just for my own debugging)
	}
	
	/**
	 * You really should break out a single iteration/cycle of the
	 * simulation, so you can separate the work.
	 * 
	 * We don't need to bother with the Horde, because that only uses
	 * static's. However, we do need to hang onto our list of brave
	 * heroes somehow.
	 * 
	 * @param heroes Said 'brave heroes'
	 */
	private void iterate(LinkedList<Survivor> heroes) {
		//This is just a *very* basic skeleton
		
		//for (Survivor s:heroes) System.out.println(s.act()); //Heroes do their thing
		System.out.println(heroes.get(0).act());
		
		for (Undead u:Horde.boo) u.advance(); //Undead advance
		
		for (Undead u:Horde.boo) { //End the turn with the zombies chowing down
			if (heroes.size()>0) { //You might want something more elegant for this
				if (u.getDistance()<=0) System.out.println(u.chomp(heroes.get(0)));
				if (heroes.get(0).getHP()<=0) {
					heroes.remove(0);
				}
			}
		}
		
		//Rotating, so everyone gets a chance to be eaten!
		if (heroes.size()>0) {
			Survivor s=heroes.removeFirst();
			heroes.addLast(s);
		}
	}
	
	/**
	 * You can probably do better than this.
	 * Still, the requirements are:
	 *  1. Draw the undead currently on the field
	 *  2. Draw the survivors, including their sequence
	 *  ?. Profit?
	 *
	 * @param heroes Our do-gooders
	 */
	private void displayField(LinkedList<Survivor> heroes) {
		if (Horde.boo.length==0) {
			for (Survivor hero:heroes) System.out.print(hero);
			System.out.println();
		}
		for (int i=0;i<Horde.boo.length;i++) {
			//for (int j=0;j<field[i].length;j++) System.out.print(Undead.render(field[i][j]));
			System.out.print(Undead.render(Horde.boo[i]));
			System.out.print('|');
			if (i==Horde.boo.length/2) for (Survivor hero:heroes) System.out.print(hero);
			System.out.println();
			
		}
	}
	
	
	
	public static void main(String[] args) {new Apocalypse().tempDemo();}
}
