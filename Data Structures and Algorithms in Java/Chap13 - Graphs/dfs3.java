// dfs.java
// demonstrates depth-first search
// to run this program: C>java DFSApp
import java.awt.*;

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
	private Vertex vertexList[]; // array of vertices
	private int adjMat[][];      // adjacency matrix
	private int nVerts;          // current number of vertices
	private StackX theStack;

	public Graph() {	// constructor
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++) {	// set adjacency
			for(int k=0; k<MAX_VERTS; k++) {	//    matrix to 0
				adjMat[j][k] = 0;
			}
		}
		theStack = new StackX();
	}	// end constructor

	public void addVertex(char lab) {	// argument is label
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
		// adjMat[end][start] = 1;
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
			int v = getAdjUnvisitedVertex( theStack.peek() );
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

	// returns an unvisited vertex adj to v
	public int getAdjUnvisitedVertex(int v) {
		for(int j=0; j<nVerts; j++)
			if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
				return j;
		return -1;
	}	// end getAdjUnvisitedVert()

	public void displayConnectTable() {
		for(int v = 0; v < nVerts; v++) {
			vertexList[v].wasVisited = true;
			displayVertex(v);
			theStack.push(v);
			while(!theStack.isEmpty()) {
				int current = theStack.pop();
				// store all successor
				for (int s = 0; s < nVerts; s++) {
					if(adjMat[current][s] > 0 && vertexList[s].wasVisited==false) {
						vertexList[s].wasVisited = true;
						displayVertex(s);
						theStack.push(s);
					}
				}
			}
			
			System.out.println();
			for(int j=0; j<nVerts; j++)			// reset flags
				vertexList[j].wasVisited = false;
		}
	}
}	// end class Graph

class DFS3App {
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

		System.out.print("Visits: ");
		theGraph.dfs();				// depth-first search
		System.out.println();

		System.out.println("Connected Table: ");
		theGraph.displayConnectTable();
		System.out.println();
	}	// end main()
}	// end class DFS3App

