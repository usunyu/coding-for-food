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

	public static void wapMod(int[] array) {
		int first = array[0], i = 0;
		while(array[i] != 0) {
			int tmp = array[i];
			array[i] = array[array[i]];
			i = tmp;
		}
		array[i] = first;
	}

	public static void main(String[] args) {
		int[] array = {2,3,1,0};
		print(array);
		wapMod(array);
		print(array);
	}
}