package exercise2;

public class SortedTableMapRunnable {
	public static void main(String[] args) {
		SortedTableMap<Integer,Integer> sortedMap = new SortedTableMap<>();
		sortedMap.put(1, 54);
		sortedMap.put(5, 512);
		sortedMap.put(86, 42);

		System.out.println("is key 86 in the SortedTableMap?, " + sortedMap.containKey(86));
		System.out.println("is key 2 in the SortedTableMap?, " + sortedMap.containKey(2));

		sortedMap.put(999, 123);
		System.out.println("Adding key 2");
		sortedMap.put(2, 64);
		System.out.println("is key 2 in the SortedTableMap?, " + sortedMap.containKey(2));

		sortedMap.put(5, 43);


	}

}
