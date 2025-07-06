
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithm {

    public List<List<Integer>> threeSum(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int val = target - (arr[i] + arr[j]);
                int cnt = map.getOrDefault(val, 0);
                for (int k = 0; k < cnt; k++) {
                    List<Integer> list = Arrays.asList(arr[i], arr[j], val);
                    Collections.sort(list);
                    result.add(list);
                }
                map.put(arr[j], cnt + 1);
            }
            map.clear();
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        int i = 0, j, k, sum, currI, currJ, currK;
        while (i < n) {
            j = i + 1;
            k = n - 1;

            while (j < k) {
                sum = nums[j] + nums[k] + nums[i];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    currJ = nums[j];
                    while (j < n && nums[j] == currJ) {
                        j++;
                    }

                    currK = nums[k];
                    while (k > 0 && nums[k] == currK) {
                        k--;
                    }

                } else if (sum < 0) {
                    currJ = nums[j];
                    while (j < n && nums[j] == currJ) {
                        j++;
                    }
                } else if (sum > 0) {
                    currK = nums[k];
                    while (k > 0 && nums[k] == currK) {
                        k--;
                    }
                }
            }

            currI = nums[i];
            while (i < n && nums[i] == currI) {
                i++;
            }
        }

        return res;
    }
}
