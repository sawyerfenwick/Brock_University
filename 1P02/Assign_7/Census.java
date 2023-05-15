package Assign_7;

import BasicIO.*;                // for IO classes
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions

/** This class uses a GUI to make census forms. It reads address, constructs forms, and writes a final report
  * of the amount of people residing at an address, their sex, and their spoken language. 
  *
  * @author Sawyer Fenwick st# 6005011
  * @version 1.0 December 1 2016                                                    */

public class Census {
    
    // instance variables
    private BasicForm houseDisplay;
    private BasicForm personDisplay;
    private ASCIIDataFile addressReader;
    private ASCIIOutputFile outputFile;
    int numMale = 0; // num male
    int numFemale = 0; // num female
    int numEng = 0; // num eng
    int numFr = 0; // num french
    int numOth = 0; // oth lang
    
    /** This constructor calls the buildFormHouse and buildFormPerson methods, creating the forms for each, then 
      * though a series of for loops and other methods, reads a file (the address file) and populates the 
      * household field, then spawns a new individual field (if OK pressed) or skips ahead to the next address
      * (if Skip pressed). The individual field prompts the user for information on a singular person, the information
      * is read (as well as the informatoin from the household form) and written to a new file (outputFile). */
    
    public Census ( ) {
      
      // local variables
      int houseButton; //houseButton pressed
      int p; // person
      String address; // address read
     
      //Household
      houseDisplay = new BasicForm("OK", "Skip");
      houseDisplay.setTitle("Household");
      
      //Individual
      personDisplay = new BasicForm();
      personDisplay.setTitle("Individual");
     
      //Address Reader
      addressReader = new ASCIIDataFile();
      addressReader.getFile();
      
      //Output File
      outputFile = new ASCIIOutputFile();
      outputFile.getFile();
      
      buildFormHouse();
      buildFormPerson();
      
      //statements
     
      for ( ; ; ){ //start of for loop 1
        
        //household form
        address = addressReader.readLine(); 
        
        if(addressReader.isEOF()) break; //checking for EOF
        
        fillHouseDisplay(address);
        houseButton = houseDisplay.accept();
        
        //if skip button is pressed
        if( houseButton == 1 ){
          address = addressReader.readLine();
          fillHouseDisplay(address);
          houseButton = houseDisplay.accept();
        }
        
        p = houseDisplay.readInt("amount");
        
        //individual form
        for(int i = 1; i <= p; i++){ // start of for loop 2
          
          houseDisplay.hide();
          fillPersonDisplay(i);
          personDisplay.accept();
          
          int s = personDisplay.readInt("sex");
      
          if (s == 0){
            numMale = numMale + 1; 
          }else{
            numFemale = numFemale + 1; 
          }
          
          int l = personDisplay.readInt("lang");
          
          if (l == 0){
            numEng = numEng + 1;
          }else if (l== 1){
            numFr = numFr + 1;
          }else{
            numOth = numOth + 1; 
          }
          
        } // end of for loop 2
        
        personDisplay.hide();
        writeReport(address, p, numMale, numFemale, numEng, numFr, numOth);
        
        reset();
      
      } // end of for loop 1
      
      outputFile.close();
      addressReader.close();
      houseDisplay.close();
      personDisplay.close();
      
    }; // constructor
    
    // methods
    
    /* This method constructs the BasicForm for the household.*/
    private void buildFormHouse(){
      
      houseDisplay.addTextField("address", "Address", 20);
      houseDisplay.setEditable("address", false);
      
      houseDisplay.addTextField("amount", "# People", 3);
      
    } //buildFormHouse
     
    /* This method fills the BasicForm for the household with the address read by the addressReader, and makes 
     * sure that the number of people text field is clear.*/
    private void fillHouseDisplay(String address){
       
      houseDisplay.writeString("address", address);
      houseDisplay.clear("amount");
      
    } //fillHouseDisplay
    
    /* This method constructs the BasicForm for the individual.*/
    private void buildFormPerson(){
      
      personDisplay.addTextField("person", "Person", 1);
      personDisplay.setEditable("person", false);
      
      personDisplay.addTextField("name", "Name", 20);
      personDisplay.addTextField("age", "Age", 3);
      personDisplay.addRadioButtons("sex", "Sex", false, "Male", "Female");
      personDisplay.addRadioButtons("lang", "Language", false, "English", "French", "Other");
      
    } //buildFormPerson
    
    /* This method fills the BasicForm for the individual with the person number (person number is changing based on
     * what person the census taker is currently filling out information for). It makes sure that the name, age, sex,
     * and language fields are clear.*/
    private void fillPersonDisplay(int p){
      
      personDisplay.writeInt("person", p);
      personDisplay.clear("name");
      personDisplay.clear("age");
      personDisplay.clear("sex");
      personDisplay.clear("lang");
      
    } //fillPersonDisplay 
    
    /*This method resets the count of number of males, females etc etc.*/
    private void reset(){
      
      numMale = 0;
      numFemale = 0;
      numEng = 0;
      numFr = 0;
      numOth = 0;
      
    } //reset
      
    /*This method writes the report of the census data, taking the address and total number of people from the 
     * house hold form, as well as the number of male, female, english speakers, french speakers and other language
     * speakers from the individual form.*/
    
    private void writeReport(String address, int numPeople, int male, int female, int eng, int fr, int oth){
      
      outputFile.writeString(address);
      outputFile.writeInt(numPeople);
      outputFile.writeInt(male);
      outputFile.writeInt(female);
      outputFile.writeInt(eng);
      outputFile.writeInt(fr);
      outputFile.writeInt(oth);
      outputFile.newLine();
      
    } //writeReport
    
    public static void main ( String[] args ) { Census c = new Census(); };
    
} // Census

