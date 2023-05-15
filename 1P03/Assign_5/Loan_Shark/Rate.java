package Loan_Shark;


import java.io.*;
import BasicIO.*;


/** This interface represents the different rates used to charge customers.    */

public interface Rate {

  
  /** This method returns the rates */
  
  public double getRates ( ASCIIDataFile file );
  
  
  /** This method returns the tier that the customer falls in. 
    * If prevBalance <= 1000 tier = 1.
    * If prevBalance <= 6000 tier = 2.
    * If prevBalance  > 6000 tier = 3. */
  
  public double getTier ( double total );
  
  
  /** This method returns the minimum payment rate. */
  
  public double getMinPayRate ( );
 
  
  /** This method returns the low rate.  */
  
  public double getLowRate ( );
  
  
  /** This method returns the medium rate. */
  
  public double getMedRate ( );
  
  
  /** This method returns the high rate. */
  
  public double getHighRate ( );
   
}