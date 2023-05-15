package sorting;
import java.util.*;
/**
 * This class uses 5 different sorts to sort the elements in an array (integers 
 * only). It uses Heap Sort, Quick Sort, Insertion Sort, Radix Sort and Merge 
 * Sort. It also fills the remainder of the array with random elements between 
 * any specified upper and lower bound (determined by the user). The user can
 * also add any amount of elements to the array through the add function. 
 * 
 * @author Sawyer Fenwick [6005011]
 */
public class SoMuchSort {
    
    Scanner sc = new Scanner(System.in);
    
    public SoMuchSort(){
       
    }//constructor
    
    /**
     * Clears the array
     * 
     * @param a the array 
     */
    public void empty(int a[]){
        for(int i = 0; i < a.length; i ++){
            a[i] = 0;
        }
    }
    
    /**
     * Fills the remaining empty spaces in the array with a random number 
     * between a user selected upper and lower bound.
     * 
     * @param a the array
     * @return the full array of random integers
     */
    public int[] fill(int a[]){
        empty(a);
        System.out.println("Upper bound:");
        int u = sc.nextInt();
        System.out.println("Lower bound:");
        int l = sc.nextInt();
        for(int i = 0; i < a.length; i++){
            if(a[i] == 0){
                a[i] = random(u,l);
            }
        }
        return a;
    }//fill
    
    /**
     * Fills the array randomly with integers between 0 and 10000000. (Used for
     * the time trials so user does not have to constantly ask for lower and 
     * upper bound)
     * 
     * @param a the array
     * @return the full array of random integers
     */
    public int[] randfill(int a[]){
        empty(a);
        for(int i=0; i<a.length; i++){
            if(a[i] == 0){
                a[i] = random(10000000,0);
            }
        }
        return a;
    }
    
    /**
     * Returns a random integer between l and u
     * 
     * @param u the upper bound
     * @param l the lower bound
     * @return a random integer
     */
    private int random(int u, int l){
        int random = (int)(Math.random() * u + l);
        return random;
    }//random
    
    /**
     * Adds however many elements the user would like
     *
     */
    public void add(){
        int a[];
        System.out.print("How many elements would you like to sort? ");
        int s = sc.nextInt();
        a = new int[s];
        for(int i = 0; i < s; i++){
            System.out.print("Add: ");
            int n = sc.nextInt();
            for(int j = 0; j < s; j++){
                if(a[j] == 0 ){
                    a[j] = n;
                    break;
                }
            }
        }
        choice(a);
        print(a);
    }//add
    
