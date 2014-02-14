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

	public SortedList() { 
		first = null; 
	}

	public boolean isEmpty(){ 
		return (first==null); 
	}

	public void insert(double key) {
		Link newLink = new Link(key);
		Link previous = null;
		Link current = first;

		while(current != null && key > current.dData) {
			previous = current;
			current = current.next;
		}

		if(previous==null)
			first = newLink;
		else
			previous.next = newLink;
		newLink.next = current;
	}

	public Link remove() {
		Link temp = first;
   		first = first.next;
   		return temp;
	}

	public void displayList() {
		System.out.print("List (first-->last): ");
		Link current = first;
		while(current != null) {
			current.displayLink();
			current = current.next;  // move to next link
		}
        System.out.println("");
    }
}

class SortedListApp {
	public static void main(String[] args) {                            
		SortedList theSortedList = new SortedList();	// create new list
		theSortedList.insert(20);    // insert 2 items
		theSortedList.insert(40);
		theSortedList.displayList(); // display list
		theSortedList.insert(10);    // insert 3 more items
		theSortedList.insert(30);
		theSortedList.insert(50);
		theSortedList.displayList(); // display list
		theSortedList.remove();      // remove an item
		theSortedList.displayList(); // display list
	}  // end main()
}  // end class SortedListApp





