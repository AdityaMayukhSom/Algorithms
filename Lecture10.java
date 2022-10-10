public class Lecture10 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("racecar"));
        System.out.println(isPalindrome("racecar", 0, 6));
        System.out.print("\n");
        printAllSubsequence("abc", 0, "");
        printStringPermutations("abc", "");
        towerOfHanoi(4, "A", "B", "C");
    }

    public static void towerOfHanoi(int n, String source, String helper, String destination) {
        if (n == 1) {
            System.out.println("Move Disk " + n + " From " + source + " To " + destination);
            return;
        }
        towerOfHanoi(n - 1, source, destination, helper);
        System.out.println("Move Disk " + n + " From " + source + " To " + destination);
        towerOfHanoi(n - 1, helper, source, destination);
    }

    public static boolean isPalindrome(String str) {
        // Base case
        if (str.length() == 0)
            return true;
        if (str.length() == 1)
            return true;
        // Condition Checking
        if (str.charAt(0) != str.charAt(str.length() - 1))
            return false;
        return isPalindrome(str.substring(1, str.length() - 1));
    }

    public static boolean isPalindrome(String str, int l, int r) {
        if (l >= r)
            return true;
        if (str.charAt(l) != str.charAt(r))
            return false;
        return isPalindrome(str, l + 1, r - 1);
    }

    public static void printAllSubsequence(String str, int n, String curr) {
        if (n == str.length()) {
            System.out.println(curr);
            return;
        }
        printAllSubsequence(str, n + 1, curr + str.charAt(n)); // When we are choosing the character
        printAllSubsequence(str, n + 1, curr); // When we are not choosing the character
    }

    public static void printStringPermutations(String str, String curr) {
        if (str.length() == 0) {
            System.out.println(curr);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            // for i = str.length() - 1; str.substring(i + 1) will return an empty string
            // that case will not throw an array index out of bound exception
            printStringPermutations(str.substring(0, i) + str.substring(i + 1), curr + str.charAt(i));
        }
    }
}
