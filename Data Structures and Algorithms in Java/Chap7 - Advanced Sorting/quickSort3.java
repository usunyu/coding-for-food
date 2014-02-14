class ArrayIns {
	private double[] theArray;        // ref to array theArray
	private int nElems;               // number of data items

	public ArrayIns(int max) {
		theArray = new double[max];
		nElems = 0;
	}

	public void insert(double value) {
		if(nElems < theArray.length)
		theArray[nElems++] = value;			// insert it
	}

	public void display() {
		for(int j=0; j<nElems; j++)			// for each element,
			System.out.print(theArray[j] + " ");  // display it
		System.out.println("");
	}

	public void quickSort() {
		recQuickSort(0, nElems-1);
		// insertionSort(0, nElems-1);
	}

	public void recQuickSort(int left, int right) {
		int size = right - left + 1;

		if(size <= 10)					// insertion sort if small
			insertionSort(left, right);
		else {							// quicksort if large
			double median = medianOf3(left, right);
			int partition = partitionIt(left, right, median);

			recQuickSort(left, partition - 1);
			recQuickSort(partition + 1, right);
		}
	}

	public double medianOf3(int left, int right) {
		int center = (left + right) / 2;
		if(theArray[left] > theArray[center])
			swap(left, center);
		if(theArray[left] > theArray[right])
			swap(left, right);
		if(theArray[center] > theArray[right])
			swap(center, right);

		swap(center, right - 1);		// put pivot on right
		return theArray[right-1];		// return median value
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

	public void insertionSort(int left, int right) {
		int inner, outer;
		for(outer = left + 1; outer <= right; outer++) {
			double temp = theArray[outer];
			for(inner = outer - 1; inner >= left && theArray[inner] > temp; inner--) {
				theArray[inner + 1] = theArray[inner];
			}
			theArray[++inner] = temp;
		}
	}
}

class QuickSort3App {
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
}  // end class QuickSort3App




