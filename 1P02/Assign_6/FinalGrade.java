package Assign_6;

import BasicIO.*;                // for IO classes
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions

/** This class reads a data file and then creates a report of the final grades based off the data file. 
  *
  * @author Sawyer Fenwick
  * @version 1.0 November 22 2016                                                       */

public class FinalGrade {
    
  // instance variables
  //private ASCIIDisplayer display;  
  private ASCIIDataFile reader;
  private ReportPrinter report;
  
  /** This constructor sets up a report using the method "reportSetUp" and grabs the data file to be used, it then
    * reads the data, checking for EOF and sends it to the method "writeDetailLines" where the report is written, 
    * then it calculates the average of the final grades and writes the summary data using the method "summaryData".*/
  
  public FinalGrade ( ) {
    
    // local variables
    String studentNum;
    String name;
    double a1;
    double a2;
    double test;
    double exam;
    double fin;
    int count = 0;
    double avg = 0;
    double average = 0;
    
    // statements
    reader = new ASCIIDataFile(); 
    report = new ReportPrinter();
    reportSetUp();
    reader.getFile();
    
    reader.nextLine();
    
    for ( ; ; ){
        
        studentNum = reader.readString();
        name = reader.readString();
        a1 = reader.readDouble();
        a2 = reader.readDouble();
        test = reader.readDouble();
        exam = reader.readDouble();
        fin = finalGrade(a1,a2,test,exam);
        avg = avg + fin;
        
        
        if(reader.isEOF()){break;}
        
        writeDetailLines(studentNum, name, a1, a2, test, exam, fin);
        
        count += 1;
        
      }
      
      average = avg/count;
      double roundOff = Math.round(average *10.0) / 10.0; //rounding to 1 decimal place
      summaryData(roundOff, count);
     
  }; // constructor
  
  // methods
  /*This method creates the outline of the report, setting the title and adding all the fields.*/
  private void reportSetUp (){
    
    report.setTitle("COSC 1P02 " , "Final Grades");
    report.addField("studentNum","Student #",10);
    report.addField("name","Name",21);
    report.addField("a1","A1",5);
    report.addField("a2","A2",5);
    report.addField("test","Test",6); 
    report.addField("exam","Exam",6);
    report.addField("fin","Final",5);
      
  }
  
  /*This method calculates the final grade of each student based on their marks on the assignments, tests and the exam
   * and their respected weights. */
  private double finalGrade(double a1, double a2, double test, double exam){
   
    double finalGrade;
    finalGrade = a1/10*10 + a2/10*10 + test/50*30 + exam/100*50;
    
    return finalGrade;
    
  }
  
  /*This method writes to the report the student number along with the associated grades. */
  private void writeDetailLines (String studentNum, String name, double a1, double a2, double test, double exam, double fin){
        
    report.writeString("studentNum",studentNum);
    report.writeString("name",name);
    report.writeDouble("a1",a1);
    report.writeDouble("a2",a2);
    report.writeDouble("test",test);
    report.writeDouble("exam",exam);
    report.writeDouble("fin",fin);
    report.newLine();
    
  }
  
  /*This method writes the average of the students final grades and writes the number of students in the class. It 
   * also closes the report printer and the data file. */
  private void summaryData(double roundOff, int count){
    
    report.writeLine("Average                                                     " + roundOff);
    report.writeLine("Number of Students: " + count);
    report.close();
    reader.close();
  }
  
  public static void main ( String[] args ) { FinalGrade c = new FinalGrade(); };
  
  
} //FinalGrade
