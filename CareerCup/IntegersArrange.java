/*
Given an array of (unsorted) integers, arrange them such that a < b > c < d > e... etc.
*/

class Main {
	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void arrange(int[] array) {
		for(int i = 0; i < array.length - 1; i++) {
			if(i % 2 == 0) {
				if(array[i] > array[i + 1]) {
					swap(array, i, i + 1);
				}
			}
			else {
				if(array[i] < array[i + 1]) {
					swap(array, i, i + 1);
				}
			}
		}
	}

	public static void print(int[] array) {
		for(int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		print(array);
		arrange(array);
		print(array);
	}
}