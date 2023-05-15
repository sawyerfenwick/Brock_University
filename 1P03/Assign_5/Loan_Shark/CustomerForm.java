package Loan_Shark;


import BasicIO.*;
import static BasicIO.Formats.*;


/** This class represents the user interface (a form) for update and display of 
  * customer information.
  *
  * @see LoanShark
  * 
  * @author S. Fenwick
  * 
  * @version 1.0 (Apr. 2017)                                                                  */

class CustomerForm {
  
  
  private BasicForm form; 
  private Customer theCustomer;
  
  
  /** This constructor builds the form to update the customer's debits and credits in 
    * the loan shark agency. 
    * 
    * @param aCustomer customer to be displayed                                               */
  
  public CustomerForm ( Customer aCustomer ) {
    
    theCustomer = aCustomer;
    form = new BasicForm();
    form.setTitle("Sharkey's Loans");
    form.addLabel("num", theCustomer.getLnNum(), 10, 40);
    form.addLabel("name", theCustomer.getName(), 10, 60);
    form.addTextField("debits","Debits:", 10 );
    form.addTextField("credits","Credits:", 10 );
    
    
  }; //constructor 
  
  /** This method returns the debit amount in the debit field. 
    *
    * @return double debits                                                                  */
  
  public double readDebits ( ) {
    
    return form.readDouble("debits");
    
  }; //readDebits
  
  
  /** This method returns the credit amount in the credit field. 
    *
    * @return double credits                                                                 */
  
  public double readCredits ( ) {
    
    return form.readDouble("credits");
    
  }; //readCredit
  
  
  /** This method presents the form to allow update of customer information. It 
    * returns true if the user presses 'OK'. If the user presses 'OK' the values
    * of the fields can be read.                                                             */
  
  public boolean update ( ) {
    
    int button; // button pressed
    
    button = form.accept();
    form.hide();
    return button == 0;
    
  }; //update 
  
  /** This method closes the form. The form should no longer be used.          */
  
  public void close ( ) {
    
    form.close();
    
  }; //close
  
}; //CustomerForm