import java.io.*;

class OrdArray {
	private double[] a;
	private int nElems;

	public OrdArray(int max) {
		a = new double[max];
		nElems = 0;
	}

	public int size() {
		return nElems;
	}

	public int find(double searchKey) {
		int lowerBound = 0;
		int upperBound = nElems - 1;
		int curIn;
		while(lowerBound <= upperBound)
		{
			curIn = (lowerBound + upperBound) / 2;
			if(a[curIn] == searchKey)
				return curIn;
			else
			{
				if(a[curIn] < searchKey)
					lowerBound = curIn + 1;
				else
					upperBound = curIn - 1;
			}
		}
		return nElems;
	}

	public void insertB(double value) {
		int low = 0, high = nElems - 1;
		int cur = 0;
		// find the position
		int flag = 0;
		while(low <= high) {
			cur = (low + high) / 2;
			if(a[cur] == value)
				break;
			else {
				if(a[cur] < value) {
					low = cur + 1;
					flag = 1;
				}
				else {
					high = cur - 1;
					flag = -1;
				}
			}
		}
		if(flag == 1) {
			for(int i = nElems; i > cur + 1; i--) {
				a[i] = a[i - 1];
			}
			a[cur + 1] = value;
		}
		else {
			for(int i = nElems; i > cur; i--) {
				a[i] = a[i - 1];
			}
			a[cur] = value;
		}
		nElems++;
	}

	public void insert(double value) {
		int j;
		for(j = 0; j < nElems; j++)
			if(a[j] > value)
				break;
		for(int k = nElems; k > j; k--) {
			a[k] = a[k - 1];
		}
		a[j] = value;
		nElems++;
	}

	public boolean delete(double value) {
		int j = find(value);
		if(j == nElems)
			return false;
		else
		{
			for(int i = j; i < nElems; i++)
				a[i] = a[i + 1];
			nElems--;
			return true;
		}
	}

	public void display() {
		for(int i = 0; i < nElems; i++)
			System.out.print(a[i] + " ");
		System.out.println("");
	}

	public double get(int i) {
		return a[i];
	}

	public  OrdArray merge(OrdArray b) {
		int maxSize = 100;
		if(nElems + b.size() > maxSize)
			return null;
		OrdArray c = new OrdArray(maxSize);
		int i = 0, j = 0, k = 0;
		while(i < nElems && j < b.size()) {
			if(a[i] < b.get(j))
				c.insertB(a[i++]);
			else
				c.insertB(b.get(j++));
		}
		if(i == nElems) {
			while(j < b.size())
				c.insertB(b.get(j++));
		}
		if(j == b.size()) {
			while(i < nElems)
				c.insertB(a[i++]);
		}
		return c;
	}
}

class OrderedApp {
	public static void main(String[] args) {
		int maxSize = 100;
		OrdArray arr;
		arr = new OrdArray(maxSize);
		arr.insertB(77);
		arr.insertB(99);
		arr.insertB(44);
		arr.insertB(55);
		arr.insertB(22);
		arr.insertB(88);
		arr.insertB(11);
		arr.insertB(00);
		arr.insertB(66);
		arr.insertB(33);
		System.out.print("A:\t");
		arr.display();
		/* prog 2.5 */
		OrdArray b = new OrdArray(maxSize);
		b.insertB(78);
		b.insertB(98);
		b.insertB(45);
		b.insertB(54);
		b.insertB(23);
		b.insertB(89);
		b.insertB(13);
		b.insertB(06);
		b.insertB(69);
		b.insertB(32);
		System.out.print("B:\t");
		b.display();

		OrdArray c = arr.merge(b);
		System.out.print("Merge:\t");
		c.display();

		/* original
		int searchKey = 55;
		if(arr.find(searchKey) != arr.size())
			System.out.println("Found " + searchKey);
		else
			System.out.println("Can't find " + searchKey);

		arr.display();
		arr.delete(00);
		arr.delete(55);
		arr.delete(99);
		arr.display();
		*/
	}
}




