import java.util.Arrays;

public class CapacityOfAircraft {
    public static int aircrafts(int[] weight, int n, int k) {
        boolean[] visited = new boolean[n];
        Arrays.sort(weight);

        int ans = 0;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            if (weight[left] > k)
                return -1;

            // we decrement the right pointer to the point where two people can actually fit
            // into the aircraft
            while (left < right && weight[left] + weight[right] > k) {
                right--;
            }

            if (left == right) {
                // if left is equal to right that means no two pair can be put together
                // hence we simply break from the loop
                break;
            } else {
                // we have found two pairs such that they can be put together, hence we visit
                // them and increment the answer by one and move our pointers
                ans++;

                visited[left] = true;
                visited[right] = true;

                left++;
                right--;
            }
        }

        // if we are outside that means every cases where two pairs can be put together
        // are exhausted, hence we need to check for single entries
        // here we are making the entry of visited and checking if that is visited
        // simultaneously to put everything under one loop
        boolean notPossible = false;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && weight[i] <= k) {
                ans++;
                visited[i] = true;
            }

            if (!visited[i]) {
                notPossible = true;
                break;
            }
        }

        return notPossible ? -1 : ans;
    }

    public static void main(String[] args) {
        // int[] weight = new int[] { 3, 3, 5, 2, 1, 4, 5, 1, 5 };
        int k = 1;
        int[] weight = new int[] { 1 };
        System.out.println(aircrafts(weight, weight.length, k));
    }
}