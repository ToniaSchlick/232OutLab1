 
package huffmancoding;

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
	
	public static void main(String[] args) throws IOException{		
	/* Will be replaced by newMessage()
	****************************************************************/
	newMessage();
	/*********************************************************************/
		
		System.out.println("Enter the corisponding letter to the action you wish to proform, ");
     
                       Scanner menu = new Scanner(System.in);
                       String choice ="";
                       boolean run =true;
                       while(run){
			System.out.println(" c - compress message, u - uncompressed message, t - tree, n - new message, or q for quit: ");
			 choice = menu.next();
			switch(choice){
			case "c":
				printCompressedMessage();
				break;
			case "u":
				printUnCompMessage();
				break;
			case "t": 
				break;
			case "n":
				newMessage();
				break;
                        case "q":
                                 run=false;
                                 break;
                            
			default:
				System.out.print("Invalid entry\n");
			
			}
		}
}
	////////////////////////////End of main///////////////////////////////////

// /*****************************************************************************
//  * Function getMessage: This Function takes in a message containing multiple 
//  * lines of text. The message will be submitted upon the input of a # symbol
//  * * Returns: series of strings.
//  *****************************************************************************
              public static void newMessage(){	    
        	System.out.print("Enter message or '#' symble to end message ");
                Scanner input = new Scanner(System.in);
                String message = "";
                
                while(input.hasNext())
                {
                 message += input.nextLine() + "\n"; 
                 if (message.contains("#"))
                 {
                     message= message.substring(0, message.length()-2);
                     break;
                 }
                }
                
                if(message.equals(""))
                {
                    System.out.println("Goodby");
                    return;
                }
               
                
                
		
	
	
//	 Convert the string to MessageChar array
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
	}
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
		return new Node(sortedList.poll(), sortedList.poll());
	}
 	///////////////////End of Function genHuffman/////////////////////////////	
 
          public static void printCompressedMessage()
          {
              
          }
 
 	////////////////End of Function printCompressedMessage/////////////////
 
         public static void  printUnCompMessage()
         {
             
         }
 

 	////////////////End of Function printUnCompMessage/////////////////////////
 

}