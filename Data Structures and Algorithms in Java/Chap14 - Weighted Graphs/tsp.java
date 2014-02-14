import java.awt.*;
import java.util.*;

class Vertex {
	public char label;        // label e.g. 'A'
	int index;
	public Vertex(char lab, int id) {	// constructor
		label = lab;
		index = id;
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

	public void addVertex(char lab, int id) {
		vertexList[nVerts++] = new Vertex(lab, id);
	}

	public void addEdge(int start, int end, int weight) {
		adjMat[start][end] = weight;  // (directed)
	}

	public void tsp() {
		doTSP(nVerts);
	}

	public void rotate(int newSize) {
		int position = nVerts - newSize;
		Vertex temp = vertexList[position];
		for(int i = position + 1; i < nVerts; i++)
			vertexList[i - 1] = vertexList[i];
		vertexList[nVerts - 1] = temp;
	}

	private void doTSP(int newSize) {
		if(newSize == 1)
			return;
		for(int i = 0; i < newSize; i++) {
			doTSP(newSize - 1);
			if(newSize == 2)
				displayPath();
			rotate(newSize);
		}
	}

	public void displayPath() {
		int cost = 0;
		int last = -1;
		for(int i = 0; i < nVerts; i++) {
			int current = vertexList[i].index;
			if(last != -1)
				cost += adjMat[last][current];
			System.out.print(vertexList[i].label + " ");
			last = current;
		}
		if(cost < INFINITY)
			System.out.println("Cost: " + cost);
		else
			System.out.println("Cost: inf");
	}

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

class TSPApp {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A', 0); //0 (start) 
		theGraph.addVertex('B', 1); //1 
		theGraph.addVertex('C', 2); //2 
		theGraph.addVertex('D', 3); //3 
		theGraph.addVertex('E', 4); //4

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
		System.out.println("TSP");
		theGraph.tsp();
		System.out.println();
	}  // end main()
} // end class TSPApp




