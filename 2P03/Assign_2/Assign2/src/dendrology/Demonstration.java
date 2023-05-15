package dendrology;
import java.util.Scanner;
import java.io.*;
/**This class demonstrates the AVLTree class, BinarySearchTree class, and the 
 * three iterators.
 *
 * @author Sawyer Fenwick [6005011]
 * @version 1.0 (June 2018)
 * @param <E>
 */
public class Demonstration<E> {
    
    Scanner in = new Scanner(System.in);
    AVLTree avl = new AVLTree<>();
    BinarySearchTree bst = new BinarySearchTree<>();
    
    public Demonstration() throws FileNotFoundException{
        System.out.println("AVL(0) or BST(1)?"); //asking user for input
        int choice = in.nextInt();
        switch (choice) {
            case 0:
                avl();
                break;
            case 1:
                bst();
                break;
            default:
                System.out.println("Unrecognized Option");
        }
    }//constructor
    
    /**
     * Asking the User to choose between an Integer AVL Tree or a String AVL
     * Tree
     * 
     * @throws FileNotFoundException 
     */
    private void avl() throws FileNotFoundException{
        System.out.println("Integers(0) or Strings(1)");
        int choice = in.nextInt();
        switch (choice) {
            case 0:
                avlint();
                break;
            case 1:
                avlstring();
                break;
            default:
                System.out.println("Unrecognized Option");
        }
    }
    
    /**
     * Asking the User to choose between an Integer BST or a String BST
     */
    private void bst(){
        System.out.println("Integers(0) or Strings(1)");
        int choice = in.nextInt();
        switch (choice) {
            case 0:
                bstint();
                break;
            case 1:
                bststring();
                break;
            default:
                System.out.println("Unrecognized Option");
        }
    }
    
    /**
     * Operations for the AVL Tree using Integers, including
     * insertion, insertion from a file, traversals, and time trials.
     * 
     * @throws FileNotFoundException 
     */
    private void avlint() throws FileNotFoundException{
        int c = -1;
        long time1;
        long time2;
        while(c!=0){
            avlmenu();
            c = in.nextInt();
            switch(c){
                case 1:
                    System.out.print("Add: ");
                    int i;
                    i = in.nextInt();
                    avl.add(i);
                    break;
                case 2:
                    BinaryTreeIteratorBFS avlbfs = new BinaryTreeIteratorBFS(avl.getRoot());
                    System.out.println("Breadth First Traversal");
                    while(avlbfs.hasNext()){
                        System.out.println(avlbfs.next());
                    }
                    break;
                case 3:
                    BinaryTreeIteratorIOS avlios = new BinaryTreeIteratorIOS(avl.getRoot());
                    System.out.println("In Order Traversal");
                    while(avlios.hasNext()){
                        System.out.println(avlios.next());
                    }
                    break;
                case 4:
                    BinaryTreeIteratorPOS avlpos = new BinaryTreeIteratorPOS(avl.getRoot());
                    System.out.println("Pre Order Traversal");
                    while(avlpos.hasNext()){
                        System.out.println(avlpos.next());
                    }
                    break;
                case 5:
                    File file = new File("input.txt");
                    time1 = System.currentTimeMillis();
                    try(Scanner input = new Scanner(file)){
                        while(input.hasNext()){
                            int a = input.nextInt();
                            avl.add(a);
                        }
                    }catch(Exception ex){
                        System.out.println("File not found.");
                    }
                    time2 = System.currentTimeMillis();
                    System.out.println("Time to add to AVL from file: " 
                            + (time2 - time1) + "ms");
                    break;
                case 6:
                    time1 = System.currentTimeMillis();
                    BinaryTreeIteratorBFS b = new BinaryTreeIteratorBFS(avl.getRoot());
                    while(b.hasNext()){
                        System.out.println(b.next());
                    }
                    time2 = System.currentTimeMillis();
                    System.out.println("Time to traverse AVL Tree: " 
                            + (time2 - time1) + "ms");
                    break;
                case 7:
                    time1 = System.currentTimeMillis();
                    BinaryNode<E> min = avl.findMin(avl.getRoot());
                    time2 = System.currentTimeMillis();
                    System.out.println("Minimum: " + min.info);
                    System.out.println("Time to find minimum: " 
                            + (time2 - time1) + "ms");
                    break;
                case 0:
                    break;
            }
        }
    }
    
