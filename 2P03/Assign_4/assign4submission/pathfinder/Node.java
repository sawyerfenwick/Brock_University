package pathfinder;

/**
 * This class defines a Node for the Graph class.
 * 
 * @author Sawyer Fenwick [6005011]
 * @version 1.0
 */
public class Node {
    
    Node north;
    Node south;
    Node east;
    Node west;
    char c;
    
    Node(Node north, Node south, Node east, Node west, char c){
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.c = c;
    }
    
}
