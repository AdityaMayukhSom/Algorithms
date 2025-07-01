
import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    public List<Integer> majorityElementTwo(int[] nums) {
        int cnt1 = 0;
        int cnt2 = 0;
        int num1 = Integer.MIN_VALUE;
        int num2 = Integer.MIN_VALUE;
        int threshold = nums.length / 3;

        for (int num : nums) {
            if (num == num1) {
                cnt1++;
            } else if (num == num2) {
                cnt2++;
            } else if (cnt1 == 0 && num != num2) {
                cnt1 = 1;
                num1 = num;
            } else if (cnt2 == 0 && num != num1) {
                cnt2 = 1;
                num2 = num;
            } else {
                cnt1--;
                cnt2--;
            }
        }

        cnt1 = cnt2 = 0;
        for (int num : nums) {
            if (num == num1) {
                cnt1++;
            }
            if (num == num2) {
                cnt2++;
            }
        }

        List<Integer> ans = new ArrayList<>();
        if (cnt1 > threshold) {
            ans.add(num1);
        }

        if (cnt2 > threshold) {
            ans.add(num2);
        }

        if (ans.size() == 2 && ans.get(0) > ans.get(1)) {
            ans.set(0, ans.get(0) ^ ans.get(1));
            ans.set(1, ans.get(0) ^ ans.get(1));
            ans.set(0, ans.get(0) ^ ans.get(1));
        }

        return ans;
    }
}
