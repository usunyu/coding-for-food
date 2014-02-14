public class binary_tree
{
	public class NodeStack
	{
		private Node current;


		public void push(Node node)
		{

		}
		public Node pop()
		{
			return current;
		}
		public boolean isEmpty()
		{
			return false;
		}
	}

	public class Node
	{
		private Node left;
		private Node right;
		private int value;

		public Node( Node left, Node right, int value )
		{
			this.left = left;
			this.right = right;
			this.value = value;
		}

		public Node getLeft()
		{
			return left;
		}
		public Node getRight()
		{
			return right;
		}
		public int getValue()
		{
			return value;
		}

		public Node findNode(Node root, int value)
		{
			while(root != null)
			{
				int curval = root.getValue();
				if(curval == value)
					break;
				if(curval < value)
					root = root.getRight();
				else
					root = root.getLeft();
			}
			return root;
		}

		public Node findNodeRec(Node root, int value)
		{
			if(root == null)
				return null;
			int curval = root.getValue();
			if(curval == value)
				return root;
			if(curval < value)
				return findNodeRec(root.getRight(), value);
			else
				return findNodeRec(root.getLeft(), value);
		}

		public void printValue()
		{
			System.out.println(getValue());
		}

		public void preorderTraversal(Node root)
		{
			if(root == null)
				return;
			root.printValue();
			preorderTraversal(root.getLeft());
			preorderTraversal(root.getRight());
		}

		public void preorderTraversalIteration(Node root)
		{
			NodeStack stack = new NodeStack();
			stack.push(root);
			while(stack.isEmpty())
			{
				Node cur = stack.pop();
				cur.printValue();
				if(cur.getRight() != null)
					stack.push(cur.getRight());
				if(cur.getLeft() != null)
					stack.push(cur.getLeft());
			}
		}

		public void inorderTraversal(Node root)
		{
			if(root == null)
				return;
			inorderTraversal(root.getLeft());
			root.printValue();
			inorderTraversal(root.getRight());
		}

		public void postorderTraversal(Node root)
		{
			if(root == null)
				return;
			postorderTraversal(root.getLeft());
			postorderTraversal(root.getRight());
			root.printValue();
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Binary Tree.");
	}
}
