package dendrology;
import java.util.*;
/**This class extends the BinarySearchTree class and builds an AVL tree.
 *
 * @author Sawyer Fenwick [6005011]
 * @version 1.0 (June 2018)
 * @param <E>
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> implements Iterable<E> {
    
    public AVLTree(){
        myTree = null;
    }//AVLTreeConstructor
    
    /**
     * Helper method to add to the tree. Overrides the add method in Binary
     * Search Tree.
     * 
     * @param toInsert the item being inserted 
     */
    @Override
    public void add(E toInsert){
        myTree = add(myTree, toInsert);
    }//add
    
    /**
     * Adds to the tree, then checks the height balance of the tree.
     * 
     * @param root the current tree
     * @param toInsert the item being added to the tree
     * @return the new tree with the added element after being balance checked
     */
    private BinaryNode<E> add(BinaryNode<E> root, E toInsert){
        if(root == null){
            return new BinaryNode<>(toInsert);
        }
        int compare = toInsert.compareTo(root.info);
        
        if(compare < 0){
            root.left = add(root.left, toInsert);
        }
        else if(compare > 0){
            root.right = add(root.right, toInsert);
        }
        else{
            //duplicate do nothing
        }
        return balance(root);
    }//add
    
    /**
     * Checks the balance factor of the node, and if it is unbalanced performs
     * the necessary rotations to balance it.
     * 
     * @param root the node being balance checked
     * @return the new balanced tree
     */
    private BinaryNode<E> balance(BinaryNode<E> root){
        if(root == null){
            return root;
        }
        if(height(root.left) - height(root.right) > 1){
            if(height(root.left.left) >= height(root.left.right)){
                root = rotateLeft(root);
            }
            else{
                root = doubleLeft(root);
            }
        }
        else{
            if(height(root.right) - height(root.left) > 1){
                if(height(root.right.right) >= height(root.right.left)){
                    root = rotateRight(root);
                }
                else{
                    root = doubleRight(root);
                }
            }
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }//balance
    
    /**
     * Checks the height of a node 
     * 
     * @param root the node being height checked 
     * @return the height of the node 
     */
    public int height(BinaryNode<E> root){
        return root == null ? -1 : root.height;
    }//height
  
    /**
     * Performs a single left rotation
     * 
     * @param k2 the unbalanced node
     * @return the new height balanced tree
     */
    public BinaryNode<E> rotateLeft(BinaryNode<E> k2){
        BinaryNode<E> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }//rotateLeft
    
    /**
     * Performs a double left rotation
     * 
     * @param k3 the unbalanced node
     * @return the new height balanced tree
     */
    public BinaryNode<E> doubleLeft(BinaryNode<E> k3){
        k3.left = rotateRight(k3.left);
        return rotateLeft(k3);
    }//doubleLeft
     
    /**
     * Performs a single right rotation
     * 
     * @param k1 the unbalanced node
     * @return the new height balanced tree
     */
    public BinaryNode<E> rotateRight(BinaryNode<E> k1){
        BinaryNode<E> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }//rotateRight
    
    /**
     * Performs a double right rotation
     * 
     * @param k1 the unbalanced node
     * @return the new height balanced tree
     */
    public BinaryNode<E> doubleRight(BinaryNode<E> k1){
        k1.right = rotateLeft(k1.right);
        return rotateRight(k1);
    }//doubleRight
    
    /**
     * Creates a pre-order iterator of the tree
     * 
     * @return pre-order iterator
     */
    public Iterator<E> preorder(){
        return new BinaryTreeIteratorPOS<>(myTree);
    }//preorder
    
    /**
     * Creates an in-order iterator of the tree
     * 
     * @return in-order iterator 
     */
    public Iterator<E> inorder(){
        return new BinaryTreeIteratorIOS<>(myTree);
    }//inorder
}
