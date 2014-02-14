// path.java
// demonstrates shortest path with weighted, directed graphs
// to run this program: C>java PathApp
import java.awt.*;

class Vertex {
	public char label;        // label e.g. 'A'

	public Vertex(char lab) {	// constructor
		label = lab;
	}
}  // end class Vertex

class Graph {
	private final int MAX_VERTS = 20;
	private final int INFINITY = 1000000;
	private Vertex vertexList[];	// list of vertices
	private int adjMat[][];			// adjacency matrix
	private int nVerts;				// current number of vertices

	public Graph() {	// constructor
		vertexList = new Vertex[MAX_VERTS];
		// adjacency matrix
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++)     // set adjacency
			for(int k=0; k<MAX_VERTS; k++)  //     matrix
				adjMat[j][k] = INFINITY;     //     to infinity
	}  // end constructor

	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdge(int start, int end, int weight) {
		adjMat[start][end] = weight;  // (directed)
	}

	public void path() {			// find all shortest paths
		for(int v = 0; v < nVerts; v++) {	// for each vertex
			for(int c = 0; c < nVerts; c++) {	// for each column
				if(adjMat[v][c] < INFINITY) {	// has edge
					for(int r = 0; r < nVerts; r++) {	// for each row
						if(adjMat[r][v] < INFINITY) {
							int newWeight = adjMat[r][v] + adjMat[v][c];
							if(newWeight < adjMat[r][c])
								adjMat[r][c] = newWeight;
						}
					}
				}
			}
		}
	}	// end path()

	public void displayGraph() {
		System.out.print("\t");
		for(int i = 0; i < nVerts; i++)
			System.out.print(vertexList[i].label + "\t");
		System.out.println();
		for(int i = 0; i < nVerts; i++) {
			System.out.print(vertexList[i].label + "\t");
			for(int j = 0; j < nVerts; j++) {
				if(adjMat[i][j] < INFINITY)
					System.out.print(adjMat[i][j] + "\t");
				else
					System.out.print("inf\t");
			}
			System.out.println();
		}
	}
}	// end class Graph

class FloydApp {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A'); //0 (start) 
		theGraph.addVertex('B'); //1 
		theGraph.addVertex('C'); //2 
		theGraph.addVertex('D'); //3 
		theGraph.addVertex('E'); //4

		theGraph.addEdge(0, 1, 50);  // AB 50
		theGraph.addEdge(0, 3, 80);  // AD 80
		theGraph.addEdge(1, 2, 60);  // BC 60
		theGraph.addEdge(1, 3, 90);  // BD 90
		theGraph.addEdge(2, 4, 40);  // CE 40
		theGraph.addEdge(3, 2, 20);  // DC 20
		theGraph.addEdge(3, 4, 70);  // DE 70
		theGraph.addEdge(4, 1, 50);  // EB 50

		System.out.println("Graph");
		theGraph.displayGraph();
		System.out.println();
		System.out.println("Shortest paths");
		theGraph.path();             // shortest paths
		theGraph.displayGraph();
		System.out.println();
	}  // end main()
} // end class FloydApp




