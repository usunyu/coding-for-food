import java.io.*;

class Link {
	public double dData;
	public Link next;

	public Link(double d) {
		dData = d;
	}

	public void displayLink() {
		System.out.print(dData + " ");
	}
}

class SortedList {
	private Link first;

	public SortedList(Link[] linkArr) { // constructor (array as argument)
		first = null;;                     // initialize list
		for(int j=0; j<linkArr.length; j++)  // copy array
			insert( linkArr[j] );             // to list
	}

	public boolean isEmpty(){ 
		return (first==null); 
	}

	public void insert(Link k) {
		Link previous = null;
		Link current = first;

		while(current != null && k.dData > current.dData) {
			previous = current;
			current = current.next;
		}

		if(previous==null)
			first = k;
		else
			previous.next = k;
		k.next = current;
	}

	public Link remove() {
		Link temp = first;
   		first = first.next;
   		return temp;
	}
}

class ListInsertionSortApp {
	public static void main(String[] args) {
		int size = 10;
		Link[] linkArray = new Link[size];

		for(int j=0; j<size; j++) { // fill array with links
			int n = (int)(java.lang.Math.random()*99); // random number
			Link newLink = new Link(n);  // make link
			linkArray[j] = newLink;      // put in array
		}

		System.out.print("Unsorted array: ");
		for(int j=0; j<size; j++)
			System.out.print( linkArray[j].dData + " " );
		System.out.println("");

		SortedList theSortedList = new SortedList(linkArray);
		for(int j=0; j<size; j++)  // links from list to array
			linkArray[j] = theSortedList.remove();

		System.out.print("Sorted Array: ");
		for(int j=0; j<size; j++)
			System.out.print(linkArray[j].dData + " ");
		System.out.println("");
	}
}


