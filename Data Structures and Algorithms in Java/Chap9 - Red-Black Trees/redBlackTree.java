/* Red-Black Rules
	1. Every node is either red or black.
	2. The root is always black.
	3. If a node is red, its children must be black (although the converse isn't necessarily true).
	4. Every path from the root to a leaf, or to a null child, must contain the same number of black nodes.
*/
/* Inserting a New Node
	<Preview>
	X is a particular node.
	P is the parent of X.
	G is the grandparent of X (the parent of P).

	On the way down the tree to find the insertion point, you perform a color flip whenever you find a black
	node with two red children (a violation of Rule 2). Sometimes the flip causes a red-red conflict (a violation
	of Rule 3). Call the red child X and the red parent P. The conflict can be fixed with a single rotation or 
	a double rotation, depending on whether X is an outside or inside grandchild of G. Following color flips 
	and rotations, you continue down to the insertion point and insert the new node.

	After you've inserted the new node X, if P is black you simply attach the new red node. If P is red, there 
	are two possibilities: X can be an outside or inside grandchild of G. You perform two color changes. If X 
	is an outside grandchild, you perform one rotation, and if it's an inside grandchild you perform two. 
	This restores the tree to a balanced state.

	<Color Flips on the Way Down>
	
	<Rotations Once the Node is Inserted>
	Three post-insertion possibilities
	1. P is black.
	2. P is red and X is an outside grandchild of G.
	3. P is red and X is an inside grandchild of G.

	Possibility 1: P Is Black
	If P is black, we get a free ride. The node we've just inserted is always red. If its parent is black, there's
	no red-to-red conflict (Rule 3), and no addition to the number of black nodes (Rule 4). Thus no color rules are 
	violated. We don't need to do anything else. The insertion is complete.

	Possibility 2: P Is Red, X Is Outside
	If P is red and X is an outside grandchild, we need a single rotation and some color changes. Let's set this up 
	with the Workshop applet so we can see what we're talking about. Start with the usual 50 at the root, and insert 
	25, 75, and 12. You'll need to do a color flip before you insert the 12.
	Now insert 6, which is X, the new node. The message on the Workshop applet says Error: parent and child both red,
	so we know we need to take some action.

	In this situation, we can take three steps to restore red-black correctness and thereby balance the tree. 
	Here are the steps:
	1. Switch the color of X's grandparent G (25 in this example).
	2. Switch the color of X's parent P (12).
	3. Rotate with X's grandparent G (25) at the top, in the direction that raises X (6). This is a right rotation in the example.

	In this example, X was an outside grandchild and a left child. There's a symmetrical situation when the X is an outside 
	grandchild but a right child. Try this by creating the tree 50, 25, 75, 87, 93 (with color flips when necessary). 
	Fix it by changing the colors of 75 and 87, and rotating left with 75 at the top. Again the tree is balanced.

	Possibility 3: P Is Red and X Is Inside
	If P is red and X is an inside grandchild, we need two rotations and some color changes. To see this one in action, 
	use the Workshop applet to create the tree 50, 25, 75, 12, 18. (Again you'll need a color flip before you insert the 12.) 
	Note that the 18 node is an inside grandchild. It and its parent are both red, so again you see the errormessage Error: parent 
	and child both red.
	Fixing this arrangement is slightly more complicated. If we try to rotate right with the grandparent node G (25) at the top, 
	as we did in Possibility 2, the inside grandchild X (18) moves across rather than up, so the tree is no more balanced than before. 
	(Try this, then rotate back, with 12 at the top, to restore it.) A different solution is needed.

	The trick when X is an inside grandchild is to perform two rotations rather than one. The first changes X from an inside grandchild
	to an outside grandchild. Now the situation is similar to Possibility 1, and we can apply the same rotation, with the grandparent
	at the top, as we did before.

	We must also recolor the nodes. We do this before doing any rotations. (This order doesn't really matter, but if we wait until 
	after the rotations to recolor the nodes, it's hard to know what to call them.) 
	The steps are:
	1. Switch the color of X's grandparent (25 in this example).
	2. Switch the color of X (not its parent; X is 18 here).
	3. Rotate with X's parent P at the top (not the grandparent; the parent is 12), in the direction that raises X 
	(a left rotation in this example).
	4. Rotate again with X's grandparent (25) at the top, in the direction that raises X (a right rotation).

	This restores the tree to red-black correctness and also balances it (as much as possible). As with Possibility 2, there is 
	an analogous case in which P is the right child of G rather than the left.

	<Rotations on the Way Down>
	There are two possibilities, corresponding to Possibility 2 and Possibility 3 during the insertion phase described above. 
	The offending node can be an outside grandchild or it can be an inside grandchild. (In the situation corresponding to 
	Possibility 1, no action is required.)

	Possibility 1: Outside Grandchild
	First we'll examine an example in which the offending node is an outside grandchild. By "offending node" we mean the child in 
	the parent-child pair that caused the red-red conflict.
	Start a new tree with the 50 node, and insert the following nodes: 25, 75, 12, 37, 6, and 18. You'll need to do color flips 
	when inserting 12 and 6.
	Now try to insert a node with the value 3. You'll be told you must flip 12 and its children 6 and 18. You push the Flip button. 
	The flip is carried out, but now the message says Error: parent and child are both red, referring to 25 and its child 12.
	The procedure used to fix this is similar to the post-insertion operation with an outside grandchild, described earlier. 
	We must perform two color switches and one rotation. So we can discuss this in the same terms we did when inserting a node, 
	we'll call the node at the top of the triangle that was flipped (which is 12 in this case) X. This looks a little odd, because 
	we're used to thinking of X as the node being inserted, and here it's not even a leaf node. However, these on-the-way-down 
	rotations can take place anywhere within the tree.
	The parent of X is P (25 in this case), and the grandparent of X—the parent of P—is G (50 in this case). 

	We follow the same set of rules we did under Possibility 2, discussed above.
	1. Switch the color of X's grandparent G (50 in this example). Ignore the message that the root must be black.
	2. Switch the color of X's parent P (25).
	3. Rotate with X's grandparent (50) at the top, in the direction that raises X (here a right rotation).

	Possibility 2: Inside Grandchild
	If X is an inside grandchild when a red-red conflict occurs on the way down, two rotations are required to set it right. 
	This situation is similar to the inside grandchild in the post- insertion phase, which we called Possibility 3.
	Click Start in the RBTree Workshop applet to begin with 50, and insert 25, 75, 12, 37, 31, and 43. You'll need color flips 
	before 12 and 31.
	Now try to insert a new node with the value 28. You'll be told it needs a color flip (at 37). But when you perform the flip, 37 
	and 25 are both red, and you get the Error: parent and child are both red message. Don't press Ins again.
	In this situation G is 50, P is 25, and X is 37

	To cure the red-red conflict, you must do the same two color changes and two rotations as in Possibility 3.
	1. Change the color of G (it's 50; ignore the message that the root must be black).
	2. Change the color of X (37).
	3. Rotate with P (25) as the top, in the direction that raises X (left in this example).
	4. Rotate with G as the top, in the direction that raises X (right in this example).

	<Implementation>
	You'll need to add a red-black field (which can be type boolean) to the Node class.

	You can adapt the insertion routine from the tree.java program in Chapter 8. On the way down to the insertion point, 
	check whether the current node is black and its two children are both red. If so, change the color of all three (unless the 
	parent is the root, which must be kept black).
	
	After a color flip, check that there are no violations of Rule 3. If so, perform the appropriate rotations: one for an outside 
	grandchild, two for an inside grandchild.

	When you reach a leaf node, insert the new node as in tree.java, making sure the node is red. Check again for red-red conflicts, 
	and perform any necessary rotations.
*/

