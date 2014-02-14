import java.awt.*;

class Link {
	public Link next;
	public int index;

	public Link(int id) {
		index = id;
		next = null;
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

	public void insertFirst(int id) {
		Link newLink = new Link(id);
		newLink.next = first;
		first = newLink;
	}

	public Link deleteFirst() {
		Link temp = first;
		first = first.next;
		return temp;
	}

	// returns an unvisited vertex adj to v
	public int find(Vertex vertexList[]) {
		Link current = first;
		while(current != null) {
			if(vertexList[current.index].wasVisited==false)
				return current.index;
			current = current.next;
		}
		return -1;
	}

	public void displayList(Vertex vertexList[]) {
		Link current = first;
		while(current != null) {
			System.out.print(" -> " + vertexList[current.index].label);
			current = current.next;  // move to next link
		}
		System.out.println();
	}
}

class StackX {
	private final int SIZE = 20;
	private int[] st;
	private int top;

	public StackX() {
		st = new int[SIZE];
		top = -1;
	}

	public void push(int j) {	// put item on stack
		st[++top] = j;
	}

	public int pop() {	// take item off stack
		return st[top--];
	}

	public int peek() {	// peek at top of stack
		return st[top];
	}

	public boolean isEmpty() {	// true if nothing on stack
		return (top == -1);
	}
}	// end class StackX

class Vertex {
	public char label;        // label e.g. 'A'
	public boolean wasVisited;

	public Vertex(char lab) {	// constructor
		label = lab;
		wasVisited = false;
	}
}  // end class Vertex

class Graph {
	private final int MAX_VERTS = 20;
	private Vertex vertexList[];	// array of vertices
	private LinkList adjacencyList[];	// adjacency list
	private int nVerts;          // current number of vertices
	private StackX theStack;

	public Graph() {	// constructor
		vertexList = new Vertex[MAX_VERTS];
		adjacencyList = new LinkList[MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++) {	// set adjacency
			adjacencyList[j] = new LinkList();
		}
		theStack = new StackX();
	}	// end constructor

	public void addVertex(char lab) {	// argument is label
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdge(int start, int end) {
		adjacencyList[start].insertFirst(end);
		adjacencyList[end].insertFirst(start);
	}

	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}

	public void dfs() {	// depth-first search
		vertexList[0].wasVisited = true;	// begin at vertex 0, mark it
		displayVertex(0);					// display it
		theStack.push(0);					// push it

		while( !theStack.isEmpty() ) {		// until stack empty,
			// get an unvisited vertex adjacent to stack top
			int current = theStack.peek();
			int v = adjacencyList[current].find(vertexList);
			if(v == -1)						// if no such vertex,
				theStack.pop();
			else {
				vertexList[v].wasVisited = true;	// mark it
				displayVertex(v);					// display it
				theStack.push(v);					// push it
			}
		}	// end while

		for(int j=0; j<nVerts; j++)			// reset flags
			vertexList[j].wasVisited = false;
	}	// end dfs

	public void displayGraph() {
		for(int i = 0; i < nVerts; i++) {
			displayVertex(i);
			System.out.print(": ");
			adjacencyList[i].displayList(vertexList);
		}
	}
}	// end class Graph

class DFS2App {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A');	//0 (start for dfs)
		theGraph.addVertex('B');	//1
		theGraph.addVertex('C');	//2
		theGraph.addVertex('D');	//3
		theGraph.addVertex('E');	//4

		theGraph.addEdge(0, 1);		// AB
		theGraph.addEdge(1, 2);		// BC
		theGraph.addEdge(0, 3);		// AD
		theGraph.addEdge(3, 4);		// DE

		System.out.println("Graph: ");
		theGraph.displayGraph();
		System.out.println();

		System.out.print("Visits: ");
		theGraph.dfs();				// depth-first search
		System.out.println();
	}	// end main()
}	// end class DFSApp

