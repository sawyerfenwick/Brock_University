package Assign_2;

import BasicIO.*;

/** This class describes everything a job contains; the description of the job, the name of the sender, the 
  * number of pages to be printed, and the priority of the job. 
  *
  * @author S. Fenwick
  *
  * @version 1.0 (Feb. 2017)                                                     */

public class Job{
  
  public static final String[] PRIORITY = {"Students" , "Staff" , "Faculty"};
  
  private String desc;
  private String name;
  private int pages;
  private int priority;
  
  public Job(BasicForm form){
    
    desc = form.readString("description");
    name = form.readString("send");
    pages = form.readInt("pages");
    priority = form.readInt("priority");
    
  } //constructor
  
  /** This method returns the description of the print job. 
    *
    * @return String the description                                               */
  
  public String getDescription(){
    
    return desc;
  } //getDescription
  
  /** This method returns the name of the user who is printing. 
    *
    * @return String the name                                                      */
  
  public String getName(){
    
    return name;
  } //getName
  
  
  /** This method returns the number of pages which the user is printing. 
    *
    * @return int the pages                                               */
  
  public int getPages(){
    
    return pages;
  } //getPages
  
  
  /** This method returns the priority of the print job.  
    *
    * @return int 0 for students; 1 for staff; 2 for faculty             */
  
  public int getPriority(){
    
    return priority;
  } //getPriority
  
    
} //Job