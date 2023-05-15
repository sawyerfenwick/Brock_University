package dendrology;
import java.util.*;
/**This class creates an iterator for the pre-order traversal of a binary search
 * tree (visit, left, right).
 *
 * @author Sawyer Fenwick [6005011]
 * @version 1.0 (June 2018)
 * @param <E>
 */
public class BinaryTreeIteratorPOS<E extends Comparable<E>> implements Iterator<E> {
    
    Stack<BinaryNode<E>> stack;
    
    public BinaryTreeIteratorPOS(BinaryNode<E> tree){
        stack = new Stack<>();
        if(tree != null){
            stack.push(tree);
        }
    }//constructor
    
    /**
     * Checks if there are more elements 
     * 
     * @return true if there are more elements on the stack
     */
    @Override
    public boolean hasNext(){
        return !stack.isEmpty();
    }//hasNext
    
    /**
     * Returns the next element in the stack
     * 
     * @return the next item in the stack 
     */
    @Override
    public E next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        BinaryNode<E> ptr = stack.pop();
        if(ptr.right != null){
            stack.push(ptr.right);
        }
        if(ptr.left != null){
            stack.push(ptr.left);
        }
        return ptr.info;
    }//next
    
    /**
     * Optional method, not implemented
     */
    @Override
    public void remove(){
        throw new UnsupportedOperationException();
    }//remove
    
    
}
