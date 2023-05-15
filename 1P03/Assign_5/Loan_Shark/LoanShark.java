package Loan_Shark;


/** This interface represents a loan shark agency with the associated customers. A loan shark
  * has total value for the previous balance, new balance, debits, credits, interest, and a 
  * total minimum payment across all customers. 
  *
  * @see Customer
  * 
  * @author S. Fenwick
  * 
  * @version 1.0 (Apr. 2017)                                                                 */

public interface LoanShark extends Iterable<Customer>{
  
  
  /** This method returns an iterator over the students in the course.
    * 
    * @return  Iterator  an iterator over the students in the course.                      */
  
 // public Iterator<Customer> iterator ( ); // from Iterable
  
  
  /** This method returns the total value of the previous balances across the customers.
    * 
    * @return double the total previous balance.                                             */
  
  public double getPrevTotal ( );
  
  
  /** This method returns the total value of the debits across the customers.
    * 
    * @return double the total debits.                                             */
  
  public double getDebTotal ( );
  
  
  /** This method returns the total value of the credits across the customers.
    * 
    * @return double the total credits.                                             */
  
  public double getCredTotal ( );
  
  
  /** This method returns the total value of the interest across the customers.
    * 
    * @return double the total interest.                                             */
  
  public double getIntTotal ( );
  
  
  /** This method returns the total value of the new balances across the customers.
    * 
    * @return double the total new balances.                                             */
  
  public double getNewTotal ( );
  
  
  /** This method returns the total value of the minimum payments across the customers.
    * 
    * @return double the total minimum payment.                                             */
  
  public double getMinTotal ( );
  
   
  /** This method returns the total number of customers in the agency. 
    * 
    * @return int the number of customers in the agency. */ 
  
  public int getCustomerNum ( );
  
} //LoanShark