/*
Write a method to return all subsets of aset.
*/

import java.util.*;

class Solution {
	public static void print(int[] set) {
		for(int i = 0; i < set.length; i++) {
			System.out.print(set[i] + " ");
		}
		System.out.println();
	}

	public static void print(ArrayList<Integer> subset) {
		for(int i = 0; i < subset.size(); i++) {
			System.out.print(subset.get(i) + " ");
		}
		System.out.println();
	}

	public static void subset(int[] set, ArrayList<Integer> subset, int start) {
		if(subset.size() > 0)
			print(subset);

		if(subset.size() == set.length)
			return;

		for(int i = start; i < set.length; i++) {
			ArrayList<Integer> subsetTemp = new ArrayList<Integer>(subset);
			subsetTemp.add(set[i]);
			subset(set, subsetTemp, i + 1);
		}
	}

	public static void main(String[] args) {
		int[] set = {1, 2, 3, 4};
		ArrayList<Integer> subset = new ArrayList<Integer>();
		subset(set, subset, 0);
	}
}
/*
	Second Round
*/
class Solution2 {
	public static ArrayList<ArrayList<Integer>> getSubset(ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> subset = new ArrayList<Integer>();
		subsets.add(subset);	// initial
		for(int i : set) {
			int size = subsets.size();
			for(int j = 0; j < size; j++) {
				subset = new ArrayList<Integer>(subsets.get(j));
				subset.add(i);
				subsets.add(subset);
			}
		}
		return subsets;
	}

	public static void main(String[] args) {
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1); set.add(2); set.add(3); set.add(4);
		for(ArrayList<Integer> s : getSubset(set))
			System.out.println(s);
	}
}

