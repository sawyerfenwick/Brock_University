package Assign_2;

/** This class references the node being used. 
  * 
  * @author S. Fenwick
  *
  * @version 1.0 (Feb. 2017)                                                     */

class Node{
  
  Job item; //job to be printed
  Node next; //next node
  
  Node (Job j, Node n){
    
    item = j;
    next = n;
    
  } //constructor 
  
} //Node