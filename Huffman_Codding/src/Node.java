class Node implements Comparable<Node>{
	public char letter; // data item (key)
	public int count; // data item
	public Node leftChild; // this node's left child
	public Node rightChild; // this node's right child
	
	//This is used for leaf nodes
	public Node(char myLetter, int myCount){
		letter = myLetter;
		count = myCount;
		leftChild = null;
		rightChild = null;
	}
	
	//This is used for parent nodes 
	public Node(Node left, Node right){
		leftChild = left;
		rightChild = right;
		count = left.getCount() + right.getCount();
	}
	
	public void setLeftChild(Node left){
		leftChild = left;
	}
	
	public void displayNode(){	
		System.out.print('{');
		System.out.print(letter);
		System.out.print(", ");
		System.out.print(count);
		System.out.print("} ");
	}
	
	public char getLetter(){
		 return letter;
	}
	
	public int getCount(){
		return count;
	} 
	
	public Node getLeftChild(){
		return leftChild;
	}
	
	public Node getRightChild(){
		return rightChild;
	}
	 
	@Override
	public int compareTo(Node o) { 
		return this.count - o.count;
	}
} 
