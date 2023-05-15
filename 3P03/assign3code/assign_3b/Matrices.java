package assign_3b;
import java.util.Scanner;

/**This class finds the optimal way of multiplying n matrices
 * 
 *  @author Sawyer Fenwick (6005011)
 *  @version 1.0 (November 2018)
 */
public class Matrices {
        
        /**This method finds the optimal number of multiplications
         * 
         * @param a the matrix with user input (rows and columns)
         */
       void matrixchain(int p[]){
        	int q;
        	int n=p.length;
        	int m[][]=new int[n][n];
        	int s[][]=new int[n][n];
                
        	for (int i=1;i<n;i++){
        		m[i][i]=0;
                }
        	for (int l=2;l<n;l++){
        	   for(int i=1 ;i<n-l+1;i++){
        		 int j=i+l-1;
        		 m[i][j]=Integer.MAX_VALUE;
        		 for (int k=i ;k<=j-1;k++){
        			q=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
        			if(q<m[i][j]){
        				m[i][j]=q;
        				s[i][j]=k;
        			}
        		 }
        	   }
        	}
        	printparens(s,1,n-1);
       }//matrixchain
        
        /**This method prints the optimal way to multiply n matrices with 
         * proper parenthesis
         * 
         * @param s the matrix
         * @param i 1
         * @param j length - 1 
         */
       void printparens(int s[][],int i,int j){
        	if (i==j){
        		System.out.print("M"+i);
                }else{
                    System.out.print("(");
        	    printparens(s,i,s[i][j]);
        	    printparens(s,s[i][j]+1,j);
        	    System.out.print(")");
        	}
       }//printparens
       
       /**Gets user input for how many matrices will be multiplied (n) and 
        * the dimensions of the matrices.
        * 
        * @param args 
        */
       public static void main(String args[]){ 	
            Matrices m = new Matrices();
            Scanner s = new Scanner(System.in);
            System.out.println("Please enter the number of matrices to be"
                        + "multiplied (n): ");
            int n = s.nextInt();
            int r[] = new int[n];
            System.out.println("Please enter dimensions of the matrices.");
            for(int i = 0; i < n; i++ ){
                System.out.print("R" + i + "=");
                r[i] = s.nextInt(); 
            }
            m.matrixchain(r);
       }//main
}//Matrices