class Link {
	public int iData;
	public Link next;
	public Link down;

	public Link(int id) {
		iData = id;
		next = null;
		down = null;
	}

	public void displayLink() {
		System.out.print(iData + " ");
	}
}

class MatrixList {
	private Link first;
	private int row;
	private int column;
	private int lineMax;

	public MatrixList(int max) {
		first = null;
		row = 0;
		column = 0;
		lineMax = max;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insert(int id) {
		Link theNewLink = new Link(id);
		if(isEmpty()) {
			first = theNewLink;
		}
		else {
			Link current = first;

			for(int i = 0; i < row; i++)
				current = current.down;

			if(column == lineMax - 1) {
				current.down = theNewLink;
				column = 0;
				row++;
			}
			else {
				for(int i = 0; i < column; i++)
					current = current.next;

				current.next = theNewLink;
				column++;
			}
		}
	}

	public void displayList() {
		Link head = first;
		while(head != null) {
			Link current = head;
			while(current != null) {
				current.displayLink();
				current = current.next;
			}
			System.out.println();
			head = head.down;
		}
		System.out.println("");
	}

	public void show(int r, int c) {
		Link current = first;
		for(int i = 0; i < r; i++)
			current = current.down;
		for(int i = 0; i < c; i++)
			current = current.next;
		current.displayLink();
		System.out.println();
	}
}

class MatrixListApp {
	public static void main(String[] args) {
		MatrixList theList = new MatrixList(5);
		for(int i = 0; i < 20; i++) {
			theList.insert(i);
		}
		theList.displayList();
		theList.show(2, 3);
	}
}


