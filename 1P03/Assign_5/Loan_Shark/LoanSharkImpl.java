package Loan_Shark;


import java.io.*;
import BasicIO.*;
import java.util.*;

public class LoanSharkImpl implements LoanShark, Serializable {
  
  private static final long serialVersionUID = 99990001L;
  
  private double prevTotal;                // the previous balance total across all customers
  private double debTotal;                 // the debits total across all customers
  private double credTotal;                // the credits total across all customers
  private double intTotal;                 // the interest total across all customers
  private double newTotal;                 // the new balance total across all customers
  private double minTotal;                 // the minimum payment total across all customers
  private Customer[] theCustomers;         // customers in the agency
  public int customerNum;                 // number of customers
  
  /** This constructor creates a new "loan shark", and loads in the student list.            */
  
  public LoanSharkImpl ( ASCIIDataFile customerList ) {
    
    loadCustomers(customerList);
    
  }; //constructor 
  
  
  /** This method returns an iterator over the custoemrs in the agency. It is
    * required by the Iterable interface.
    * 
    * @return  Iterator  an iterator over the customers in the agency.         */
  
  public Iterator<Customer> iterator ( ) { // from Iterable 
    
    return new LoanSharkIterator(theCustomers);
    
  }; //iterator
  
  
  /** This method returns the previous balance total. 
    * 
    * @return double the previous balance total. */
  
  public double getPrevTotal ( ) {
    
    for ( int i = 0; i <= theCustomers.length; i ++ ) {
      
      prevTotal = prevTotal + theCustomers[i].getPrevBalance();
      
    }
    
    return prevTotal; 
    
  }; //getPrevTotal
  
  
  /** This method returns the debits total. 
   * 
   * @return double the debits total. */
  
  public double getDebTotal ( ) {
    
    for ( int i = 0; i <= theCustomers.length; i ++ ) {
      
      debTotal = debTotal + theCustomers[i].getDebits();
      
    }
    
    return debTotal;
    
  }; //getDebTotal
  
  
  /** This method returns the credits total. 
   * 
   * @return double the credits total. */
  
  
  public double getCredTotal ( ) {
    
    for ( int i = 0; i <= theCustomers.length; i ++ ) {
      
      credTotal = credTotal + theCustomers[i].getCredits();
      
    }
    
    return credTotal;
    
  }; //getCredTotal
  
  
  /** This method returns the interest total. 
    * 
    * @return double the interest total. */
  
  public double getIntTotal ( ) {
    
    for ( int i = 0; i <= theCustomers.length; i ++ ) {
      
      intTotal = intTotal + theCustomers[i].getInterest();
      
    }
    
    return intTotal;
    
  }; //getIntTotal
  
  
  /** This method returns the new balance total.  
   * 
   * @return double the new balance total. */
  
  public double getNewTotal ( ) {
    
    for ( int i = 0; i <= theCustomers.length; i ++ ) {
      
      newTotal = newTotal + theCustomers[i].getNewBalance();
      
    }
    
    return newTotal;
    
  }; //getNewTotal
  
  
  /** This method returns the minimum payment total. 
   * 
   * @return double the minimum payment total. */
  
  public double getMinTotal ( ) {
    
    for ( int i = 0; i <= theCustomers.length; i ++ ) {
      
      minTotal = minTotal + theCustomers[i].getMinPay();
      
    }
    
    return minTotal;
    
  }; //getMinTotal
  
  
  /** This method loads the customers names, loan numbers and previous balances from the 
    * file and creates the list of customers in the loan shark agency. 
    *
    * @param file the customer list file of customer names, numbers and previous balances. */
  
  private void loadCustomers ( ASCIIDataFile file ) {
    
    theCustomers = new Customer[100];
    
    for ( int i = 0; i <9; i ++) {
      theCustomers[i] = new CustomerImpl(file);
      customerNum++;
    } 
    file.close();
    
  }; //loadCustomers
  
  /** This method returns the total number of customers in the agency. 
    * 
    * @return int the number of customers in the agency. */ 
  
  public int getCustomerNum ( ) {
 
   return customerNum;
   
  }; //getCustomerNum
  
} //LoanSharkImpl