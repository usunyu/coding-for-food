/*
Given a undirected graph with corresponding edges. Find the number of possible triangles? 
Example: 
0 1 
2 1 
0 2 
4 1 

Answer: 
1
*/
import java.util.HashMap;
import java.util.HashSet;

class Solution {
	private static int addEdge(HashMap<Integer, HashSet<Integer>> graph, int from, int to) {
		int count = 0;
		HashSet<Integer> set;
		if(!graph.containsKey(from)) {	// not possible
			set = new HashSet<Integer>();
			graph.put(from, set);
		}
		else {
			set = graph.get(from);
			if(graph.containsKey(to)) {	// possible
				HashSet<Integer> other = graph.get(to);
				for(int n : set) {	// triangle
					if(other.contains(n)) count++;
				}
			}
		}
		set.add(to);
		return count;
	}

	public static int possibleTriangles(int[] from, int[] to) {
		int count = 0;
		// from node : to node set
		HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
		// calculate
		for(int i = 0; i < from.length; i++) {
			int f = from[i], t = to[i];
			count += addEdge(graph, f, t);	// only need add once
			addEdge(graph, t, f);
		}
		return count;
	}

	public static void main(String[] args) {
		int[] from = {1, 2, 2, 3, 1};
		int[] to = {2, 4, 3, 4, 3};
		System.out.println(possibleTriangles(from, to));
	}
}