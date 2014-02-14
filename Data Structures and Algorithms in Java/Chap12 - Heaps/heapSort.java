// heapSort.java
// demonstrates heap sort
// to run this program: C>java HeapSortApp
import java.io.*;                 // for I/O
import java.lang.Integer;         // for parseInt()

class Node {
	public int iData;
	public Node(int key) {
		iData = key;
	}
}

class Heap {
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;

	public Heap(int mx) {
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize];
	}

	public Node remove() {
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}

	public void trickleDown(int index) {
		int largerChild;
		Node top = heapArray[index];
		while(index < currentSize / 2) {
			int leftChild = index * 2 + 1;
			int rightChild = leftChild + 1;
			if(rightChild < currentSize && heapArray[rightChild].iData > heapArray[leftChild].iData)
				largerChild = rightChild;
			else
				largerChild = leftChild;
			if(top.iData >= heapArray[largerChild].iData)
				break;
			heapArray[index] = heapArray[largerChild];
			index = largerChild;
		}
		heapArray[index] = top;
	}

	public void displayHeap() {
		// heap format
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0;		// current item
		String dots = "...................................";
		System.out.println(dots+dots);	// dotted top line

		while(currentSize > 0) {		// for each heap item
			if(column == 0)			// first item in row?
				for(int k=0; k<nBlanks; k++)	// preceding blanks
					System.out.print(' ');

			System.out.print(heapArray[j].iData);	// display item

			if(++j == currentSize)	// done?
				break;

			if(++column==itemsPerRow) {			// end of row?
				nBlanks /= 2;				// half the blanks
				itemsPerRow *= 2;			// twice the items
				column = 0;					// start over on
				System.out.println();		//    new row
			}
			else							// next item on row
				for(int k=0; k<nBlanks*2-2; k++)
					System.out.print(' ');		// interim blanks
		}	// endwhile
		System.out.println("\n"+dots+dots); // dotted bottom line
	}	// end displayHeap()

	public void displayArray() {
		for(int j=0; j<maxSize; j++)
			System.out.print(heapArray[j].iData + " ");
		System.out.println("");
	}

	public void insertAt(int index, Node newNode) {
		heapArray[index] = newNode;
	}

	public void incrementSize() {
		currentSize++;
	}
}

class HeapSortApp {
	public static void main(String[] args) throws IOException {
		int size, j;
		System.out.print("Enter number of items: ");
		size = getInt();
		Heap theHeap = new Heap(size);
		for(j=0; j<size; j++) {	// fill array with random nodes
			int random = (int)(java.lang.Math.random()*100);
			Node newNode = new Node(random);
			theHeap.insertAt(j, newNode);
			theHeap.incrementSize();
		}

		System.out.print("Random: ");
		theHeap.displayArray();  // display random array

		for(j=size/2-1; j>=0; j--)  // make random array into heap
			theHeap.trickleDown(j);

		System.out.print("Heap: ");
		theHeap.displayArray();	// dislay heap array
		theHeap.displayHeap();	// dislay heap heap

		for(j=size-1; j>=0; j--) {// store at array end 
			Node biggestNode = theHeap.remove();
			theHeap.insertAt(j, biggestNode);
		}

		System.out.print("Sorted: ");
		theHeap.displayArray();     // display sorted array
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




