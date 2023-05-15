package dendrology;
/*BinaryTreeIteratorBFS
 *This is an iterator for breadth-first traversal of a BinarySearchTree
 *Brock University
 */
import java.util.*;
public class BinaryTreeIteratorBFS<E extends Comparable<E>> implements Iterator<E> {
	LinkedList<BinaryNode<E>> queue; //A queue will be used for BFT
	
	//Constructor. Pretty straight-forward
	public BinaryTreeIteratorBFS(BinaryNode<E> tree) {
		queue=new LinkedList<>();
		if (tree!=null) {
			queue.add(tree);
		}
	}
	
	//If there are still nodes on the queue, then there are more elements
        @Override
	public boolean hasNext() {
		return queue.size()>0;
	}
	
	//Returns the current node's information. But first, it adds any children to the queue
        @Override
	public E next() {
		E forReturn;
		BinaryNode<E> ptr;
		if (hasNext()) {
			ptr=queue.removeFirst();
			forReturn=ptr.info;
			if (ptr.left!=null) queue.add(ptr.left);
			if (ptr.right!=null) queue.add(ptr.right);
			return forReturn;
		}
		else throw new NoSuchElementException();
	}
	
	//Optional method. Not implemented
        @Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}