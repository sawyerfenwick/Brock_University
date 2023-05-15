package Loan_Shark;


import java.util.*;
import BasicIO.*;
import static BasicIO.Formats.*;


/** This class sets up a report printer for the month end report for the loan shark agency. */

class MonthEndReport {
  
  private ReportPrinter report;
  private Customer theCustomer;
  private LoanShark theShark;
  
  public MonthEndReport (  ) {
    
    report = new ReportPrinter();
    
    reportSetUp();
    
  } //constructor
  
  
  /** This method sets up the report, adding the fields and setting the title. */
  
  private void reportSetUp ( ) {
    
    report.setTitle("Sharkeys Loans " , "Monthly Report ", "Apr 7, 2017");
    report.addField("ln","Loan #", 6);
    report.addField("name","Name", 17);
    report.addField("pb","Prev Bal", 10);
    report.addField("d","Debits", 10);
    report.addField("c","Credits", 10); 
    report.addField("int","Interest", 10);
    report.addField("nb","New Bal",10);
    report.addField("mp", "Min Payment", 10);
    
  }; //reportSetUp
  
  
  /** This method writes the detail line for the customer.
    * 
    * @param Customer c the customer. */
  
  public void writeDetailLine ( Customer c ) {
   
    report.writeString("ln",  c.getLnNum());
    report.writeString("name", c.getName());
    report.writeString("pb", "$" + c.getPrevBalance());
    report.writeString("d", "$" + c.getDebits());
    report.writeString("c", "$" + c.getCredits());
    report.writeString("int", "$" + c.getInterest());
    report.writeString("nb", "$" + c.getNewBalance());
    report.writeString("mp", "$" + c.getMinPay());
    
  }; //writeDetailLine
  
  
  /** This method closes the report printer. */
  
  public void close ( ) {
    
    report.close();
    
  }; //close
  
  
  /** This method writes the summary line to the report (The totals of categories. */
  
  public void writeSummary( ) {
    
    report.writeString("ln", "Totals");
    report.writeString("pb", "$" + theShark.getPrevTotal());
    report.writeString("d", "$" + theShark.getDebTotal());
    report.writeString("c", "$" + theShark.getCredTotal());
    report.writeString("int", "$" + theShark.getIntTotal());
    report.writeString("nb" , "$" + theShark.getNewTotal());
    report.writeString("mp", "$" + theShark.getMinTotal());
    
  }; //writeDetailLines
  
  
} //MonthEndReport