class ArrayIns {
	private double[] theArray;        // ref to array theArray
	private int nElems;               // number of data items
	private int copyCount;
	private int compCount;

	public ArrayIns(int max) {
		theArray = new double[max];
		nElems = 0;
		copyCount = 0;
		compCount = 0;
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
	}

	public void recQuickSort(int left, int right) {
		int size = right - left + 1;

		if(size <= 3)					// manual sort if small
			manualSort(left, right);
		else {							// quicksort if large
			double median = medianOf3(left, right);
			int partition = partitionIt(left, right-1, median);

			recQuickSort(left, partition - 1);
			recQuickSort(partition + 1, right - 1);
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
			while(theArray[++leftPtr] < pivot)
				compCount++;
			while(theArray[--rightPtr] > pivot)
				compCount++;

			if(leftPtr >= rightPtr) {
				compCount++;
				break;
			}
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
		copyCount += 3;
	}

	public void manualSort(int left, int right) {
		int size = right-left+1;
		if(size <= 1)
			return;						// no sort necessary
		if(size == 2) {					// 2-sort left and right
			if( theArray[left] > theArray[right] )
				swap(left, right);
			return;
		}
		else {               // 3-sort left, center (right-1) & right
			if( theArray[left] > theArray[right-1] )
				swap(left, right-1);                // left, center
			if( theArray[left] > theArray[right] )
				swap(left, right);                  // left, right
			if( theArray[right-1] > theArray[right] )
				swap(right-1, right);               // center, right
		}
	}  // end manualSort()

	public void displayCopyCount() {
		System.out.println("Copy Count: " + copyCount);
	}

	public void displayCompCount() {
		System.out.println("Comp Count: " + compCount);
	}
}

class QuickSort2App {
	public static void main(String[] args) {
		int maxSize = 11;             // array size
		ArrayIns arr;
		arr = new ArrayIns(maxSize);  // create array
		for(int j=0; j<maxSize; j++) { // fill array with random numbers
			double n = (int)(java.lang.Math.random()*99);
			arr.insert(n);
		}
		arr.display();                // display items
		arr.quickSort();              // quicksort them
		arr.display();                // display them again
		arr.displayCopyCount();
		arr.displayCompCount();
	}  // end main()
}  // end class QuickSort2App




