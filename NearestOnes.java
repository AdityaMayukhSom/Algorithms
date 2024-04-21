import java.util.ArrayList;
import java.util.Queue;

public class Solution {
	private static class Container {
		public int x;
		public int y;
		public int dist;

		public Container(final int x, final int y, final int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	public static ArrayList<ArrayList<Integer>> nearest(ArrayList<ArrayList<Integer>> mat, int n, int m) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		Queue<Container> queue = new LinkedList<Container>();
		for (int i = 0; i < n; i++) {
			ans.add(new ArrayList<Integer>());
			for (int col = 0; col < m; col++) {
				ans.get(i).add(-1);
				if (mat.get(i).get(j) == 1) {
					queue.add(new Container(i, j, 0));
					ans.get(i).set(j, 0);
				}
			}
		}

		while (!queue.isEmpty()) {
			Container container = queue.remove();

		}

	}
}
