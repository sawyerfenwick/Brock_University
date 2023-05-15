package assign_3a;

import java.util.Scanner;

/** This class uses Dynamic Programming to find the largest common substring 
 *  between two strings.
 *
 *  @author Sawyer Fenwick (6005011)
 *  @version 1.0 (November 2018)
 */
public final class CommonSubs {

    String x;
    String y;
    String z;
    Scanner s = new Scanner(System.in);
    
    /** Constructor, opens the menu, determines LCS
     *
     */
    public CommonSubs(){
        menu();
        int m = x.length();
        int n = y.length();
        char[] X = x.toCharArray();
        char[] Y = y.toCharArray();
        System.out.println("Length of largest common substring is "
                + compare(X,Y, m, n));
    }//commonsubs
    
    /** Creates the menu for user interaction, asks user for input X and Y which
     *  will be compared for LCS
     * 
     */
    private void menu(){
        System.out.println("Please enter the first string: ");
        x = s.next();
        System.out.println("Please enter the second string: ");
        y = s.next();
    }//menu
    
    /** Finds the maximum of two numbers x and y
     * 
     * @param x an integer 
     * @param y an integer
     * @return the larger of x and y
     */
    public int max(int x, int y){
        if(x > y){
            return x;
        }
        else{
            return y;
        }
    }//max
    
    /** This method compares the chars in the array, to find the LCS. 
     *  If the length of either string is 0 there is no LCS. Otherwise
     *  if m and n are equal compare everything left of m and n, (m-1, n-1).
     *  Otherwise m and n are not equal, compare the maximum of m-1 and n-1.
     * 
     * @param X char array of sequence 1 
     * @param Y char array of sequence 2
     * @param m length of sequence 1
     * @param n length of sequence 2
     * @return  length of LCS
     */
    public int compare(char[] X, char[] Y, int m, int n){
        if(m == 0 || n == 0){
            return 0;
        }
        if(X[m-1] == Y[n-1]){
            return 1 + compare(X,Y,m-1, n-1);
        }
        else{
            return max(compare(X,Y,m,n-1), compare(X,Y,m-1, n));
        }
    }//compare
    
    public static void main(String[] args) {
        CommonSubs cs = new CommonSubs();
    }
    
}
