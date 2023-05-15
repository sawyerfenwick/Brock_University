package everybodyintothepool;

/**
 * This class defines a Node for the Macguffin Games Pool Queue
 * 
 * @param <E> 
 * @author Sawyer Fenwick 
 * @version 1.0 May 2018
 */
public class Node<E> {
    E item;
    int size;
    Node<E> next;
    Node(E item, int size, Node<E> next) {
        this.item = item;
        this.size = size;
        this.next = next;
    }
}
