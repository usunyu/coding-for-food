import java.util.ArrayList;
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
	ArrayList<Item> queue;
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		queue = new ArrayList<Item>();
	}
	
	// is the queue empty?
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	// return the number of items on the queue
	public int size() {
		return queue.size();
	}
	
	// add the item
	public void enqueue(Item item) {
		queue.add(item);
	}
	
	// delete and return a random item
	public Item dequeue() {
		if(isEmpty()) throw new java.util.NoSuchElementException("no such element");
		int index = StdRandom.uniform(size());
		Item ret = queue.get(index);
		queue.remove(index);
		return ret;
	}
	
	// return (but do not delete) a random item
	public Item sample() {
		if(isEmpty()) throw new java.util.NoSuchElementException("no such element");
		int index = StdRandom.uniform(size());
		Item ret = queue.get(index);
		return ret;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIteraotr();
	}
	
	private class RandomizedQueueIteraotr implements Iterator<Item> {
		int current;
		int[] order;
		
		public RandomizedQueueIteraotr() {
			current = 0;
			order = new int[queue.size()];
			for(int i = 0; i < queue.size(); i++) {
				order[i] = i;
			}
			StdRandom.shuffle(order);
		}
		
		@Override
		public boolean hasNext() {
			return current < order.length;
		}

		@Override
		public Item next() {
			return queue.get(order[current++]);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("unsupported operation");
		}
		
	}
	
	// unit testing
	public static void main(String[] args) {
		RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
		for(int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}
		Iterator<Integer> it = queue.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
