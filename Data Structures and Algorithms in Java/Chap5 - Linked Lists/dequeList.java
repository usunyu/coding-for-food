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

class DequeList {
	private Link first;
	private Link last;

	public DequeList() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return first==null;
	}

	public void insertLeft(double dd) {
		Link newLink = new Link(dd);
		if( isEmpty() )
        	last = newLink;
        else
        	first.previous = newLink;
        newLink.next = first;
        first = newLink;
	}

	public void insertRight(double dd) {
		Link newLink = new Link(dd);
		if(isEmpty()) 
			first = newLink;
		else
			last.next = newLink;
		newLink.previous = last;
		last = newLink;
	}

	public Link removeLeft() {
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

	public Link removeRight() {
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

class DequeListApp {
	public static void main(String[] args) {
		DequeList theList = new DequeList();
		theList.insertLeft(22);
		theList.insertLeft(44);
		theList.insertLeft(66);
		theList.insertRight(11);
		theList.insertRight(33);
		theList.insertRight(55);
		theList.displayForward();
		theList.displayBackward();
		theList.removeLeft();
		theList.removeRight();
        theList.displayForward();
	}
}



