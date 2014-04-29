/*
Topological Sorting

Sandro is a well organised person. Every day he makes a list of things which need to be done and enumerates them 
from 1 to n. However, some things need to be done before others. In this task you have to find out whether Sandro 
can solve all his duties and if so, print the correct order.

Input
In the first line you are given an integer n and m (1<=n<=10000, 1<=m<=1000000). On the next m lines there are 
two distinct integers x and y, (1<=x,y<=10000) describing that job x needs to be done before job y.

Output
Print "Sandro fails." if Sandro cannot complete all his duties on the list. If there is a solution print the correct 
ordering, the jobs to be done separated by a whitespace. If there are multiple solutions print the one, whose first 
number is smallest, if there are still multiple solutions, print the one whose second number is smallest, and so on.

Example 1
Input:
8 9
1 4
1 2
4 2
4 3
3 2
5 2
3 5
8 2
8 6
Output:
1 4 3 5 7 8 2 6 

Example 2
Input:
2 2
1 2
2 1
Output:
Sandro fails.

http://n00tc0d3r.blogspot.com/search?q=Topological+Sort
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Main {
	private static void toposort(ArrayList<ArrayList<Integer>> graph, int[] indegs) {
		int n = indegs.length;
		boolean[] visited = new boolean[n];
		Queue<Integer> que = new PriorityQueue<Integer>(); // use heap s.t. smallest-numbered available vertex first
		ArrayList<Integer> res = new ArrayList<Integer>(n);  
   
		// enque 0-in-degree nodes
		for (int i=0; i<n; ++i) {
			if (indegs[i] == 0) que.offer(i);
		}

		// bfs
		while (!que.isEmpty()) {
			int node = que.poll();
			res.add(node+1);
			// mark as visited
			visited[node] = true;
			// update its neighbors and enqueue 0-in-degree ones
			for (int nb : graph.get(node)) {
				if (visited[nb]) { // a cycle detected
					break;
				}
				if (--indegs[nb] == 0) que.offer(nb);
			}
		}

		// print
		if (res.size() < n) {
			System.out.println("Sandro fails.");
		} else {
			System.out.println(res);
		}
	}
   
	public static void main(String[] args) throws FileNotFoundException {
		// Scanner in = new Scanner(System.in);
		File file = new File(args[0]);
		Scanner in = new Scanner(file);
		int n = in.nextInt(), m = in.nextInt();

		// initial graph
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(n);
		int[] indegs = new int[n];
		for (int i=0; i<n; ++i) {
			graph.add(new ArrayList<Integer>(n));
		}

		// parse edges  
		for (int j=0; j<m; ++j) {
			int v1 = in.nextInt() - 1, v2 = in.nextInt() - 1;
			// add v2 to v1's neighbor list
			graph.get(v1).add(v2);
			// increase v2's indegree
			indegs[v2]++;
		}

		toposort(graph, indegs);
	}
}
