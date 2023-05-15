package Bags;

import java.io.*;
import static java.lang.Math.*; 


/*This class provides an implementation of the Bag class. 
 * 
 * @see Bag
 * 
 * @author S.Fenwick 
 * 
 * @version 1.0(March 19 2017)                                                             */

public class ConBag implements Bag {
  
  private String[] aBag; //the bag
  private String[] bBag; //the copy of aBag 
  private int  front;  // index of the front item
  private int  rear;   // index of next available item
  private int  counter;  // number of items in the bag
  private int index; //index of what is being searched for in order to remove from bag
  
  
  /*This constructor creates a new empty bag capable of holding 100 items. */
  
  public ConBag(){
    
    this(100);
    
  } //constructor
  
  /*This constructor creates a new empty bag capable of holding a particular number of items. 
   * 
   * @param size  max number of items in the bag. */
  
  public ConBag(int size){
    
    aBag = new String[size];
    front = 0;
    rear = 0;
    counter = 0;
    
  } //constructor 
  
  /*This constructor takes an array of Strings and copies it into an empty bag with capacity 100.
   * 
   *@param aBag  array of Strings to be copied. */
  
  public ConBag(String[] aBag){
    
    bBag = new String[aBag.length];
    
    for(int i = 0; i < aBag.length; i++){
      
      bBag[i] = aBag[i];
      
    }
    
    front = 0;
    rear = 0;
    counter = 0;
    
  } //constructor
  
  public void add ( String item ){
    
    if ( counter >= aBag.length ) {
      throw new NoSpaceException();
    }
    else {
      aBag[rear] = item;
      rear = rear + 1;
      counter = counter + 1;
    };
    
  } //add
  
  public void remove ( String item ){
    
    if ( counter <= 0 ) {
      throw new NoItemException();
    }
    else{
      
      for(int i = 0; i < aBag.length; i ++){
        
        if(aBag[i] == item){
          
          index = i;
          break;
        }
        
      }
      
      for(int i = index; i <= rear - 1; i ++){
        
        aBag[i] = aBag[i + 1];
        
      }
      counter = counter - 1;
      rear = rear - 1;
    }
    
  } //remove
  
  public int cardinality ( ){
    
    return counter;
    
  } //cardinality
  
  public boolean contains ( String item ){
    
    boolean result = false;
    
    for(int i = 0; i < aBag.length; i ++){
      
      if(aBag[i] == item){
        result = true;
        break;
      }
      else{
        result = false;
      }
    }
    
    return result;
    
  } //contains
  
  public int count ( String item ){
    
    int result = 0;
    
    for(int i = 0; i < aBag.length; i ++){
      
      if(aBag[i] == item){
        result += 1;
      }
      
    }
    
    return result;
    
  } //count
  
  public String draw ( ){
    
    int i;
    String item;
    
    i = (int)((counter)*random()) + 0;
    item = aBag[i];
    
    if(counter <= 0){
      throw new NoItemException();
    }
    else{
      remove(item);
    }
    return item;
    
  } //draw
  
} //ConBag