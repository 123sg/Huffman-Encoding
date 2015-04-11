/**
 @author Suma Gopal
 * This class has the major functions for Huffman Encoding.
 * 1. Insertion-sorts inputs of probability-character pairs into a doubly linked list. Characters
 * are treated as letter strings in this program. This sorted list is printed to the user.
 * 2. From this list, a binary tree is created by repeatedly creating new root nodes by adding and concatenating the prob. and letters respectively, of two different nodes from the beginning linked list until the list is empty.
 * 3. Next, preorder traversal is performed on the binary tree. This traversal is printed as a list of the prob.-character pairs of each node to the user.
 * 4. Lastly, binary compression is performed on the binary tree, calculating the binary code for each letter and
 * its code's length. These results are printed to the user as well as the entropy.
 * Entropy calculation: The code length * prob. is calculated during binary compression of each leaf node and stored  as a product into that node. The products from all the leaf nodes are then added. The resulting sum is divided by 100 which gives the entropy.
 */

import java.lang.StringBuffer;
	
public class HuffmanList
{
	private Node head; // Head of the linked list.
	int size = 0; 	// Number of nodes in the linked list.

	/**
	 * Default constructor initializing head of the list to empty and the size to 0.
	 */
	public HuffmanList()
	{
		head = null;
		size = 0;
	}
	
	/**
	 * Returns the (head) first node of the list.
	 * @return head node of the list.
	 */
	public Node getHead()
	{
		return head;
	}
	
	/**
	 *Sets the head to a given node h.
	 * @param h is what head is set equal to
	 */
	public void setHead(Node h)
	{
		head = h;
	}
	
	/**
	 * Gives the size of the list.
	 * @return size of list.
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 *If the head is empty, then the list is empty.  This method 
	 *returns true if the list is empty. If the list is not empty, it returns false.
	 * @return true if head is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return(head == null);
	}
	
	/**
	 * Insertion sort - Inserts a probability-character pair into a new in the ascending order of prob.
	 * @param p probability
	 * @param c character string
	 */
	public void insertionSort(int p, String c)
	{	
		//New node to be inserted into the linked list.
		Node n = new Node(p, c);
		
		//If the list is empty, sets the new node to be the head of the list.
		if(isEmpty())
		{
			setHead(n);
		}
		
		//If the list is not empty, inserts the node in ascending order.
		else
		{
			//Temporary node starting from the head of the list.
			Node curr = getHead();
			
			/*If the new node's prob. <= the current head node's prob., inserts it at the head of the list.*/
			if(n.getProb() <= curr.getProb())
			{
				n.setNext(curr);
				curr.setPrev(n);
				setHead(n);
			}
			//If the new node's prob. > the head node's prob., insert it after the head node.
			else
			{
				//curr = getHead();
				//Iterates down the list until the current node's prob. > the new node's prob.
				while(n.getProb() > curr.getProb() && curr.getNext() != null)
				{
					curr = curr.getNext();	
				}
				/*If the new node's prob < current's node's prob, the new node is inserted before the current node.*/
				 if(n.getProb() <= curr.getProb())
				{
					n.setPrev(curr.getPrev());
					n.setNext(curr);
					if(curr.getPrev() != null)
					{
						curr.getPrev().setNext(n);
					}
					curr.setPrev(n);
				}
				/*If the new node's prob > current node's prob, the new node is inserted after the current node.*/
				else if(n.getProb() > curr.getProb())
				{
					n.setNext(curr.getNext());
					curr.setNext(n);
					if(curr.getNext() != null)
					{
						curr.getNext().setPrev(n);
					}
					n.setPrev(curr);
				}
			}
		}
		//Update the size of the linked list by 1, for each new node added.
		size++;
	}	
	
	/**
	 *Overrides the above insertion sort function by insertion-sorting a Node n
	 * @param n node to be inserted into the list
	 */
	public void insertionSort(Node n)
	{
        //If the list is empty, set the new node to be the head of the list.
		if(isEmpty())
		{
			setHead(n);
		}
		
        //If the list is not empty, insert the node in ascending order into the list.
		else
		{
            //Temporary current node.
			Node curr = getHead();
            
            //If new node's prob. <= current head node's prob., set the new node to be the head.
			if(n.getProb() <= curr.getProb())
			{
				n.setNext(curr);
				curr.setPrev(n);
				setHead(n);
			}
            
            //If the new node's prob > than the head node's prob.
			else
			{
                /*Traverse the list until the new node's prob. is less than the current node's prob.
                 or until the last node has been reached.*/
				while(n.getProb() > curr.getProb() && curr.getNext() != null)
				{
					curr = curr.getNext();
				}
                
                /*If the new node's prob. <= the current node's prob., insert the new node before the current node.*/
				if(n.getProb() <= curr.getProb())
				{
					n.setNext(curr);
					n.setPrev(curr.getPrev());
                    //If the node before current is not empty, let this node's next pointer point to new node.
					if(curr.getPrev() != null)
					{
						curr.getPrev().setNext(n);
					}
					curr.setPrev(n);
				}
                
                //If the new node's prob. > the current node's prob., insert the new node after the current node.
				else if(n.getProb() > curr.getProb())
				{
					n.setPrev(curr);
					n.setNext(curr.getNext());
                    //If current's next node is not empty, let this next node's "prev" pointer point to new node.
					if(curr.getNext() != null)
					{
						curr.getNext().setPrev(n);
					}
					curr.setNext(n);
				}
			}
		}
        //Updates the number of nodes in list to measure the list's size.
		size++;
	}
	
