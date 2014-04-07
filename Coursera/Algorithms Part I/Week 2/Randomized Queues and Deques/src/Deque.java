import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class Deque<Item> implements Iterable<Item> {
	class Node {
		Item item;
		Node next, prev;
		
		public Node(Item item) {
			this.item = item;
		}
	}
	
	private Node first, last;
	int size;
	
	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		size = 0;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}
	
	// insert the item at the front
	public void addFirst(Item item) {
		if(item == null) throw new NullPointerException("null pointer");
		Node node = new Node(item);
		if(isEmpty()) {
			first = node;
			last = node;
		}
		else {
			node.next = first;
			first.prev = node;
			first = node;
		}
		size++;
	}
	
	// insert the item at the end
	public void addLast(Item item) {
		if(item == null) throw new NullPointerException("null pointer");
		Node node = new Node(item);
		if(isEmpty()) {
			first = node;
			last = node;
		}
		else {
			last.next = node;
			node.prev = last;
			last = node;
		}
		size++;
	}
	
	// delete and return the item at the front
	public Item removeFirst() {
		if(isEmpty()) {
			throw new java.util.NoSuchElementException("no such element");
		}
		else {
			Item ret = first.item;
			first = first.next;
			size--;
			if(isEmpty()) last = null;
			else first.prev = null;
			return ret;
		}
	}
	
	// delete and return the item at the end
	public Item removeLast() {
		if(isEmpty()) {
			throw new java.util.NoSuchElementException("no such element");
		}
		else {
			Item ret = last.item;
			last = last.prev;
			size--;
			if(isEmpty()) first = null;
			else last.next = null;
			return ret;
		}
	}
	
	// return an iterator over items in order from front to end
	@Override
	public Iterator<Item> iterator() {
		return new DequeIteraotr();
	}
	
	private class DequeIteraotr implements Iterator<Item> {
		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(current == null) throw new java.util.NoSuchElementException("no such element");
			Item ret = current.item;
			current = current.next;
			return ret;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("unsupported operation");
		}
	}
	
	// unit testing
	@Test
	public static void main(String[] args) {
		Deque<Integer> queue = new Deque<Integer>();
		for(int i = 0; i < 10; i++) {
			queue.addFirst(i);
		}
		for(int i = 0; i < 10; i++) {
			int val = queue.removeLast();
			assertEquals(val, i);
		}
	}
}
