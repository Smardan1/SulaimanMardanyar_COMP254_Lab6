package exercise1;

import java.util.ArrayList;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V>{
	private UnsortedTableMap<K, V>[] table;
	public ChainHashMap() { super(); }
	public ChainHashMap(int cap) { super(cap); }
	/**Adding the changes necessary to run the super constructor with the newly implemented loadFactor parameter**/
	public ChainHashMap(int cap, int p, double lf) { super(cap, p, lf); }
	public ChainHashMap(int cap, int p) { super(cap, p); }
	protected void createTable() {
		table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
	}

	protected V bucketGet(int h, K k) {
		UnsortedTableMap<K, V> bucket = table[h];
		if(bucket == null) {
			return null;
		}
		return bucket.get(k);
	}

	protected V bucketPut(int h, K k, V v) {
		UnsortedTableMap<K, V> bucket = table[h];
		if(bucket == null) { // if this index has no entries, create an unsorted map and insert it here then insert the entry
			System.out.println("Creating a bucket for this index....");
			bucket = table[h] = new UnsortedTableMap<>();
		}
		int oldSize = bucket.size();
		V answer = bucket.put(k, v);
		n += (bucket.size() - oldSize); // will give us the amount of entries put into this bucket
		return answer;
	}

	protected V bucketRemove(int h, K k) {
		UnsortedTableMap<K, V> bucket = table[h];
		if(bucket == null) {
			System.out.println("There is nothing here..");
			return null; // there is nothing to remove as this bucket is empty
		}
		int oldSize = bucket.size();
		V answer = bucket.remove(k);
		n -= (bucket.size() - oldSize);
		return answer;
	}

	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		for(int h = 0; h < capacity; h++) { //loop through each index in the map
			if (table[h] != null) { // if the index contains a bucket enter it
				for(Entry<K, V> entry : table[h].entrySet()) { // loop through each buckets content and add to the buffer
					buffer.add(entry);
				}
			}
		}
		return buffer;
	}
 }
