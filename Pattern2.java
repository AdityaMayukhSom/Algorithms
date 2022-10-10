import java.util.Scanner;

public class Pattern2 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter No Of Lines To Print: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        // Outer Loop Controls Line Number
        // Inner Loop Contains What Is Printed In That Line
        for (int i = 1; i <= (n - 1); i++) {
            if (i == 1 || i == (n - 1)) {
                for (int j = 1; j <= n; j++) {
                    System.out.print("* ");
                }
            } else {
                for (int j = 1; j <= n; j++) {
                    if (j == 1 || j == n) {
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
}