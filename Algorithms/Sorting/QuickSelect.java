/*
http://blog.teamleadnet.com/2012/07/quick-select-algorithm-find-kth-element.html
http://www.geekviewpoint.com/java/search/quickselect
*/

import java.util.Arrays;

class Main {
	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	public static int quickSelect(int[] array, int K) {
		if(array == null || array.length < K) return -1;
		int start = 0, end = array.length - 1;
		// if start == end we reached the kth element
		while(start < end) {
			int left = start, right = end - 1;
			int pivot = array[end];	// chosen as pivot
			// stop if the reader and writer meets
			while (left < right) {
				if(array[right] <= pivot) {	// put the small values at the left
					swap(array, left++, right);
				}
				else right--;	// the value is larger than the pivot, skip
			}
			if(array[right] > pivot)	// not good
				swap(array, right, end);	// move the pivot in right place
			
			if(K - 1 <= right) {
				end = right;
			}
			else {
				start = right + 1;
			}
		}
		return array[K];
	}
	
	private static void quickSort(int[] array, int start, int end) {
		if(start < end) {
			int left = start, right = end - 1;
			int pivot = array[end];	// chosen as pivot
			// partition
			while(left < right) {
				if(array[right] <= pivot) {	// put the small items left
					swap(array, left++, right);
				}
				else right--;	// skip large items
			}
			if(array[right] > pivot)
				swap(array, right, end);	// put pivot at right place
			// divide
			quickSort(array, start, right);
			quickSort(array, right + 1, end);
		}
	}

	// better solution for memory and in practice
	// form Introduction to Algorithm
	private static int partition(int[] array, int start, int end) {
		int pivot = array[end];
		int i = start, j = start;
		while(j < end) {
			if(array[j] >= pivot) {
				swap(array, i++, j);
			}
			j++;
		}
		swap(array, i, end);	// always swap
		return i;
	}
	
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
	
	public static void quickSort(int[] array) {
		if(array == null || array.length <= 1) return;
		quickSort(array, 0, array.length - 1);
	}
	
	public static void main(String[] args) {
		int[] array = {21,3,34,5,13,8,2,55,1,19};
		
		int K = 4;
		System.out.println(Arrays.toString(array));
		System.out.println("Quick Select: " + K);
		quickSelect2(array, K);
		System.out.println(Arrays.toString(array));
		
		int[] array2 = {21,3,34,5,13,8,2,55,1,19};
		System.out.println("Quick Sort:");
		quickSort(array2);
		System.out.println(Arrays.toString(array2));
	}
}