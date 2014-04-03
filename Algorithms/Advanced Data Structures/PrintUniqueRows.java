/*
Given a binary matrix, print all unique rows of the given matrix.

Input:
	{0, 1, 0, 0, 1}
    {1, 0, 1, 1, 0}
    {0, 1, 0, 0, 1}
    {1, 1, 1, 0, 0}

Output:
	0 1 0 0 1 
	1 0 1 1 0 
	1 1 1 0 0 

http://www.geeksforgeeks.org/print-unique-rows/

Method 3 (Use Trie data structure)
Since the matrix is boolean, a variant of Trie data structure can be used where each node will be 
having two children one for 0 and other for 1. Insert each row in the Trie. If the row is already there, 
don’t print the row. If row is not there in Trie, insert it in Trie and print it.
*/

class TrieNode {
	int value;
	boolean terminal;
	TrieNode[] childrens;

	public TrieNode() {
		childrens = new TrieNode[2];	// Only two children needed for 0 and 1
	}

	public TrieNode(int value) {
		this();
		this.value = value;
	}

	public boolean insert(int[] array) {
		return insert(array, 0);
	}

	private boolean insert(int[] array, int index) {
		int val = array[index];
		TrieNode node;
		if(childrens[val] != null) {
			node = childrens[val];
		}
		else {
			node = new TrieNode(val);
			childrens[val] = node;
		}
		if(index < array.length - 1) {
			return node.insert(array, index + 1);
		}
		else {
			if(node.terminal) {	// its already there
				return false;
			}
			else {
				node.terminal = true;
				return true;
			}
		}
	}

	@Override
	public String toString() {
		return "TrieNode [value=" + value + ", terminal=" + terminal + "]";
	}
}

class Trie {
	TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public boolean insert(int[] array) {
		return root.insert(array);
	}
}

class Main {
	public static void printArray(int[] array) {
		for(int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void findUniqueRows(int matrix[][]) {
		Trie trie = new Trie();
		for(int i = 0; i < matrix.length; i++) {
			if(trie.insert(matrix[i])) {
				printArray(matrix[i]);
			}
		}
	}

	public static void main(String[] args) {
		int matrix[][] = {
			{0, 1, 0, 0, 1},
			{1, 0, 1, 1, 0},
			{0, 1, 0, 0, 1},
			{1, 0, 1, 0, 0}
		};
		findUniqueRows(matrix);
	}
}