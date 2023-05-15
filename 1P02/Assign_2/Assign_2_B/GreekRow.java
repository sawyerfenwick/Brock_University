package Assign_2_B;


import Media.*;                  // for Turtle and TurtleDisplayer
import java.awt.*;               // for Color objects and methods
import static java.lang.Math.*;  // for math constants and functions
import static java.awt.Color.*;  // for Color constants
import static Media.Turtle.*;   // for FAST turtle 


/** This class creates a Greek Row that using the methods 'drawKey' and 'drawRow' in Turtle Graphics. It contains 
  * 17 Greek Keys.
  *
  * @author Sawyer Fenwick (st# 6005011)
  *
  * @version 1.0 October 11 2016                                                       */

public class GreekRow {
    
    
    // instance variables
     private TurtleDisplayer display;    
     private Turtle yertle; 
     
    /** This constructor creates the Turtle Displayer and the Turtle object 'yertle'
      *  places yertle on the canvas, calls the 'drawRow' method, which draws the Greek Row using 17 Greek Keys. */
    
    public GreekRow ( ) {
        
        // statements including call of method
        display = new TurtleDisplayer();      
        yertle = new Turtle(FAST); //creating a FAST turtle 
        display.placeTurtle(yertle); //placing turtle on display 
        drawRow();
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
    
    /** This method creates the Greek Row using the 'drawKey' method                                             */
    
    private void drawRow ( ) {
    
        //statements
      yertle.backward(136);
      for(int i = 1; i <=17; i++){
        drawKey();
        yertle.penUp();
      }
    
    }; // drawRow
    
    public static void main ( String[] args ) { GreekRow s = new GreekRow(); };

}  // GreekRow
