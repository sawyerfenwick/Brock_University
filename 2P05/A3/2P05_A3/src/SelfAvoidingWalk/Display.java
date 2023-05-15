package SelfAvoidingWalk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Creates the button panel for the JFrame
 * 
 * @author Sawyer Fenwick | 6005011 | sf15zx 
 *
 */
public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton upB, downB, rightB, leftB, startB, stopB;
	GridBagConstraints c = new GridBagConstraints();
	
	/**
	 * Create the display
	 */
	public Display() {
		setLayout(new GridBagLayout());
		setBackground(Color.lightGray);
	    
		upB = new JButton("Increase Y");
		downB = new JButton("Decrease Y");
		rightB = new JButton("Increase X");
		leftB = new JButton("Decrease X");
		startB = new JButton("Start");
		stopB = new JButton("Stop");
		
		//decrease X
		upB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(HillClimber.currHeight < HillClimber.MAX_HEIGHT) {
					HillClimber.bestCount = 1;
					HillClimber.count = 1;
					HillClimber.currHeight++;
					HillClimber.walk.clear();
					HillClimber.resize(new Graph());
				}
			}
		});
		
		//increase Y
		downB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(HillClimber.currHeight > HillClimber.MIN) {
					HillClimber.bestCount = 1;
					HillClimber.count = 1;
					HillClimber.currHeight--;
					HillClimber.walk.clear();
					HillClimber.resize(new Graph());
				}
			}
		});
		
		//increase X
		rightB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(HillClimber.currWidth < HillClimber.MAX_WIDTH) {
					HillClimber.bestCount = 1;
					HillClimber.count = 1;
					HillClimber.currWidth++;
					HillClimber.walk.clear();
					HillClimber.resize(new Graph());
				}
			}
		});
		
		//decrease X
		leftB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(HillClimber.currWidth > HillClimber.MIN) {
					HillClimber.bestCount = 1;
					HillClimber.count = 1;
					HillClimber.currWidth--;
					HillClimber.walk.clear();
					HillClimber.resize(new Graph());
				}
			}
		});
		
		//begins the program, try catch block that is commented out would run the threads. Uncomment to see thread execution. 
		startB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HillClimber.begin();
				/*try {
					HillClimber.start();
				} catch (InterruptedException e1) {
					System.out.println(e1);
				}*/
			}
		});
		
		stopB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HillClimber.stop(); //stop all threads
			}
		});
		
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		add(upB,c);
		c.gridx = 0;
		c.gridy = 1;
		add(downB,c);
		c.gridx = 0;
		c.gridy = 2;
		add(rightB,c);
		c.gridx = 0;
		c.gridy = 3;
		add(leftB,c);
		c.gridx = 0;
		c.gridy = 4;
		add(startB,c);
		c.gridx = 0;
		c.gridy = 5;
		add(stopB,c);
	}//constructor
}//Display