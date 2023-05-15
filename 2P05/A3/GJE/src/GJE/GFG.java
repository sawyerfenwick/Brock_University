package GJE;

public class GFG{
//Dimension of input square matrix 
static final int N = 3; 
  
// Function to get cofactor of  
// mat[p][q] in temp[][]. n is  
// current dimension of mat[][] 
static void getCofactor(int mat[][],  
         int temp[][], int p, int q, int n) 
{ 
    int i = 0, j = 0; 
  
    for(int k = 0; k < temp.length; k++) {
    	for(int l = 0; l < temp.length; l++) {
    		System.out.print("temp["+k+"]["+l+"] = " + temp[k][l] + " ");
    	}
    	System.out.println();
    }
    
    
    // Looping for each element of  
    // the matrix 
    for (int row = 0; row < n; row++) 
    { 
    	//System.out.println("row="+row);
        for (int col = 0; col < n; col++) 
        { 
            //System.out.println("col=" + col);
            // Copying into temporary matrix  
            // only those element which are  
            // not in given row and column 
        	for(int k = 0; k < temp.length; k++) {
            	for(int l = 0; l < temp.length; l++) {
            		System.out.print("temp["+k+"]["+l+"] = " + temp[k][l] + " ");
            	}
            	System.out.println();
            }
            
        	if (row != p && col != q) 
            { 
        		
                temp[i][j] = mat[row][col]; 
                j++;
                System.out.println("temp[i][j++] = " + i + " " + j + " " + mat[row][col]);
                // Row is filled, so increase  
                // row index and reset col  
                //index 
                if (j == n - 1) 
                { 
                    j = 0; 
                    i++; 
                } 
            } 
        }
        
        
    } 
} 
  
/* Recursive function for finding determinant 
of matrix. n is current dimension of mat[][]. */
static int determinantOfMatrix(int mat[][], int n) 
{ 
    int D = 0; // Initialize result 
  
    // Base case : if matrix contains single 
    // element 
    if (n == 1) 
        return mat[0][0]; 
      
    // To store cofactors 
    int temp[][] = new int[N][N];  
      
    // To store sign multiplier 
    int sign = 1;  
  
    // Iterate for each element of first row 
    for (int f = 0; f < n; f++) 
    { 
    	//System.out.println("f="+f);
        // Getting Cofactor of mat[0][f] 
    
        getCofactor(mat, temp, 0, f, n); //here
        
        
        //System.out.println("sign:" + sign + " " + mat[0][f] + " " + determinantOfMatrix(temp,n-1));
        D += sign * mat[0][f]  
           * determinantOfMatrix(temp, n - 1); 
        //System.out.println("DETERMINANT=" + D);
        // terms are to be added with  
        // alternate sign 
        sign = -sign; 
    } 
  
    return D; 
} 
  
/* function for displaying the matrix */
static void display(int mat[][], int row, int col) 
{ 
	System.out.println();
    for (int i = 0; i < row; i++) 
    { 
        for (int j = 0; j < col; j++) {
        	System.out.println("i = " + i + " j = " + j);
        }
        for(int j = 0; j < col; j++) {
        	System.out.print(mat[i][j]);
        }
        System.out.println();
        
    } 
} 
  
// Driver code 
public static void main (String[] args) 
{ 
      
    int mat[][] = {{9,1,4}, 
                    {5,3,1},
                    {2,3,1}
                   }; 
  
    System.out.print("Determinant " +  
                "of the matrix is : "+ 
         determinantOfMatrix(mat, N));
    //display(mat,N,N);
} 
} 