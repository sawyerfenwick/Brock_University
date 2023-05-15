package Assign_1;

import BasicIO.*;

public class WordSearch {
  
  private ASCIIDataFile pData;
  private ASCIIDisplayer display;
  
  private char[] [] puzzle;
  
  public WordSearch( ) {
    pData = new ASCIIDataFile();
    display = new ASCIIDisplayer();
    loadPuzzle();
    pData.close();
    display.close();
    
  }; // constructor
  
  private void loadPuzzle(){
    
    int x = pData.readInt();
    int y = pData.readInt();
    
    puzzle = new char[x][y];
    
    for(int i = 0; i < puzzle.length; i ++){
      for(int j = 0; j < puzzle[i].length; j ++){
        
        puzzle[i][j] = pData.readChar();
        
      }
    }
    
    displayPuzzle();
    
    for( ; ; ){
      
      String word = pData.readString();
      
      if(pData.isEOF()){break;}
      
      check(word);
      
    }
    
    
  }; // loadPuzzle
  
  private void check(String word){
    
    int x = 0;
    int y = 0;
    
    checkRight(x, y, word);
    
  } // check
  
  private boolean checkRight ( int x, int y, String word ) { 
    
    
    display.writeLine(word + " found (right) at (" + x  + ", " + y + ")");
    
    
   /* boolean[]  checker;
    boolean    valid = true;
    
    for ( int i=0 ; i<puzzle.length ; i++ ) {
      checker = makeChecker();
      for ( int j=0 ; j<puzzle[i].length ; j++ ) {
        checker[puzzle[i][j]] = true;
      };
      valid = evalChecker(checker);
      display.writeLine(word + " found (right) at (" + x  + ", " + y + ")");
    };
    
    return valid;
    */
  } // checkRight
  
  private boolean[] makeChecker ( ) {
    
    boolean[]  result;
    
    result = new boolean[puzzle.length+1];
    for ( int i=0 ; i<result.length ; i++ ) {
      result[i] = false;
    };
    return result;
    
  };  // makeChecker
  
  
  private boolean evalChecker ( boolean[] checker ) {
    
    boolean result;
    
    result = true;
    for ( int i=1 ; i<checker.length ; i++ ) {
      result = result & checker[i];
    };
    return result;
    
  };  // evalChecker
  
  private void displayPuzzle(){
    
    for(int i = 0; i < puzzle.length; i ++){
      for(int j = 0; j < puzzle[i].length; j ++){
        
        display.writeChar(puzzle[i][j]);
        
      }
      display.newLine();
    }
    
  }; // displayPuzzle
  public static void main ( String[] args ) { WordSearch w = new WordSearch(); };
  
} // Sudoku
