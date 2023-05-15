package Loan_Shark;


import BasicIO.*;



public class MonthEnd { 
  
  
  private RateImpl            aRate;
  private CustomerImpl        aCustomer;
  private LoanSharkImpl       aShark;
  private ASCIIDataFile       rateFile;
  private ASCIIDataFile       customerFile;
  private CustomerForm        form;
  private MonthEndReport      report;
  private LoanSharkIterator   iterator;
  
  public MonthEnd ( ) {
    
    customerFile = new ASCIIDataFile();
    rateFile = new ASCIIDataFile();
  
    aShark = new LoanSharkImpl(customerFile);
    aRate = new RateImpl(rateFile);
    report = new MonthEndReport();
    
    
    for( int i = 0; i < aShark.getCustomerNum(); i ++ ){
      aCustomer.update();
      report.writeDetailLine(aCustomer);
      iterator.next();
    }
    
    report.writeSummary();
    report.close();
    
    
 
  }; //constructor 
  
  public static void main (String args[]) {MonthEnd m = new MonthEnd();};
  
}; //MonthEnd