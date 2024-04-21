import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static class Container {
		public int x;
		public int y;
		public int time;

		public Container(final int x, final int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static int minTimeToRot(int[][] grid, int n, int m) {
		int freshCount = 0, timeToRot = 0;
		int[][] vis = new int[n][m];
		Queue<Container> queue = new LinkedList<>();

		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				vis[row][col] = grid[row][col];
				if (vis[row][col] == 1) {
					freshCount++;
				} else if (vis[row][col] == 2) {
					queue.add(new Container(row, col, 0));
				}
			}
		}

		// what to do if there is just no fresh fruits? is it -1 or 0

		while (!queue.isEmpty()) {
			Container container = queue.poll();
			int time = container.time;
			int x = container.x;
			int y = container.y;
			timeToRot = Math.max(time, timeToRot);
			if (x > 0 && vis[x - 1][y] == 1) {
				vis[x - 1][y] = 2;
				queue.add(new Container(x - 1, y, time + 1));
				freshCount--;
			}
			if (y > 0 && vis[x][y - 1] == 1) {
				vis[x][y - 1] = 2;
				queue.add(new Container(x, y - 1, time + 1));
				freshCount--;
			}
			if (x < n - 1 && vis[x + 1][y] == 1) {
				vis[x + 1][y] = 2;
				queue.add(new Container(x + 1, y, time + 1));
				freshCount--;
			}
			if (y < m - 1 && vis[x][y + 1] == 1) {
				vis[x][y + 1] = 2;
				queue.add(new Container(x, y + 1, time + 1));
				freshCount--;
			}
		}

		return (freshCount == 0) ? timeToRot : -1;
	}

	public static void main(String[] args) {
		int n = 3;
		int m = 3;
		int[][] arr = new int[][] { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		System.out.println(Solution.minTimeToRot(arr, n, m));
	}
}