    /**
     * Operations for the Binary Search Tree using Integers, including
     * insertion, insertion from a file, traversals, and time trials.
     */
    private void bstint(){
        int c = -1;
        long time1;
        long time2;
        while(c!=0){
            bstmenu();
            c = in.nextInt();
            switch(c){
                case 1:
                    System.out.print("Add: ");
                    int i;
                    i = in.nextInt();
                    bst.add(i);
                    break;
                case 2:
                    BinaryTreeIteratorBFS bfs = new BinaryTreeIteratorBFS(bst.getRoot());
                    System.out.println("Breadth First Traversal");
                    while(bfs.hasNext()){
                        System.out.println(bfs.next());
                    }
                    break;
                case 3:
                    BinaryTreeIteratorIOS ios = new BinaryTreeIteratorIOS(bst.getRoot());
                    System.out.println("In Order Traversal");
                    while(ios.hasNext()){
                        System.out.println(ios.next());
                    }
                    break;
                case 4:
                    BinaryTreeIteratorPOS pos = new BinaryTreeIteratorPOS(bst.getRoot());
                    System.out.println("Pre Order Traversal");
                    while(pos.hasNext()){
                        System.out.println(pos.next());
                    }
                    break;
                case 5:
                    File file = new File("input.txt");
                    time1 = System.currentTimeMillis();
                    try(Scanner input = new Scanner(file)){
                        while(input.hasNext()){
                            int a = input.nextInt();
                            bst.add(a);
                        }
                    }catch(Exception ex){
                        System.out.println("File not found.");
                    }
                    time2 = System.currentTimeMillis();
                    System.out.println("Time to add to BST from file: " 
                            + (time2 - time1) + "ms");
                    break;
                case 6:
                    time1 = System.currentTimeMillis();
                    BinaryTreeIteratorBFS b = new BinaryTreeIteratorBFS(bst.getRoot());
                    while(b.hasNext()){
                        System.out.println(b.next());
                    }
                    time2 = System.currentTimeMillis();
                    System.out.println("Time to traverse BST(BFS): " 
                            + (time2 - time1) + "ms");
                    break;
                case 7:
                    time1 = System.currentTimeMillis();
                    BinaryNode<E> min = bst.findMin(bst.getRoot());
                    time2 = System.currentTimeMillis();
                    System.out.println("Minimum: " + min.info);
                    System.out.println("Time to find minimum: " 
                            + (time2 - time1) + "ms");
                    break;
                case 0:
                    break;
            }
        }
    }
    
    /**
     * Operations for the AVL Tree using Strings, including
     * insertion, insertion from a file, traversals, and time trials.
     */
    private void avlstring(){
        int c = -1;
        long time1;
        long time2;
        while(c!=0){
            avlmenu();
            c = in.nextInt();
            switch(c){
                case 1:
                    System.out.print("Add: ");
                    String s;
                    s = in.next();
                    avl.add(s);
                    break;
                case 2:
                    BinaryTreeIteratorBFS avlbfs = new BinaryTreeIteratorBFS(avl.getRoot());
                    System.out.println("Breadth First Traversal");
                    while(avlbfs.hasNext()){
                        System.out.println(avlbfs.next());
                    }
                    break;
                case 3:
                    BinaryTreeIteratorIOS avlios = new BinaryTreeIteratorIOS(avl.getRoot());
                    System.out.println("In Order Traversal");
                    while(avlios.hasNext()){
                        System.out.println(avlios.next());
                    }
                    break;
                case 4:
                    BinaryTreeIteratorPOS avlpos = new BinaryTreeIteratorPOS(avl.getRoot());
                    System.out.println("Pre Order Traversal");
                    while(avlpos.hasNext()){
                        System.out.println(avlpos.next());
                    }
                    break;
                case 5:
                    File file = new File("input2.txt");
                    time1 = System.currentTimeMillis();
                    try(Scanner input = new Scanner(file)){
                        while(input.hasNext()){
                            String a = input.next();
                            avl.add(a);
                        }
                    }catch(Exception ex){
                        System.out.println("File not found.");
                    }
                    time2 = System.currentTimeMillis();
                    System.out.println("Time to add to AVL from file: " 
                            + (time2 - time1) + "ms");
                    break;
                case 6:
                    time1 = System.currentTimeMillis();
                    BinaryTreeIteratorBFS b = new BinaryTreeIteratorBFS(avl.getRoot());
                    while(b.hasNext()){
                        System.out.println(b.next());
                    }
                    time2 = System.currentTimeMillis();
                    System.out.println("Time to traverse AVL Tree: " 
                            + (time2 - time1) + "ms");
                    break;
                case 7:
                    time1 = System.currentTimeMillis();
                    BinaryNode<E> min = avl.findMin(avl.getRoot());
                    time2 = System.currentTimeMillis();
                    System.out.println("Minimum: " + min.info);
                    System.out.println("Time to find minimum: " 
                            + (time2 - time1) + "ms");
                    break;
                case 0:
                    break;
            }  
        }
    }
    
