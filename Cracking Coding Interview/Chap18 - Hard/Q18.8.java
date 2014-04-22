/*
Given a string s and an array of smaller strings T, design a method to search s for each small string in T.
*/

import java.util.HashMap;

class SuffixTree {
	SuffixTreeNode root = new SuffixTreeNode();

	public SuffixTree(String text) {
		for(int i = 0; i < text.length(); i++) {
			root.insertString(text.substring(i));
		}
	}

	public boolean search(String text) {
		return root.search(text);
	}
}

class SuffixTreeNode {
	HashMap<Character, SuffixTreeNode> childrens = new HashMap<Character, SuffixTreeNode>();
	char value;

	public void insertString(String text) {
		if(text != null && text.length() != 0) {
			value = text.charAt(0);
			SuffixTreeNode children;
			if(childrens.containsKey(value)) {
				children = childrens.get(value);
			}
			else {
				children = new SuffixTreeNode();
				childrens.put(value, children);
			}
			children.insertString(text.substring(1));
		}
	}

	public boolean search(String word) {
		if(word == null || word.length() == 0)
			return true;
		else {
			char first = word.charAt(0);
			if(childrens.containsKey(first)) {
				return childrens.get(first).search(word.substring(1));
			}
			return false;
		}
	}
}

class Solution {
	public static void main(String[] args) {
		String testString = "mississippi";
        String[] stringList = {"is", "sip", "hi", "sis"};
        System.out.println(testString);
        SuffixTree tree = new SuffixTree(testString);
		for (String s : stringList) {
        	boolean conatins = tree.search(s);
        	if (conatins) {
        		System.out.println("conatins:\t" + s);
        	}
        	else {
				System.out.println("not conatins:\t" + s);
        	}
		}
	}
}