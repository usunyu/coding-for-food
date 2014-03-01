/*
WAP to modify the array such that arr[I] = arr[arr[I]]. 
Do this in place i.e. with out using additional memory. 

example : if a = {2,3,1,0} 
o/p = a = {1,0,3,2} 

Note : The array contains 0 to n-1 integers.

http://www.careercup.com/question?id=4909367207919616
*/

class Main {
	public static void print(int[] array) {
		for(int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public static void flip(int[] array) {
		for(int i = 0; i < array.length; i++) {
			array[i] = -array[i];
		}
	}
	
	public static int firstIndex(int[] array) {
		int index = -1;
		for(int i = 0; i < array.length; i++) {
			if(array[i] < 0) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static void wapMod(int[] array) {
		// mark unfinish
		flip(array);
		int index;
		// if all number is positive, we finish
		while((index = firstIndex(array)) != -1) {
			// find first index if we haven't finish
			int first = Math.abs(array[index]), i = index;
			while(Math.abs(array[i]) != index) {	// haven't go back to first
				int nextIndex = Math.abs(array[i]);
				array[i] = Math.abs(array[nextIndex]);
				i = nextIndex;
			}
			array[i] = first;
		}
	}

	public static void main(String[] args) {
		int[] array = {5, 7, 2, 8, 3, 6, 4, 1, 0};
		print(array);
		wapMod(array);
		print(array);
	}
}