    /**
     * Operations for the Binary Search Tree using Strings, including
     * insertion, insertion from a file, traversals, and time trials.
     */
    private void bststring(){
        int c = -1;
        long time1;
        long time2;
        while(c!=0){
            bstmenu();
            c = in.nextInt();
            switch(c){
                case 1:
                    System.out.print("Add: ");
                    String s;
                    s = in.next();
                    bst.add(s);
                    break;
                case 2:
                    BinaryTreeIteratorBFS bfs = new BinaryTreeIteratorBFS(bst.getRoot());
                    System.out.println("Breadth First Traversal");
                    while(bfs.hasNext()){
                        System.out.println(bfs.next());
                    }
                    break;
                case 3:
                    BinaryTreeIteratorIOS ios = new BinaryTreeIteratorIOS(bst.getRoot());
                    System.out.println("In Order Traversal");
                    while(ios.hasNext()){
                        System.out.println(ios.next());
                    }
                    break;
                case 4:
                    BinaryTreeIteratorPOS pos = new BinaryTreeIteratorPOS(bst.getRoot());
                    System.out.println("Pre Order Traversal");
                    while(pos.hasNext()){
                        System.out.println(pos.next());
                    }
                    break;
                case 5:
                    File file = new File("input2.txt");
                    time1 = System.currentTimeMillis();
                    try(Scanner input = new Scanner(file)){
                        while(input.hasNext()){
                            String a = input.next();
                            bst.add(a);
                        }
                    }catch(Exception ex){
                        System.out.println("File not found.");
                    }
                    time2 = System.currentTimeMillis();
                    System.out.println("Time to add to BST from file: " 
                            + (time2 - time1) + "ms");
                    break;
                case 6:
                    time1 = System.currentTimeMillis();
                    BinaryTreeIteratorBFS b = new BinaryTreeIteratorBFS(bst.getRoot());
                    while(b.hasNext()){
                        System.out.println(b.next());
                    }
                    time2 = System.currentTimeMillis();
                    System.out.println("Time to traverse BST: " 
                            + (time2 - time1) + "ms");
                    break;
                case 7:
                    time1 = System.currentTimeMillis();
                    BinaryNode<E> min = bst.findMin(bst.getRoot());
                    time2 = System.currentTimeMillis();
                    System.out.println("Minimum: " + min.info);
                    System.out.println("Time to find minimum: " 
                            + (time2 - time1) + "ms");
                    break;
                case 0:
                    break;
            }
        }
    }
    
    /**
     * Creates a menu for the AVL Tree
     */
    private void avlmenu(){
        System.out.println("====AVL Menu====");
        System.out.println("1.Add");
        System.out.println("2.Display BFS");
        System.out.println("3.Display IOS");
        System.out.println("4.Display POS");
        System.out.println("5.Time Trial Insert");
        System.out.println("6.Time Trial Traversal");
        System.out.println("7.Time Trial Minimum");
        System.out.println("0.Quit");
    }//avlmenu
    
    /**
     * Creates a menu for the Binary Search Tree
     */
    private void bstmenu(){
        System.out.println("====BST Menu====");
        System.out.println("1.Add");
        System.out.println("2.Display BFS");
        System.out.println("3.Display IOS");
        System.out.println("4.Display POS");
        System.out.println("5.Time Trial Insert");
        System.out.println("6.Time Trial Traversal");
        System.out.println("7.Time Trial Minimum");
        System.out.println("0.Quit");
    }//bstmenu
    
    public static void main(String[] args) throws FileNotFoundException{
         Demonstration d = new Demonstration();
        
    }//main
    
}//Demonstration
