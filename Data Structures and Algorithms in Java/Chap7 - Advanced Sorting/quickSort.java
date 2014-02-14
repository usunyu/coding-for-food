import java.io.*;

class ArrayIns {
	private double[] theArray;
	private int nElems;

	public ArrayIns(int max) {
		theArray = new double[max];
		nElems = 0;
	}

	public void insert(double value) {
		if(nElems < theArray.length)
			theArray[nElems++] = value;
	}

	public void quickSort() {
		recQuickSort(0, nElems - 1);
	}

	public void recQuickSort(int left, int right) {
		if(left >= right)
			return;
		else {
			double pivot = theArray[right];

			int partition = partitionIt(left, right, pivot);
			// display();
			recQuickSort(left, partition - 1);
			recQuickSort(partition + 1, right);
		}
	}

	public int partitionIt(int left, int right, double pivot) {
		int leftPtr = left;
		int rightPtr = right - 1;
		while(true) {
			while(theArray[leftPtr] < pivot && leftPtr < right)
				leftPtr++;
			while(theArray[rightPtr] > pivot && rightPtr > left)
				rightPtr--;
			if(leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr++, rightPtr--);
		}
		swap(leftPtr, right);
		return leftPtr;
	}

	public void swap(int dex1, int dex2) {
		double temp;
		temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}

	public void display() {
		for(int j = 0; j < nElems; j++)
			System.out.print(theArray[j] + " ");
		System.out.println();
	}
}

class QuickSortApp {
	public static void main(String[] args) {
		int maxSize = 20;
		ArrayIns arr;
		arr = new ArrayIns(maxSize);
		for(int j = 0; j < maxSize; j++) {
			double n = (int)(java.lang.Math.random()*99);
			arr.insert(n);
		}
		// arr.insert(91);
		// arr.insert(73);
		// arr.insert(24);
		// arr.insert(51);
		// arr.insert(27);
		// arr.insert(53);
		// arr.insert(91);
		// arr.insert(68);
		// arr.insert(52);
		// arr.insert(27);
		// arr.insert(21);
		// arr.insert(27);
		// arr.insert(32);
		// arr.insert(38);
		// arr.insert(45);
		// arr.insert(10);
		// arr.insert(9);
		// arr.insert(85);
		// arr.insert(96);
		// arr.insert(9);

		arr.display();
		arr.quickSort();
		arr.display();
	}
}


