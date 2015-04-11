/**
 * @author Suma Gopal
 * This class is for nodes of the of the HuffmanList.
 * Each node contains data for probabilities, characters, and entropyCalculations.
 * Each node also contains the following pointers: next and previous for the doubly linked list and left, right, and root for the binary tree.
 */
public class Node
{
	private int prob, entropyCalc;
	private String charr;
	private Node next, prev, left, right, root;
	
	/**
	 * Default constructor initializing all variables to null.
	 */
	public Node()
	{
		prob = entropyCalc = 0;
		charr = "";
		next = prev = left = right = root = null;
	}
	
	/**
	 * Constructor to initialize a node with probability and a string letter.
	 * @param p probability
	 * @param c character strings
	 */
	public Node(int p, String c)
	{
		prob = p;
		entropyCalc = 0;
		charr = c;
		next = prev = left = right = root = null;
	}
	
	/**
	 * Constructor that takes in values for prob., character, entropy calc., left and right node.
	 * @param p probability
	 * @param e entropy calculation
	 * @param c character string
	 * @param l left node
	 * @param r right node
	 */
	public Node(int p, int e, String c, Node l, Node r)
	{
		prob = p;
		entropyCalc = e;
		charr = c;
		entropyCalc = e;
		next = prev = null;
		left = l;
		right = r;
	}
	
	/**
	 * Retrieves the probability.
	 * @return probability
	 */
	public int getProb()
	{
		return prob;
	}
	
	/**
	 * Sets the probability value
	 * @param p probability
	 */
	public void setProb(int p)
	{
		prob = p;
	}
	
	/**
	 * Gets the entropy calculation.
	 * @return entropy calculation
	 */
	public int getEntropyCalc()
	{
		return entropyCalc;
	}
	
	/**
	 * Sets the entropy calculation value
	 * @param e entropy calculation
	 */
	public void setEntropyCalc(int e)
	{
		entropyCalc = e;
	}
	
	/**
	 * Retrieves the character string.
	 * @return character string
	 */
	public String getCharr()
	{
		return charr;
	}
	
	/**
	 * Sets the character string.
	 * @param c character string
	 */
	public void setCharr(String c)
	{
		charr = c;
	}
	
	/**
	 * Retrieves the next node.
	 * @return next node
	 */
	public Node getNext()
	{
		return next;
	}	
	
	/**
	 * Sets the next node.
	 * @param n next node
	 */
	public void setNext(Node n)
	{
		next = n;
	}
	
	/**
	 * Retreives the previous node.
	 * @return previous node
	 */
	public Node getPrev()
	{
		return prev;
	}
	
	/**
	 * Sets the previous node.
	 * @param p previous node
	 */
	public void setPrev(Node p)
	{
		prev = p;  
	}
	
	/**Retrieves the left node.
	 * 
	 * @return left node
	 */
	public Node getLeft()
	{
		return left;
	}
	
	/**
	 * Sets the left node.
	 * @param l left node
	 */
	public void setLeft(Node l)
	{
		left = l;
	}
	
	/**
	 * Retrieves the right node.
	 * @return right node
	 */
	public Node getRight()
	{
		return right;
	}
	
	/**
	 * Sets the right node.
	 * @param r right node
	 */
	public void setRight(Node r)
	{
		right = r;
	}
	
	/**
	 * Retrieves the root node.
	 * @return root node
	 */
	public Node getRoot()
	{
		return root;
	}
	
	/**
	 * Sets the root node.
	 * @param r root node
	 */
	public void setRoot(Node r)
	{
		root = r;
	}
}