/*
http://www.geeksforgeeks.org/pattern-searching-set-8-suffix-tree-introduction/
*/

import java.util.HashMap;

// this suffix tree is not compressed
class SuffixTreeNode {
	char value;
	boolean terminates = false;
	HashMap<Character, SuffixTreeNode> childrens;

	public SuffixTreeNode() {
		childrens = new HashMap<Character, SuffixTreeNode>();
	}

	public SuffixTreeNode(char value) {
		this();
		this.value = value;
	}
	
	public void insert(String text) {
		if(text != null && text.length() > 0) {
			char ch = text.charAt(0);
			SuffixTreeNode node;
			if(childrens.containsKey(ch)) {
				node = childrens.get(ch);
			}
			else {
				node = new SuffixTreeNode(ch);
				childrens.put(ch, node);
			}
			if(text.length() > 1)
				node.insert(text.substring(1));
			else {
				node.terminates = true;
			}
		}
	}
}

class SuffixTree {
	SuffixTreeNode root = new SuffixTreeNode();

	public SuffixTree(String text) {
		for(int i = 0; i < text.length(); i++) {
			root.insert(text.substring(i));
		}
	}

	public boolean search(String text) {
		if(text == null || text.length() == 0) 
			return true;
		SuffixTreeNode node = root;
		for(int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if(node.childrens.containsKey(ch)) {
				node = node.childrens.get(ch);
			}
			else {
				return false;
			}
		}
		return node.terminates;
	}
}

class Main {
	public static void main(String[] args) {
		SuffixTree suffixTree = new SuffixTree("banana");
		System.out.println("na\t:\t" + suffixTree.search("na"));
		System.out.println("anan\t:\t" + suffixTree.search("anan"));
		System.out.println("test\t:\t" + suffixTree.search("test"));
	}
}