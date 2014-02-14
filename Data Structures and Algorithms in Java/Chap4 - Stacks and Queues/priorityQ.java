import java.io.*;

class PriorityQ {
	private int maxSize;
	private double[] queArray;
	private int nItems;

	public PriorityQ(int s) {
		maxSize = s;
		queArray = new double[maxSize];
		nItems = 0;
	}

	public void insert(double item) {
		if(nItems == 0)
			queArray[nItems++] = item;
		else {
			int j;
			for(j = nItems - 1; j >= 0; j--)
				if(item > queArray[j])
					queArray[j + 1] = queArray[j];
				else
					break;
			queArray[++j] = item;
			nItems++;
		}
	}

	public void insert2(double item) {
		queArray[nItems++] = item;
	}

	public double remove() {return queArray[--nItems];}

	public double remove2() {
		double min = 999;
		int index = 0;
		// find
		for(int i = 0; i < nItems; i++) {
			if(queArray[i] < min) {
				min = queArray[i];
				index = i;
			}
		}
		// remove
		for(int i = index; i < nItems - 1; i++) {
			queArray[i] = queArray[i + 1];
		}
		nItems--;
		return min;
	}

	public double peekMin() {return queArray[nItems - 1];}

	public boolean isEmpty() {return nItems == 0;}

	public boolean isFull() {return nItems == maxSize;}
}

class PriorityQApp {
	public static void main(String[] args) {
		PriorityQ thePQ = new PriorityQ(5);
		thePQ.insert2(30);
		thePQ.insert2(50);
		thePQ.insert2(10);
		thePQ.insert2(40);
		thePQ.insert2(20);
		while( !thePQ.isEmpty() ) {
			double item = thePQ.remove2();
			System.out.print(item + " ");  // 10, 20, 30, 40, 50
		}  // end while
		System.out.println("");
	}  // end main()
}



