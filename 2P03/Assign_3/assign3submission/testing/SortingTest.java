package testing;
import sorting.*;
import java.util.*;
/**
 * Test Harness for the SoMuchSort class. 
 * 
 * @author Sawyer Fenwick [6005011]
 */
public class SortingTest {
    
    int a[] = null;
    Scanner sc = new Scanner(System.in);
    
    public SortingTest(){
        int choice = -1;
        SoMuchSort s = new SoMuchSort();
        do {
            try {
                choice = menu();
                switch(choice){
                    case 1://adds to the array, sorts the array, and prints
                        s.add();
                        break;
                    case 2://fills the array with random numbers
                        System.out.println("Enter the number of elements to be "
                                + "randomly put in the array: ");
                        int i = sc.nextInt();
                        a = new int[i];
                        s.fill(a);
                        s.choice(a);
                        s.print(a);
                        break;
                    case 3://times the different sorts
                        System.out.println("Enter the number of elements to be "
                                + "stored in the array: ");
                        i = sc.nextInt();
                        a = new int[i];
                        s.timetrial(a);
                    case 0://quit
                        break;
                    default:
                        System.out.println("Unrecognized Option");
                }
            }
            catch(InputMismatchException ime){
                System.out.println("Exception caught. Invalid request.\n");
            }
        } while(choice!=0);
    }//constructor
    
    /**
     * Creates a menu for user input
     * 
     * @return integer for users choice 
     */
    private int menu(){
        System.out.print("1.Sort");
        System.out.print("\t2.Fill");
        System.out.print("\t3.Time Trial\n");
        System.out.print("0.Quit\n");
        return sc.nextInt();
    }//menu
    
    public static void main(String args[]){
        SortingTest sortingTest = new SortingTest();
    }//main
}//SortingTest