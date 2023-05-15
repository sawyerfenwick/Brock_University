package Assign_2_A;


import Media.*;                  // for Turtle and TurtleDisplayer
import java.awt.*;               // for Color objects and methods
import static java.lang.Math.*;  // for math constants and functions
import static java.awt.Color.*;  // for Color constants
import static Media.Turtle.*;   // for FAST turtle 


/** This class creates a Greek Key using the method 'drawKey' in Turtle Graphics. 
  *
  * @author Sawyer Fenwick (st# 6005011)
  *
  * @version 1.0 October 11 2016                                                       */

public class GreekKey {
    
    
    // instance variables
     private TurtleDisplayer display;    
     private Turtle yertle; 
     
    /** This constructor creates the Turtle Displayer and the Turtle object 'yertle'
      *  places yertle on the canvas, calls the 'drawKey' method, which draws the Greek Key. */
    
    public GreekKey ( ) {
        
        // statements including call of method
        display = new TurtleDisplayer();      
        yertle = new Turtle(FAST); //creating a FAST turtle 
        display.placeTurtle(yertle); //placing turtle on display 
        drawKey();
        display.close();
    }; // constructor

    
    
    /** This method creates the Greek Key                                                           */
    
    private void drawKey ( ) {
    
        // statements
        yertle.penDown();
        yertle.forward(2);
        yertle.left(2*PI/4);
        yertle.forward(12);
        yertle.right(2*PI/4);
        yertle.forward(12);
        yertle.right(2*PI/4);
        yertle.forward(8);
        yertle.right(2*PI/4);
        yertle.forward(4);
        yertle.right(2*PI/4);
        yertle.forward(4);
        yertle.left(2*PI/4);
        yertle.forward(4);
        yertle.left(2*PI/4);
        yertle.forward(8);
        yertle.left(2*PI/4);
        yertle.forward(10);
      
      
    
    }; // drawKey

    
    
    public static void main ( String[] args ) { GreekKey s = new GreekKey(); };

    
    
}  // GreekKey
