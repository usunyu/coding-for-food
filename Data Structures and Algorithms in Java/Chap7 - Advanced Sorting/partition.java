class ArrayPar {
	private double[] theArray;
	private int nElems;

	public ArrayPar(int max) {
		theArray = new double[max];
		nElems = 0;
	}

	public double get(int index) {
		return theArray[index];
	}

	public void insert(double value) {
		if(nElems <= theArray.length)
			theArray[nElems++] = value;         // insert it
	}

	public int size() { return nElems; }

	public void display() {
		for(int j=0; j<nElems; j++)       // for each element,
			System.out.print(theArray[j] + " ");  // display it
		System.out.println("");
	}

	public int partitionIt(int left, int right, double pivot) {
		int leftPtr = left - 1;
		int rightPtr = right + 1;

		while(true) {
			while(leftPtr < right && theArray[++leftPtr] < pivot);
			while(rightPtr > left && theArray[--rightPtr] > pivot);

			if(leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		return leftPtr;
	}

	public int partitionIt2(int left, int right, double pivot) {
		int leftPtr = left;
		int rightPtr = right - 1;
		while(true) {
			while(leftPtr < right && theArray[leftPtr] < pivot)
				leftPtr++;
			while(rightPtr > left && theArray[rightPtr] > pivot)
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
}

class PartitionApp {
	public static void main(String[] args) {
		int maxSize = 16;             // array size
		ArrayPar arr;                 // reference to array
		arr = new ArrayPar(maxSize);  // create the array
		for(int j=0; j<maxSize; j++) {  // fill array with   
			double n = (int)(java.lang.Math.random()*199); // random numbers
			arr.insert(n);
		}
		arr.display();                // display unsorted array
		// double pivot = 100;            // pivot value
		double pivot = arr.get(maxSize - 1);
		System.out.print("Pivot is " + pivot);
		int size = arr.size();
                                       // partition array
		int partDex = arr.partitionIt2(0, size-1, pivot);
		System.out.println(", Partition is at index " + partDex);
		arr.display();                // display sorted array
	}  // end main()
}  // end class PartitionApp

