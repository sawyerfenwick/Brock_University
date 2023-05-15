package apocalypse;

public class Horde {
	private static int remaining = 0;
	static Undead[] boo;
	
	public static int getRemaining() {
		return remaining;
	}
	
	static void populate(int remaining, int breadth) {
		Horde.remaining=remaining;
		Horde.boo = new Undead[breadth];
		for(int i=0; i < Horde.boo.length; i++) {
			Horde.replenish(i);
		}
	}
	
	public static void replenish(int pos) {
		if(Horde.remaining<=0) {
			if(Horde.boo.length<=1) {
				Horde.boo = new Undead[0];
			}
			else {
				Undead[] temp = new Undead[Horde.boo.length-1];
				for(int i=0;i<pos;i++)
					temp[i]=Horde.boo[i];
				for(int i=pos+1;i<Horde.boo.length;i++)
					temp[i-1]=Horde.boo[i];
				Horde.boo=temp;
			}
		}
		else {
			double r = Math.random();
			if(r<0.25) {
				Horde.boo[pos]=new CHUD();
			}
			else if(r<0.75) {
				Horde.boo[pos]=new Deadite();
			}
			else {
				Horde.boo[pos]=new Trioxin();
			}
			Horde.remaining--;
		}
	}
}
