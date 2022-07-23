public class PrintKeyboardCombination {
    private static String[] keyLetters = new String[] { ".", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx",
            "yz" };

    public static void findCombination(String inputString, int index, String combinationString) {
        if (index == inputString.length()) {
            System.out.println(combinationString);
            return;
        }
        char currChar = inputString.charAt(index);
        String keyNumberLetters = keyLetters[currChar - '0'];
        // here not to be change is not a chance, hence we loop over keyNumberLetters
        for (int i = 0; i < keyNumberLetters.length(); i++) {
            findCombination(inputString, index + 1, combinationString + keyNumberLetters.charAt(i));
        }
    }

    public static void main(String[] args) {
        String myStr = "123";
        findCombination(myStr, 0, "");
    }
}
