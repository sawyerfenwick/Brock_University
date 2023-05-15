package Assign_1_A;

import Media.*;                  // for Turtle and TurtleDisplayer
import static java.lang.Math.*;  // for Math constants and functions
import static java.awt.Color.*;  // for Color constants

/** This class constructs a dodecagon using Turtle Graphics.
  *
  * @author Sawyer Fenwick(st# 6005011)
  * 
  * @version 1.0 October 1st 2016                                                        */

public class Dodecagon {
    
    // instance variables
    private TurtleDisplayer display; 
    private Turtle yertle;
    
    public Dodecagon ( ) {
      
    // constructor
    display = new TurtleDisplayer(); 
    yertle = new Turtle(0);
      
    // statements
    display.placeTurtle(yertle); //placing turtle on display
       
    for(int i = 1; i <= 12; i++){ //drawing the dodecagon
       yertle.penDown();
       yertle.forward(25); 
       yertle.right(2*PI/12);      
    }
        
    display.close();  //placing the close button on the display
     
    }; 
    
    public static void main ( String[] args ) { Dodecagon s = new Dodecagon(); };
    
} // Dodecagon