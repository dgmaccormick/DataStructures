/*
 * BinarySearchTree
 * Used to create a binary search tree
 * Contains methods to insert nodes into the tree, search for nodes, 
 * delete nodes, and traverse the tree.
 * 
 * @Author David MacCormick
 * March 2014
 */
public class BinarySearchTree
{
	/*
	 * Node class
	 * Used to create nodes belonging to a Binary Search Tree.
	 */
	private class Node
	{
		protected int data;
		protected Node left;
		protected Node right;
		
		public Node(int data)
		{
			this.data = data;
			left = null;
			right = null;
		}		
	}
	
	// the root Node of the binary search tree
	private Node root; 
	
	/*
	 * Function to insert a node into the Binary Search Tree
	 */
	public void insert(int data)
	{
		Node newNode = new Node(data);
		if(root == null) 
			root = newNode;
		else
		{
			Node curr = root;
			Node parent;
			while(true)
			{
				parent = curr;
				if(data < curr.data)
				{
					curr = curr.left;
					if(curr == null)
					{
						parent.left = newNode;
						return;
					}
				}
				else
				{
					curr = curr.right;
					if(curr == null)
					{
						parent.right = newNode;
						return;
					}
				}
			}	
		}		
	}
	
	/*
	 * Function to find a Node with specified data value, and return it.
	 */
	public Node search(int data)
	{		
		if(root == null){
			System.out.println("Tree is empty.");
			return null;
		}
		else
		{
			Node curr = root;
			
			while(true)
			{
				if(data < curr.data){
					curr = curr.left;
					if(curr == null){
						System.out.println("Item does not exist.");
						return null;
					}
				}
				else if(data == curr.data){					
					System.out.println("Item found.");
					return curr;			
				}
				else{
					curr = curr.right;
					if(curr == null){
						System.out.println("Item does not exist.");
						return null;
					}
				}	
			}
			
		}
		
	}
	
	/* 
	 * Function to remove the node with specified data value, and return the node that was removed. 
	 * If node does not exist in the tree, the function will return null.
	 */
	public Node remove(int data)
	{
		Node curr = root;
		Node parent = null;
		boolean isLeftChild = true;
		while(curr.data != data)
		{
			parent = curr;
			if(data < curr.data)
			{
				curr = curr.left;
				isLeftChild = true;
			}
			else if (data > curr.data)
			{
				curr = curr.right;
				isLeftChild = false;
			}
			else if(curr == null)
			{
				return null;
			}
		}
		
		if(curr.left == null && curr.right == null){
			if(curr == root)
				root = null;
			else
			{
				if(isLeftChild) 
					parent.left = null;
				else 
					parent.right = null;
			}
		}
		else if(curr.left == null){
			if(isLeftChild) 
				parent.left = curr.right;
			else 
				parent.right = curr.right;

		}
		else if(curr.right == null){
			if(isLeftChild) 
				parent.left = curr.left;
			else 
				parent.right = curr.left;
		}
		else{ // two children involved
			Node replacement = curr.right;
			Node curr1 = curr.right;
			Node curr1Parent = null;
			while(curr1 != null){
				curr1Parent = curr1;
				curr1 = curr1.left;	
			}
			curr1Parent.left = curr.left;
					
			if(isLeftChild)
			{
				parent.left = replacement;					
			}
			else
				parent.right = replacement;
		}
			
		return curr;
	}
	
	
	// print the nodes in ascending order
	public void inOrderTraversal(Node curr)
	{
		if(curr != null)
		{
			inOrderTraversal(curr.left);
			System.out.print(curr.data);
			inOrderTraversal(curr.right);
		}
	}
	public static void main(String[]args)
	{
		
		BinarySearchTree t = new BinarySearchTree();
		t.insert(7);
		t.insert(8);
		t.insert(3);
		t.insert(2);
		t.insert(5);
		t.insert(4);
		t.insert(6);
		t.insert(1);
		
		t.inOrderTraversal(t.root);
		t.search(5);
		t.remove(3);
		t.inOrderTraversal(t.root);
	}
	

}