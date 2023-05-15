package dealie;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

/** This is just a simple example that demonstrates some line-drawing.
  * It presents a dialog to the user, who can then input well-defined sets of
  * coordinates.
  *
  * Note that it is NOT properly threaded. Almost like threading is covered in other examples...
  *
  * Anyhoo, the 'Lines' class is also the 'main' class, and the primary JFrame.
  * Additionally, it relies on JOptionPane, which is good for modal dialogs.
  *
  * See also:
  * https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
  * https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
  *
  * Incidentally, if you wanted to get REALLY fancy with proper adherence to standards (wrt threading):
  * https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html#threading
  *
  * Lastly, if you're interested in how 'painting' works:
  * https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
  * https://www.oracle.com/technetwork/java/painting-140037.html
  */
public class Lines extends JFrame { //A JFrame, because it's a window
	/*You might notice, this time, we're just using a JPanel. That's because this is a simpler example,
	  where the panel itself doesn't actually do anything.
	  //Instead, we'll be drawing onto the 'graphics' context directly.
	  (That's actually not a great idea, but we need to look at different approaches)
	  */
	JPanel panel;
	JButton button;
	
	public Lines() {
		super("Odd, eh?");
		setup();
		doThings();
	}
	
	/**Let's set up the GUI, shall we? This is a simple one, so there's nothing weird to worry about.
	  */
	private void setup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //We just want the program to stop when we close the window
		setLayout(new BorderLayout()); //There ARE other layouts, but this one's just too easy to not use!
		
		panel=new JPanel();
		panel.setSize(new Dimension(200,200));
		panel.setPreferredSize(new Dimension(200,200));
		add(panel,BorderLayout.NORTH);
		/*BTW, there's a major problem with not customizing the JPanel (to override the paintComponent):
		 *If, while it isn't drawing (e.g. if it's waiting on input), you switch away and back (or drag another window in
		 *front of it and then remove it), it can't repaint itself. So it'll be blank until the next painting cycle.
		 */
		
		setResizable(false); //No reason, really
		//It's probably silly to point this out, but 'setResizable', 'add', etc. all work because 'this' class extends JFrame
		pack(); //Again, we need it to assemble the window (solve constraints, etc.)
		setVisible(true); //The window is easier to see if it's visible
	}
	
	private void doThings() {
		/*BTW, since Swing isn't considered 'thread-safe', it's actually best to let the 'main GUI thread' handle
		 *components, and let separate threads deal with data.
		 *However, in this case, there likely isn't anything to worry about.
		 */
		int[] xes={10,50,30}, yes={70,30,40};
		while (true) {
			String pts="";
			for (int i=0;i<xes.length;i++)
				pts+=" "+xes[i]+","+yes[i];
			pts=pts.trim();
			pts=(String)JOptionPane.showInputDialog(
				null, //Normally this should be 'this', but we want it to not superimpose on the frame
				"X,Y X,Y X,Y (etc.)",
				"Points, please!",
				JOptionPane.PLAIN_MESSAGE, //Just a plain message, without an icon
				null, //Still don't care about an icon
				null, //If this were an array of strings, it'd be a drop-down list instead
				pts //Default value
			); //BTW, if this seems slightly unnecessary... it's good practice?
			if (pts==null) break;
			
			String[] newPts=pts.split(" ");
			int[] newXes=new int[newPts.length];int[] newYes=new int[newPts.length];
			try {
				for (int i=0;i<newPts.length;i++) {
					String[] pt=newPts[i].split(",");
					newXes[i]=Integer.parseInt(pt[0]);
					newYes[i]=Integer.parseInt(pt[1]);
				}
				xes=newXes; yes=newYes;
			}
			catch (Exception e){} //Yeah, no
			
			Graphics g=panel.getGraphics();
			g.drawPolyline(xes,yes,xes.length);
			
		}
		
	}
	
	/*private void delay(long time) {
		try {Thread.sleep(time);}catch (InterruptedException ie){};
	}*/
	
	public static void main(String[] args) {new Lines();}
}