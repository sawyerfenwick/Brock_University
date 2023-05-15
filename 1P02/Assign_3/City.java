package Assign_3;

import Media.*;                  // for Turtle and TurtleDisplayer
import java.awt.*;               // for Color objects and methods
import static java.lang.Math.*;  // for math constants and functions
import static java.awt.Color.*;  // for Color constants
import static Media.Turtle.*;    // for FAST turtle

/** This class randomly generates a cityscape with 3 to 6 buildings each containing 5 to 15 stories.
  *
  * @author Sawyer Fenwick(st# 6005011)
  *
  * @version 1.0 November 2 2016                                                        */

public class City {
    
  // instance variables
    private TurtleDisplayer display;
    private Turtle yertle;
    
    /** This constructor creates the turtle object "yertle", creates a canvas of 500x500 and places yertle on the 
      * display. It calls on the "drawCityScape" method which draws the CityScape by calling on several other 
      * methods. */
    
    public City ( ) {
      
    // statements including call of method
      yertle = new Turtle(0);
      display = new TurtleDisplayer(yertle,500,500);
      int buildings = (int)(3*random())+3;
      drawCityScape(buildings);
      display.close();
        
    }; // constructor

    /** This method creates a square                                                          */
      private void drawSquare ( ) {
      
        // statements
        yertle.penDown();
        
        for (int i = 1; i <=4; i++){
          yertle.forward(10);
          yertle.left(PI/2);
        }
        
        yertle.penUp();
        
      }; // drawSquare
    
    /** This method draws a window built of 4 squares by calling on the "drawSquare" method.   */
      private void drawWindow ( ) {
      
        //statements
        yertle.penDown();
        
        for(int i = 1; i <=4; i++){
          drawSquare();
          yertle.right(PI/2);
        }
        
        yertle.penUp();
        
      }; // drawWindow
    
    /** This method draws a rectangle, which will be the outside of each building. It is passed the parameter
      * "storie" which is a randomly generated number between 5 and 15. The storie is multiplied by 
      *  30 (the height of 1 storie) to find the proper height of a rectangle that will accomodate how many 
      *  stories there will be. */
      private void drawRectangle(int storie){
        
        int width = 70;
        int height;
        height = storie*30;
        yertle.penDown();
      
        for (int i = 1; i <=2; i ++){
          yertle.forward(width);
          yertle.left(PI/2);
          yertle.forward(height);
          yertle.left(PI/2);
        }
        
        yertle.penUp();
        
      }; //drawRectangle
      
      /* This method draws however many stories is required to fill a building. */
      private void drawStorie(int storie){
        
      //local variables
        int height;
        height = storie*30;
      //statements 
        yertle.left(PI/2);
        yertle.forward(15);
        yertle.right(PI/2);
        yertle.forward(20);
        drawWindow();
        yertle.forward(30);
        drawWindow();
        yertle.left(PI);
        yertle.forward(50);
        yertle.right(PI/2);
        
        for(int i = 2; i <= storie; i ++){
          
          yertle.forward(30);
          yertle.right(PI/2);
          yertle.forward(20);
          drawWindow();
          yertle.forward(30);
          drawWindow();
          yertle.left(PI);
          yertle.forward(50);
          yertle.right(PI/2);
          
        }
        
        yertle.right(PI);
        yertle.forward(height - 15);
        yertle.left(PI/2);
        
      }; // drawStorie
      
      /* This method draws a completed building, using the "drawRectangle" and "drawStorie" methods. */
      private void drawBuilding(int storie){
        
        drawRectangle(storie);
        drawStorie(storie);
        
      } // drawBuilding
      
      /* This method draws a complete CityScape, based on the randomly generated numbers for how many buildings
       * will exist and how many stories each building will have. It calls on the "drawBuilding" method.*/
      private void drawCityScape(int buildings){
        
        yertle.moveTo(-210,-225); //"centering"
        
        for(int i = 1; i <= buildings; i ++){
          int storie = (int)(10*random())+5;
          drawBuilding(storie);
          yertle.forward(70);
        }
        
      } // drawCityScape
      
      public static void main ( String[] args ) { City s = new City(); };
      
}  // City
