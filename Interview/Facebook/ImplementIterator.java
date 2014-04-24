/*
 * http://www.youtube.com/watch?v=jHNKpQI46fc
 */

import java.util.ArrayList;
import java.util.Iterator;

class Bag<T> {
	private ArrayList<T> data;
	
	public Bag() {
		data = new ArrayList<T>();
	}
	
	public void add(T e) {
		data.add(e);
	}
	
	public String toString() {
		return data.toString();
	}
	
	public class BagIterator<T> implements Iterator<T> {
		
		private int index;
		
		public BagIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return index < data.size();
		}

		@Override
		public T next() {
			return (T) data.get(index++);
		}

		@Override
		public void remove() {
			data.remove(index);
		}
	}
	
	public Iterator<T> iterator() {
		return new BagIterator<T>();
	}
}

class Main {
	public static void main(String[] args) {
		Bag<Integer> bag = new Bag<Integer>();
		bag.add(4);
		bag.add(8);
		bag.add(16);
		System.out.println(bag);
		
		for(Iterator<Integer> it = bag.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
		
		Iterator<Integer> it = bag.iterator();
		it.next();
		it.remove();
		
		System.out.println(bag);
	}
}

