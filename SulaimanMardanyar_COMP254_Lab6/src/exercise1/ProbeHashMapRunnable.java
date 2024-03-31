package exercise1;

public class ProbeHashMapRunnable {
	public static void main(String[] args) {
		ProbeHashMap<Integer, String> ProbeMap = new ProbeHashMap<>(4, 25253, 0.4);
		ProbeMap.put(9234, "Hello");
		ProbeMap.put(1, "Apple");
		ProbeMap.put(43, "Money");
		ProbeMap.put(64312, "Coffee");
		ProbeMap.put(7, "Pear");
		ProbeMap.put(901, "Shoe");

		System.out.println();
		System.out.println("Size: " + ProbeMap.size());
		System.out.println("Capacity: " + ProbeMap.getCapacity());
	}


}
