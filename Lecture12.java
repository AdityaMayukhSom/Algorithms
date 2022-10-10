public class Lecture12 {

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 1, 3, 3, 2, 2 };
        System.out.println(bayerMooreMajorityVoteAlgorithm(arr));
    }

    public static int bayerMooreMajorityVoteAlgorithm(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        int majorityElement = arr[0];
        int majorityElementCount = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == majorityElement) {
                majorityElementCount++;
            } else {
                majorityElementCount--;
            }
            if (majorityElementCount == 0) {
                majorityElement = arr[i];
                majorityElementCount = 1;
            }
        }
        majorityElementCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == majorityElement) {
                majorityElementCount++;
            }
        }
        if (majorityElementCount > (arr.length / 2)) {
            return majorityElement;
        } else {
            return -1;
        }
    }
}