	/**
	 *Creates a binary tree from the doubly linked list.
	 */
	public void createTree()
	{
        /*Two temporary nodes for adding & concatenating their prob. & letters. */
		Node curr = getHead();
		Node secondCurr = getHead().getNext();
		
        //If the list is not empty
		if(isEmpty() == false)
		{
            /*Traverses the linked list by adding and concatenating two nodes' prob.-letter pairs.
             A new node is created each time from this sum/concatenation. This new node is insertion-sorted back into the list. This node is then set as the root node of the two temporary nodes. The root node's left and right pointers are then set to point to the two temporary nodes respectively. Thus a binary tree is created. The two temporary pointers then traverse two the next two nodes on the list until the end of the list has been reached.*/
			while(curr != null && secondCurr != null)
			{
				Node n = new Node(curr.getProb() + secondCurr.getProb(), curr.getCharr() + secondCurr.getCharr());
				insertionSort(n);
				curr.setRoot(n);
				secondCurr.setRoot(n);
				n.setLeft(curr);
				n.setRight(secondCurr);
				
				//Move on to the next node pair to traverse the list.
				curr = secondCurr.getNext();
				secondCurr = curr.getNext();
			}
		}
	}
	
	/**
	 *Prints out the insertion sorted linked list.
	 */
	public void printSortedList()
	{
        //Temporary node starting from the head of the list.
		Node curr = getHead();
		System.out.println("Input insertion-sorted in ascending order, into a doubly linked list: ");
        
        //Prints out prob.-letter pairs until the end of the list.
		while(curr != null)
		{
			System.out.println(curr.getProb() + " " + curr.getCharr());
			curr = curr.getNext();
		}
		
	}
	

	/**
	 * Recursively traverses the tree in a pre-order fashion, printing the root first and
	 *  then traversing left & then traversing right.
	 * @param rootNode root node of the tree which has no parent nodes
	 */
	public void preorderTraversal(Node rootNode)
	{
        //Base case to exit out of recursion: If the root node is empty, return.
		if(rootNode == null)
		{
			return;
		}
		
		System.out.println(rootNode.getProb() + " " + rootNode.getCharr());
		
		preorderTraversal(rootNode.getLeft()); //Traverses nodes of the left tree.
		preorderTraversal(rootNode.getRight()); //Traverses nodes of the right tree.
		
	}
	
	/**
	 *Prints the entire binary tree
	 */
	public void printBinaryTree()
	{
        //Temporary node starting from the head of the list.
		Node curr = getHead();
        
        //If the list is not empty.
		if(isEmpty() == false)
		{
			System.out.println("The entire binary tree using preorder traversal: ");
            
            //Finds the topmost root of the tree, by finding the node that has no root.
			while(curr.getRoot() != null && curr != null)
			{
				curr = curr.getNext();
			}
            //curr is now at the root node. Preorder traversal is performed on curr.
			preorderTraversal(curr);
		}
        
        //If the list is empty, notify the user that the list cannot be printed.
		else
		{
			System.out.println("The list is empty, thus cannot be printed.");
		}
	}
	
	/**
	 * 	Performs compression by creating a binary code for each leaf node, using the algorithm from the following source: http://rosettacode.org/wiki/Huffman_coding#Java.
		Prints all leaf nodes' probabilities, characters, binary codes and the length of the codes. 
	 * @param rootNode root node that has no parents
	 * @param code binary code
	 */
	public void compress(Node rootNode, StringBuffer code)
	{
        //Base case to exit recursion: If the root is empty, return.
		if(rootNode == null)
		{
			return;
		}
        //Base case: if node is a leaf node where its left and right pointers are null, print the compression.
		else if(rootNode.getLeft() == null && rootNode.getRight() == null)
		{
			rootNode.setEntropyCalc(code.length() * rootNode.getProb());
			System.out.println(rootNode.getProb() + " " + rootNode.getCharr() + " " + code + ", Number of bits: " + code.length());
		}
        /*Recursively appends a '0' for traversing the left tree and recursively appends '1' for traversing the right tree.*/
		else
		{
			code.append('0');
			compress(rootNode.getLeft(), code);
            /*Deletes the last binary character before calculating binary code for the next node.*/
			code.deleteCharAt(code.length()-1);
			
			code.append('1');
			compress(rootNode.getRight(), code);
			code.deleteCharAt(code.length()-1);
		}
	}
	
	/**
	 * Function to print binary codes
	 */
	public void printCompression()
	{
        System.out.println("Huffman Encoding, printed in the format: ");
        System.out.println("Character, Letter, Binary Code, Number of bits. ");
        
        //Temporary node starting from the head of the list.
		Node curr = getHead();
        
        /*Intialized to 0 to calculate the sum of the product of code length * prob. of each node.*/
		double entropy = 0.0;
        
        //Finds the topmost root of the tree, by finding the node that has no root.
		while(curr.getRoot() != null && curr != null)
		{
			curr = curr.getNext();
		}
        
        //If there is no such root was found, notify the user of this error.
		if(curr == null)
		{
			System.out.println("Error, the root of the binary tree could not be found.");
            return;
		}
        
        //Performs compression starting from the topmost root of the tree. Then the entropy is calculated.
		else
		{
			compress(curr, new StringBuffer());

            
			curr = getHead();
            //Calculuates the entropy by traversing the linked list.
			while(curr!= null)
			{
                /*If a leaf node is found, its entropyCalc a.k.a. product of its code length * its prob. is added into the entropy sum.*/
                if(curr.getLeft() == null && curr.getRight() == null)
                {
                    entropy = entropy + curr.getEntropyCalc();
                }
				curr = curr.getNext();
			}
            
            //The entropy percentage is printed to the user.
			System.out.println("The entropy is: " + (entropy/100) + "%");
		}
	}
	
	
}