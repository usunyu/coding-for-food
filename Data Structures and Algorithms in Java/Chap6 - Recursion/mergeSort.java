import java.io.*;

class DArray {
	private double[] theArray;        // ref to array theArray
	private int nElems;               // number of data items

	public DArray(int max) {
		theArray = new double[max];
		nElems = 0;
	}

	public void insert(double value) {
		theArray[nElems] = value;      // insert it
		nElems++;                      // increment size
	}

	public void display() {
		for(int j=0; j<nElems; j++)    // for each element,
			System.out.print(theArray[j] + " ");  // display it
		System.out.println("");
	}

	public void mergeSort() {
		double[] workSpace = new double[nElems];
		recMergeSort(workSpace, 0, nElems-1);
	}

	private void recMergeSort(double[] workSpace, int lowerBound, int upperBound) {
		if(lowerBound == upperBound)
			return;
		else {
			int mid = (lowerBound + upperBound) / 2;

			recMergeSort(workSpace, lowerBound, mid);
			recMergeSort(workSpace, mid + 1, upperBound);

			merge(workSpace, lowerBound, mid + 1, upperBound);
		}
	}

	private void merge(double[] workSpace, int lowPtr, int highPtr, int upperBound) {
		int i = 0;
		int mid = highPtr - 1;
		int lowerBound = lowPtr;
		int n = upperBound - lowerBound + 1;
		while(lowPtr <= mid && highPtr <= upperBound) {
			if(theArray[lowPtr] < theArray[highPtr])
				workSpace[i++] = theArray[lowPtr++];
			else
				workSpace[i++] = theArray[highPtr++];
		}
		while(lowPtr <= mid) {
			workSpace[i++] = theArray[lowPtr++];
		}
		while(highPtr <= upperBound) {
			workSpace[i++] = theArray[highPtr++];
		}
		for(i = 0; i < n; i++) {
			theArray[lowerBound + i] = workSpace[i];
		}
	}
}

class MergeSortApp {
	public static void main(String[] args) {
		int maxSize = 100;
		DArray arr;
		arr = new DArray(maxSize);
		arr.insert(64);
		arr.insert(21);
		arr.insert(33);
		arr.insert(70);
		arr.insert(12);
		arr.insert(85);
		arr.insert(44);
		arr.insert(3);
		arr.insert(99);
		arr.insert(0);
		arr.insert(108);
		arr.insert(36);

		arr.display();
		arr.mergeSort();
		arr.display();
	}
}



