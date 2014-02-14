// path.java
// demonstrates shortest path with weighted, directed graphs
// to run this program: C>java PathApp
import java.awt.*;
import java.util.*;

class Vertex {
	public char label;        // label e.g. 'A'
	public ArrayList<Edge> edgeList;

	public Vertex(char lab) {	// constructor
		label = lab;
		edgeList = new ArrayList<Edge>();
	}

	public void addEdge(Vertex v, int w) {
		Edge edge = new Edge(v, w);
		edgeList.add(edge);
	}
}  // end class Vertex

class Edge {
	public Vertex toVertex;
	public int weight;

	public Edge(Vertex v, int w) {
		toVertex = v;
		weight = w;
	}
}

class Graph {
	private final int MAX_VERTS = 20;
	private final int INFINITY = 1000000;
	private Vertex vertexList[];	// list of vertices
	private int nVerts;				// current number of vertices

	public Graph() {	// constructor
		vertexList = new Vertex[MAX_VERTS];
		nVerts = 0;
	}  // end constructor

	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdge(int start, int end, int weight) {
		// adjMat[start][end] = weight;  // (directed)
		Vertex from = vertexList[start];
		Vertex to = vertexList[end];
		from.addEdge(to, weight);
	}

	public void displayGraph() {
		for(int i = 0; i < nVerts; i++) {
			Vertex from = vertexList[i];
			System.out.print(from.label + ": ");
			for(int j = 0; j < from.edgeList.size(); j++) {
				Edge edge = from.edgeList.get(j);
				Vertex to = edge.toVertex;
				int weight = edge.weight;
				System.out.print(to.label + "(" + weight + ") ");
			}
			System.out.println();
		}
	}
}	// end class Graph

class GraphRefApp {
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
	}  // end main()
} // end class GraphRefApp




