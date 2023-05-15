package dendrology;
import java.util.*;
/**This class creates an iterator for the in-order traversal of a binary 
 * search tree (left, visit, right).
 *
 * @author Sawyer Fenwick [6005011]
 * @version 1.0 (June 2018)
 * @param <E>
 */
public class BinaryTreeIteratorIOS<E extends Comparable<E>> implements Iterator<E> {
    
    Stack<BinaryNode<E>> stack;
    
    /**
     * Pushes elements onto the stack
     * 
     * @param tree the node to be added to the stack
     */
    private void push(BinaryNode<E> tree){
        while(tree != null){
            stack.push(tree);
            tree = tree.left;
        }
    }//push
    
    public BinaryTreeIteratorIOS(BinaryNode<E> root){
        stack = new Stack<>();
        if(root!=null){
            push(root);
        }
    }//constructor
    
    /**
     * Checks if there are more elements on the stack
     * 
     * @return true if there are more elements on the stack 
     */
    @Override
    public boolean hasNext(){
        return !stack.isEmpty();
    }//hasNext
    
    /**
     * Returns the next element on the stack
     * 
     * @return the next item in the stack
     */
    @Override
    public E next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        BinaryNode<E> ptr = stack.pop();
        push(ptr.right);
        return ptr.info;
    }//next
    
    /**
     * Optional method, not implemented
     */
    @Override
    public void remove(){
        throw new UnsupportedOperationException();
    }
}
