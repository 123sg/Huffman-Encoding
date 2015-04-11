/**
 @author Suma Gopal
 * Using methods from the HuffmanList class, the MainClass prints the insertion sorted linked list of the input,
 * prints the Huffman encoding binary tree and the final results of the Huffman encoding.
 */
public class MainClass
{
	public static void main(String [] args)
	{
		HuffmanList list = new HuffmanList();
		
		list.insertionSort(10, "a");
		list.insertionSort(5, "b");
		list.insertionSort(5, "c");
		list.insertionSort(60, "d");
		list.insertionSort(5, "e");
		list.insertionSort(10, "f");
		list.insertionSort(15, "g");
		list.insertionSort(8, "h");
	
		//Prints out the insertion sorted linked list of probability-character pairs.
		list.printSortedList();
		System.out.println();
		
		//Creates the binary tree.
		list.createTree();
		//Prints out the entire binary tree.
		list.printBinaryTree();
		
		System.out.println();
		
		/*Prints out binary code for the leaf nodes and the lenght of the codes along w/ the probabilities and characters.*/
		list.printCompression();
		
		
	}
	
}