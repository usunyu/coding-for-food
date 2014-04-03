/**
 * @App:		Sorting Algorithm
 *
 * @Author:		Yu Sun
 * @Date:		2013-3-1
*/
class ArraySort {
	private int[] array;
	int num;
	int max;

	public ArraySort(int max) {
		this.array = new int[max];
		this.num = 0;
		this.max = max;
	}

	public void insert(int value) {
		if(num < max) {
			array[num++] = value;
		}
	}

	public void display() {
		for(int i = 0; i < num; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("");
	}

	public void swap(int i1, int i2) {
		int temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}

	public void bubbleSort() {
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num - i - 1; j++) {
				if(array[j] > array[j + 1])
					swap(j, j + 1);
			}
		}
	}

	public void selectionSort() {
		for(int i = 0; i < num; i++) {
			int selected = i;
			for(int j = i + 1; j < num; j++) {
				if(array[j] < array[selected])
					selected = j;
			}
			swap(i, selected);
		}
	}

	public void insertionSort() {
		for(int i = 1; i < num; i++) {
			int temp = array[i];
			int j;
			for(j = i - 1; j >= 0; j--) {
				if(array[j] > temp)
					array[j + 1] = array[j];
				else
					break;
			}
			array[++j] = temp;
		}
	}

	public void merge(int left, int mid, int right) {
		int lPtr = left;
		int rPtr = mid + 1;
		int size = right - left + 1;
		int[] buffer = new int[size];
		int bPtr = 0;
		while(lPtr <= mid && rPtr <= right) {
			if(array[lPtr] < array[rPtr])
				buffer[bPtr++] = array[lPtr++];
			else
				buffer[bPtr++] = array[rPtr++];
		}
		while(lPtr <= mid) {
			buffer[bPtr++] = array[lPtr++];
		}
		while(rPtr <= right) {
			buffer[bPtr++] = array[rPtr++];
		}
		for(bPtr = 0; bPtr < size; bPtr++) {
			array[left + bPtr] = buffer[bPtr];
		}
	}

	public void recMergeSort(int left, int right) {
		if(left >= right)
			return;
		int mid = (left + right) / 2;

		recMergeSort(left, mid);
		recMergeSort(mid + 1, right);

		merge(left, mid, right);
	}

	public void mergeSort() {
		recMergeSort(0, num - 1);
	}

	public void shellSort() {
		int h = 1;
		while(h < num / 3)
			h = h * 3;
		while(h > 0) {
			for(int i = h; i < num; i++) {
				int temp = array[i];
				int j;
				for(j = i - h; j >= 0; j = j - h) {
					if(array[j] > temp)
						array[j + h] = array[j];
					else
						break;
				}
				array[j + h] = temp;
			}
			h = h / 3;
		}
	}

	public void recQuickSort(int left, int right) {
		if(left >= right)
			return;

		int partition = partition(left, right);

		recQuickSort(left, partition - 1);
		recQuickSort(partition + 1, right);
	}

	public int partition(int left, int right) {
		int pivot = array[right];
		int lPtr = left;
		int rPtr = right - 1;
		while(true) {
			while(lPtr < right - 1 && array[lPtr] < pivot)
				lPtr++;
			while(rPtr > left && array[rPtr] > pivot)
				rPtr--;
			if(lPtr >= rPtr)
				break;
			else
				swap(lPtr++, rPtr--);
		}
		swap(lPtr, right);
		return lPtr;
	}

	public void quickSort() {
		recQuickSort(0, num - 1);
	}
}

class ArraySortApp {
	static int max = 40;

	public static void main(String[] args) {
		ArraySort array = new ArraySort(max);
		for(int i = 0; i < max; i++) {
			int n = (int)(java.lang.Math.random()*199);
			array.insert(n);
		}
		System.out.println("Before sort:");
		array.display();

		// array.bubbleSort();
		// array.selectionSort();
		// array.insertionSort();
		// array.mergeSort();
		// array.shellSort();
		array.quickSort();

		System.out.println("After sort:");
		array.display();
	}
}