import java.util.*;

class Node {
	int iData;			// data used as key value
	Node leftChild;		// this node's left child
	Node rightChild;	// this node's right child
	boolean isRed;		// used in Red-Black Tree

	public Node(int iData, boolean isRed) {
		this.iData = iData;
		this.isRed = isRed;
		leftChild = null;
		rightChild = null;
	}

	public void changeColor() {
		if(isRed == true)
			isRed = false;
		else
			isRed = true;
	}

	public void displayNode() {
		System.out.print(iData + "(" + (isRed ? "R" : "B") + ")\t");
	}
}

class Tree {
	private Node root;

	public Tree(int iData) {
		root = new Node(iData, false);
	}

	public Node find(int key) {	// the only data field in Tree
		Node current = root;

		while(current.iData != key) {
			if(current.iData > key)
				current = current.leftChild;
			else
				current = current.rightChild;
			if(current == null)
				return null;
		}
		return current;
	}

	private boolean isLeftChild(Node parent, Node node) {
		if(parent.leftChild == node)
			return true;
		else
			return false;
	}

	private boolean isOutsideGrandChild(Node grandParent, Node parent, Node node) {
		if(isLeftChild(parent, node) && isLeftChild(grandParent, parent))
			return true;
		else if(!isLeftChild(parent, node) && !isLeftChild(grandParent, parent))
			return true;
		else
			return false;
	}

