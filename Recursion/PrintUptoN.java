public class PrintUptoN {
    // Code To Recursively Print From 1 To N
    public static void main(String[] args) {
        // print(1, 100);
        print(10);
    }

    public static void print(int printFrom, int printUpto) {
        if (printFrom > printUpto) {
            return;
        }
        System.out.println(printFrom);
        print(printFrom + 1, printUpto);
    }

    public static void print(int printUpto) {
        if (printUpto == 0) {
            return;
        }
        print(printUpto - 1);
        System.out.println(printUpto);
    }

}