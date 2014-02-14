import java.util.*;

class MyNode {
	char value;
	MyEdge firstEdge;
	boolean visited;

	public MyNode(char value) {
		this.value = value;
		visited = false;
	}

	public void display() {
		System.out.print(value + "\t");
	}
}

class MyEdge {
	int index;
	MyEdge nextEdge;

	public MyEdge(int index) {
		this.index = index;
	}

	public void display() {
		System.out.print(index + "\t");
	}
}

class MyGraph {
	MyNode[] nodeList;
	int size;
	int max;

	public MyGraph(int max) {
		this.max = max;
		nodeList = new MyNode[max];
	}

	public void addNode(char value) {
		if(size < max) {
			MyNode node = new MyNode(value);
			nodeList[size++] = node;
		}
	}

	public void addEdge(int start, int end) {
		// assume the node are exist
		MyEdge edge = new MyEdge(end);
		MyNode node = nodeList[start];
		MyEdge tempEdge = node.firstEdge;
		node.firstEdge = edge;
		edge.nextEdge = tempEdge;
	}

	public boolean hasPath(int start, int end) {
		boolean hasPath = false;
		MyNode node = nodeList[start];
		LinkedList<MyNode> queue = new LinkedList<MyNode>();
		queue.add(node);
		while(!queue.isEmpty()) {
			node = queue.poll();
			// find the end node
			if(node == nodeList[end]) {
				hasPath = true;
				break;
			}
			node.visited = true;
			// iterate all its edge
			MyEdge edge = node.firstEdge;
			while(edge != null) {
				if(nodeList[edge.index].visited == false)
					queue.add(nodeList[edge.index]);
				edge = edge.nextEdge;
			}
		}

		// reset visited
		for(int i = 0; i < size; i++)
			nodeList[i].visited = false;
		return hasPath;
	}

	public void display() {
		for(int i = 0; i < size; i++) {
			System.out.print(i + ": ");
			nodeList[i].display();
			MyEdge edge = nodeList[i].firstEdge;
			while(edge != null) {
				System.out.print("->");
				edge.display();
				edge = edge.nextEdge;
			}
			System.out.println();
		}
	}
}

class Q4_2App {
	public static void main(String[] args) {
		MyGraph graph = new MyGraph(20);

		graph.addNode('A');	//0
		graph.addNode('B');	//1
		graph.addNode('C');	//2
		graph.addNode('D');	//3
		graph.addNode('E');	//4
		graph.addNode('F');	//5

		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 5);
		graph.addEdge(0, 4);
		graph.addEdge(4, 3);
		graph.addEdge(4, 1);

		graph.display();

		System.out.println("A to F: has " + (graph.hasPath(0, 5) ? "path" : "no path"));
		System.out.println("E to A: has " + (graph.hasPath(4, 0) ? "path" : "no path"));
	}
}
