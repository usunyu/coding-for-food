import java.io.*;
import java.util.*;
import java.lang.Integer;

class DataItem {
	int iData;

	public DataItem(int ii) {
		iData = ii;
	}
}

class HashTable {
	DataItem[] hashArray;
	int arraySize;
	int base;
	DataItem nonItem;

	public HashTable(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1);
		base = 1;
		while(size > 0) {
			base *= 10;
			size /= 10;
		}
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
		int hashVal = 0;
		while(key > 0) {
			hashVal += key % base;
			key /= base;
		}
		hashVal %= arraySize;
		return hashVal;
	}

	public void insert(DataItem item) {	// insert a DataItem
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
	}	// end insert()

	public DataItem delete(int key) {  // delete a DataItem
		int hashVal = hashFunc(key);  // hash the key
		while(hashArray[hashVal] != null) { // until empty cell,
			// found the key?
			if(hashArray[hashVal].iData == key) {
				DataItem temp = hashArray[hashVal]; // save item
				hashArray[hashVal] = nonItem;       // delete item
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
}

class HashFoldApp {

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
}