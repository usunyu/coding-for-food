import java.io.*;

class lowArray {
	private double[] a;								// ref to array a

	public lowArray(int size) {						// constructor
		a = new double[size];
	}

	public void setElem(int index, double value) {	// put element into array
		a[index] = value;
	}

	public double getElem(int index) {				// get element from array
		return a[index];
	}
}	// end class LowArray

////////////////////////////////////////////////////////////////

class lowArrayApp {
	public static void main(String[] args) {
		lowArray arr;								// reference
		arr = new lowArray(100);					// create LowArray object
		int nElems = 0;								// number of items in array
		int j;										// loop variable

		arr.setElem(0, 77);							// insert 10 items
		arr.setElem(1, 99);
		arr.setElem(2, 44);
		arr.setElem(3, 55);
		arr.setElem(4, 22);
		arr.setElem(5, 88);
		arr.setElem(6, 11);
		arr.setElem(7, 00);
		arr.setElem(8, 66);
		arr.setElem(9, 33);
		nElems = 10;								// now 10 items in array
		//-------------------------------------------------------------
		for(j=0; j<nElems; j++)						// display items
			System.out.print(arr.getElem(j) + " ");
		System.out.println("");
		//-------------------------------------------------------------
		int searchKey = 26;							// search for data item
		for(j=0; j<nElems; j++)						// for each element,
			if(arr.getElem(j) == searchKey)			// found item?
				break;
		if(j == nElems)								// no
			System.out.println("Can't find " + searchKey);
		else										// yes
			System.out.println("Found " + searchKey);
		//-------------------------------------------------------------
		for(j=0; j<nElems; j++)						// delete value 55
			if(arr.getElem(j) == 55)				// look for it
				break;
		for(int k=j; k<nElems; k++)					// move higher down
			arr.setElem(k, arr.getElem(k + 1));
		nElems--;									// decrement size
		//-------------------------------------------------------------
		for(j=0; j<nElems; j++)						// display items
			System.out.print( arr.getElem(j) + " ");
		System.out.println("");
	}	// end main()
}	// end class LowArrayApp


