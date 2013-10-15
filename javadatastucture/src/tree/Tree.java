package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tree {
	
	
	private Node root;
	
	public Tree()
	{
		root = null;
	}
	
//	public Tree(char data)
//	{
//		root = new BiTNode(data);
//	}
	
	public void createTree(Node node)
	{
		System.out.println("Enter your number : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			char readChar = (char)br.read();
			if(readChar == '#')
				node = null;
			else
			{
				node = new Node(readChar);
				if(root == null)
				{
					root = node;
				}
				createTree(node.lchild);
				createTree(node.rchild);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
/*	public void findNode(char data)
	{
		
	}
	
	public void insertNode(char data)
	{
		
	}
	
	public void deleteNode(char data)
	{
		
	}*/
	
	public void inOrderTraverse(Node node)
	{
		if(node == null)
			return;
		
		node.displayNode();
		
		inOrderTraverse(node.lchild);
		
		inOrderTraverse(node.rchild);
		
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	
	
	
}
