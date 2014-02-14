import java.io.*;

class ArraySel {
	private double[] a;
	private int nElems;

	public ArraySel(int max) {
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

	public void selectionSort() {
		int min;
		for(int i = 0; i < nElems; i++)
		{
			min = i;
			for(int j = i + 1; j < nElems; j++)
				if(a[j] < a[min])
					min = j;
			swap(i, min);
		}
	}

	public void swap(int a1, int a2) {
		double temp = a[a1];
		a[a1] = a[a2];
		a[a2] = temp;
	}
}

class SelectionSortApp {
	public static void main(String[] args) {
		int maxSize = 100;            // array size
		ArraySel arr;                 // reference to array
		arr = new ArraySel(maxSize);  // create the array
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
		arr.selectionSort();
		arr.display();
	}
}