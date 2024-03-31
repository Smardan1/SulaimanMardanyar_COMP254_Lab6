package exercise1;

public class ChainHashMapRunnable {

	public static void main(String[] args) {
		//create a ChainHashMap with a cap of 4 and prime of 15329
		ChainHashMap<Integer, String> ChainMap = new ChainHashMap<>(4, 15329, 0.7); // remove the 0.7 to test

		//insert into the map
		ChainMap.put(75, "Monday");
		ChainMap.put(123, "Sunday");
		ChainMap.put(6, "Goodbye");
		ChainMap.put(12, "Bread");
		ChainMap.put(42, "abc");

		System.out.println();
		System.out.println("Size: " + ChainMap.size());
		System.out.println("Capacity: " + ChainMap.getCapacity());


	}
}
