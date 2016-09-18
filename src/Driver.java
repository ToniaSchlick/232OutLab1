import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		
		// Get Message
		Scanner input = new Scanner(System.in);
	    System.out.print("Input text message: ");
	    String message = input.nextLine();
		
		// Convert the string to MessageChar array
		char[] messageChar = message.toCharArray();
		
		ArrayList<Tuple> myList = makeArray(messageChar);
		for (int i = 0; i < myList.size(); i++){
			System.out.print(myList.get(i).getLetter());
			System.out.print(myList.get(i).getCount());
		}
	}
	
	/*******************************************************************
	 * makeArray - iterates through a char array and constructs a single 
	 * tuple for each letter that has an occurrence in the. The tuple 
	 * class has two instance variables, the letter and the number of
	 * times the letter occurs in the char array. It will then add the 
	 * tuple to an arrayList.
	 * -Input Parameter: char array
	 * -Returns: ArrayList
	 * *****************************************************************
	 */
	
	public static ArrayList<Tuple> makeArray(char [] messageChar){
		ArrayList<Tuple> dataArray = new ArrayList<Tuple>();
		Tuple myTuple;
		for (int i = 0; i < messageChar.length; i++){
			int count = 0;
			int j = 0;
			boolean found = false;			
			for(j = 0; j < dataArray.size(); j++){
				if (messageChar[i] == dataArray.get(j).getLetter()){
					found = true;
				}
			}			
			if (found == false){
				for(j = 0; j < messageChar.length; j++ ){
					if (messageChar[i] == messageChar[j]){
						count ++;
					}	
				}
				myTuple = new Tuple (messageChar[i], count);
				dataArray.add(myTuple);
			}
		}
		return dataArray;
//		for (int i = 0; i < dataArray.size(); i++){
//			System.out.print(dataArray.get(i).getLetter());
//			System.out.print(dataArray.get(i).getCount());
		
	}
}
