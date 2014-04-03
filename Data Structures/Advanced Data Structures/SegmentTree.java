/*
http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
*/

class SegmentTree {
	int[] array;
	int[] tree; // for sum
	int[] tree2; // for min

	/* Function to construct segment tree from given array. This function
	   allocates memory for segment tree and calls constructSTUtil() to
	   fill the allocated memory */
	public SegmentTree(int[] array) {
		this.array = array;
		double x = Math.ceil(Math.log(array.length) / Math.log(2));
		int size = (int)(2 * Math.pow(2, x) - 1);
		tree = new int[size];
		tree2 = new int[size];
		construct(0, array.length - 1, 0);
	}

	// A recursive function that constructs Segment Tree for array[ss..se].
	// si is index of current node in segment tree st
	private void construct(int left, int right, int st) {
		if(left == right) {	// If there is one element in array, store it in current node of
							// segment tree and return
			tree[st] = array[left];
			tree2[st] = array[left];
		}
		else if(left < right) {	// If there are more than one elements, then recur for left and
								// right subtrees and store the sum of values in this node
			int mid = left + (right - left) / 2, stl = st * 2 + 1, str = st * 2 + 2;
			construct(left, mid, stl);
			construct(mid + 1, right, str);
			tree[st] = tree[stl] + tree[str];
			tree2[st] = Math.min(tree[stl], tree[str]);
		}
	}

	/*  A recursive function to get the sum of values in given range of the array.
	    The following are parameters for this function.
	 
	    st    --> Pointer to segment tree
	    index --> Index of current node in the segment tree. Initially 0 is
	             passed as root is always at index 0
	    ss & se  --> Starting and ending indexes of the segment represented by
	                 current node, i.e., st[index]
	    qs & qe  --> Starting and ending indexes of query range */
	private int sum(int ss, int se, int qs, int qe, int index) {
		// If segment of this node is a part of given range, then return the 
    	// sum of the segment
    	if(qs <= ss && qe >= se)
        	return tree[index];
        // If segment of this node is outside the given range
    	if(se < qs || ss > qe)
        	return 0;
        // If a part of this segment overlaps with the given range
        int mid = ss + (se - ss) / 2;
        return sum(ss, mid, qs, qe, 2 * index + 1) + sum(mid + 1, se, qs, qe, 2 * index + 2);
	}

	// Return sum of elements in range from index qs (quey start) to
	// qe (query end).  It mainly uses getSumUtil()
	public int sum(int left, int right) {
		// Check for erroneous input values
		if(left < 0 || right > array.length - 1 || left > right)
			return -1;
		return sum(0, array.length - 1, left, right, 0);
	}

	/*  A recursive function to get the minimum value in a given range of array
	    indexes. The following are parameters for this function.
	 
	    st    --> Pointer to segment tree
	    index --> Index of current node in the segment tree. Initially 0 is
	             passed as root is always at index 0
	    ss & se  --> Starting and ending indexes of the segment represented by
	                 current node, i.e., st[index]
	    qs & qe  --> Starting and ending indexes of query range */
	private int min(int ss, int se, int qs, int qe, int index) {
		// If segment of this node is a part of given range, then return the 
    	// min of the segment
    	if(qs <= ss && qe >= se)
        	return tree2[index];
        // If segment of this node is outside the given range
    	if(se < qs || ss > qe)
        	return Integer.MAX_VALUE;
        // If a part of this segment overlaps with the given range
        int mid = ss + (se - ss) / 2;
        return Math.min(min(ss, mid, qs, qe, 2 * index + 1), min(mid + 1, se, qs, qe, 2 * index + 2));
	}

	// Return minimum of elements in range from index qs (quey start) to
	// qe (query end).  It mainly uses RMQUtil()
	public int min(int left, int right) {
		// Check for erroneous input values
		if(left < 0 || right > array.length - 1 || left > right)
			return -1;
		return min(0, array.length - 1, left, right, 0);
	}

	/* A recursive function to update the nodes which have the given index in
	   their range. The following are parameters
	    st, index, ss and se are same as getSumUtil()
	    i    --> index of the element to be updated. This index is in input array.
	   diff --> Value to be added to all nodes which have i in range */
	private void update(int ss, int se, int i, int diff, int index) {
		// Base Case: If the input index lies outside the range of this segment
		if(i < ss || i > se) return;
		// If the input index is in range of this node, then update the value
    	// of the node and its children
		tree[index] += diff;
		if(ss < se) {
			int mid = ss + (se - ss) / 2;
			update(ss, mid, i, diff, index * 2 + 1);
			update(mid + 1, se, i, diff, index * 2 + 2);
		}
	}

	// The function to update a value in input array and segment tree.
	// It uses updateValueUtil() to update the value in segment tree
	public void update(int i, int value) {
		// Check for erroneous input index
		if(i < 0 || i >= array.length)
			return;
		// Get the difference between new value and old value
    	int diff = value - array[i];
    	// Update the value in array
    	array[i] = value;
    	// Update the values of nodes in segment tree
    	update(0, array.length - 1, i, diff, 0);
	}
}

class Main {
	public static void main(String[] args) {
		int arr[] = {1, 3, 5, 7, 9, 11};

    	// Build segment tree from given array
    	SegmentTree segmentTree = new SegmentTree(arr);
 
    	// Print sum of values in array from index 1 to 3
    	System.out.println("Sum of values in given range = " + segmentTree.sum(1, 3));
 
    	// Update: set arr[1] = 10 and update corresponding segment tree nodes
    	segmentTree.update(1, 10);
 
    	// Find sum after the value is updated
    	System.out.println("Updated sum of values in given range = " + segmentTree.sum(1, 3));

    	// Print minimum value in arr[qs..qe]
    	System.out.println("Minimum of values in range [1, 5] is = " + segmentTree.min(1, 5));
	}
}