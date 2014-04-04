/*
Given an array of size n, generate and print all possible combinations of r elements in array. 
For example, if input array is {1, 2, 3, 4} and r is 2, then output should be {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}.

http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
*/
import java.util.ArrayList;

class Main {
	public static void printCombination(int[] arr, int r) {
		printCombination(arr, 0, new ArrayList<Integer>(), r);
	}

	private static void printCombination(int[] arr, int index, ArrayList<Integer> path, int r) {
		if(r == 0) {
			System.out.println(path);
			return;
		}
		for(int i = index; i < arr.length; i++) {
			path.add(arr[i]);
			printCombination(arr, i + 1, path, r - 1);
			path.remove(path.size() - 1);
		}
	}

	/*
	How to handle duplicates?
	Note that the above method doesn’t handle duplicates. For example, if input array is {1, 2, 1} and r is 2, then the program prints {1, 2} and {2, 1} as two different combinations. We can avoid duplicates by adding following two additional things to above code.
	1) Add code to sort the array before calling combinationUtil() in printCombination()
	2) Add following lines at the end of for loop in combinationUtil()

		// Since the elements are sorted, all occurrences of an element
        // must be together
        while (arr[i] == arr[i+1])
             i++; 
	*/

	public static void main(String[] args) {
		int arr[] = {1, 2, 3, 4, 5};
		int r = 3;
		printCombination(arr, r);
	}
}