/*
A circus is designing a tower routine consisting of people standing atop one another's shoulders. 
For practical and aesthetic reasons, each person must be both shorter and lighter than the person 
below him or her. Given the heights and weights of each person in the circus, write a method to 
compute the largest possible number of people in such a tower.
*/
// time complexity : O(N!) -> O(N^2)
// space complexity : O(N^2) -> O(N)

import java.util.*;

class Person {
	int weight;
	int height;
	public Person(int h, int w) {
		weight = w;
		height = h;
	}

	public boolean isLargerThan(Person p) {
		if(p == null)
			return true;
		if(weight > p.weight && height > p.height)
			return true;
		else
			return false;
	}
}

class Result {
	int height;
	LinkedList<Person> pList;

	public Result(int h) {
		height = h;
		pList = new LinkedList<Person>();
	}

	public Result(Result r) {
		height = r.height;
		pList = new LinkedList<Person>(r.pList);
	}

	public Person getLast() {
		return pList.getLast();
	}

	public boolean isEmpty() {
		return pList.size() == 0;
	}

	public void addPerson(Person p) {
		pList.add(p);
	}
}

class Solution {
	// copy A to B except index
	public static void copyArrayExcept(Person[] A, Person[] B, int index) {
		for(int i = 0; i < A.length; i++) {
			if(i < index)
				B[i] = A[i];
			else if(i > index)
				B[i - 1] = A[i];
			else
				continue;
		}
	}

	public static Result maxHeight(Person[] pArray, Result res) {
		if(res == null)
			res = new Result(0);
		int maxH = 0;
		Result maxR = res;
		for(int i = 0; i < pArray.length; i++) {
			Result r = new Result(res);
			if(r.isEmpty() || r.getLast().isLargerThan(pArray[i])) {	// valid
				Person[] nPArray = new Person[pArray.length - 1];
				copyArrayExcept(pArray, nPArray, i);
				// add person
				r.height += pArray[i].height;
				r.addPerson(pArray[i]);
				r = maxHeight(nPArray, r);
				if(r.height > maxH) {
					maxH = r.height;
					maxR = r;
				}
			}
		}
		return maxR;
	}

	public static void printRes(Result res) {
		System.out.println("Max height: " + res.height);
		LinkedList<Person> pList = res.pList;
		for(Person p : pList) {
			System.out.print("(" + p.height + "," + p.weight + ") ");
		}
		System.out.println();
	}

	public static void printArr(Person[] pArray) {
		System.out.println("Array: ");
		for(int i = 0; i < pArray.length; i++)
			System.out.print("(" + pArray[i].height + "," + pArray[i].weight + ") ");
		System.out.println();
	}

	public static void quickSort(Person[] pArray, int start, int end) {
		if(start >= end)
			return;
		int index = partition(pArray, start, end);
		quickSort(pArray, start, index - 1);
		quickSort(pArray, index, end);
	}

	public static int partition(Person[] pArray, int start, int end) {
		int mid = (start + end) / 2;
		Person pivot = pArray[mid];
		int p1 = start;
		int p2 = end;
		while(p1 <= p2) {
			while(pArray[p1].height < pivot.height)
				p1++;
			while(pArray[p2].height > pivot.height)
				p2--;
			if(p1 <= p2) {
				exchange(pArray, p1, p2);
				p1++;
				p2--;
			}
		}
		return p1;
	}

	public static void exchange(Person[] pArray, int i1, int i2) {
		Person temp = pArray[i1];
		pArray[i1] = pArray[i2];
		pArray[i2] = temp;
	}

	public static void quickSort(Person[] pArray) {
		quickSort(pArray, 0, pArray.length - 1);
	}

	public static Result maxHeightDP(Person[] pArray) {
		Result[] resArr = new Result[pArray.length];
		for(int i = 0; i < resArr.length; i++) {
			resArr[i] = new Result(pArray[i].height);
			resArr[i].addPerson(pArray[i]);
		}
		for(int i = 0; i < pArray.length; i++) {
			Result maxRes = resArr[i];
			for(int j = 0; j < i; j++) {
				if(pArray[j].weight < pArray[i].weight) {
					int newHeight = resArr[j].height + pArray[i].height;
					if(newHeight > maxRes.height) {
						maxRes = new Result(resArr[j]);
						maxRes.addPerson(pArray[i]);
						maxRes.height += pArray[i].height;
					}
				}
			}
			resArr[i] = maxRes;
		}

		Result maxRes = null;
		for(int i = 0; i < resArr.length; i++) {
			if(maxRes == null)
				maxRes = resArr[i];
			else {
				if(resArr[i].height > maxRes.height) {
					maxRes = resArr[i];
				}
			}
		}
		return maxRes;
	}

	public static void main(String[] args) {
		Person[] pArray = new Person[6];
		pArray[0] = new Person(65, 100);
		pArray[1] = new Person(70, 150);
		pArray[2] = new Person(56, 90);
		pArray[3] = new Person(75, 190);
		pArray[4] = new Person(60, 95);
		pArray[5] = new Person(68, 110);
		Result res = maxHeight(pArray, null);
		printRes(res);
		quickSort(pArray);
		printArr(pArray);
		res = maxHeightDP(pArray);
		printRes(res);
	}
}