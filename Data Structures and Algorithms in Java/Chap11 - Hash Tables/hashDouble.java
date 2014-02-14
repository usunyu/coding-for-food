// hashDouble.java
// demonstrates hash table with double hashing
// to run this program: C:>java HashDoubleApp
import java.io.*;	// for I/O
import java.util.*;	// for Stack class
import java.lang.Integer;	// for parseInt()

class DataItem {
	public int iData;

	public DataItem(int ii) {
		iData = ii;
	}
}

class HashTable {
	DataItem[] hashArray;
	int arraySize;
	DataItem nonItem;

	HashTable(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1);
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

	public int hashFunc1(int key) {
		return key % arraySize;
	}

	public int hashFunc2(int key) {
		// non-zero, less than array size, different from hF1
		// array size must be relatively prime to 5, 4, 3, and 2
		return 5 - key % 5;
	}

	public void insert(int key, DataItem item) {
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		while(hashArray[hashVal] != null && hashArray[hashVal].iData != -1) {
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		hashArray[hashVal] = item;
	}

	public DataItem delete(int key) {
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);

		while(hashArray[hashVal] != null) {
			if(hashArray[hashVal].iData == key) {
				DataItem temp = hashArray[hashVal];
				hashArray[hashVal] = nonItem;
				return temp;
			}
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		return null;
	}

	public DataItem find(int key) {
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);

		while(hashArray[hashVal] != null) {
			if(hashArray[hashVal].iData == key)
				return hashArray[hashVal];
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		return null;
	}
}

class HashDoubleApp {
	public static void main(String[] args) throws IOException {
		int aKey;
		DataItem aDataItem;
		int size, n;

		putText("Enter size of hash table: ");
		size = getInt();
		putText("Enter initial number of items: ");
		n = getInt();

		HashTable theHashTable = new HashTable(size);

		for(int j=0; j<n; j++) {
			aKey = (int)(java.lang.Math.random() * 2 * size);
			aDataItem = new DataItem(aKey);
			theHashTable.insert(aKey, aDataItem);
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
					aDataItem = new DataItem(aKey);
					theHashTable.insert(aKey, aDataItem);
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


