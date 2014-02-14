class Link {
	public double dData;
	public Link next;

	public Link(double d) {
		dData = d;
		next = null;
	}

	public void displayLink() {
		System.out.print(dData + " ");
	}
}

class CycleList {
	private Link current;

	public CycleList() {
		current = null;
	}

	public void push(double dd) {
		Link theNewLink = new Link(dd);
		if(current == null) {
			current = theNewLink;
			current.next = current;
		}
		else {
			theNewLink.next = current.next;
			current.next = theNewLink;
		}
		step();
	}

	public double pop() {
		double value = current.dData;
		if(current.next == current) {
			current.next = null;
			current = null;
		}
		else {
			Link previous = current;
			while(previous.next != current)
				previous = previous.next;
			previous.next = current.next;
			current = previous;
		}

		return value;
	}

	public void insert(double dd) {
		Link theNewLink = new Link(dd);
		if(current == null) {
			current = theNewLink;
			current.next = current;
		}
		else {
			theNewLink.next = current.next;
			current.next = theNewLink;
		}
		step();
	}

	public double delete() {
		double value = current.dData;
		if(current.next == current) {
			current.next = null;
			current = null;
		}
		else {
			Link previous = current;
			while(previous.next != current)
				previous = previous.next;
			previous.next = current.next;
			current = previous;
		}

		return value;
	}

	public Link current() {
		return current;
	}

	public void step() {
		current = current.next;
	}

	public boolean isEmpty() {
		return current == null;
	}

	public boolean find(double dd) {
		Link temp = current;
		do {
			if(temp.dData == dd)
				return true;
			temp = temp.next;
		}
		while(temp != current);
		return false;
	}

	public boolean onlyOne() {
		return current.next == current;
	}

	public void displayList() {
		if(isEmpty())
			return;
		Link temp = current;
		do {
			System.out.print(temp.dData + " ");
			temp = temp.next;
		}
		while(temp != current);
		System.out.println();
	}
}

class CycleListApp {
	public static void main(String[] args) {
		CycleList theList = new CycleList();
		// theList.insert(0);
		theList.insert(1);
		theList.insert(2);
		theList.insert(3);
		theList.insert(4);
		theList.insert(5);
		theList.insert(6);
		theList.insert(7);
		theList.displayList();
		int num = 4;
		while(!theList.onlyOne()) {
			for(int i = 0; i < num; i++) {
				theList.step();
			}
			System.out.print(theList.delete() + " ");
		}
		System.out.println();
		// System.out.println(theList.find(5) ? "find 5" : "cannot find 5");
		// theList.push(0);
		// theList.push(1);
		// theList.push(2);
		// theList.push(3);
		// theList.push(4);
		// theList.push(5);
		// theList.push(6);
		// while(!theList.isEmpty()) {
		// 	double value = theList.pop();
		// 	System.out.print(value + " ");
		// }
		// System.out.println();

	}
}



