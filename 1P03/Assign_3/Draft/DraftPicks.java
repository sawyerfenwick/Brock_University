package Draft;

import BasicIO.*;
import Bags.*;


/*This class simulates the entry draft for a hockey little league. 
 * 
 * @author S.Fenwick
 * 
 * @version 1.0(March 19 2017)                                                  */

public class DraftPicks{
  
  private ASCIIDisplayer display;
  private ASCIIDataFile standings;
  private int count; 
  private Bag aBag;
  
  /* This constructor reads a data file of little league standings which represents the amount of tokens a team
   * will recieve in the draw. The tokens are added to a bag. The tokens are then removed 1 at a time, 
   * then all of those items are removed from the bag. When a token is removed from the bag its number (pick) is 
   * outputed to the displayer. */
  
  public DraftPicks(){
    
    count = 1;
    String name;
    int tokens;
    int teamCounter = 0;
    
    standings = new ASCIIDataFile();
    display = new ASCIIDisplayer();
    
    aBag = new ConBag();
   
    for( ; ; ){
      
      name = standings.readString();
      if(standings.isEOF()){break;}
      teamCounter += 1;
      tokens = standings.readInt();
      
      addTokens(name, tokens);
      
    }
    
    for(int i = 1; i < teamCounter; i ++){
      
      removeOneToken();
  
    }
    
    display.close();
    standings.close();
    
  } //constructor
  
  /* This method adds the tokens to the bag. 
   * 
   * @param name the name of the team
   * 
   * @param tokens the number of tokens to be added to the bag  */
  
  private void addTokens(String name, int tokens){
    
    for(int i = 0; i < tokens; i ++){
      aBag.add(name);
    }
    
  } //addTokens
  
  /* This method removes one token from the bag at random.  */
  
  private void removeOneToken(){
    
    String name;
    name = aBag.draw();
    writeToDisplay(name);
    removeAllTokens(name);
    
  } //removeOneToken
  
  /* This method removes all the tokens of one particular colour, the one that was removed at random from
   * the method removeOneToken(); 
   * 
   * @param team the team name to be removed from the bag  */
  
  private void removeAllTokens(String team){
    
    while(aBag.contains(team)){
      aBag.remove(team);
    }
    
  } //removeAllTokens
  
  /* This method writes the team name and the pick number to the display. 
   * 
   * @param team the team to be written to the display  */
  
  private void writeToDisplay(String team){
    
    display.writeString("Pick " + count + ": " + team );
    display.newLine();
    count += 1;
    
  } //writeToDisplay
  
  public static void main (String args[]) {DraftPicks d = new DraftPicks();}
  
} //DraftPicks