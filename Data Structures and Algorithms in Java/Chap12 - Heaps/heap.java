// heap.java
// demonstrates heaps
// to run this program: C>java HeapApp
import java.io.*;                 // for I/O
import java.lang.Integer;         // for parseInt()

class Node {
	public int iData;	// data item key
	public Node(int key) {	// constructor
		iData = key;
	}
} // end class Node

class Heap {
	private Node[] heapArray;
	private int maxSize;           // size of array
	private int currentSize;       // number of nodes in array

	public Heap(int mx) {			// constructor
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize];  // create array
	}

	public boolean isEmpty() {
		return currentSize==0;
	}

	public boolean insert(int key) {
		if(currentSize==maxSize)
			return false;
		Node newNode = new Node(key);
		heapArray[currentSize] = newNode;
		trickleUp(currentSize++);
		return true;
	}  // end insert()

	public void trickleUp(int index) {
		int parent = (index - 1) / 2;
		Node bottom = heapArray[index];

		while (index > 0 && bottom.iData > heapArray[parent].iData) {
			heapArray[index] = heapArray[parent];  // move it down
			index = parent;
			parent = (parent - 1) / 2;
		}	// end while

		heapArray[index] = bottom;
	}	// end trickleUp()

	public Node remove() {		// delete item with max key
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}	// end remove()

	public void trickleDown(int index) {
		int largerChild;
		Node top = heapArray[index];	// save root
		while(index < currentSize/2) {	// while node has at least one child,
			int leftChild = 2*index+1;
			int rightChild = leftChild+1;
			// find larger child
			if(rightChild < currentSize && 
			heapArray[leftChild].iData < heapArray[rightChild].iData) // (rightChild exists?)
				largerChild = rightChild;
			else
				largerChild = leftChild;
			// top >= largerChild?
			if(top.iData >= heapArray[largerChild].iData)
				break;
			heapArray[index] = heapArray[largerChild];	// shift child up
			index = largerChild;		// go down
		}	// end while
		heapArray[index] = top;		// root to index
	}

	public boolean change(int index, int newValue) {
		if(index<0 || index>=currentSize)
			return false;
		int oldValue = heapArray[index].iData;  // remember old
		heapArray[index].iData = newValue;  // change to new
		if(oldValue < newValue)	// if raised,
			trickleUp(index);	// trickle it up
		else					// if lowered,
			trickleDown(index);	// trickle it down
		return true;
	}

	public void displayHeap() {
		System.out.print("heapArray: ");    // array format
		for(int m=0; m<currentSize; m++)
			if(heapArray[m] != null)
				System.out.print( heapArray[m].iData + " ");
			else
				System.out.print( "-- ");
		System.out.println();
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
}	// end class Heap

class HeapApp {
	public static void main(String[] args) throws IOException {
		int value, value2;
		Heap theHeap = new Heap(31);  // make a Heap; max size 31
		boolean success;
		// insert 10 items
		theHeap.insert(70);
		theHeap.insert(40);
		theHeap.insert(50);
		theHeap.insert(20);
		theHeap.insert(60);
		theHeap.insert(100);
		theHeap.insert(80);
		theHeap.insert(30);
		theHeap.insert(10);
		theHeap.insert(90);
		while(true) {				// until [Ctrl]-[C]
			putText("Enter first letter of ");
			putText("show, insert, remove, change: ");
			int choice = getChar();
			switch(choice) {
				case 's':					// show
					theHeap.displayHeap();
					break;
				case 'i':					// insert
					putText("Enter value to insert: ");
					value = getInt();
					success = theHeap.insert(value);
					if( !success )
						putText("Can't insert; heap is full" + '\n');
					break;
				case 'r':					// remove
					if( !theHeap.isEmpty() )
						theHeap.remove();
					else
						putText("Can't remove; heap is empty" + '\n');
					break;
				case 'c':					// change
					putText("Enter index of item: ");
					value = getInt();
					putText("Enter new priority: ");
					value2 = getInt();
					success = theHeap.change(value, value2);
					if( !success )
					putText("Can't change; invalid index" + '\n');
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
}	// end class HeapApp







