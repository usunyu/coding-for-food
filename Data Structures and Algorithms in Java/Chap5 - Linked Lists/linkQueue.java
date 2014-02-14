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

class FirstLastList {
	private Link first;
	private Link last;

	public FirstLastList() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insertFirst(double dd) {
		Link newLink = new Link(dd);
		if( isEmpty() )
			last = newLink;
		newLink.next = first;
		first = newLink;
	}

	public void insertLast(double dd) {
		Link newLink = new Link(dd);
		if( isEmpty() )
			first = newLink;
		else
   			last.next = newLink;
   		last = newLink;
	}

	public double deleteFirst() {
		double temp = first.dData;
		if(first.next == null)
   			last = null;
		first = first.next;
		return temp;
	}

	public void displayList() {
		System.out.print("List (first-->last): ");
		Link current = first;
		while(current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}

class LinkQueue {
	private FirstLastList theList;

	public LinkQueue() {
		theList = new FirstLastList();
	}

	public boolean isEmpty() {
		return theList.isEmpty();
	}

	public void insert(double j) {
		theList.insertLast(j);
	}

	public double remove() {
		return theList.deleteFirst();
	}

	public void displayQueue() {
		System.out.print("Queue (front-->rear): ");
		theList.displayList();
	}
}

class LinkQueueApp {
	public static void main(String[] args) throws IOException {
		LinkQueue theQueue = new LinkQueue();
		theQueue.insert(20);
		theQueue.insert(40);

		theQueue.displayQueue();

   		theQueue.insert(60);
   		theQueue.insert(80);

   		theQueue.displayQueue();

   		theQueue.remove();
   		theQueue.remove();
   		theQueue.displayQueue();
	}
}






