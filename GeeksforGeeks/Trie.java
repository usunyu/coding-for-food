/*
http://www.geeksforgeeks.org/trie-insert-and-search/
*/

import java.util.HashMap;

class TrieNode {
	boolean terminates = false;
	char value;
	HashMap<Character, TrieNode> childrens;

	public TrieNode() {
		childrens = new HashMap<Character, TrieNode>();
	}

	public TrieNode(char value) {
		this();
		this.value = value;
	}

	public void insert(String word) {
		if(word == null || word.isEmpty()) return;
		char ch = word.charAt(0);
		TrieNode node;
		if(childrens.containsKey(ch)) {
			node = childrens.get(ch);
		}
		else {
			node = new TrieNode(ch);
			childrens.put(ch, node);
		}
		if(word.length() > 1) {
			node.insert(word.substring(1));
		}
		else {
			node.terminates = true;
		}
	}
}

class Trie {
	TrieNode root;

	public Trie(String[] list) {
		root = new TrieNode();
		for(String str : list) {
			root.insert(str);
		}
	}

	public boolean search(String word) {
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if(node.childrens.containsKey(ch)) {
				node = node.childrens.get(ch);
			}
			else {
				return false;
			}
		}
		return node != null && node.terminates;
	}
}

class Main {
	public static void main(String[] args) {
		String[] words = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
		Trie trie = new Trie(words);
		System.out.println("the\t:\t" + trie.search("the"));
		System.out.println("by\t:\t" + trie.search("by"));
		System.out.println("test\t:\t" + trie.search("test"));
	}
}