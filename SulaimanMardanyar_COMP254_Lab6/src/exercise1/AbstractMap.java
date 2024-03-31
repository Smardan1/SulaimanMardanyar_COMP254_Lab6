package exercise1;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V>{
	public boolean isEmpty() { return size() == 0; }

	protected static class MapEntry<K, V> implements Entry<K, V> {
		private K k;
		private V v;
		public MapEntry(K key, V value) {
			k = key;
			v = value;
		}
		//Getters
		@Override
		public K getKey() {
			return k;
		}
		@Override
		public V getValue() {
			return v;
		}
		//Setters
		protected void setKey(K key) { k = key; }
		protected V setValue(V value) {
			//store old value reference
			V oldValue = v;
			// now replace with new value
			v = value;
			return oldValue;
		}
	}

	/**
	 * An iterator that loops through the keys
	 * 	Changing the implementation of some of the base Iterator methods
	 * 	Such as the next() & remove() methods
	 * */
	private class KeyIterator implements Iterator<K> {
		private Iterator<Entry<K, V>> entries = entrySet().iterator();
		public boolean hasNext() { return entries.hasNext(); }
		public K next() { return entries.next().getKey(); }
		public void remove() { throw new UnsupportedOperationException(); }
	}
	/**
	 * A keyIterable allowing for multiple instances of KeyIterators.
	 * **/
	private class KeyIterable implements Iterable<K> {
		public Iterator<K> iterator() { return new KeyIterator(); }
	}
	/**Create an instance of the KeyIterable**/
	public Iterable<K> keySet() { return new KeyIterable(); }
	/**
	 * A value iterator that loops through all the values
	 * Changing the implementation of some of the base Iterator methods
	 * Such as the next() & remove() methods
	 **/
	private class ValueIterator implements Iterator<V> {
		private Iterator<Entry<K, V>> entries = entrySet().iterator();
		public boolean hasNext() { return entries.hasNext(); }
		public V next() { return entries.next().getValue(); }
		public void remove() { throw new UnsupportedOperationException(); }
	}
	/**
	 * A ValueIterable allowing for multiple instances of ValueIterators.
	 * **/
	private class ValueIterable implements Iterable<V> {
		public Iterator<V> iterator() { return new ValueIterator(); }
	}
	/**Create an instance of the ValueIterable**/
	public Iterable<V> values() { return new ValueIterable(); }

}