    /**
     * Lets the user choose which sorting method.
     * 
     * @param a the array 
     */
    public void choice(int a[]){
        System.out.println("Which sorting method would you like to use?");
        System.out.print("1.Heap Sort");
        System.out.print("\t2.Quick Sort");
        System.out.print("\t3.Merge Sort\n");
        System.out.print("4.Radix Sort");
        System.out.print("\t5.Insertion Sort\n");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                heapsort(a);
                break;
            case 2:
                quicksort(a,0,a.length -1);
                break;
            case 3:
                mergesort(a,0,a.length -1);
                break;
            case 4:
                radixsort(a);
                break;
            case 5:
                insertionsort(a,0,a.length -1);
                break;
            default:
                System.out.println("Unrecognized Option");
        }
    }
    
    /**
     * Prints the array to the console
     * 
     * @param a the array 
     */
    public void print(int a[]){
        for(int i = 0; i < a.length; i++){
            if(a[i] != 0)
                System.out.println(a[i]);
        }
    }//print
    
    /**
     * Menu for the time trials. 
     * 
     * @param a the array 
     */
    public void timetrial(int a[]){
        System.out.println("Which sort would you like to test? ");
        System.out.print("1.Heap Sort");
        System.out.print("\t2.Quick Sort");
        System.out.print("\t3.Merge Sort\n");
        System.out.print("4.Radix Sort");
        System.out.print("\t5.Insertion Sort\n");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                timetrialHS(a);
                break;
            case 2:
                timetrialQS(a);
                break;
            case 3:
                timetrialMS(a);
                break;
            case 4:
                timetrialRS(a);
                break;
            case 5:
                timetrialIS(a);
                break;
            default:
                System.out.println("Unrecognized Option.");
        }
    }//timetrial
    
    /**
     * Tests how long it takes to sort a random and sorted list using Insertion
     * Sort. 
     * 
     * @param a the array
     */
    public void timetrialIS(int a[]){
        long time1;
        long time2;
        long sort1;
        long sort2;
        long avg1 = 0;
        long avg2 = 0;
        for(int i = 0; i < 9; i++){
            randfill(a);
            time1 = System.currentTimeMillis();
            insertionsort(a, 0, a.length -1);
            time2 = System.currentTimeMillis();
            sort1 = time2-time1;
            time1 = System.currentTimeMillis();
            insertionsort(a, 0, a.length -1);
            time2 = System.currentTimeMillis();
            sort2 = time2-time1;
            avg1 = sort1 + avg1;
            avg2 = sort2 + avg2;
        }
        avg1 = avg1/10; avg2 = avg2/10;
        System.out.println("Insertion Sort takes: "  + avg1 + "ms on average.");
        System.out.println("Insertion Sort (on a sorted list) takes: " + avg2 + 
                "ms on average.");
    }
    
    /**
     * Tests how long it takes to sort a random list and a sorted list using 
     * Quick Sort
     * 
     * @param a the array 
     */
    public void timetrialQS(int a[]){
        long time1;
        long time2;
        long sort1;
        long sort2;
        long avg1 = 0;
        long avg2 = 0;
        for(int i = 0; i < 9; i++){
            randfill(a);
            time1 = System.currentTimeMillis();
            quicksort(a, 0, a.length -1);
            time2 = System.currentTimeMillis();
            sort1 = time2-time1;
            time1 = System.currentTimeMillis();
            quicksort(a, 0, a.length -1);
            time2 = System.currentTimeMillis();
            sort2 = time2-time1;
            avg1 = sort1 + avg1;
            avg2 = sort2 + avg2;
        }
        avg1 = avg1/10; avg2 = avg2/10;
        System.out.println("Quick Sort takes: "  + avg1 + "ms on average.");
        System.out.println("Quick Sort (on a sorted list) takes: " + avg2 + 
                "ms on average.");
    }
    
    /**
     * Tests how long it takes to sort a random list and a sorted list using 
     * Heap Sort
     * 
     * @param a the array 
     */
    public void timetrialHS(int a[]){
        long time1;
        long time2;
        long sort1;
        long sort2;
        long avg1 = 0;
        long avg2 = 0;
        for(int i = 0; i < 9; i++){
            randfill(a);
            time1 = System.currentTimeMillis();
            heapsort(a);
            time2 = System.currentTimeMillis();
            sort1 = time2-time1;
            time1 = System.currentTimeMillis();
            heapsort(a);
            time2 = System.currentTimeMillis();
            sort2 = time2-time1;
            avg1 = sort1 + avg1;
            avg2 = sort2 + avg2;
        }
        avg1 = avg1/10; avg2 = avg2/10;
        System.out.println("Heap Sort takes: "  + avg1 + "ms on average.");
        System.out.println("Heap Sort (on a sorted list) takes: " + avg2 + 
                "ms on average.");
    }
    
    /**
     * Tests how long it takes to sort a random list and a sorted list using 
     * Merge Sort
     * 
     * @param a the array
     */
    public void timetrialMS(int a[]){
        long time1;
        long time2;
        long sort1;
        long sort2;
        long avg1 = 0;
        long avg2 = 0;
        for(int i = 0; i < 9; i++){
            randfill(a);
            time1 = System.currentTimeMillis();
            mergesort(a, 0, a.length - 1);
            time2 = System.currentTimeMillis();
            sort1 = time2-time1;
            time1 = System.currentTimeMillis();
            mergesort(a, 0, a.length - 1);
            time2 = System.currentTimeMillis();
            sort2 = time2-time1;
            avg1 = sort1 + avg1;
            avg2 = sort2 + avg2;
        }
        avg1 = avg1/10; avg2 = avg2/10;
        System.out.println("Merge Sort takes: "  + avg1 + "ms on average.");
        System.out.println("Merge Sort (on a sorted list) takes: " + avg2 + 
                "ms on average.");
    }
    
    /**
     * Tests how long it takes to sort a random list and a sorted list 
     * using Radix Sort.
     * 
     * @param a the array 
     */
    public void timetrialRS(int a[]){
        long time1;
        long time2;
        long sort1;
        long sort2;
        long avg1 = 0;
        long avg2 = 0;
        for(int i = 0; i < 9; i++){
            randfill(a);
            time1 = System.currentTimeMillis();
            radixsort(a);
            time2 = System.currentTimeMillis();
            sort1 = time2-time1;
            time1 = System.currentTimeMillis();
            radixsort(a);
            time2 = System.currentTimeMillis();
            sort2 = time2-time1;
            avg1 = sort1 + avg1;
            avg2 = sort2 + avg2;
        }
        avg1 = avg1/10; avg2 = avg2/10;
        System.out.println("Radix Sort takes: "  + avg1 + "ms on average.");
        System.out.println("Radix Sort (on a sorted list) takes: " + avg2 + 
                "ms on average.");
    }
    
    /**
     * Turns the array into a max heap
     * 
     * @param a the array
     * @param n the length of the array
     * @param i 
     */
    public void maxheap(int a[], int n, int i){
        int max = i;
        int l = 2*i + 1;
        int r = 2*i + 2;
        
        if(l < n && a[l] > a[max]){
            max = l;
        }
        
        if(r < n && a[r] > a[max]){
            max = r;
        }
        
        if(max != i){
            int sub = a[i];
            a[i] = a[max];
            a[max] = sub;
            
            maxheap(a, n, max);
        }
    }//maxheap
    
    /**
     * Sorts the array using the Heap Sort method
     * 
     * @param a the array
     */
    public void heapsort(int a[]){
        int n = a.length;
        for(int i = n/2-1; i >= 0; i--){
            maxheap(a, n, i);
        }
        for(int i = n - 1; i >= 0; i--){
            int t = a[0];
            a[0] = a[i];
            a[i] = t;
            
            maxheap(a, i, 0);
        }
    }//heapsort
    
    /**
     * Sorts the array using the Quick Sort method, uses Insertion Sort for 
     * small values of n (n <= 20).
     * 
     * @param a the array
     * @param first
     * @param last
     */
    public void quicksort(int a[], int first, int last){
        int size = (last + 1) - first;
        
        if(first < last){
            if(size <= 20){ //if n = 20 use insertion sort instead of quicksort
                insertionsort(a, first, last);
            }
            else{
                int pivot = pivot(a, first, last);
                quicksort(a, first, pivot-1);
                quicksort(a, pivot + 1, last);
            }
        }
    }//quicksort
    
    /**
     * Chooses a pivot point for quick sort
     * 
     * @param a the array
     * @param small
     * @param big 
     * @return the pivot point
     */
    public int pivot(int a[], int small, int big){
        int pivot = a[big];
        int i = (small-1);
        for(int j=small; j<big; j++){
            if(a[j] <= pivot){
                i++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[i+1];
        a[i+1] = a[big];
        a[big] = t;
        
        return i + 1;
    }//pivot
    
    /**
     * Sorts the array using the Merge Sort method
     * 
     * @param a
     * @param left
     * @param right
     */
    public void mergesort(int a[], int left, int right){
        if (left < right){
           
            int middle = (left+right)/2;//find the middle of the array
 
            mergesort(a, left, middle);//sort the left half of the list
            mergesort(a , middle+1, right);//sort the right half of the list
 
            // Merge the sorted halves
            merge(a, left, middle, right);
        }
        
    }//mergesort
    
    /**
     * Merges the two sub arrays (left and right).
     * 
     * @param a the array
     * @param left
     * @param middle
     * @param right 
     */
    public void merge(int a[], int left, int middle, int right){
        int n1 = middle - left + 1;
        int n2 = right - middle;
 
        int L[] = new int [n1];//left side array
        int R[] = new int [n2];//right side array

        for (int i=0; i<n1; ++i){
            L[i] = a[left + i];
        }
        for (int j=0; j<n2; ++j){
            R[j] = a[middle + 1+ j];
        }
       
        int i = 0;
        int j = 0;
        int k = left;
        
        while (i < n1 && j < n2){
            if (L[i] <= R[j]){
                a[k] = L[i];
                i++;
            }else{
                a[k] = R[j];
                j++;
            }
            k++;
        }
 
        // Last elements of Left side
        while (i < n1){
            a[k] = L[i];
            i++;
            k++;
        }
 
        // Last elements of Right side
        while (j < n2){
            a[k] = R[j];
            j++;
            k++;
        }
    }//merge
    
    /**
     * Sorts the array using the Radix Sort method
     * 
     * @param a the array
     */
    public void radixsort(int a[]){
        int max = maximum(a);
        
        for(int i = 1; max/i > 0; i*=10){
            radix(a,i);
        }
    }//radixsort
    
    /**
     * Sorts the array using the Radix Sort method
     * 
     * @param a the array
     * @param e the exponent ( _'s column)
     */
    public void radix(int a[], int e){
        int array2[] = new int[a.length];
        int count[] = new int[10];
        
        for(int i = 0; i < a.length; i++){
            count[(a[i]/e)%10]++;
        }
        
        for(int i = 1; i < 10; i++){
            count[i] += count[i-1];
        }
        
        for (int i = a.length - 1; i >= 0; i--){
            array2[count[ (a[i]/e)%10 ] - 1] = a[i];
            count[ (a[i]/e)%10 ]--;
        }
        
        System.arraycopy(array2, 0, a, 0, a.length);
    }//radix
    
    /**
     * Finds the maximum number in the array. Used to find out how many columns 
     * occur for radix sort. (i.e. max = 10, 1s column and 10s column for radix
     * sort).
     * 
     * @param a the array
     * @return the maximum number in the array
     */
    public int maximum(int a[]){
        int max = a[0];
        for(int i=1; i < a.length; i++){
            if(a[i] > max){
                max = a[i];
            }
        }
        return max;
    }
    
    /**
     * Sorts the array using the Insertion Sort method
     * 
     * @param a the array
     * @param first
     * @param last
     */
    public void insertionsort(int a[], int first, int last){
        for(int i = first+1; i <= last; i++){
            int current = a[i];
            int j = i-1;
            while(j>=0 && a[j] > current){
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = current;
        }
    }//insertionsort
}//SoMuchSort