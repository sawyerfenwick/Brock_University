package Assign_1_B;

import Media.*;                  // for Turtle and TurtleDisplayer
import static java.lang.Math.*;  // for Math constants and functions
import static java.awt.Color.*;  // for Color constants
import static Media.Turtle.*;   // for FAST turtle

/** This class draws a crystal made up of 24 dodecagons using Turtle Graphics.
  *
  * @author Sawyer Fenwick(st# 6005011)
  * 
  * @version 1.0 October 1st 2016                                                        */

public class Crystal {
    
    // instance variables
    private TurtleDisplayer display; 
    private Turtle yertle;
    
    public Crystal ( ) {
      // constructor
      display = new TurtleDisplayer();
      yertle = new Turtle(FAST); //creating a FAST turtle
      
      //statements
      display.placeTurtle(yertle); //placing turtle on display
      
      for(int j = 1; j <= 24; j++){ //drawing the crystal by repeating a dodecagon 24 times
        for(int i = 1; i <= 12; i++){ //drawing a single dodecagon
          yertle.penDown();
          yertle.forward(25);
          yertle.right(2*PI/12);
          
        }
        yertle.right(2*PI/24); //rotating the turtle after each dodecagon to create a full circle crystal
      }
      display.close(); //placing the close button the display
    }; 
     
    public static void main ( String[] args ) { Crystal s = new Crystal(); };
    
} // Crystal