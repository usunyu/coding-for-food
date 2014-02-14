import java.io.*;

class ArrayBub {
	private double[] a;
	private int nElems;

	public ArrayBub(int max) {
		a = new double[max];
		nElems = 0;
	}

	public void insert(double value) {
		a[nElems] = value;
		nElems++;
	}

	public void display() {
		for(int i = 0; i < nElems; i++)
			System.out.print(a[i] + "  ");
		System.out.println("");
	}

	public void bubbleSort() {
		for(int i = 0; i < nElems - 1; i++)
			for(int j = 0; j < nElems - i - 1; j++)
				if(a[j] > a[j + 1])
					swap(j, j + 1);
	}

	public void bubbleSort2() {
		for(int i = nElems - 1; i >= 0; i--) {
			for(int j = 0; j < i; j++)
				if(a[j] > a[j + 1])
					swap(j, j + 1);
			for(int j = i; j >= 0; j--)
				if(a[j] > a[j + 1])
					swap(j, j + 1);
		}
	}

	public void oddEvenSort() {
		boolean flag = false;
		while(!flag) {
			flag = true;
			// odd
			for(int i = 1; i < nElems; i += 2) {
				if(a[i] > a[i + 1]) {
					swap(i, i + 1);
					flag = false;
				}
			}
			// even
			for(int i = 0; i < nElems; i += 2) {
				if(a[i] > a[i + 1]) {
					swap(i, i + 1);
					flag = false;
				}
			}
		}
	}

	public void swap(int a1, int a2) {
		double temp = a[a1];
		a[a1] = a[a2];
		a[a2] = temp;
	}
}

class BubbleSortApp {
	public static void main(String[] args) {
		int maxSize = 100;            // array size
		ArrayBub arr;                 // reference to array
		arr = new ArrayBub(maxSize);  // create the array
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);

		arr.display();
		// arr.bubbleSort();
		// arr.bubbleSort2();
		arr.oddEvenSort();
		arr.display();
	}
}