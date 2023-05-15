package Bags;


/** This interface defines the ADT Bag---a collection of items (Strings) where
  * multiple occurrences of any item may be present. Items may be added and
  * removed. The bag can be tested to determine whether or not it contains a
  * particular item. The total number of items in the bag (including duplicates)
  * can be determined as can the number of occurrences of a particular item.
  * Finally an arbitrary item can be drawn from the bag.
  * 
  * @author D. Hughes
  * 
  * @version 1.1 (Feb. 2017)                                                     */

public interface Bag {
  
  
  /** This method adds an item (String) to the bag. It fails if there is no more
    * room to add an item.
    * 
    * @param  item  the item (String) to be added
    * 
    * @exception  NoSpaceException  if no space is availablle to add the item. */
  
  public void add ( String item );
  
  
  /** This method removes an occurrence of an item (String) from the bag. It
    * fails if there are no occurrences of that item in the bag.
    * 
    * @param  item  the item (String) to be removed
    * 
    * @exception  NoItemException  if no occurrences of the item in the bag.   */
  
  public void remove ( String item );
  
  
  /** This method determines total number of items (including duplicates) in the
    * bag.
    * 
    * @return  int  the number of items in the bag.                            */
  
  public int cardinality ( );
  
  
  /** This method determines whether or not there is an occurrence of an item
    * (String) in the bag.
    * 
    * @param  item  the item (String) to be checked
    * 
    * @return  boolean  at least one occurrence of the item in the bag.        */
  
  public boolean contains ( String item );
  
  
  /** This method determines number of occurrences of an item in the bag.
    * 
    * @param  item  the item (String) to be counted
    * 
    * @return  int  the number of occurrences of the item in the bag.          */
  
  public int count ( String item );
  
  
  /** This method randomly selects an item and removes it from the bag. The
    * probability of selecting a particular item is proportional to the number of
    * occurrences of that item in the bag. It fails if there are no items in the
    * bag.
    * 
    * @return  String  the item (String) selected
    * 
    * @exception  NoItemException  if there are no items in the bag.           */
  
  public String draw ( );
  
  
}  // Bag