import java.util.ArrayList;

public class MissingAndRepeat {

    public static int[] missingAndRepeating(ArrayList<Integer> arr, int n) {
        int xor = 0;
        int axor = 0;
        for (int i = 1; i <= n; i++) {
            xor = xor ^ arr.get(i - 1);
            axor = axor ^ i;
        }

        int mask = 0;
        for (int i = 0; i < 32; i++) {
            mask = 1 << i;
            if ((mask ^ xor ^ axor) != 0)
                break;
        }

        int val1 = 0;
        for (int i = 0; i < n; i++) {
            if ((arr.get(i) ^ mask) == 0)
                val1 = val1 ^ arr.get(i);
        }

        int val2 = xor ^ axor ^ val1;

        for (int i = 0; i < n; i++) {
            if (arr.get(i) == val1) {
                return new int[] { val2, val1 };
            }
        }

        return new int[] { val1, val2 };
    }
}