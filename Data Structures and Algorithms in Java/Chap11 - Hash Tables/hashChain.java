// hashChain.java
// demonstrates hash table with separate chaining
// to run this program: C:>java HashChainApp
import java.io.*;
import java.util.*;
import java.lang.Integer;

class Link {
	public int iData;
	public Link next;

	public Link(int it) {
		iData= it;
	}

	public void displayLink() {
		System.out.print(iData + " ");
	}
}

class SortedList {
	private Link first;

	public void SortedList() {
		first = null;
	}

	public void insert(Link theLink) {
		int key = theLink.iData;
		Link previous = null;
		Link current = first;

		while(current != null && key > current.iData) {
			previous = current;
			current = current.next;
		}

		if(previous==null)
			first = theLink;
		else
			previous.next = theLink;
		theLink.next = current;
	}

	public void delete(int key) {
		Link previous = null;
		Link current = first;

		while(current != null && key <= current.iData) {
			if(key == current.iData) {
				if(previous == null)
					first = first.next;
				else
					previous.next = current.next;
				break;
			}
			previous = current;
			current = current.next;
		}

		// if(previous == null)
		// 	first = first.next;
		// else
		// 	previous.next = current.next;
	}

	public Link find(int key) {
		Link current = first;

		while(current != null && key <= current.iData) {
			if(key == current.iData)
				return current;
			current = current.next;
		}

		return null;
	}

	public void displayList() {
		System.out.print("List (first-->last): ");
		Link current = first;
		while(current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}

class HashTable {
	private SortedList[] hashArray;
	private int arraySize;

	public HashTable(int size) {
		arraySize = size;
		hashArray = new SortedList[arraySize];
		for(int j=0; j<arraySize; j++) {
			hashArray[j] = new SortedList();
		}
	}

	public void displayTable() {
		for(int j=0; j<arraySize; j++) {
			System.out.print(j + ". ");
			hashArray[j].displayList();
		}
	}

	public int hashFunc(int key) {
		return key % arraySize;
	}

	public void insert(Link theLink) {
		int key = theLink.iData;
		int hashVal = hashFunc(key);
		hashArray[hashVal].insert(theLink);
	}

	public void delete(int key) {
		int hashVal = hashFunc(key);
		hashArray[hashVal].delete(key);
	}

	public Link find(int key) {
		int hashVal = hashFunc(key);
		Link theLink = hashArray[hashVal].find(key);
		return theLink;
	}
}

class HashChainApp {
	public static void main(String[] args) throws IOException {
		int aKey;
		Link aDataItem;
		int size, n, keysPerCell = 100;

		putText("Enter size of hash table: ");
		size = getInt();
		putText("Enter initial number of items: ");
		n = getInt();

		HashTable theHashTable = new HashTable(size);
		for(int j=0; j<n; j++) {
			aKey = (int)(java.lang.Math.random() * keysPerCell * size);
			aDataItem = new Link(aKey);
			theHashTable.insert(aDataItem);
		}

		while(true) {
			putText("Enter first letter of ");
			putText("show, insert, delete, or find: ");
			char choice = getChar();
			switch(choice) {
				case 's':
					theHashTable.displayTable();
					break;
				case 'i':
					putText("Enter key value to insert: ");
					aKey = getInt();
					aDataItem = new Link(aKey);
					theHashTable.insert(aDataItem);
					break;
				case 'd':
					putText("Enter key value to delete: ");
					aKey = getInt();
					theHashTable.delete(aKey);
					break;
				case 'f':
					putText("Enter key value to find: ");
					aKey = getInt();
					aDataItem = theHashTable.find(aKey);
					if(aDataItem != null)
						System.out.println("Found " + aKey);
					else
						System.out.println("Could not find " + aKey);
					break;
				default:
					putText("Invalid entry\n");
			}
		}
	}

	public static void putText(String s) {
		System.out.print(s);
		System.out.flush();
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}



