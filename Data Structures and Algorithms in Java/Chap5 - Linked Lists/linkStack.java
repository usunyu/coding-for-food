import java.io.*;

class Link {
	public double dData;
	public Link next;

	public Link(double dd) {
		dData = dd;
	}

	public void displayLink() {
		System.out.print(dData + " ");
	}
}

class LinkList {
	private Link first;

	public LinkList() {
		first = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insertFirst(double dd) {
		Link newLink = new Link(dd);
		newLink.next = first;
		first = newLink;
	}

	public double deleteFirst() {
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

class LinkStack {
	private LinkList theList;

	public LinkStack() {
		theList = new LinkList();
	}

	public void push(double j) {
		theList.insertFirst(j);
	}

	public double pop() {
		return theList.deleteFirst();
	}

	public boolean isEmpty() {
		return ( theList.isEmpty() );
	}

	public void displayStack() {
		System.out.print("Stack (top-->bottom): ");
    	theList.displayList();
	}
}

class LinkStackApp {
	public static void main(String[] args) throws IOException {
		LinkStack theStack = new LinkStack();

		theStack.push(20);
   		theStack.push(40);

   		theStack.displayStack();

   		theStack.push(60);
   		theStack.push(80);

   		theStack.displayStack();

   		theStack.pop();
		theStack.pop();

		theStack.displayStack();
	}
}

