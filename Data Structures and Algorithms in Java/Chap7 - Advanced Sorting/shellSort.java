class ArraySh {
	private double[] theArray;
	private int nElems;

	public ArraySh(int max) {
		theArray = new double[max];
		nElems = 0;
	}

	public void insert(double value) {
		if(nElems < theArray.length)
			theArray[nElems++] = value;
	}

	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.print(theArray[i] + " ");
		}
		System.out.println("");
	}

	public void shellSort() {
		int inner, outer;
		double temp;

		int h = 1;
		while(h <= nElems / 3)
			h = h * 3 + 1;

		while(h > 0) {
			for(outer = h; outer < nElems; outer++) {
				temp = theArray[outer];

				for(inner = outer; inner >= h && theArray[inner - h] >= temp; inner -= h) {
					theArray[inner] = theArray[inner - h];
				}

				theArray[inner] = temp;
			}
			h = (h - 1) / 3;
		}
	}
}

class ShellSortApp {
	public static void main(String[] args) {
		int maxSize = 10;
		ArraySh arr;
		arr = new ArraySh(maxSize);

		for(int j=0; j<maxSize; j++) {
			double n = (int)(java.lang.Math.random()*99); // random numbers
			arr.insert(n);
		}

		arr.display();                // display unsorted array
		arr.shellSort();              // shell sort the array
		arr.display();                // display sorted array
	}
}


