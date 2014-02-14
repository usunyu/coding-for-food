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

	// returns an unvisited vertex adj to v
	public int getAdjUnvisitedVertex(int v) {
		for(int j=0; j<nVerts; j++)
			if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
				return j;
		return -1;
	}	// end getAdjUnvisitedVert()

	public void warshall() {
		for(int v = 0; v < nVerts; v++) {	// for each vertex
			for(int c = 0; c < nVerts; c++) {	// for each column
				if(adjMat[v][c] > 0) {
					for(int r = 0; r < nVerts; r++) {	// for each row
						if(adjMat[r][v] > 0)
							adjMat[r][c] = 1;
					}
				}
			}
		}
	}

	public void displayAdjMat() {
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j < nVerts; j++)
				System.out.print(adjMat[i][j] + "\t");
			System.out.println();
		}
	}
}	// end class Graph

class WarShallApp {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A');	//0 (start for dfs)
		theGraph.addVertex('B');	//1
		theGraph.addVertex('C');	//2
		theGraph.addVertex('D');	//3
		theGraph.addVertex('E');	//4

		theGraph.addEdge(1, 0);		// BA
		theGraph.addEdge(0, 2);		// AC
		theGraph.addEdge(4, 0);		// EA
		theGraph.addEdge(3, 4);		// DE

		System.out.println("Original Table: ");
		theGraph.displayAdjMat();
		System.out.println();
		System.out.println("WarShall Table: ");
		theGraph.warshall();
		theGraph.displayAdjMat();
		System.out.println();
	}	// end main()
}	// end class WarShallApp

