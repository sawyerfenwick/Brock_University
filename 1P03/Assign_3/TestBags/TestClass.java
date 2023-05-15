package TestBags;

import Bags.*;
import BasicIO.*;


/* This class is a test harness to test the Bags library including ConBag.
 * 
 * @author S.Fenwick
 * 
 * @version 1.0(March 2017)                                           */

public class TestClass{
  
  private ASCIIDataFile data;
  
  /* This constructor tests the ConBag implementations of the Bag interface. */
  
  public TestClass(){
   
   // data = new ASCIIDataFile();
    Bag bag100; //uses constructor 1
    Bag fullBag; //uses constructor 2 
    Bag stringBag; //uses constructor 3
    String[] sBag = {"a", "b", "c", "d"};
    
    bag100 = new ConBag();
    fullBag = new ConBag(1);
    sBag = new String[100];
    
    stringBag = new ConBag(sBag);
    
    //Testing NoItemException
    try{
      bag100.remove("red");
    }
    catch (NoItemException e ){
      System.out.println("No Items to Withdraw.");
    }
    
    //Testing NoSpaceException
    fullBag.add("red");
    try{
      fullBag.add("red");
    }
    catch(NoSpaceException e ){
      System.out.println("No Space in Bag.");
    }
    
    //Testing method ADD
    bag100.add("red");
    bag100.add("red");
    bag100.add("green");
    bag100.add("red");
    //Testing method CARDINALITY
    System.out.println(bag100.cardinality());
    //Testing method CONTAINS
    System.out.println(bag100.contains("red"));
    System.out.println(bag100.contains("green"));
    System.out.println(bag100.contains("blue"));
    //Testing method REMOVE
    bag100.remove("red");
    System.out.println("bag100 cardinality" + bag100.cardinality());
    System.out.println(bag100.contains("red"));
    //Testing method COUNT
    bag100.add("red");
    bag100.add("red");
    System.out.println(bag100.count("blue"));
    System.out.println(bag100.count("red"));
    System.out.println(bag100.count("green"));
    //Testing method DRAW
    System.out.println(bag100.cardinality());
    bag100.draw();
    System.out.println(bag100.cardinality());
    System.out.println(bag100.contains("red"));
    System.out.println(bag100.contains("green"));
    System.out.println(bag100.contains("blue"));
    
  } //constructor
 
  
  public static void main (String args[]){ TestClass t = new TestClass();}
  
} //TestClass