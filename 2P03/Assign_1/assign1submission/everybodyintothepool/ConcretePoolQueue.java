package everybodyintothepool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**This class implements the PoolQueue interface. It creates a linked list 
 * of groups of 1-4 elements depending on user input. After the user builds the 
 * list they may remove members from the list based on priority/availability
 * (i.e [A] [BC] [DEF] [GH] remove 4 -> [ADEF]). If the user tries to remove an
 * element or a group of elements an InsufficientElementsException is thrown.
 * 
 * @author Sawyer Fenwick [6005011]
 * @version 1.0 May 2018
 * @param <E> 
 * @throws InsufficientElementsException 
 */
public final class ConcretePoolQueue<E> implements PoolQueue<E> {
    
        Node<E> head;
        int count;
        Scanner in = new Scanner(System.in);
        public ConcretePoolQueue(){
            head=new Node<>(null,0,null);
            System.out.println("---Macguffin Games---");
            menu();
            int choice=-1;
            while(choice != 0){
                choice = in.nextInt();
                switch(choice){
                    case 1:
                        addSingle();
                        break;
                    case 2:
                        addPair();
                        break;
                    case 3:
                        addTriple();
                        break;
                    case 4:
                        addQuartet();
                        break;
                    case 5:
                        try{
                            if(hasSingle()){
                                System.out.println("\t" + "Removed [" + 
                                        removeSingle() + "]");
                            }
                            else{
                                throw new InsufficientElementsException();
                            }
                        }catch(InsufficientElementsException e){
                            System.out.println("Exception Caught [" + 
                                    e.getMessage() + "]");
                        }
                        break;
                    case 6: 
                        try{
                            if(hasPair()){
                                List<E> l = removePair();
                                System.out.println("\t" + "Removed [" + 
                                        l.get(0) + "," + l.get(1) + "]");
                            }
                            else{
                                throw new InsufficientElementsException();
                            }
                        }catch(InsufficientElementsException e){
                            System.out.println("Exception Caught [" + 
                                    e.getMessage() + "]");
                        }
                        break;
                    case 7:
                        try{
                            if(hasTriple()){
                                List<E> l = removeTriple();
                                System.out.println("\t" + "Removed [" + 
                                        l.get(0) + "," + l.get(1) + "," + 
                                        l.get(2) + "]");
                            }
                            else{
                                throw new InsufficientElementsException();
                            }
                        }catch(InsufficientElementsException e){
                            System.out.println("Exception Caught [" + 
                                    e.getMessage() + "]");
                        }
                        break;
                    case 8:
                        try{
                            if(hasQuartet()){
                                List<E> l = removeQuartet();
                                System.out.println("\t" + "Removed [" + 
                                       l.get(0) + "," + l.get(1) + ","+ l.get(2)
                                       + "," + l.get(3) + "]");
                            }
                            else{
                                throw new InsufficientElementsException();
                            }
                        }catch(InsufficientElementsException e){
                            System.out.println("Exception Caught [" + 
                                    e.getMessage() + "]");
                        }
                        break;
                    case 9:
                        test();
                    case 0: 
                        break;
                }//end switch
                System.out.println();
                menu();
            }//end while loop
            
        }//constructor
    
         
        /**
         * Prints the menu for the user
         */
        public void menu(){
            System.out.println("1. Add One " + "\t" + "2. Add Two" + "\t" + 
                    "3. Add Three" + "\t" + "4. Add Four");
            System.out.println("5. Remove One" + "\t" + "6. Remove Two" + "\t" 
                    + "7. Remove Three" + "\t" + "8. Remove Four");
            System.out.println("\t\t" + "9. Test Options" + "\t" + "0. Quit");
        }//menu
        
         /** 
         * Prints the result of hasSingle, hasPair, hasTriple, hasQuartet and 
         * count
         */     
        public void test(){
            System.out.println("One: \t" + hasSingle());
            System.out.println("Two: \t" + hasPair());
            System.out.println("Three: \t" + hasTriple());
            System.out.println("Four: \t" + hasQuartet());
            System.out.println("Count: \t" + count());
        }//test
    
        
         /** 
         * Reads input from user
         */
        private void addSingle(){
            Scanner i = new Scanner(System.in);
            System.out.print("Enter Name: ");
            String name = i.nextLine();
            addSingle((E) name);
        }//addSingleHelper
        
        /**
         * Adds a single element to the list
         * 
         *@param item the name added the the list 
         */
        @Override
        public void addSingle(E item){
            count++;
            Node<E> p = head;
            Node<E> q = null;
            
            while(p!= null){
                q=p;
                p=p.next;
            }
            if(q==null){
                head = new Node<>(item, 1, null);
            }
            else{
                q.next = new Node<>(item, 1, null);
            }
            
            System.out.println("\t" + "Added [" + item + "]" );
        }//addSingle
	
