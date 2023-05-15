package Loan_Shark;


import java.util.*;


/** This class defines an iterator over the customers in a course as required 
  * by the Iterator interface. 
  *
  * @see Customer
  * @see LoanShark
  * 
  * @author S. Fenwick
  * 
  * @version 1.0 (Apr. 2017)                                                           */

class LoanSharkIterator implements Iterator<Customer> {
  
  private Customer[] theCustomers; // the customers of the loan shark
  private int        index;        // next customer to access
  
  /** This constructor creates an iterator over the specified array of customers
    * being the customers in the agency. Iteration begins at the first customer 
    * in the list.
    *
    * @param c the array of customers in the agency.                                   */
  
  LoanSharkIterator ( Customer[] c ) {
    
    theCustomers = c;
    index = 0;
    
  }; //constructor
  
  
  /** This method returns true if there is at least one more customer in the agency.
    *
    * @return boolean true if at least one more customer in agency.                    */
  
  public boolean hasNext ( ) { // from Iterator
    
    return index < theCustomers.length;
    
  }; //hasNext
  
  
  /** This method returns the next customer in the agency. 
    * 
    * @return Customer the next customer in the list. 
    * 
    * @throws NoSuchElementException no customers left in the course. */
  
  public Customer next ( ) { // from Iterator
   
    if ( !hasNext() ) {
      throw new NoSuchElementException();
    };
    index = index + 1;
    return theCustomers[index-1];
    
  }; //next
  
  
} //LoanSharkIterator