class Link {
	public double dData;
	public Link next;
	public Link previous;

	public Link(double d) {
		dData = d;
	}

	public void displayLink() {
		System.out.print(dData + " ");
	}
}

class DoublyLinkedList {
	private Link first;
	private Link last;

	public DoublyLinkedList() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return first==null;
	}

	public void insertFirst(double dd) {
		Link newLink = new Link(dd);
		if( isEmpty() )
        	last = newLink;
        else
        	first.previous = newLink;
        newLink.next = first;
        first = newLink;
	}

	public void insertLast(double dd) {
		Link newLink = new Link(dd);
		if(isEmpty()) 
			first = newLink;
		else
			last.next = newLink;
		newLink.previous = last;
		last = newLink;
	}

	public Link deleteFirst() {
		if(isEmpty())
			return null;
		Link temp = first;
		first = first.next;
		if(isEmpty())
			last = null;
		else
			first.previous = null;
		return temp;
	}

	public Link deleteLast() {
		if(isEmpty())
			return null;
		Link temp = last;
		last = last.previous;
		if(last == null)
			first = null;
		else
			last.next = null;
		return temp;
	}

	public boolean insertAfter(double key, double dd) {
		Link current = first;
		while(current.dData != key) {
			current = current.next;
			if(current == null)
   				return false;
		}
		Link newLink = new Link(dd);
		if(current == last) {
			newLink.next = null;
			last = newLink;
		}
		else {
			newLink.next = current.next;
			current.next.previous = newLink;
		}
		newLink.previous = current;
		current.next = newLink;
		return true;
	}

	public Link deleteKey(double key) {
		Link current = first;
		while(current.dData != key) {
			current = current.next;
			if(current == null)
				return null;
		}
		if(current==first)
			first = first.next;
		else
			current.previous.next = current.next;
		if(current== last)
			last = last.previous;
		else
			current.next.previous = current.previous;
		return current;
	}

	public void displayForward() {
		System.out.print("List (first-->last): ");
		Link current = first;
		while(current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}

	public void displayBackward() {
		System.out.print("List (last-->first): ");
		Link current = last;
		while(current != null) {
			current.displayLink();
			current = current.previous; // move to previous link
		}
		System.out.println("");
	}
}

class DoublyLinkedApp {
	public static void main(String[] args) {
		DoublyLinkedList theList = new DoublyLinkedList();
		theList.insertFirst(22);
		theList.insertFirst(44);
		theList.insertFirst(66);
		theList.insertLast(11);
		theList.insertLast(33);
		theList.insertLast(55);
		theList.displayForward();
		theList.displayBackward();
		theList.deleteFirst();
		theList.deleteLast();
		theList.deleteKey(11);
		theList.displayForward();
		theList.insertAfter(22, 77);
        theList.insertAfter(33, 88);
        theList.displayForward();
	}
}



