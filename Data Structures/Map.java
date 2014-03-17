/*
reference: http://www.vogella.com/tutorials/JavaDatastructures/article.html
*/

class Entry<K, V> {
	private K key;
	private V value;

	public public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}

class Map<K, V> {
	private int size;
	private int DEFAULT_CAPACITY = 16;
	private Entry<K, V>[] values = new MyEntry[DEFAULT_CAPACITY];
}

class Test {
	public static void main(String[] args) {
		
	}
}