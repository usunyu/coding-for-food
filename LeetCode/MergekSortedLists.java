/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

import java.util.*;
import LCLibrary.*;

class NodeInQ {
	int val;
	int index;

	NodeInQ(int x, int i) {
		val = x;
		index = i;
	}
}

class Solution {
	private ListNode encapsulate(ListNode node, int i, PriorityQueue<NodeInQ> priorityQ) {
		NodeInQ nodeInQ = null;
		if (node != null) {
			nodeInQ = new NodeInQ(node.val, i);
			priorityQ.add(nodeInQ);
			node = node.next;
		}
		return node;
	}

	// time complexity : NlogSize
	// space complexity : Size
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		// Note: The Solution object is instantiated only once and is reused by each test case.
		if(lists == null || lists.size() == 0) {
			return null;
		}
		int size = lists.size();
		PriorityQueue<NodeInQ> priorityQ = new PriorityQueue<NodeInQ>( size, new Comparator<NodeInQ>() {
					public int compare(NodeInQ n1, NodeInQ n2) {
						return n1.val - n2.val;
					}
				});
		ListNode head = null;
		ListNode[] pointer = new ListNode[size];
		// initial
		for (int i = 0; i < size; i++) {
			pointer[i] = lists.get(i);
			pointer[i] = encapsulate(pointer[i], i, priorityQ);
		}

		while (!priorityQ.isEmpty()) {
			NodeInQ nodeInQ = priorityQ.poll();
			int i = nodeInQ.index;
			int x = nodeInQ.val;
			ListNode temp = new ListNode(x);
			if (head == null) {
				head = temp;
			} else {
				ListNode p = head;
				while (p.next != null) {
					p = p.next;
				}
				p.next = temp;
			}
			pointer[i] = encapsulate(pointer[i], i, priorityQ);
		}
		return head;
	}

	// time complexity : NlogSize
	// space complexity : Size
	public ListNode mergeKLists2(ArrayList<ListNode> lists) {
		// Note: The Solution object is instantiated only once and is reused by each test case.
		if(lists == null || lists.size() == 0) {
			return null;
		}
		int size = lists.size();
		Comparator<ListNode> comparator = new Comparator<ListNode>() {
	        @Override
	        public int compare(ListNode n1, ListNode n2) {
	            if (n1.val < n2.val)
	                return -1;
	            else if (n1.val > n2.val)
	                return 1;
	            else
	                return 0;
	        }
	    };
	    PriorityQueue<ListNode> priorityQ = new PriorityQueue<ListNode>(size, comparator);

		ListNode head = null;
		ListNode current = null;
		for(int i = 0; i < size; i++) {
			if(lists.get(i) != null) {
				priorityQ.add(lists.get(i));
			}
		}
		while(!priorityQ.isEmpty()) {
			ListNode temp = priorityQ.poll();
			if(head == null) {
				head = temp;
				current = temp;
			}
			else {
				current.next = temp;
				current = temp;
			}
			if(current.next != null) {
				priorityQ.add(current.next);
			}
		}
		return head;
	}
	/*
		Second Round
	*/
	// O(nlogk)
	public ListNode mergeKLists3(ArrayList<ListNode> lists) {
		if(lists == null || lists.size() == 0) return null;
        MinHeap minHeap = new MinHeap(lists.size());
        ArrayList<ListNode> ptr = new ArrayList<ListNode>(lists);
        // initial
        for(int i = 0; i < ptr.size(); i++) {
        	ListNode node = ptr.get(i);
        	if(node != null)
        		minHeap.add(node);
        }
        ListNode head = null, prev = null;
        while(!minHeap.isEmpty()) {
        	ListNode node = minHeap.extract();
        	if(head == null) {
        		head = node;
        		prev = node;
        	}
        	else {
        		prev.next = node;
        		prev = node;
        	}
        	ListNode next = node.next;
        	if(next != null) {
        		minHeap.add(next);
        	}
        }
        return head;
    }
}

class MinHeap {
	ListNode[] heap;
    int size = 0;

    public MinHeap(int capacity) {
    	heap = new ListNode[capacity];
    }

    public boolean isFull() {
    	return size == heap.length;
    }

    public boolean isEmpty() {
    	return size == 0;
    }

    public void add(ListNode node) {
    	heap[size] = node;
    	upHeapify(size++);
    }

	public ListNode extract() {
		ListNode top = heap[0];
		heap[0] = heap[--size];
		downHeapify(0);
		return top;
    }

    private void swap(int i, int j) {
    	ListNode tmp = heap[i];
    	heap[i] = heap[j];
    	heap[j] = tmp;
    }

    private void upHeapify(int index) {
    	while(index > 0) {
    		int parent = (index - 1) / 2;
    		if(heap[parent].val > heap[index].val)
    			swap(parent, index);
    		else
    			break;
    		index = parent;
    	}
    }

    private void downHeapify(int index) {
    	while(index < size) {
    		int left = 2 * index + 1, right = 2 * index + 2;
    		int child;
    		if(right < size)
    			child = heap[left].val < heap[right].val ? left : right;
    		else if(left < size)
    			child = left;
    		else break;
    		if(heap[child].val < heap[index].val)
    			swap(child, index);
    		else break;
    		index = child;
    	}
    }
}

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		ListNode head = solution.mergeKLists3(Input.buildExampleLists());
		Output.printList(head);
	}
}