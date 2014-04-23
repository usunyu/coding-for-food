/*
Describe an algorithm to find the smallest one million numbers in one billion numbers. 
Assume that the computer memory can hold all one billion numbers.
*/
import CtCILibrary.AssortedMethods;
import java.util.Arrays;

class Solution {

	public static int max(int[] array, int left, int right) {
		int max = Integer.MIN_VALUE;
		for (int i = left; i <= right; i++) {
			max = Math.max(array[i], max);
		}
		return max;
	}

	public static void swap(int[] array, int i, int j) {
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
	}

	public static int quickSelect(int[] array, int left, int right, int rank) {
		int i = left, j = right;
		int pivot = array[left + (right - left) / 2];
		while(i <= j) {
			while(array[i] < pivot) i++;
			while(array[j] > pivot) j--;
			if(i <= j) swap(array, i++, j--);
		}
		int leftSize = i - left;
		if(rank == leftSize) return max(array, left, i - 1);
		else if(rank < leftSize) return quickSelect(array, left, i - 1, rank);
		else return quickSelect(array, i, right, rank - leftSize);
	}

	public static void quickSort(int[] array, int left, int right) {
		int i = left, j = right;
		int pivot = array[left + (right - left) / 2];
		while(i <= j) {
			while(array[i] < pivot) i++;
			while(array[j] > pivot) j--;
			if(i <= j) swap(array, i++, j--);
		}
		if(left < j) quickSort(array, left, j);
		if(i < right) quickSort(array, i, right);
	}

	private static int partition(int[] array, int start, int end) {
		int pivot = array[end];
		int i = start, j = start;
		while(j < end) {
			if(array[j] >= pivot) {
				swap(array, i++, j);
			}
			j++;
		}
		swap(array, i, end);
		return i;
	}
	/*
		Second Round
	*/
	public static int quickSelect2(int[] array, int k) {
		int start = 0, end = array.length - 1;
		while(start < end) {
			int p = partition(array, start, end);
			if(p == k - 1) 
				return array[p];
			else if(p < k - 1)
				start = p + 1;
			else 
				end = p - 1;
		}
		return array[start];
	}

	public static void main(String[] args) {
		int[] array = AssortedMethods.randomArray(10, 0, 100);
		// int[] array = {3, 71, 21, 78, 67, 52, 30, 33, 50, 13};
		AssortedMethods.printIntArray(array);
		int no = 5;
		// System.out.println("NO " + no + " number is: " + quickSelect(array, 0, array.length - 1, no));
		System.out.println("NO " + no + " number is: " + quickSelect2(array, no));
		// Arrays.sort(array);
		// quickSort(array, 0, array.length - 1);
		AssortedMethods.printIntArray(array);
	}
}