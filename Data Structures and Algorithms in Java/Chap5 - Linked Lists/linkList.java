class Link {
	public int iData;
	public double dData;
	public Link next;

	public Link(int id, double dd) {
		iData = id;
		dData = dd;
		next = null;
	}

	public void displayLink() {
		System.out.print("{" + iData + ", " + dData + "}");
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

	public void insertFirst(int id, double dd) {
		Link newLink = new Link(id, dd);
		newLink.next = first;
		first = newLink;
	}

	public Link deleteFirst() {
		Link temp = first;
		first = first.next;
		return temp;
	}

	public Link find(int key) {
		Link temp = first;
		while(temp != null) {
			if(temp.iData == key)
				break;
			temp = temp.next;
		}
		return temp;
	}

	public Link delete(int key) {
		Link current = first;
		Link previous = null;
		while(current != null) {
			if(current.iData == key) {
				if(current == first) {
					first = first.next;
					return current;
				}
				else {
					previous.next = current.next;
					return current;
				}
			}
			previous = current;
			current = current.next;
		}
		return null;
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

class LinkListApp {
	public static void main(String[] args) {
		LinkList theList = new LinkList();

		theList.insertFirst(22, 2.99);
		theList.insertFirst(44, 4.99);
		theList.insertFirst(66, 6.99);
		theList.insertFirst(88, 8.99);

		theList.displayList();

		Link f = theList.find(88);
		if(f != null)
			System.out.println("Found link with key " + f.iData);
		else
			System.out.println("Can't find link");

		Link d = theList.delete(88);

		if( d != null )
            System.out.println("Deleted link with key " + d.iData);
        else
        	System.out.println("Can't delete link");

        d = theList.delete(22);

		if( d != null )
            System.out.println("Deleted link with key " + d.iData);
        else
        	System.out.println("Can't delete link");

		d = theList.delete(44);

		if( d != null )
            System.out.println("Deleted link with key " + d.iData);
        else
        	System.out.println("Can't delete link");

		d = theList.delete(66);

		if( d != null )
            System.out.println("Deleted link with key " + d.iData);
        else
        	System.out.println("Can't delete link");

		// while(!theList.isEmpty()) {
		// 	Link aLink = theList.deleteFirst();
		// 	System.out.print("Deleted ");
		// 	aLink.displayLink();
		// 	System.out.println("");
		// }

		theList.displayList();
	}
}


