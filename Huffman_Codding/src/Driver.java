import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.PriorityQueue;

/*****************************************************************************
* Main Function: 
******************************************************************************/
public class Driver{
	static Map<Character, String> myKey = new HashMap<Character, String>();
	static String key = ""; // Will be sent in as a parameter to the makeKey function
	static Map<String, Character> deCodeKey = new HashMap<String, Character>();
	
	public static void main(String[] args) throws IOException{		
	/* Will be replaced by newMessage()
	****************************************************************/
		Scanner input = new Scanner(System.in);
	    System.out.print("Input text message: ");
	    String message = input.nextLine();
		// Convert the string to MessageChar array
	    char[] messageChar = message.toCharArray();
	    // Takes messageChar [] and makes an ArrayList of Nodes
		ArrayList<Node> myNodes = establishNodes(messageChar);		
		// Takes an ArrayList of Nodes and makes a PriorityQueue of Nodes 
		// that is ordered by the each Nodes count instance variable from 
		// largest to smallest.
		PriorityQueue<Node> sortedNodes = new PriorityQueue<Node> (myNodes);		
		// Takes a Ordered Priority Queue and creates a Huffman structure Node
		Node huffmanRoot = genHuffman(sortedNodes);		
		// Takes a Node that is the root of a Huffman structure and and an empty
		// string and creates a hash map that will be the key for compressing 
		// and uncompressing a message
		makeKey(huffmanRoot, key);
		System.out.println(printCompressedMessage(messageChar));
		System.out.println(printUnCompMessage(messageChar));
	/*********************************************************************/
		
//		System.out.println("Enter the corresponding letter to the action you wish to perform, ");
//		int choice = getChar();
//		while(true){
//			System.out.println(" c - compress message, u - uncompressed message, t - tree or n - new message: ");
//			int choice = getChar();
//			switch(choice){
//			case 'c':
//				System.out.println(printCompressedMessage(messageChar));
//				break;
//			case 'u':
//				printUnCompMessage;
//				break;
//			case 't':
//				printHuffmanStruct;
//				break;
//			case 'n':
//				newMessage();
//				break;
//			default:
//				System.out.print("Invalid entry\n");
//			
//			}
//		}
}
	////////////////////////////End of main///////////////////////////////////

// /*****************************************************************************
//  * Function getMessage: This Function takes in a message containing multiple 
//  * lines of text. The message will be submitted upon the input of a # symbol
//  * * Returns: series of strings.
//  *****************************************************************************
//  */ public void newMessage(){	    
//		System.out.print("Enter message followed by a # symbol ");
//		
//		/* code to get input */
//		
//		// Convert the string to MessageChar array
//	    char[] messageChar = message.toCharArray();
//	    
//	    // Takes messageChar [] and makes an ArrayList of Nodes
//		ArrayList<Node> myNodes = establishNodes(messageChar);
//		
//		// Takes an ArrayList of Nodes and makes a PriorityQueue of Nodes 
//		// that is ordered by the each Nodes count instance variable from 
//		// largest to smallest.
//		PriorityQueue<Node> sortedNodes = new PriorityQueue<Node> (myNodes);
//		
//		// Takes a Ordered Priority Queue and creates a Huffman structure Node
//		Node huffmanRoot = genHuffman(sortedNodes);
//		
//		// Takes a Node that is the root of a Huffman structure and and an empty
//		// string and creates a hash map that will be the key for compressing 
//		// and uncompressing a message
//		makeKey(huffmanRoot, key);
//	}
  	///////////////////////End of Function newMessage////////////////////////////

/*****************************************************************************
 * 
 *****************************************************************************
 */	public static char getChar() throws IOException{
		String s = getString();
		return s.charAt(0);
	}
  	///////////////////////End of Function getChar////////////////////////////
  
/*****************************************************************************
 * 
 *****************************************************************************
 */	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	///////////////////////End of Function getString////////////////////////////
 
 
/***************************************************************************** 
 * function makes the key to compress and uncompress the message. The key
 * is made by recursively traversing the huffman structure. When the function 
 * traverses down to a leaf of the structure it will create a key set in a hash
 * map consisting of a the letter and the string of 0's and 1's . The string of 
 * 0's and 1's was made during the traversal by adding a 0 for every left path 
 * taken and a 1 for every right path).
 *	* Requires Parameter: A Node that is the root tree, Empty String
 *****************************************************************************
 */	public static void makeKey(Node myNode, String key){	
		if (myNode.getLeftChild() == null){
			System.out.println("{" + myNode.getLetter() + ", " + key + "}"); // will print the Key
			myKey.put(myNode.getLetter(), key);
			deCodeKey.put(key, myNode.getLetter());
			return;
		}
		makeKey(myNode.getLeftChild(), key + "0");		
		if (myNode.getRightChild() == null){
			return;
		}
		makeKey(myNode.getRightChild(), key + "1");
	}
 	///////////////////////End of Function makeKey////////////////////////////

/*****************************************************************************
 * Function establishNodes: This function will iterate through the given char 
 * array finding every type of letter that occurs. Once a new letter type is 
 * found the char array is iterated through again while keeping a running count 
 * of every occurrence of that letter. The letter and count are then sent in a 
 * parameters to a node constructor. The node is then added to an ArrayList. 
 *	* Requires Parameter: char array 
 *	* Returns: ArrayList of Nodes.
 *****************************************************************************
 */	public static ArrayList<Node> establishNodes(char [] messageChar){
		ArrayList<Node> nodeArray = new ArrayList<Node>();
		Node myNode;
		for (int i = 0; i < messageChar.length; i++){
			int count = 0;
			int j = 0;
			boolean found = false;			
			for(j = 0; j < nodeArray.size(); j++){
				if (messageChar[i] == nodeArray.get(j).getLetter()){
					found = true;
				}
			}			
			if (found == false){
				for(j = 0; j < messageChar.length; j++ ){
					if (messageChar[i] == messageChar[j]){
						count ++;
					}	
				}
				myNode = new Node (messageChar[i], count);
				nodeArray.add(myNode);
			}
		}
		return nodeArray;
	}
 	///////////////////End of Function establishNodes/////////////////////////
	
/*****************************************************************************
 * Function genHuffman: This function removes nodes from the given PriorityQueue
 * of nodes two at a time and sends them in to new node constructor. 
 * These nodes will then become the left and right children of the new node 
 * and the new nodes count will be the sum of the two given nodes count.
 * This action will repeat until there are only two nodes remaining. The final
 * two nodes will be sent to the node constructor just as the nodes before it
 * but this new node will then be returned by the function.
 * 	* Requires Parameter: PriorityQueue of Nodes
 * 	* Returns: A single Node
 *****************************************************************************
 */	public static Node genHuffman(PriorityQueue<Node> sortedList){
		while (sortedList.size() > 2){			
			sortedList.add (new Node (sortedList.poll(), sortedList.poll()));
		}
		if (sortedList.size() == 1){
			return new Node(sortedList.poll());
		}
		return new Node(sortedList.poll(), sortedList.poll());
	}
 	///////////////////End of Function genHuffman/////////////////////////////	
 
