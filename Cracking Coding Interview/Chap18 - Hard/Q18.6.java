import java.util.Arrays;

class Main {
	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}

	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}

	public static int[] randomArray(int N, int min, int max) {
		int[] array = new int[N];
		for (int j = 0; j < N; j++) {
			array[j] = randomIntInRange(min, max);
		}
		return array;
	}

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

	public static void print(int[] array) {
		System.out.print("[");
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1)
				System.out.print(array[i]);
			else
				System.out.print(array[i] + ", ");
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		int[] array = randomArray(10, 0, 100);
		// int[] array = {3, 71, 21, 78, 67, 52, 30, 33, 50, 13};
		print(array);
		int no = 5;
		System.out.println("NO " + no + " number is: " + quickSelect(array, 0, array.length - 1, no));
		// Arrays.sort(array);
		// quickSort(array, 0, array.length - 1);
		print(array);
	}
}