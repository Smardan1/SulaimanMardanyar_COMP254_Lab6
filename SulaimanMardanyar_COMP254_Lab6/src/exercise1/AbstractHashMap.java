package exercise1;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {
	protected int n = 0; // number of entries in the Map, will increase as Entries are inserted
	protected int capacity; //the maximum length of the table
	private int prime;// used in the MAD compression function
	private long scale, shift; // also used in the MAD Compression function(will act as a and b) -> ((a*i * b) % prime) % cap
	private double loadFactor = 0.5;
	/**
	 * Exercise 1 Question
	 * Adding a new parameter for the load factor
	 * **/
	public AbstractHashMap(int cap, int p, double lf) {
		prime = p;
		capacity = cap;
		Random rand = new Random();
		scale = rand.nextInt(prime - 1) + 1;
		shift = rand.nextInt(prime);
		createTable();
		loadFactor = lf;
	}
	/**The original constructor**/
	public AbstractHashMap(int cap, int p) {
		prime = p;
		capacity = cap;
		Random rand = new Random();
		scale = rand.nextInt(prime - 1) + 1;
		shift = rand.nextInt(prime);
		createTable();
	}
	/**If no load factor passed use a default load factor**/
	public AbstractHashMap(int cap) { this(cap, 109345121, 0.5);} //default cap, prime, and load factor
	public AbstractHashMap() { this(17); }// default cap

	public int size() { return n; }
	public int getCapacity() { return capacity; }
	public V get(K key) { return bucketGet(hashValue(key), key); }
	public V remove(K key) { return bucketRemove(hashValue(key), key); }
	public V put(K key, V value) {
		V answer = bucketPut(hashValue(key), key, value);
		System.out.println("Load factor is: " + String.format("%.2f", ((double) n/capacity)));
		if(((double) n /capacity) > loadFactor) { //checks if load factor is > then the loadFactor we have defined
			System.out.println("Load Factor is over limit, resizing the map...");
			resize(2 * capacity - 1);
		}
		return answer;
	}
	private int hashValue(K key) {
		return (int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
	}

	private void resize(int newCap) {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>(n); //create new
		for (Entry<K, V> e : entrySet()) {
			buffer.add(e);
		}
		capacity = newCap;
		createTable();
		n = 0;
		for(Entry<K, V> e : buffer) {
			put(e.getKey(), e.getValue());
		}
	}

	protected abstract void createTable();
	protected abstract V bucketGet(int h, K k);
	protected abstract V bucketPut(int h, K k, V v);
	protected abstract V bucketRemove(int h, K k);
}
