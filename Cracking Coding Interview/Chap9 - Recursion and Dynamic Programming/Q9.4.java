import java.util.*;

class Q9_4App {
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