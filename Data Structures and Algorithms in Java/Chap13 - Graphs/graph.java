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

	public Graph() {	// constructor
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++) {	// set adjacency
			for(int k=0; k<MAX_VERTS; k++) {	//    matrix to 0
				adjMat[j][k] = 0;
			}
		}
	}	// end constructor

	public void addVertex(char lab) {	// argument is label
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}

	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}
}	// end class Graph



