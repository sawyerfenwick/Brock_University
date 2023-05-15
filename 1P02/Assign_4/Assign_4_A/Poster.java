package Assign_4_A;

import Media.*;                  // for Picture and PictureDisplayer
import java.awt.*;               // for Color objects and methods
import static java.lang.Math.*;  // for math constants and functions
import static java.awt.Color.*;  // for Color constants

/** This class creates a "posterized" version of a photo. 
  *
  * @author Sawyer Fenwick st#6005011
  * @version 1.0 November 7 2016                                                        */

public class Poster {
    
    // instance variables
    private PictureDisplayer display;
    
    /* This constructor displays a picture on the display and posterizes it using the method "Posterize" */
    
    public Poster ( ) {

      Picture pic;
      display = new PictureDisplayer(500, 619);
      pic = new Picture();
      display.placePicture(pic);
      display.waitForUser();
      Posterize(pic); 
      
      display.close();
        
    }; // constructor
    
    /* This method sets the color channel of each color between 0-3, then scales it (*64) so that the picture
     * is not all black, giving color channels of either, 0, 64, 128, or 192.*/
    private void Posterize(Picture aPic){
      
      Pixel x; 
      int b;
      int g;
      int r;
      
      while(aPic.hasNext()){
       
       x = aPic.next();
       g = x.getGreen();
       b = x.getBlue();
       r = x.getRed();
       
       x.setGreen((int)(g/64*64));
       x.setBlue((int)(b/64*64));
       x.setRed((int)(r/64*64));
   
      }
      
    }; // Posterize

    public static void main ( String[] args ) { Poster s = new Poster(); };
    
} // Poster
