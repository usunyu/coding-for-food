/*
Example:
       Machine M1 contains 3 numbers: {30, 40, 50}
       Machine M2 contains 2 numbers: {35, 45} 
       Machine M3 contains 5 numbers: {10, 60, 70, 80, 100}
       
       Output: {10, 30, 35, 40, 45, 50, 60, 70, 80, 100}

http://www.geeksforgeeks.org/sort-numbers-stored-on-different-machines/
*/

import java.util.ArrayList;
import java.util.Arrays;

class MinHeapNode {
	int value;
	int machine;	// machine no
	int index;		// index in the machine

	public MinHeapNode(int value, int machine, int index) {
		this.value = value;
		this.machine = machine;
		this.index = index;
	}

	@Override
	public String toString() {
		return "MinHeapNode [value=" + value + ", machine=" + machine
				+ ", index=" + index + "]";
	}
}

class MinHeap {
	MinHeapNode[] heap;
	int size;

	public MinHeap(int capacity) {
		heap = new MinHeapNode[capacity];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(MinHeapNode node) {
		if(size == heap.length) return;
		heap[size] = node;
		size++;
		upHeapify();
	}

	private void swap(int i, int j) {
		MinHeapNode tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}

	public void upHeapify() {
		int current = size - 1, parent = (current - 1) / 2;
		while(current > 0 && heap[current].value < heap[parent].value) {
			swap(current, parent);
			current = parent;
			parent = (current - 1) / 2;
		}
	}

	public MinHeapNode extractMin() {
		if(size == 0) return null;
		MinHeapNode min = heap[0];
		size--;
		if(size == 0) heap[0] = null;
		else {
			heap[0] = heap[size];
			heap[size] = null;
			downHeapify();
		}
		return min;
	}

	public void downHeapify() {
		int current = 0;
		while(current < size) {
			int left = 2 * current + 1, right = 2 * current + 2;
			int min = Math.min(left < size ? heap[left].value : Integer.MAX_VALUE, 
					right < size ? heap[right].value : Integer.MAX_VALUE);
			if(heap[current].value > min) {
				if(min == heap[left].value) {
					swap(current, left);
					current = left;
				}
				else {
					swap(current, right);
					current = right;
				}
			}
			else break;
		}
	}

	@Override
	public String toString() {
		return "MinHeap [heap=" + Arrays.toString(heap) + ", size=" + size
				+ "]";
	}
}

class Machines {
	ArrayList<ArrayList<Integer>> sortedLists;

	public Machines() {
		sortedLists = new ArrayList<ArrayList<Integer>>();
	}

	public void input(ArrayList<Integer> input) {
		sortedLists.add(input);
	}

	public ArrayList<Integer> externalSort() {
		ArrayList<Integer> output = new ArrayList<Integer>();
		int capacity = sortedLists.size();
		MinHeap heap = new MinHeap(capacity);
		// initial
		for(int i = 0; i < sortedLists.size(); i++) {
			int val = sortedLists.get(i).get(0);
			MinHeapNode node = new MinHeapNode(val, i, 0);
			heap.insert(node);
		}
		// process
		while(!heap.isEmpty()) {
			MinHeapNode min = heap.extractMin();
			output.add(min.value);
			ArrayList<Integer> list = sortedLists.get(min.machine);
			if(min.index < list.size() - 1) {
				int next = min.index + 1;
				MinHeapNode node = new MinHeapNode(list.get(next), min.machine, next);
				heap.insert(node);
			}
		}
		return output;
	}
}

class Main {
	public static void main(String[] args) {
		Machines machines = new Machines();

		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(30); list1.add(40); list1.add(50);
		machines.input(list1);
		System.out.println("Machine M1 contains: " + list1);
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(35); list2.add(45);
		machines.input(list2);
		System.out.println("Machine M1 contains: " + list2);
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(10); list3.add(60); list3.add(70); list3.add(80); list3.add(100);
		machines.input(list3);
		System.out.println("Machine M1 contains: " + list3);
		System.out.print("Output: ");
		System.out.println(machines.externalSort());
	}
}