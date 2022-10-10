public class CodingExercise9 {
    public static void main(String[] args) {
        System.out.println(first("prefZ"));
    }

    static char first(String str) {
        if (str.length() == 0) {
            return '\0';
        }
        if ((str.charAt(0) >= 65) && (str.charAt(0) <= 90)) {
            return str.charAt(0);
        }
        return first(str.substring(1));

    }
}
