package pathfinder;
import java.util.*;
/**
 * This class doesn't work, its just all I have. 
 * 
 * @author Sawyer Fenwick [6005011]
 * @version 1.0
 */
public class Graph {
    
    int pathcost = 0;
    Scanner sc = new Scanner(System.in);
    Node adjList[];
    String a[];
    Node b[][];
    
    public Graph(){
        newPuzzle();
        int choice =-1;
        while(choice != 4){
            menu();
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Optimal Path Cost: " + pathcost);
                    break;
                case 2:
                    solve();
                    break;
                case 3:
                    newPuzzle();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Unrecognized Option.");
            }
        }
    }//constructor
    
    /**
     * 
     */
    private void solve(){
        
    }//solve
    
    /**
     * Prompts user to give them the dimensions of the maze, creates graph.
     */
    private void newPuzzle(){
        int w;
        int h;
        int d;
        String line;
        pathcost = 0; //new puzzle, new pathcost
        System.out.print("Enter width (w): ");
        w = sc.nextInt();
        System.out.print("Enter height (h): ");
        h = sc.nextInt();
        System.out.print("Enter depth (d): ");
        d = sc.nextInt();
        System.out.println("Enter maze below. Only rows of width " + w 
                + " will be accpeted.");
        
        int V = w*h*d; //number of vertices (V)
        a = new String[h];
        
        for(int i = 0; i < d*h; i++){
            line = sc.next();
            
            if(!legal(line, w)){
                System.out.println("Not accepted. Please start over.");
                break;
            }
            else if(line.isEmpty() || line.trim().equals("") || 
                    line.trim().equals("\n")){ //skips blank line
                i--; //deduct 1 from i to redo the "missed" loop
            }
            else if(line.length() > w){ //too many W characters
                System.out.println("Not accepted. Please start over.");
                break;
            }
            else{ //accept the line
                a[i] = line;
            }
            
        }
        createGraph(w, h, V);
    }//newPuzzle
    
    /**
     * Helper method to determine if the user entered correct input
     * @param line the String
     * @param w the width 
     * @return false if the user enter any char other than X E S or O
     */
    public boolean legal(String line, int w){
        for(int i = 0; i < w; i++){
            if(line.charAt(i) == 'X' || line.charAt(i) == 'S' || 
                    line.charAt(i) == 'E' || line.charAt(i) == 'O'){
                return true;
            }
        }
        return false;
    }//legal
    
    /**
     * Creates the adjacency list for the Graph
     * @param V the total vertexes of the Graph
     */
    private void createGraph(int w, int h, int V){
        
        adjList = new Node[V];
        b = new Node[h][w];
        
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                b[i][j] = new Node(b[i-1][j],b[i+1][j],b[i][j+1],b[i][j-1],a[i].charAt(j));
            }
        }
        
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(b[i][j].c != 'X'){
                    adjList[i] = b[i][j];
                }
            }
        }
        print();
       
    }//createGraph
    
    /**
     * Prints the adjacency list for the graph
     * @param g the Graph
     */
    private void print(){
        for (Node adjList1 : adjList) {
            if (adjList1 == null) {
                break;
            }
            System.out.println(adjList1.c);
        }
    }//print 
    
    /**
     * Creates a menu for user input
     */
    private void menu(){
       System.out.println("1. Estimate optimal solution cost");
       System.out.println("2. Solve optimally");
       System.out.println("3. Enter new puzzle");
       System.out.println("4. Quit");
    }//menu
    
    public static void main (String arg[]){
        Graph g = new Graph();
    }
    
}