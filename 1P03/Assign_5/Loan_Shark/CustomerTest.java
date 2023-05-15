package Loan_Shark;

import BasicIO.*;


/** This class is a test harness for nuit testing of the CustomerImpl class.
  * 
  * @author S. Fenwick
  *
  * @version 1.0 (Apr. 2017) */

public class CustomerTest { 
 
  
  private LoanShark           aShark;
  private Customer            aCustomer;
  private ASCIIDataFile       file;
  
  /** The constructor performs unit testing of the CustomerImpl class. */
  
  public CustomerTest ( ) {
    
    file = new ASCIIDataFile();
    aShark = new LoanSharkImpl(file);
    aCustomer = new CustomerImpl(file);
    file.close();
    System.out.println();
    System.out.println("New Customer Created: " + aCustomer);
    display();
    System.out.println();
    System.out.println("Calculate Interest");
    display();
    
    
  }; //constructor 
  
  private void display ( ) {
    
    System.out.println("Customer: " + aCustomer);
    System.out.println("loan number: " + aCustomer.getLnNum());
    System.out.println("name: " + aCustomer.getName());
    System.out.println("prevBalance: " + aCustomer.getPrevBalance());
    System.out.println("debits:" + aCustomer.getDebits());
    System.out.println("credits:" + aCustomer.getCredits());
    System.out.println("interest:" + aCustomer.getInterest());
    System.out.println("newBalance: " + aCustomer.getNewBalance());
    System.out.println("minPayment: " + aCustomer.getMinPay());
    
  }; //display
  
  
  public static void main ( String args[] ) { CustomerTest c = new CustomerTest(); };
  
} //CustomerTest