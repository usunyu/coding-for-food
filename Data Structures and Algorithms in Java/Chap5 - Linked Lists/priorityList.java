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

class PriorityList {
	private Link first;

	public PriorityList() { 
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

	public double remove() {
		Link temp = first;
   		first = first.next;
   		return temp.dData;
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

class PriorityListApp {
	public static void main(String[] args) {                            
		PriorityList thePriorityList = new PriorityList();	// create new list
		thePriorityList.insert(20);    // insert 2 items
		thePriorityList.insert(40);
		thePriorityList.insert(10);    // insert 3 more items
		thePriorityList.insert(30);
		thePriorityList.insert(50);
		while(!thePriorityList.isEmpty()) {
			double value = thePriorityList.remove();
			System.out.print(value + " ");
		}
		System.out.println();
	}  // end main()
}  // end class SortedListApp





