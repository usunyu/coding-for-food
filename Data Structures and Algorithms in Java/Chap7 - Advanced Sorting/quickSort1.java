class ArrayIns {
	private double[] theArray;        // ref to array theArray
	private int nElems;               // number of data items

	public ArrayIns(int max) {
		theArray = new double[max];
		nElems = 0;
	}

	public void insert(double value) {
		if(nElems < theArray.length)
		theArray[nElems++] = value;         // insert it
	}

	public void display() {
		for(int j=0; j<nElems; j++)       // for each element,
			System.out.print(theArray[j] + " ");  // display it
		System.out.println("");
	}

	public void quickSort() {
		recQuickSort(0, nElems-1);
	}

	public void recQuickSort(int left, int right) {
		if(right <= left)
			return;
		else {
			double pivot = theArray[right];

			int partition = partitionIt(left, right, pivot);
			recQuickSort(left, partition - 1);
			recQuickSort(partition + 1, right);
		}
	}

	public int partitionIt2(int left, int right, double pivot) {
		int pivotPtr = right;
		int leftPtr = left;
		int rightPtr = right - 1;
		while(true) {
			while(theArray[leftPtr] < pivot && leftPtr < right)
				leftPtr++;
			if(leftPtr <= rightPtr) {
				swap(leftPtr, pivotPtr);
				pivotPtr = leftPtr;
				leftPtr++;
			}
			else
				return (++rightPtr);

			while(theArray[rightPtr] > pivot && rightPtr > left)
				rightPtr--;
			if(leftPtr <= rightPtr) {
				swap(rightPtr, pivotPtr);
				pivotPtr = rightPtr;
				rightPtr--;
			}
			else
				return (--leftPtr);
		}
	}

	public int partitionIt(int left, int right, double pivot) {
		int leftPtr = left - 1;
		int rightPtr = right;
		while(true) {
			while(leftPtr < right && theArray[++leftPtr] < pivot);
			while(rightPtr > left && theArray[--rightPtr] > pivot);

			if(leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
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
}

class QuickSort1App {
	public static void main(String[] args) {
		int maxSize = 16;             // array size
		ArrayIns arr;
		arr = new ArrayIns(maxSize);  // create array
		for(int j=0; j<maxSize; j++) { // fill array with random numbers
			double n = (int)(java.lang.Math.random()*99);
			arr.insert(n);
		}
		arr.display();                // display items
		arr.quickSort();              // quicksort them
		arr.display();                // display them again
	}  // end main()
}  // end class QuickSort1App