         /** 
         * Reads input from user
         */      
        private void addPair(){
            Scanner i = new Scanner(System.in);
            System.out.print("First: ");
            String first = i.nextLine();
            System.out.print("Second: ");
            String second = i.nextLine();
            addPair((E) first, (E) second);
        }//addPair
        
        /**
         * Adds a pair to the list
         * 
         * @param first the first name entered 
         * @param second the second name entered 
         */
        @Override
        public void addPair(E first, E second){
            List<E> l = new ArrayList<>();
            l.add(first);
            l.add(second);
            count += 2;
            Node<E> p = head;
            Node<E> q = null;

            while(p!= null){
                q=p;
                p=p.next;
            }
            if(q==null){
                head = new Node<>((E)l, 2, null);
            }
            else{
                q.next = new Node<>((E)l, 2, null);
            }
            System.out.println("\t" + "Added [" + first + "," + second + "]" );
        }//addPair

         /** 
         * Reads input from user
         */
        private void addTriple(){
            Scanner i = new Scanner(System.in);
            System.out.print("First: ");
            String first = i.nextLine();
            System.out.print("Second: ");
            String second = i.nextLine();
            System.out.print("Third: ");
            String third = i.nextLine();
            addTriple((E) first, (E) second, (E) third);
        }//addTriple

        /**
         * Adds a triple to the list
         * 
         * @param first the first name entered 
         * @param second the second name entered
         * @param third the third name entered
         */
        @Override
        public void addTriple(E first, E second, E third){
            List<E> l = new ArrayList<>();
            l.add(first);
            l.add(second);
            l.add(third);
            count += 3;
            Node<E> p = head;
            Node<E> q = null;

            while(p!= null){
                q=p;
                p=p.next;
            }
            if(q==null){
                head = new Node<>((E)l, 3, null);
            }
            else{
                q.next = new Node<>((E)l, 3, null);
            }
            System.out.println("\t" + "Added [" + first + "," + second + "," 
                    + third + "]" );
        }//addTriple

         /** 
         * Reads input from user
         */
        private void addQuartet(){
            Scanner i = new Scanner(System.in);
            System.out.print("First: ");
            String first = i.nextLine();
            System.out.print("Second: ");
            String second = i.nextLine();
            System.out.print("Third: ");
            String third = i.nextLine();
            System.out.print("Fourth: ");
            String fourth = i.nextLine();
            addQuartet((E) first, (E) second, (E)third, (E) fourth);
        }//addQuartetHelperMethod

        /**
         * Adds a quartet to the list
         * 
         * @param first the first name entered 
         * @param second the second name entered
         * @param third the third name entered
         * @param fourth the fourth name entered
         */
        @Override
        public void addQuartet(E first, E second, E third, E fourth){
            List<E> l = new ArrayList<>();
            l.add(first);
            l.add(second);
            l.add(third);
            l.add(fourth);
            count += 4;
            Node<E> p = head;
            Node<E> q = null;

            while(p!= null){
                q=p;
                p=p.next;
            }
            if(q==null){
                head = new Node<>((E)l, 4, null);
            }
            else{
                q.next = new Node<>((E)l, 4, null);
            }
            System.out.println("\t" + "Added [" + first + "," + second + "," +
                    third + "," + fourth + "]" );
        }//addQuartet
        
        /**
         * Searches the list for a value
         * 
         * @param n the value being searched for
         * @param p the next node in the list 
         * @return true if n is found, false if not
         */ 
        public boolean search(int n, Node<E> p){
            if(p == null){
                return false;
            }
            if(p.size == n){
                return true;
            }
            if(n-p.size > 0){
                search(n-p.size, p.next);
            }
            if(n-p.size < 0){
                search(n, p.next);
            }
            return search(n-p.size, p.next);
        }//search
        
        /**
         * Searches the list for the first instance of a single and deletes it,
         * returning the item to remove().
         * 
         * @return a single element E from the list 
         */
        @Override
        public E removeSingle(){
            Node<E> p = head;
            Node<E> q;
            q = p;
            
            while(p.size != 1){
                q = p;
                p = p.next;
            }
            
            E item = p.item;
            q.next = p.next;
            count--;
            return item;
        }//removeSingle
        
        /**
         * Builds a new list of elements that are to be deleted 
         * 
         * @param n the number being searched for 
         * @param p the next node in the list
         * @return a list 
         */ 
        public Node<E> build(int n, Node<E> p){
            if(p == null){
                return null;
            }
            if(p.size == n){
                return new Node<>(p.item, n, null);
            }
            if(p.size > n){
                build(n, p.next);
            }
            if(n-p.size > 0){
                return new Node<>(p.item, p.size, build(n-p.size, p.next));
            }
            return build(n, p.next);
        }//build
        
