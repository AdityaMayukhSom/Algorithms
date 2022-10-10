public class CodingExcercise7 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("awesome"));
        System.out.println(isPalindrome("footbar"));
        System.out.println(isPalindrome("tacocat"));
        System.out.println(isPalindrome("racecar"));
        System.out.println(isPalindrome("aibohphobia"));
    }

    public static boolean isPalindrome(String s) {
        if (s.length() == 1 || s.length() == 0)
            return true;
        return (s.charAt(0) == s.charAt(s.length() - 1)) && isPalindrome(s.substring(1, s.length() - 1));
    }
}
