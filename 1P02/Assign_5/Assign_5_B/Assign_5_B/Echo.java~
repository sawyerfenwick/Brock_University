package Assign_5_B;

import Media.*;                  // for Pictures and Sounds
import java.awt.*;               // for Color objects and methods
import static java.lang.Math.*;  // for math constants and functions
import static java.awt.Color.*;  // for Color constants


/** This class is supposed to create an echo effect on a sound clip, however I could not produce the full echo effect.
  *
  * @author Sawyer Fenwick st # 6005011
  * @version 1.0 Novemeber 18 2016                                                      */

public class Echo {
  
  // instance variables
  private SoundPlayer player;
    
    /** This constructor creates a and places a sound on a sound player, allowing the user to hear it before
      * creating the echo effect. It runs the echoSound method which creates a new sound which is an echo of the
      * original sound file.*/
    
    public Echo ( ) {
      
      Sound original;
      Sound echoSound;
      // local variables
      
      player = new SoundPlayer();
      original = new Sound();
      player.placeSound(original);
      player.waitForUser();
      echoSound = echo(original, 0.50, 0.25);
      player.placeSound(echoSound);
      
      player.close();
      // statements including call to method
      
    }; // constructor
    
    // methods
    private Sound echo (Sound aSound, double delay, double factor){
      
      int num = aSound.getNumSamples();
      int rate = aSound.getSampleRate();
      int delayNum = (int)(rate*delay);
      int total = delayNum + num;
      
      int counter = 0;
      int counter2 = delayNum; 
      int counter3 = num;
      
      Sound result = new Sound(total, aSound);
      result.save();
      
      //part one "This"
      for(int j = 0; j < delayNum - 1; j ++){
          int amp = aSound.getSample(j).getAmp();
          result.getSample(counter).setAmp(amp);
          counter = counter + 1;
        }
        //part two "This + This is a test"
        for(int x = 0; x < num - 1; x ++){
          int ampEcho = (int)(aSound.getSample(x).getAmp()*factor);
          int ampRest = aSound.getSample(x).getAmp() - aSound.getSample(x).getAmp() - delayNum;
          int amp = ampEcho + ampRest;
          result.getSample(counter).setAmp(amp);
          counter = counter + 1;
          
          /* if(x == num -1){
          for(int y = num; y < total - 1; y ++){
            int ampFinal = (int)(aSound.getSample(y).getAmp()*factor);
             result.getSample(counter3).setAmp(ampFinal);
             counter3 = counter3 + 1; 
             }
            }*/
        }
        
        //My attempts at getting the 3rd part (the echo) of the program to run
        
        
        //part three "This + This is + is + a + a + test + test"
        /*     for(int y = 0; y < total - 1; y ++){
    int amp1 = (int)(aSound.getSample(y).getAmp()*factor);
            int ampR = aSound.getSample(y).getAmp() - num;
            int ampFinal = amp1 + ampR;
            result.getSample(counter).setAmp(ampFinal);
            counter = counter + 1;
            }*/
        
        //PART THREE?
        
        /*     for(int y = num; y < total - 1; y ++){
    * 
          int amp = (int)(aSound.getSample(y).getAmp()*factor);
          int ampRest = aSound.getSample(y).getAmp() - aSound.getSample(y).getAmp() - num;
          result.getSample(counter3).setAmp(amp);
          counter3 = counter3 + 1;
          
          } */
        
        //part three
        /*   for(int i = 0; i < total - 1; i ++){
      int ampRest = aSound.getSample(i).getAmp() - aSound.getSample(i).getAmp() - num;
          int amp = (int)(ampRest*factor);
          result.getSample(counter).setAmp(amp);
          counter = counter + 1;
        } */
        
        /*     for (Sample s : aSound){
         int amp = s.getAmp;
         s.setAmp((int)(amp*factor));
         } */ 
        
        return result;
        
    }// echo
    
   
    public static void main ( String[] args ) { Echo s = new Echo(); };
    
    
} // Echo
