import java.util.HashMap;

class TrieNode {
	char value;
	int count;
	HashMap<Character, TrieNode> childrens;

	public TrieNode() {
		childrens = new HashMap<Character, TrieNode>();
	}

	public TrieNode(char value) {
		this();
		this.value = value;
	}
}

class Trie {
	TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public int insert(String word) {
		if(word == null) return -1;
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode child;
			if(node.childrens.containsKey(ch)) {
				child = node.childrens.get(ch);
			}
			else {
				child = new TrieNode(ch);
				node.childrens.put(ch, child);
			}
			node = child;
			if(i == word.length() - 1) node.count++;
		}
		return node.count;
	}
}

class MinHeapNode {
	String word;
	int count;

	public MinHeapNode(String word, int count) {
		this.word = word;
		this.count = count;
	}
}

class MinHeap {
	MinHeapNode[] heap;
	int size;
	HashMap<String, Integer> cache;	// word, index in the minHeap

	public MinHeap(int capacity) {
		heap = new MinHeapNode[capacity];
		cache = new HashMap<String, Integer>();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(MinHeapNode node) {
		if(cache.containsKey(node.word)) {	// the word is already in the heap
			int index = cache.get(node.word);
			heap[index].count = node.count;
			downHeapify(index);
		}
		else if(size > 0 && peek().count < node.count) {	// the new come node's count is large
			extractMin();	// remove min one
			insert(node);	// insert again
		}
		else if(size < heap.length) {	// heap is not full
			heap[size] = node;
			// cache the new word
			cache.put(node.word, size);
			upHeapify(size);
			size++;
		}
	}

	public MinHeapNode peek() {
		return heap[0];
	}

	private void swap(int i, int j) {
		MinHeapNode tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}

	public void upHeapify(int index) {
		int current = index, parent = (current - 1) / 2;
		while(current > 0 && heap[current].count < heap[parent].count) {
			// update parent's index
			cache.put(heap[parent].word, current);
			swap(current, parent);
			current = parent;
			parent = (current - 1) / 2;
		}
		// update current's index
		cache.put(heap[current].word, current);
	}

	public MinHeapNode extractMin() {
		if(size == 0) return null;
		MinHeapNode min = heap[0];
		// remove in cahce
		cache.remove(min.word);
		size--;
		if(size == 0) heap[0] = null;
		else {
			heap[0] = heap[size];
			// update cahce
			cache.put(heap[0].word, 0);
			heap[size] = null;
			downHeapify(0);
		}
		return min;
	}

	public void downHeapify(int index) {
		int current = index;
		while(current < size) {
			int left = 2 * current + 1, right = 2 * current + 2;
			int min = Math.min(left < size ? heap[left].count : Integer.MAX_VALUE, 
					right < size ? heap[right].count : Integer.MAX_VALUE);
			if(heap[current].count > min) {
				if(min == heap[left].count) {
					// update child's index
					cache.put(heap[left].word, current);
					swap(current, left);
					current = left;
				}
				else {
					// update child's index
					cache.put(heap[right].word, current);
					swap(current, right);
					current = right;
				}
				// update current's index
				cache.put(heap[current].word, current);
			}
			else break;
		}
	}
}

class Controller {
	Trie trie;
	MinHeap heap;
	String[] input;

	public Controller(int K) {
		trie = new Trie();
		heap = new MinHeap(K);
	}

	public void input(String text) {
		input = text.split(" ");
	}

	public void process() {
		for(int i = 0; i < input.length; i++) {
			int count = trie.insert(input[i]);
			MinHeapNode node = new MinHeapNode(input[i], count);
			heap.insert(node);
		}
	}

	public void output() {
		while(!heap.isEmpty()) {
			MinHeapNode min = heap.extractMin();
			System.out.println(min.word + " : " + min.count);
		}
	}
}

class Main {
	public static void main(String[] args) {
		Controller controller = new Controller(5);
		String text = "Welcome to the world of Geeks This portal has been created to provide well written well thought and well explained solutions for selected questions If you like Geeks for Geeks and would like to contribute here is your chance You can write article and mail your article to contribute at geeksforgeeks org See your article appearing on the Geeks for Geeks main page and help thousands of other Geeks";
		controller.input(text);
		controller.process();
		controller.output();
	}
}
