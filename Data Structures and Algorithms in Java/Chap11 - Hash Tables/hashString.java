import java.io.*;
import java.util.*;
import java.lang.Integer;

class DataItem {
	public String sData;

	public DataItem(String s) {
		sData = s;
	}
}

class HashTable {
	DataItem[] hashArray;
	int arraySize;
	DataItem noneItem;

	public HashTable(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
		noneItem = new DataItem(null);
	}

	public void displayTable() {
		System.out.print("Table: ");
		for(int j=0; j<arraySize; j++) {
			if(hashArray[j] != null)
				System.out.print(hashArray[j].sData+ " ");
			else
				System.out.print("** ");
		}
		System.out.println("");
	}

	public int hashFunc(String key) {
		int hashVal = 0;
		for(int i = 0; i < key.length(); i++) {
			int letter = key.charAt(i) - 96;
			hashVal = (hashVal * 26 + letter) % 26;
		}
		return hashVal;
	}

	public void insert(DataItem item) {
		String key = item.sData;
		int hashVal = hashFunc(key);

		while(hashArray[hashVal] != null && hashArray[hashVal].sData != null) {
			hashVal++;
			hashVal %= arraySize;
		}

		hashArray[hashVal] = item;
	}

	public DataItem delete(String key) {
		int hashVal = hashFunc(key);

		while(hashArray[hashVal] != null) {
			if(hashArray[hashVal].sData.equals(key)) {
				DataItem temp = hashArray[hashVal];
				hashArray[hashVal] = noneItem;
				return temp;
			}
			hashVal++;
			hashVal %= arraySize;
		}
		return null;
	}

	public DataItem find(String key) {
		int hashVal = hashFunc(key);

		while(hashArray[hashVal] != null) {
			if(hashArray[hashVal].sData.equals(key)) {
				return hashArray[hashVal];
			}
			hashVal++;
			hashVal %= arraySize;
		}
		return null;
	}
}

class HashStringApp {

	public static void main(String[] args) throws IOException {
		DataItem aDataItem;
		String aKey;
		int size;
		putText("Enter size of hash table: ");
		size = getInt();

		HashTable theHashTable = new HashTable(size);
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
					aKey = getString();
					aDataItem = new DataItem(aKey);
					theHashTable.insert(aDataItem);
					break;
				case 'd':
					putText("Enter key value to delete: ");
					aKey = getString();
					theHashTable.delete(aKey);
					break;
				case 'f':
					putText("Enter key value to find: ");
					aKey = getString();
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


