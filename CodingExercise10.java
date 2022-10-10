public class CodingExercise10 {
    public static void main(String[] args) {
        System.out.println(capitalizeWord("i love to eat biriyani"));
    }

    public static String capitalizeWord(String str) {
        int n = str.length();
        if (n == 0) {
            return str;
        }
        char ch = str.charAt(n - 1);
        if (n == 1) {
            return Character.toString(Character.toUpperCase(ch));
        }
        if (str.substring(n - 2, n - 1).equals(" ")) {
            ch = Character.toUpperCase(ch);
        }
        return capitalizeWord(str.substring(0, n - 1)) + Character.toString(ch);
    }
}
