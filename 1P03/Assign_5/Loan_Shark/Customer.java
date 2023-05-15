package Loan_Shark;


import BasicIO.*;


/** This interface represents a customer in a loan shark agency. A customer has a 
  * loan number, a name and a previous balance. Customer debits and credits can be 
  * updated and a new balance, amount of interest, and minimum payment can be calculated. 
  *
  * @see LoanShark
  * 
  * @author S.Fenwick
  * 
  *@version 1.0 (Apr. 2017)                                                              */

public interface Customer{
  
  
  /** This method returns the loan number of the customer. 
    * 
    *@return String the loan number                                                      */
  
  public String getLnNum ( ) ;
  
  
  /** This method returns the customer's name. 
    *
    * @return String the customer's name.                                                */
  
  public String getName ( );
  
  
  /** This method returns the previous balance owed by the customer.
    * 
    * @return double the previous balance owed by the customer.                                  */
  
  public double getPrevBalance ( );
  
  
  /** This method returns the new balance owed by the customer.
    * 
    * @return double the new balance owed by the customer.                                */
  
  public double getNewBalance ( );
  
  
  /** This method returns the amount of "debits" the customer takes.
    * 
    * @return double the amount of borrowed funds by the customer. */
  
  public double getDebits();
  
  
  /** This method returns the amount of "credits" the customer makes.
    * 
    * @return double the amount of funds paid off by the customer. */
  
  public double getCredits();
  
  
  /** This method updates the customer's debit and credit information      */
  
  public void update ( );
  
  
  /** This method returns the interest owed by the customer. 
    * 
    * @return double the interest owed by the customer. */
  
  public double getInterest ( );
  
  
  /** This method returns the minimum payment possible the customer can make.
    * 
    * @return double the minimum payment possible by the customer. */ 
  
  public double getMinPay ( );
  
  
  /** This method calculates the total amount that interest will be owed on, 
    * previous balance + debits - credits.
    * 
    * @param prev the previous balance of the customer
    * @param deb  the debits the customer borrows  
    * @param cred the credits the customer pays off
    * 
    * @return double the total: previous balance + debits - credits */
  
  public double calcTotal ( double prev, double deb, double cred );
  
  
  /** This method calculates the interest on the total amount. 
    *
    * @param total the total amount calculated in the method 'calcTotal'.
    * 
    * @return double the interest. */
  
  public double calcInterest ( double total );
  
  
  /** This method calculates the new balance by adding the total and the interest. 
    *
    * @param total    the total calculated in the method 'calcTotal'.
    * @param interest the interest calculated in the method 'calcInterest'.
    * 
    * @return double the new balance.*/
  
  public double calcNewBalance ( double total, double interest ); 
  
  
  /** This method calculates the minimum payment by multiplying the minimum payment 
    * rate (0.25) with the new balance calculated in the method 'calcNewBalance'.
    *
    * @param nb the new balance
    * 
    * @return double the minimum payment. */
  
  public double calcMinPay ( double nb );
  
  
  
}