/*
Design and implement a hash table which uses chaining (linked lists) to handle collisions.
*/

import java.util.*;

class MyHashNode<K, V> {
	K key;
	V value;
	MyHashNode<K, V> next;

	public MyHashNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
}

class MyHashTable<K, V> {
	ArrayList<MyHashNode<K, V>> hashValueArray;
	int MAX = 1000;

	public MyHashTable() {
		hashValueArray = new ArrayList<MyHashNode<K, V>>(MAX);
		for(int i = 0; i < MAX; i++) {
			MyHashNode<K, V> nullNode = new MyHashNode<K, V>(null, null);
			hashValueArray.add(nullNode);
		}
	}

	public int computeHashKey(K key) {
		return (Integer)key % MAX;
	}

	public void put(K key, V value) {
		int hashKey = computeHashKey(key);
		MyHashNode<K, V> node = hashValueArray.get(hashKey);
		if(node.key == null) {	// no collision
			node.key = key;
			node.value = value;
		}
		else {	// has collision
			MyHashNode<K, V> previous = null;
			while(node != null) {
				if(node.key.equals(key)) {	// if same key, replace
					node.value = value;
					break;
				}
				previous = node;
				node = node.next;
			}
			if(node == null) {	// no smae key
				MyHashNode<K, V> newNode = new MyHashNode<K, V>(key, value);
				previous.next = newNode;
			}
		}
	}

	public V get(K key) {
		int hashKey = computeHashKey(key);
		MyHashNode<K, V> node = hashValueArray.get(hashKey);
		if(node == null)
			return null;
		else {
			while(node != null) {
				if(node.key.equals(key))
					return node.value;
				node = node.next;
			}
			return null;
		}
	}
}

class Q8_10App {
	public static void main(String[] args) {
		MyHashTable<Integer, String> myHashTable = new MyHashTable<Integer, String>();
		myHashTable.put(1, "One");
		myHashTable.put(2, "Two");
		myHashTable.put(3, "Three");
		myHashTable.put(4, "Four");
		myHashTable.put(5, "Five");
		myHashTable.put(1, "New One");
		myHashTable.put(1002, "One thousand and two");

		System.out.println("Key: 1,\t\tValue: " + myHashTable.get(1));
		System.out.println("Key: 2,\t\tValue: " + myHashTable.get(2));
		System.out.println("Key: 3,\t\tValue: " + myHashTable.get(3));
		System.out.println("Key: 4,\t\tValue: " + myHashTable.get(4));
		System.out.println("Key: 5,\t\tValue: " + myHashTable.get(5));
		System.out.println("Key: 1002,\tValue: " + myHashTable.get(1002));
	}
}




