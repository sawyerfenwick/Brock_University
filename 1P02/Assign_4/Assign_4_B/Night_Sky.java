package Assign_4_B;

import Media.*;                  // for Picture and PictureDisplayer
import java.awt.*;               // for Color objects and methods
import static java.lang.Math.*;  // for math constants and functions
import static java.awt.Color.*;  // for Color constants

/** This class takes a picture and changes the day sky to a night sky.
  *
  * @author Sawyer Fenwick st#6005011
  * @version 1.0 November 7 2016                                                        */

public class Night_Sky {
    
    // instance variables
    private PictureDisplayer display;
    
    /** This constructor places the picture on the display and changes the sky blue pixels to dark blue, 
      * and changes random sky blue pixels to white (approximately 1 in 2000) as if they were stars, using the
      * method "Night".*/
    
    public Night_Sky ( ) {

      // local variables
      Picture pic;
      display = new PictureDisplayer(640, 427);
      pic = new Picture();
      display.placePicture(pic);
      display.waitForUser();
      Night(pic); 
      
      display.close();
        
    }; // constructor
    
    /* This method searches each pixel, and checks if it is "sky blue". If it is sky blue this method replaces the 
     * color with "night blue". Apporximately 1 in 2000 sky blue pixels are converted to white instead of night blue.*/
    private void Night(Picture aPic){
      
      Pixel x;
      double d;
      double r;
      Color c;
      Color skyBlue;
      Color nightBlue;
      Color white;
      //local variables
      
      while (aPic.hasNext()){
        
        x = aPic.next();
        c = x.getColor();
        r = (double)(random());
        
        skyBlue = (new Color(8431307));
        nightBlue = (new Color(1054800));
        white =  (new Color(16777088));
        d = x.getDistance(skyBlue);
        
        if ( d < 64.5){
          x.setColor(nightBlue);  
        } 
        
        if ( d < 64.5 & r < 0.0005){
          x.setColor(white);
        }
       
      }
      
    }; // Night

    public static void main ( String[] args ) { Night_Sky s = new Night_Sky(); };
      
} // Night_Sky
