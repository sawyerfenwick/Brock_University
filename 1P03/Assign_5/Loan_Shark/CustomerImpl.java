package Loan_Shark;


import java.io.*;
import BasicIO.*;


/** This class represents a customer in a loan shark agency. A customer has a loan number, 
  * a name, and a previous balance. Customers can input debit and credit information which will 
  * update their previous balance to a new balance, and can be used to calculate the interest 
  * they will be charged as well as the minimum payment they may make. 
  * 
  * @see LoanShark
  *
  * @author S. Fenwick
  * 
  * @version 1.0 (Apr. 2017) */

public class CustomerImpl implements Customer, Serializable {
  
  
  private static final long serialVersionUID = 99990001L;
   
  private String lnNum;                     // loan number 
  private String name;                      // customer's name
  private double prevBalance;               // customer's previous balance
  private double newBalance;                // customer's new balance
  private double debits;                    // customer's monthly debits
  private double credits;                   // customer's monthly credits
  private double interest;                  // customer's calculated interest
  private double minPay;                    // minimum payment possible 
  private double total;                     // prevBalance + debits - credits 
  private Rate rate;                        // the rate at which interest is charged 
  
  
  /** This constructor creates a new customer reading the loan number, name
    * and previous balance from a specified file. 
    *
    * @param file the file to read from.                                   */
  
  public CustomerImpl ( ASCIIDataFile file ) {
    
    lnNum = file.readString();
    name = file.readString();
    prevBalance = file.readDouble();
    
  }; //constructor
  
  
  /** This method returns the loan number of the customer. 
    * 
    *@return String the loan number                                                      */
  
  public String getLnNum ( ) {
    
    return lnNum;
    
  }; //getLnNum
  
  
  /** This method returns the customer's name. 
    *
    * @return String the customer's name.                                                */
  
  public String getName ( ) {
    
    return name;
    
  }; //getName
  
  
  /** This method returns the previous balance owed by the customer.
    * 
    * @return double the previous balance owed by the customer.                                  */
  
  public double getPrevBalance ( ) {
    
    return prevBalance;
    
  }; //getPrevBalance
  
  
  /** This method returns the new balance owed by the customer.
    * 
    * @return double the new balance owed by the customer.                                */
  
  public double getNewBalance ( ) {
    
    return newBalance;
    
  }; //getNewBalance
  
  
  /** This method returns the amount of "debits" the customer takes.
    * 
    * @return double the amount of borrowed funds by the customer. */
  
  public double getDebits ( ) {
    
    return debits;
    
  }; //getDebits
  
  
   /** This method returns the amount of "credits" the customer makes.
    * 
    * @return double the amount of funds paid off by the customer. */
  
  public double getCredits ( ) {
    
    return credits;
    
  }; //getCredits
  
  
  /** This method updates the customer's debit and credit information      */
  
  public void update ( ) {
    
    CustomerForm form;
    
    form = new CustomerForm (this);
    
    if ( form.update() ) {
      debits = form.readDebits();
      credits = form.readCredits();
    }
    form.close();
    
    
  }; //update
  
  
  /** This method returns the interest owed by the customer. 
    * 
    * @return double the interest owed by the customer. */
  
  public double getInterest ( ) {
    
    return interest;
    
  }; //getInterest
  
  
  /** This method returns the minimum payment possible the customer can make.
    * 
    * @return double the minimum payment possible by the customer. */ 
  
  public double getMinPay ( ) { 
    
    return minPay;
    
  }; //getMinPay
  
  
 /** This method calculates the minimum payment by multiplying the minimum payment 
    * rate (0.25) with the new balance calculated in the method 'calcNewBalance'.
    *
    * @param nb the new balance
    * 
    * @return double the minimum payment. */
  
  public double calcMinPay ( double nb) {
    
    minPay = nb*rate.getMinPayRate();
    
    return minPay;
    
  }; //calcMinPay
  
  
  /** This method calculates the total amount that interest will be owed on, 
    * previous balance + debits - credits.
    * 
    * @param prev the previous balance of the customer
    * @param deb  the debits the customer borrows  
    * @param cred the credits the customer pays off
    * 
    * @return double the total: previous balance + debits - credits */
  
  public double calcTotal ( double prev, double deb, double cred ) {
    
    total = prev + deb - cred;
    
    return total;
    
  }; //calcTotal
  
  
   /** This method calculates the interest on the total amount. 
    *
    * @param total the total amount calculated in the method 'calcTotal'.
    * 
    * @return double the interest. */
  
  public double calcInterest ( double total ){
    
    double tier = rate.getTier( total);
    
    if ( tier == 1 ) { //first tier 
      
      interest = total*rate.getLowRate();
      
    }
    else if ( tier == 2 ) { //second tier 
      
      interest = (rate.getLowRate()*1000) + (rate.getMedRate()*(total - 1000));
      
    }
    else { //third tier 
      
      interest = (rate.getLowRate()*1000) + (rate.getMedRate()*(6000-1000) + (rate.getHighRate()*(total - 6000)));
      
    }
    
    return interest;
    
  }; //calcInterest
  
  
  /** This method calculates the new balance by adding the total and the interest. 
    *
    * @param total    the total calculated in the method 'calcTotal'.
    * @param interest the interest calculated in the method 'calcInterest'.
    * 
    * @return double the new balance.*/
  
  public double calcNewBalance ( double total, double interest ) {
    
    newBalance = total + interest;
    
    return newBalance;
    
  }; //calcNewBalance
  
} //CustomerImpl