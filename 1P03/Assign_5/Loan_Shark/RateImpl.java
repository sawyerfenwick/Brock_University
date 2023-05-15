package Loan_Shark;


import java.io.*;
import BasicIO.*;


/** This class */

public class RateImpl implements Rate, Serializable {
  
  private ASCIIDataFile file;
  private double rate;
  private double minPay;
  private double prevPlus;
  private double interest;
  private double newBalance;
  private double lowRate;
  private double firstTier;
  private double medRate;
  private double secondTier;
  private double highRate;
  private double minPayRate;
  private double tier;
  
  
  /** This constructor reads in the rate file and reads in the tier levels and low through high rates. */
  
  public RateImpl ( ASCIIDataFile file ) {
    
    while( !file.isEOF() ){
      
      lowRate = getRates(file);
      firstTier = getRates(file);
      medRate = getRates(file);
      secondTier = getRates(file);
      highRate = getRates(file);
      minPayRate = getRates(file);
      
    }
    
  } //constructor
  
  
  /** This method returns the tier that the customer falls in. 
    * If prevBalance <= 1000 tier = 1.
    * If prevBalance <= 6000 tier = 2.
    * If prevBalance  > 6000 tier = 3. */
  
  public double getTier ( double total ) {
    
    if ( total <= 1000) {
      tier = 1;
    }
    else if ( total <= 6000 ) {
      tier = 2;
    }
    else {
      tier = 3;
    }
    
    return tier;
  } //getTier
  
  
  /** This method returns the rates */
  
  public double getRates ( ASCIIDataFile file ){
    
    rate = file.readDouble();
    
    return rate;
  } //getRates
  
  
  /** This method returns the minimum payment rate. */
  
  public double getMinPayRate ( ) {
    
    return minPayRate;
  }
  
  
  /** This method returns the low rate.  */
  
  public double getLowRate ( ) {
    
    return lowRate;
  }
  
  
  /** This method returns the medium rate. */
  
  public double getMedRate ( ) {
    
    return medRate;
  }
  
  
  /** This method returns the high rate. */
  
  public double getHighRate ( ) {
    
    return highRate;
  }
  
}