 /*****************************************************************************
  * Function printCompressedMessage: This Function uses the hash map key to 
  * make a string of 0's and 1's that represent each character in the given 
  * message [] 
  * * Requires Parameter: char []
  *****************************************************************************
  */public static String printCompressedMessage(char [] myArry){
		String myString = "";
		for (char i : myArry){
			myString = myString + myKey.get(i);
		}
		return myString;
	}	
 	////////////////End of Function printCompressedMessage/////////////////////
 
 /*****************************************************************************
  * Function printUnCompMessage: This Function uses the hash map key to 
  * uncompress the string of 0's and 1's that represent each character back 
  * to characters
  * * Requires Parameter: String
  * * Returns: char []
  *****************************************************************************
  */public static String printUnCompMessage(char [] myArry){
	  String frontString = "";
	  String reconString = "";
	  String compString = printCompressedMessage(myArry);
	  String remainingString = compString;
	  while (remainingString.length()>0){
		  frontString = frontString + remainingString.substring(0,1);
		  remainingString = remainingString.substring(1);		   
		  if (deCodeKey.get(frontString) != null){
			  reconString = reconString + deCodeKey.get(frontString);
			  frontString = "";
		  }
	  
	  }
	  return reconString;
  }

 	////////////////End of Function printUnCompMessage/////////////////////////
 
 /*****************************************************************************
  * Function printHuffmanStruct: This Function prints the tree that is the 
  * Huffman structure.
  * * Requires Parameter: huffmanRoot Node
  * * Returns: series of strings.
  *****************************************************************************
  */
 	/////////////////End of Function printHuffmanStruct////////////////////////
}