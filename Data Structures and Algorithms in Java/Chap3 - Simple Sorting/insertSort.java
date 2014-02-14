import java.io.*;
import java.util.*;

class ArrayIns {
	private double[] a;
	private int nElems;

	public ArrayIns(int max) {
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

	public void insertionSort() {
		int countCopy = 0;
		int countComp = 0;
		for(int i = 1; i < nElems; i++) {
			double temp = a[i];
			int j;
			for(j = i - 1; j >= 0; j--) {
				countComp++;
				if(temp < a[j]) {
					a[j + 1] = a[j];
					countCopy++;
				}
				else
					break;
			}
			a[++j] = temp;
		}
		System.out.println("Compare " + countComp + " times");
		System.out.println("Copy " + countCopy + " times");
	}

	private void checkDup() {
		HashSet<Double> set	= new HashSet<Double>();
		for(int i = 0; i < nElems; i++) {
			if(set.contains(a[i]))
				a[i] = -1;
			else
				set.add(a[i]);
		}
	}

	private void removeDup() {
		int count = 0;
		for(int i = 0; i < nElems; i++) {
			if(a[i] == -1)
				count++;
			else
				break;
		}
		for(int i = count; i < nElems; i++) {
			a[i - count] = a[i];
		}
		nElems -= count;
	}

	public void insertionSort2() {
		checkDup();
		insertionSort();
		removeDup();
	}

	public void swap(int a1, int a2) {
		double temp = a[a1];
		a[a1] = a[a2];
		a[a2] = temp;
	}

	public double median() {
		insertionSort();
		int mid = nElems / 2;
		return a[mid];
	}

	public  void noDups() {
		int i = 0, j = 0;
		while(i < nElems) {
			if(a[i] != a[i + 1]) {
				a[j++] = a[i++];
			}
			else
				i++;
		}
		nElems = j;
	}
}

class InsertSortApp {
	public static void main(String[] args) {
		int maxSize = 100;            // array size
		ArrayIns arr;                 // reference to array
		arr = new ArrayIns(maxSize);  // create the array
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(44);
		arr.insert(00);
		arr.insert(66);
		arr.insert(44);
		arr.insert(33);
		// arr.insert(00);
		// arr.insert(11);
		// arr.insert(22);
		// arr.insert(33);
		// arr.insert(44);
		// arr.insert(55);
		// arr.insert(66);
		// arr.insert(77);
		// arr.insert(88);
		// arr.insert(99);

		arr.display();
		// arr.insertionSort();
		arr.insertionSort2();
		arr.display();

		// double mid = arr.median();
		// System.out.println("median:" + mid);

		// arr.noDups();
		// arr.display();
	}
}