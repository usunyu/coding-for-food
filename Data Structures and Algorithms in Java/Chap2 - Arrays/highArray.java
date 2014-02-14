import java.io.*;
import java.util.*;

class HighArray {
	private double[] a;
	private int nElems;

	public HighArray(int max) {
		a = new double[max];
		nElems = 0;
	}

	public double getMax() {
		double max = -1;
		for(int i = 0; i < nElems; i++)
			if(a[i] > max)
				max = a[i];
		return max;
	}

	public double removeMax() {
		double max = -1;
		int index = -1;
		for(int i = 0; i < nElems; i++) {
			if(a[i] > max) {
				max = a[i];
				index = i;
			}
		}
		// remove, do not use delete here to improve efficiency
		for(int i = index; i < nElems - 1; i++) {
			a[i] = a[i + 1];
		}
		nElems--;
		return max;
	}

	public boolean find(double searchKey) {
		int j;
		for(j = 0; j < nElems; j++)
			if(a[j] == searchKey)
				break;
		if(j == nElems)
			return false;
		else
			return true;
	}

	public void insert(double value) {
		a[nElems++] = value;
	}

	public boolean delete(double value) {
		int j;
		for(j = 0; j < nElems; j++)
			if(a[j] == value)
				break;
		if(j == nElems)
			return false;
		else {
			for(int k = j; k < nElems; k++)
				a[k] = a[k + 1];
			nElems--;
			return true;
		}
	}

	public void display() {
		for(int j = 0; j < nElems; j++)
			System.out.print(a[j] + " ");
		System.out.println("");
	}

	public void noDup() {
		Set<Double> set	= new HashSet<Double>();
		for(int i = 0; i < nElems; i++) {
			if(set.contains(a[i]))
				a[i] = -1;
			else
				set.add(a[i]);
		}
		for(int i = 0; i < nElems; i++) {
			if(a[i] == -1) {
				for(int j = i; j < nElems - 1; j++) {
					a[j] = a[j + 1];
				}
				nElems--;
			}
		}
	}
}

class HighArrayApp {
	public static void main(String[] args) {
		int maxSize = 100;             // array size
		HighArray arr;                 // reference to array
		arr = new HighArray(maxSize);  // create the array
		arr.insert(77);
		arr.insert(77);
		arr.insert(99);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(88);
		arr.insert(11);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		arr.display();                 // display items
		// prog 2.6
		arr.noDup();
		arr.display();

		// original 
		// int searchKey = 35;            // search for item
		// if( arr.find(searchKey) )
		// 	System.out.println("Found " + searchKey);
		// else
		// 	System.out.println("Can't find " + searchKey);
		
		// arr.delete(00);
		// arr.delete(55);
		// arr.delete(99);

		// prog 2.1
		// double max = arr.getMax();
		// prog 2.2
		// double max = arr.removeMax();
		// if(max != -1)
			// System.out.println("Max is " + max);

		// arr.display();

		// prog 2.3
		// HighArray ordArr = new HighArray(maxSize);
		// double max = 0;
		// while(true) {
		// 	max = arr.removeMax();
		// 	if(max != -1)
		// 		ordArr.insert(max);
		// 	else
		// 		break;
		// }
		// ordArr.display();
	}
}


