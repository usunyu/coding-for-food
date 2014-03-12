/*
http://www.geeksforgeeks.org/trie-insert-and-search/
http://www.geeksforgeeks.org/trie-delete/
*/

import java.util.HashMap;
import java.util.ArrayList;

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

	@Override
	public String toString() {
		return "TrieNode [terminates=" + terminates + ", value=" + value + "]";
	}
}

class Trie {
	TrieNode root;

	public Trie(String[] list) {
		root = new TrieNode();
		for(String str : list) {
			insert(str);
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

	public void insert(String word) {
		root.insert(word);
	}

	private ArrayList<TrieNode> getTrieNodePath(String word) {
		ArrayList<TrieNode> path = new ArrayList<TrieNode>();
		TrieNode node = root;
		path.add(root);
		for(int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if(node.childrens.containsKey(ch)) {
				node = node.childrens.get(ch);
				path.add(node);
			}
			else {
				return null;
			}
		}
		return path;
	}

	public void delete(String word) {
		ArrayList<TrieNode> path = getTrieNodePath(word);
		// 1. Key may not be there in trie. Delete operation should not modify trie.
		// 2. Key present as unique key (no part of key contains another key (prefix), 
		//    nor the key itself is prefix of another key in trie). Delete all the nodes.
		// 3. Key is prefix key of another long key in trie. Unmark the leaf node.
		// 4. Key present in trie, having atleast one other key as prefix key. 
		//    Delete nodes from end of key until first leaf node of longest prefix key.
		if(path != null && path.size() > 1) {
			TrieNode last = path.get(path.size() - 1);
		 	if(last.terminates) {	// it in the trie
				if(last.childrens.size() > 0) // it is prefix of others
					last.terminates = false;
				else {
					TrieNode node;
					char ch = last.value;
					for(int i = path.size() - 2; i >= 0; i--) {	// backward
						node = path.get(i);
						if(!node.terminates) {
							node.childrens.remove(ch);
							ch = node.value;
						}
					}
				}
			}
		}
	}
}

class Main {
	public static void main(String[] args) {
		String[] words = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
		Trie trie = new Trie(words);
		// search
		System.out.println("the\t:\t" + trie.search("the"));
		System.out.println("by\t:\t" + trie.search("by"));
		System.out.println("test\t:\t" + trie.search("test"));
		// delete
		trie.delete("the");
		// search
		System.out.println("the\t:\t" + trie.search("the"));
	}
}