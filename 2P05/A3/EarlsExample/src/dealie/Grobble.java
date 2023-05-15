package dealie;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

/** This is just a simple example of a window with a panel that changes what it draws, according to
  * some state information.
  * That state information can be changed via buttons.
  */
public class Grobble extends JFrame {
	final long DRAWDELAY=16;
	WokkaPanel panel;
	JButton upButton;
	JButton downButton;
	Object lock=new Object(); //Because who wants to bother with synchronized methods?
	int span=32;
	
	public Grobble() {
		super("Odd, eh?");
		setup();
		doThings();
	}
	
	private void setup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //We just want the program to stop when we close the window
		setLayout(new BorderLayout());
		
		JPanel buttonPanel=new JPanel(); //This panel acts solely as a container for additional components
		GridLayout gridout=new GridLayout(1,2); //Because why not?
		buttonPanel.setLayout(gridout);
		add(buttonPanel,BorderLayout.SOUTH);
		
		panel=new WokkaPanel(this);
		add(panel);
		
		upButton=new JButton("Up!");
		//Yeah, it might be better to have this Grobble/JFrame be the listener, but this works:
		upButton.addActionListener(
			(actionEvent)-> {
				synchronized(lock) {
					span=span<512?span*2:span;
				}
			}
		);
		buttonPanel.add(upButton);
		
		downButton=new JButton("Down!");
		downButton.addActionListener(
			(actionEvent)-> {
				synchronized(lock) {
					span=span>1?span/2:span;
				}
			}
		);
		buttonPanel.add(downButton);
		
		setResizable(false); //No reason, really
		pack(); //Again, we need it to assemble the window (solve constraints, etc.)
		setVisible(true); //The window is easier to see if it's visible
	}
	/*Incidentally, if you're wondering, "why is it so tedious to create a GUI? Manually placing everything?!?",
	 *there's a reason for that.
	 *The (old) classic approach was for an IDE to autogenerate the code for the GUI elements, according to
	 *what the developer laid out via a drag&drop interface.
	 *These days, you get largely the same, but the interface specifications might instead be stored in a more
	 *convenient format (e.g. XML).
	 */
	
	private void doThings() {
		//Could/should I have used a thread here? Mehbe
		//We could also use the AWT event queue for communication, if we had ALL the time on our hands
		while (true) {
			panel.repaint(); //Mark it as needing a full repaint
			delay(DRAWDELAY);
		}
	}
	
	private void delay(long time) {
		try {Thread.sleep(time);}catch (InterruptedException ie){};
	}
	
	public static void main(String[] args) {new Grobble();}
}

/** Whenever this panel is redrawn, it draws a single, randomly-coloured line from the upper-left corner
  * to some randomly-selected point (within a bounded range).
  * The range of the end-point of the lines comes from the received Grobble object.
  */
class WokkaPanel extends JPanel {
	Grobble grob;
	WokkaPanel(Grobble grob) {
		setPreferredSize(new Dimension(520,520));
		setSize(new Dimension(520,520));
		this.grob=grob;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//super.paintComponent(g); //This is mostly just to ensure the background looks appropriate to the window toolkit
		//(So, commented-out because this version looks neater)
		//(Also, we should probably be explicitly turning on opacity...)
		
		g.setColor(new Color((float)Math.random(),(float)Math.random(),(float)Math.random()));
		int x2=(int)(Math.random()*grob.span); int y2=(int)(Math.random()*grob.span);
		g.drawLine(0,0,x2,y2);
	}
	/*BTW, you might wonder: why doesn't this one use the lock?
	 *Between the 'span' being a primitive type, this panel not mutating it, and it being okay if we get an
	 *outdated copy, it doesn't really matter all that much.
	 *Of course, if it were a data structure...
	 */
}
