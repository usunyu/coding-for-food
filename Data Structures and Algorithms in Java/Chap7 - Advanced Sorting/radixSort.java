import java.io.*;

class Link {
	public int data;
	public Link next;

	public Link(int value) {
		data = value;
		next = null;
	}
}

class LinkList {
	private Link first;

	public LinkList() {
		first = null;
	}

	public Link getFirst() {
		return first;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insert(int value) {
		Link theNewLink = new Link(value);
		if(first == null)
			first = theNewLink;
		else {
			Link temp = first;
			while(temp.next != null)
				temp = temp.next;
			temp.next = theNewLink;
		}
	}
}

class ArrayRad {
	private int[] theArray;
	private int nElems;

	public ArrayRad(int max) {
		theArray = new int[max];
		nElems = 0;
	}

	public void insert(int value) {
		if(nElems <= theArray.length)
			theArray[nElems++] = value;         // insert it
	}

	public int size() { return nElems; }

	private void initialLists(LinkList[] lists) {
		for(int i = 0; i < lists.length; i++)
			lists[i] = new LinkList();
	}

	public void radixSort(int digit) {
		for(int i = 0; i < digit; i++) {
			LinkList[] lists = new LinkList[10];
			initialLists(lists);
			int div = (int)java.lang.Math.pow(10,i);
			// insert to list
			for(int j = 0; j < nElems; j++) {
				int value = theArray[j] / div;
				int index = value % 10;
				lists[index].insert(theArray[j]);
			}
			// store back to array
			int k = 0;
			for(int j = 0; j < 10; j++) {
				Link temp = lists[j].getFirst();
				while(temp != null) {
					theArray[k++] = temp.data;
					temp = temp.next;
				}
			}
		}
	}

	public void display() {
		for(int j=0; j<nElems; j++)       // for each element,
			System.out.print(theArray[j] + " ");  // display it
		System.out.println("");
	}
}

class RadixSortApp {
	public static void main(String[] args) {
		int maxSize = 16;             // array size
		ArrayRad arr;                 // reference to array
		arr = new ArrayRad(maxSize);  // create the array
		for(int j=0; j<maxSize; j++) {  // fill array with   
			int n = (int)(java.lang.Math.random()*999); // random numbers
			arr.insert(n);
		}
		arr.display();                // display unsorted array

		arr.radixSort(3);
		arr.display();

	}
}

