// hash.java
// demonstrates hash table with linear probing
// to run this program: C:>java HashTableApp

import java.io.*;	// for I/O
import java.util.*;		// for Stack class
import java.lang.Integer;	// for parseInt()

class DataItem {	// (could have more data)
	public int iData;	// data item key
	public DataItem(int ii) { // constructor
		iData = ii;
	}	// end class DataItem
}

class HashTable {
	DataItem[] hashArray;	// array holds hash table
	int arraySize;
	DataItem nonItem;		// for deleted items
	int itemCount;

	public HashTable(int size) {	// constructor
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1);   // deleted item key is -1
		itemCount = 0;
	}

	public void displayTable() {
		System.out.print("Table: ");
		for(int j=0; j<arraySize; j++) {
			if(hashArray[j] != null)
				System.out.print(hashArray[j].iData+ " ");
			else
				System.out.print("** ");
		}
		System.out.println("");
	}

	public int hashFunc(int key) {
		return key % arraySize;       // hash function
	}

	public void rehash() {
		int newArraySize = getPrime(arraySize * 2);
		DataItem[] newHashArray = new DataItem[newArraySize];
		DataItem[] oldHashArray = hashArray;
		int oldArraySize = arraySize;
		hashArray = newHashArray;
		arraySize = newArraySize;
		itemCount = 0;
		for(int i = 0; i < oldArraySize; i++) {
			if(oldHashArray[i] != null && oldHashArray[i].iData != -1)
				insert(oldHashArray[i]);
		}
	}

	private int getPrime(int min) {	// return 1st prime > min
		int i = min + 1;
		while(true) {
			if(isPrime(i)) {
				return i;
			}
			i++;
		}
	}

	private boolean isPrime(int n) {
		for(int i = 2; (i * i <= n); i++)
			if(n % i == 0)
				return false;
		return true;
	}

	public void insert(DataItem item) {	// insert a DataItem
		if((double)itemCount / arraySize >= 0.5)
			rehash();
		// (assumes table not full)
		int key = item.iData;         // extract key
		int hashVal = hashFunc(key);  // hash the key
		// until empty cell or -1,
		int step = 1;
		while(hashArray[hashVal] != null && hashArray[hashVal].iData != -1) {
			// ++hashVal;	// go to next cell
			int doubleStep = step * step;
			hashVal += doubleStep;
			hashVal %= arraySize;	// wraparound if necessary
			step++;
		}
		hashArray[hashVal] = item;	// insert item
		itemCount++;
	}	// end insert()

	public DataItem delete(int key) {  // delete a DataItem
		int hashVal = hashFunc(key);  // hash the key
		while(hashArray[hashVal] != null) { // until empty cell,
			// found the key?
			if(hashArray[hashVal].iData == key) {
				DataItem temp = hashArray[hashVal]; // save item
				hashArray[hashVal] = nonItem;       // delete item
				itemCount--;
				return temp;	// return item
			}
			++hashVal;	// go to next cell
			hashVal %= arraySize;	// wraparound if necessary
		}
		return null;	// can't find item
	}  // end delete()

	public DataItem find(int key) {   // find item with key
		int hashVal = hashFunc(key);  // hash the key
		// until empty cell,
		while(hashArray[hashVal] != null) {
			// found the key?
			if(hashArray[hashVal].iData == key)
				return hashArray[hashVal];   // yes, return item
			++hashVal;	// go to next cell
			hashVal %= arraySize;	// wraparound if necessary
		}
		return null;	// can't find item
	}
}	// end class HashTable

class HashTableApp {

	public static void main(String[] args) throws IOException {
		DataItem aDataItem;
		int aKey, size, n, keysPerCell;
		// get sizes
		putText("Enter size of hash table: ");
		size = getInt();
		putText("Enter initial number of items: ");
		n = getInt();
		keysPerCell = 10;
		// make table
		HashTable theHashTable = new HashTable(size);
		for(int j=0; j<n; j++) { // insert data
			aKey = (int)(java.lang.Math.random() * keysPerCell * size);
			aDataItem = new DataItem(aKey);
			theHashTable.insert(aDataItem);
		}
		while(true) {	// interact with user
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
					aDataItem = new DataItem(aKey);
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
			}	// end switch
		}	// end while
	}	// end main()

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
}	// end class HashTableApp