        /** 
         * Compares two lists, deleting members of the list 'head' if they 
         * exist in both lists 
         * 
         * @param d the list of elements to be deleted
         */
        public void delete(Node<E> d){
            Node<E> p = head;
            Node<E> q=p;
            while(p.size != d.size){
               q = p;
               p = p.next;
            }
            q.next = p.next;
            if(d.next != null){
                delete(d.next);
            }
        }//delete
        
        /**
         * Creates a list of elements to be deleted, deletes them from the 
         * original list, and returns the second list to remove() for printing
         * 
         * @return a list of the deleted elements
         */
        @Override
        public List<E> removePair(){
            Node<E> p = head;
            Node<E> q;
            
            List<E> list = new ArrayList<>();
            List<E> list2;
            q = build(2,p);
            delete(q);
            while(q!=null){
                if(q.size == 1){
                    list.add(q.item);
                }
                else if (q.size == 2){
                    list2 = (List<E>)q.item;
                    list.add(list2.get(0));
                    list.add(list2.get(1));
                }
                q = q.next;
            }
            count-=2;
            return list;
        }//removePair

        /**
         * Creates a list of elements to be deleted, deletes them from the 
         * original list, and returns the second list to remove() for printing
         * 
         * @return a list of the deleted elements
         */
        @Override
        public List<E> removeTriple(){
           Node<E> p = head;
            Node<E> q;
            List<E> list = new ArrayList<>();
            List<E> list2;
            q = build(3,p);
            delete(q);
            while(q!=null){
               switch (q.size) {
                   case 1:
                       list.add(q.item);
                       break;
                   case 2:
                       list2 = (List<E>)q.item;
                       list.add(list2.get(0));
                       list.add(list2.get(1));
                       break;
                   case 3:
                       list2 = (List<E>) q.item;
                       list.add(list2.get(0));
                       list.add(list2.get(1));
                       list.add(list2.get(2));
                       break;
                   
               }
               q = q.next;
            }
            count-=3;
            return list;
        }//removeTriple

        /**
         * Creates a list of elements to be deleted, deletes them from the 
         * original list, and returns the second list to remove() for printing
         * 
         * @return a list of the deleted elements
         */
        @Override
        public List<E> removeQuartet(){
            Node<E> p = head;
            Node<E> q;
            List<E> list = new ArrayList<>();
            List<E> list2;
            q = build(4,p);
            delete(q);
            while(q!=null){
                switch (q.size) {
                    case 1:
                        list.add(q.item);
                        break;
                    case 2:
                        list2 = (List<E>)q.item;
                        list.add(list2.get(0));
                        list.add(list2.get(1));
                        break;
                    case 3:
                        list2 = (List<E>)q.item;
                        list.add(list2.get(0));
                        list.add(list2.get(1));
                        list.add(list2.get(2));
                        break;
                    case 4:
                        list2 = (List<E>)q.item;
                        list.add(list2.get(0));
                        list.add(list2.get(1));
                        list.add(list2.get(2));
                        list.add(list2.get(3));
                        break;
                    default:
                        break;
                }
                q = q.next;
            }
            count-=4;
            return list;
        }//removeQuartet

        /**
         *Determines if there is a single element in the list
         * 
         * @return true if a single exists, false if not
         */
        @Override
        public boolean hasSingle(){
            Node<E> p = head;
            boolean t = false;
            while(p!=null){
                t = search(1,p);
                if(t){
                    break;
                }
                p=p.next;
            }
            return t;
        }//hasSingle

        /**
         *Determines if there exists a group of two in the list
         * 
         * @return true if a pair exists, false if not
         */
        @Override
        public boolean hasPair(){
            Node<E> p = head;
            boolean t = false;
            while(p!=null){
                t = search(2,p);
                if(t){
                    break;
                }
                p=p.next;
            }
            return t;
        }//hasPair
        
        /**
         *Determines if there exists a group of three in the list
         * 
         * @return true if a triple exists, false if not
         */
        @Override
        public boolean hasTriple(){
            Node<E> p = head;
            boolean t = false;
            while(p!=null){
                t = search(3,p);
                if(t){
                    break;
                }
                p=p.next;
            }
            return t;
        }//hasTriple

        /**
         * Determines if there exists a group of four in the list
         * 
         * @return true if a quartet exists, false if not
         */       
        @Override
        public boolean hasQuartet(){
            Node<E> p = head;
            boolean t = false;
            while(p!=null){
                t = search(4,p);
                if(t){
                    break;
                }
                p=p.next;
            }
            return t;
        }//hasQuartet

        /**
         * Returns the number of elements in the list
         * 
         * @return number of elements
         */
        @Override
        public int count(){
            return count;
        }//count

        public static void main(String args[]){
            PoolQueue<Integer> pq = new ConcretePoolQueue<>();}
            
}//ConcretePoolQueue