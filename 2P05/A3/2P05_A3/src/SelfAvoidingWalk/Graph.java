package SelfAvoidingWalk;

import java.awt.*;
import javax.swing.*;
/**
 * This class draws the set of vertices on the JPanel 
 * 
 * @author Sawyer Fenwick | 6005011 | sf15zx
 *
 */
public class Graph extends JPanel implements Runnable {

	public void drawGraph(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setPaint(Color.black);
		
		int w = HillClimber.currWidth;
		int h = HillClimber.currHeight;
		
		int x = 0;
		for(int i = 0; i < w; i++) {
			x += 40;
			int y = 0;
			for(int j = 0; j < h; j++) {
				g.drawOval(x, y+40, 10, 10);
				y +=40;
			}
		}
		
		if(!HillClimber.walk.isEmpty()) {
			x = 45;
			int y = 40;
			for(int i = 0; i < HillClimber.walk.size(); i++) {
				if(HillClimber.walk.get(i).dir == 'N') {
					g.drawLine(x, y, x, y-30);
					y-=40;
				}
				else if(HillClimber.walk.get(i).dir == 'E') {
					g.drawLine(x+5, y+5, x+35, y+5);
					x+=40;
				}
				else if(HillClimber.walk.get(i).dir == 'S') {
					g.drawLine(x, y+10, x, y+40);
					y+=40;
				}
				else if(HillClimber.walk.get(i).dir == 'W') {
					g.drawLine(x-5, y+5, x-35, y+5);
				}
			}
		}
		//keep current count of x and y, draw line increments of x+10, y+10
	}//drawGraph
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGraph(g);
	}//paintComponent

	@Override
	public void run() {
		System.out.println("Graph Thread");
	}//run
}//Graph