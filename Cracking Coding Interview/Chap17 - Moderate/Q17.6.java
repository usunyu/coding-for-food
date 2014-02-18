import java.util.PriorityQueue;
import java.util.Comparator;

class Range {
	int left;
	int right;

	public String toString() {
		return "(" + left + "," + right + ")";
	}
}

class Main {
	public static Range findMinRange(int[] array) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(100);

		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(100,
			new Comparator<Integer>(){
				public int compare(Integer a, Integer b){
					if(a < b) return 1;
					else if(a == b) return 0;
					else return -1;
				}
			}
		);

		for(int i = 0; i < array.length; i++) {
			minHeap.add(array[i]);
			maxHeap.add(array[i]);
		}

		Range range = new Range();
		// search left
		for(int i = 0; i < array.length; i++) {
			int val = minHeap.poll();
			if(array[i] != val) {
				range.left = i;
				break;
			}
		}
		// search right
		for(int i = array.length - 1; i >= 0; i--) {
			int val = maxHeap.poll();
			if(array[i] != val) {
				range.right = i;
				break;
			}
		}

		return range;
	}

	public static void main(String[] args) {
		int[] array = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
		System.out.println(findMinRange(array));
	}
}