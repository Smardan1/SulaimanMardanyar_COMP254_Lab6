package exercise1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**UnsortedTableMap has methods that all run in O(n) because they all need to iterate through the whole map**/
public class UnsortedTableMap<K, V> extends AbstractMap<K, V>{
	/**Creating an array list of MapEntry containing Key value pairs**/
	private ArrayList<MapEntry<K, V>> table = new ArrayList<>();
	public UnsortedTableMap() { } // Constructing an empty map

	public int size() { return table.size(); }

	/**
	 * Using the ArrayList Method .get() to get the index
	 * then the MapEntry method .getKey()
	 * then comparing it with the key passed into the function.
	 * return 1 if key matches, meaning the key is found in 'table'
	 * otherwise return a -1 meaning the key was not found
	 * Runs in O(n)
	 **/
	private int findIndex(K key) {
		int n = table.size();
		for (int j = 0; j < n; j++) {
			if(table.get(j).getKey().equals(key)) { return j; }
		}
		return -1;
	}

	/**
	 * Using the findIndex method to first find the key
	 * if found then return the value the key is paired with
	 * otherwise null
	 * Runs in O(n) because it is using the findIndex method which runs in O(n)
	 * **/
	public V get(K key) {
		int j = findIndex(key);
		if(j == -1) { return null; }
		return table.get(j).getValue();
	}

	/**
	 * Putting a new Key Value pair into the table
	 * Runs in O(n)
	 * **/
	public V put(K key, V value) {
		int j = findIndex(key);
		if(j == -1){
			table.add(new MapEntry<>(key, value));
			return null; // we did not overwrite anything so return null signifying that a new entry was inserted
		}
		return table.get(j).setValue(value); // return the value we overwrote
	}

	@Override
	public V remove(K key) {
		int j = findIndex(key);
		int n = size();
		if(j == -1) {
			return null; //we did not find a key to remove, return null
		}
		//store the value of the entry we will remove
		V answer = table.get(j).getValue();
		//check if the index of j is the last entry in the table
		if(j != n-1) {
			//Get the value of the entry at the end of the table and insert it into the index of j
			table.set(j, table.get(n-1));
		}
		//now remove the last entry as it is a duplicate
		table.remove(n-1);
		return answer; //return the value we changed
	}

	private class EntryIterator implements Iterator<Entry<K, V>> {
		private int j = 0; // act as an index
		public boolean hasNext() { return j < table.size(); }
		public Entry<K, V> next() {
			if(j == table.size()) { throw new NoSuchElementException(); }
			return table.get(j++);
		}
		public void remove() { throw new UnsupportedOperationException(); }
	}
	private class EntryIterable implements Iterable<Entry<K, V>> {
		public Iterator<Entry<K, V>> iterator() { return new EntryIterator(); }
	}
	@Override
	public Iterable<Entry<K, V>> entrySet() { return new EntryIterable(); }

	public static void main(String[] args) {
		UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();

		map.put(1, "Hello");
		map.put(2,"Goodbye");
		map.put(3, "Today");
		map.put(4, "Tomorrow");

		System.out.println(map.get(3));
		System.out.println(map.get(1));

		System.out.println(map.remove(2));
		System.out.println(map.get(2)); //removed the key at that index and replaced with key 4
		System.out.println(map.get(4));

	}
}
