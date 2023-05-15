package Assign_2_C;


import Media.*;                  // for Turtle and TurtleDisplayer
import java.awt.*;               // for Color objects and methods
import static java.lang.Math.*;  // for math constants and functions
import static java.awt.Color.*;  // for Color constants
import static Media.Turtle.*;   // for FAST turtle 


/** This class creates a Greek Border around the canvas by calling on the methods 'drawBorder', 'drawRow' and 
  * 'drawKey', it consists of 4 Greek Rows and 68 Greek Keys in Turtle Graphics.
  *
  * @author Sawyer Fenwick (st# 6005011)
  *
  * @version 1.0 October 11 2016                                                       */

public class GreekBorder {
    
    
    // instance variables
     private TurtleDisplayer display;    
     private Turtle yertle; 
     
    /** This constructor creates the Turtle Displayer and the Turtle object 'yertle'
      * places yertle on the canvas, calls the 'drawBorder' method, which draws a border around the canvas,
      * using the 'GreekRow' and 'GreekKey' methods. */
    
    public GreekBorder ( ) {
        
        // statements including call of method
        display = new TurtleDisplayer();      
        yertle = new Turtle(FAST); //creating a FAST turtle 
        display.placeTurtle(yertle); //placing turtle on display 
        drawBorder();
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
       for(int i = 1; i <=17; i++){
         drawKey();
         yertle.penUp();
       }
    
    }; // drawRow
    
    /** This method creates the Greek Border using the 'drawKey'  and 'drawRow' methods.                          */
    
     private void drawBorder ( ) {
    
       //statements
       yertle.moveTo(-136,136);
       for(int i = 1; i <=4; i++){
         drawRow();
         yertle.right(2*PI/4);
         yertle.penUp();
       }
    
    }; // drawBorder
    
    public static void main ( String[] args ) { GreekBorder s = new GreekBorder(); };

}  // GreekBorder