	private void rightRotate(Node grandParent, Node parent, Node node) {
		Node rightChildOfParent = node.rightChild;
		node.rightChild = parent;
		parent.leftChild = rightChildOfParent;
		if(grandParent == null) { // parent is root
			root = node;
		}
		else {
			if(isLeftChild(grandParent, parent))
				grandParent.leftChild = node;
			else
				grandParent.rightChild = node;
		}
	}

	private void leftRotate(Node grandParent, Node parent, Node node) {
		Node leftChildOfParent = node.leftChild;
		node.leftChild = parent;
		parent.rightChild = leftChildOfParent;
		if(grandParent == null) { // parent is root
			root = node;
		}
		else {
			if(isLeftChild(grandParent, parent))
				grandParent.leftChild = node;
			else
				grandParent.rightChild = node;
		}
	}

	public void flip(Node node) {
		// not the root
		if(node != root) {
			node.changeColor();
		}
		node.leftChild.changeColor();
		node.rightChild.changeColor();
	}

	public boolean needFlip(Node node) {
		if(node.leftChild != null && node.rightChild != null) {
			if(node.isRed == false && node.leftChild.isRed == true && node.rightChild.isRed == true)
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public boolean conflict(Node n1, Node n2) {
		if(n1.isRed == true && n2.isRed == true)
			return true;
		else
			return false;
	}

	public void insert(int id) {
		// insert node is always red
		Node newNode = new Node(id, true);

		Node current = root;
		Node parent = null;
		Node grandParent = null;
		Node grandGrandParent = null;
		boolean isInserted = false;

		while(true) {
			// check whether the current node is black and its two children are both red
			if(needFlip(current)) {
				flip(current);
				// check that if there are violations of Rule 3
				if(parent != null) {
					if(conflict(parent, current)) {
						// perform the appropriate rotations
						if(grandParent != null) {
							// Possibility 1: Outside Grandchild
							if(isOutsideGrandChild(grandParent, parent, current)) {
								// Switch the color of X's grandparent G
								grandParent.changeColor();
								// Switch the color of X's parent P
								parent.changeColor();
								// Rotate with X's grandparent at the top, in the direction that raises X.
								if(isLeftChild(parent, current))
									rightRotate(grandGrandParent, grandParent, parent);
								else
									leftRotate(grandGrandParent, grandParent, parent);
							}
							else { // Possibility 2: Inside Grandchild
								// Change the color of G
								grandParent.changeColor();
								// Change the color of X
								current.changeColor();
								if(isLeftChild(parent, current)) {
									// Rotate with P as the top, in the direction that raises X
									rightRotate(grandParent, parent, current);
									// Rotate with G as the top, in the direction that raises X
									leftRotate(grandGrandParent, grandParent, current);
								}
								else {
									// Rotate with P as the top, in the direction that raises X
									leftRotate(grandParent, parent, current);
									// Rotate with G as the top, in the direction that raises X
									rightRotate(grandGrandParent, grandParent, current);
								}
							}
						}
						// start over
						current = root;
						parent = null;
						grandParent = null;
						grandGrandParent = null;
						continue;
					}
				}
			}
			grandGrandParent = grandParent;
			grandParent = parent;
			parent = current;
			if(id < current.iData) {
				current = current.leftChild;
				if(current == null) {
					parent.leftChild = newNode;
					isInserted = true;
					break;
				}
			}
			else if(id > current.iData) {
				current = current.rightChild;
				if(current == null) {
					parent.rightChild = newNode;
					isInserted = true;
					break;
				}
			}
			else {
				System.err.println("Cannot insert, the value is already exists!");
			}
			System.out.println("Debug Start............");
			levelOrderTraversal();
			System.out.println("Debug End............");
		}

		if(isInserted == true) {
			// Possibility 1: P Is Black

			if(parent.isRed == true) {
				// Possibility 2: P Is Red, X Is Outside
				if(isOutsideGrandChild(grandParent, parent, newNode)) {
					// Switch the color of X's grandparent G
					grandParent.changeColor();
					// Switch the color of X's parent P
					parent.changeColor();
					// Rotate with X's grandparent G at the top, in the direction that raises X
					if(isLeftChild(grandParent, parent))
						rightRotate(grandGrandParent, grandParent, parent);
					else
						leftRotate(grandGrandParent, grandParent, parent);
				}
				else { // Possibility 3: P Is Red and X Is Inside
					// Switch the color of X's grandparent
					grandParent.changeColor();
					// Switch the color of X
					newNode.changeColor();
					if(isLeftChild(parent, newNode)) {
						// Rotate with X's parent P at the top, in the direction that raises X
						rightRotate(grandParent, parent, newNode);
						// Rotate again with X's grandparent at the top, in the direction that raises X
						leftRotate(grandGrandParent, grandParent, newNode);
					}
					else {
						// Rotate with X's parent P at the top, in the direction that raises X
						leftRotate(grandParent, parent, newNode);
						// Rotate again with X's grandparent at the top, in the direction that raises X
						rightRotate(grandGrandParent, grandParent, newNode);
					}
				}
			}
		}
	}

	public void levelOrderTraversal() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		HashMap<Node, Integer> levelMap = new HashMap<Node, Integer>();
		levelMap.put(root, 0);
		int currentLevel = 0;
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(levelMap.get(node) > currentLevel) {
				currentLevel++;
				System.out.println();
			}
			node.displayNode();
			if(node.leftChild != null) {
				queue.add(node.leftChild);
				levelMap.put(node.leftChild, currentLevel + 1);
			}
			if(node.rightChild != null) {
				queue.add(node.rightChild);
				levelMap.put(node.rightChild, currentLevel + 1);
			}
		}
		System.out.println();
	}

	public Node minimum() {
		Node current = root, last = null;
		while(current != null) {
			last = current;
			current = current.leftChild;
		}
		return last;
	}

	public Node maximum() {
		Node current = root, last = null;
		while(current != null) {
			last = current;
			current = current.rightChild;
		}
		return last;
	}
}  // end class Tree

class RDTApp {
	public static void main(String[] args) {
		Tree theTree = new Tree(50);        // make a tree
		theTree.insert(25);        // insert nodes
		theTree.insert(75);
		theTree.insert(12);
		theTree.insert(37);
		theTree.insert(31);
		theTree.insert(43);
		theTree.insert(28);

		theTree.levelOrderTraversal();
	}  // end main()
} // end class TreeApp