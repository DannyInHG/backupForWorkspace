package tree;


public class Node {
	private char data;
	Node lchild,rchild;
	
	public Node()
	{
		
	}
	
	public Node(char data)
	{
		
		this.data = data;
//		lchild = new Node();
//		rchild = new Node();
		
	}

	public void displayNode()
	{
		System.out.println(" "+data+" ");
	}
	
	public char getData() {
		return data;
	}

	public void setData(char data) {
		this.data = data;
	}
}
