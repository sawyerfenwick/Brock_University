package apocalypse;
import java.util.LinkedList;
import java.util.ArrayList;

public class Apocalypse {
	/**
	 * Constructor
	 */
	public Apocalypse() {
		//menu
	}
	
	private void tempDemo() {
		LinkedList<Survivor> heroes = new LinkedList<>();
		Horde.populate(60,5);  //Pretty doomed
		Horde.populate(20,5);  //Survivors will probably win
		//Horde.populate(10,10); //Very Doomed
	
		heroes.add(new Flamer("Charles",heroes));
		heroes.add(new Sniper("Chuck", heroes));
		heroes.add(new Medic("Charlie", heroes));
		heroes.add(new Plinker("Chaz",heroes));
		
		displayField(heroes);
		System.out.println();
		while(Horde.boo.length>0 && heroes.size()>0)
			iterate(heroes);
		displayField(heroes);
		
		System.out.println(Horde.boo.length+"; "+Horde.getRemaining());
	}
		
	private void iterate(LinkedList<Survivor> heroes) {
		System.out.println(heroes.get(0).act());
			
		for (Undead u:Horde.boo) u.advance();
			
		for (Undead u:Horde.boo) {
			if(heroes.size()>0) {
				if(u.getDistance()<=0) System.out.println(u.chomp(heroes.get(0)));
				if(heroes.get(0).getHP()<=0) {
					heroes.remove(0);
				}
			}
		}
		
		if(heroes.size()>0) {
			Survivor s=heroes.removeFirst();
			heroes.addLast(s);
		}
	}

	private void displayField(LinkedList<Survivor> heroes) {
	if(Horde.boo.length==0) {
			for(Survivor hero:heroes)System.out.print(hero);
			System.out.println();
		}
		for(int i=0; i<Horde.boo.length; i++) {
			System.out.print(Undead.render(Horde.boo[i]));
			System.out.print('|');
			if(i==Horde.boo.length/2) for (Survivor hero:heroes) System.out.print(hero);
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {new Apocalypse().tempDemo();}
}
