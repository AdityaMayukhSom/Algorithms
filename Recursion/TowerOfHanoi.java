public class TowerOfHanoi {
	public static void main(String[] args) {
		// Time Complexity = O(2^n-1)
		towerOfHanoi(2, "A", "B", "C");
	}

	public static void towerOfHanoi(int n, String source, String helper, String destination) {
		if (n == 1) {
			System.out.println("Move " + n + " from " + source + " to " + destination);
			return;
		}
		towerOfHanoi(n - 1, source, destination, helper);
		System.out.println("Move " + n + " from " + source + " to " + destination);
		towerOfHanoi(n - 1, helper, source, destination);
	}
}
