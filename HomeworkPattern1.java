import java.util.Scanner;

public class HomeworkPattern1 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter No Of Lines To Print: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        // Outer Loop Controls Line Number
        // Inner Loop Contains What Is Printed In That Line
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= (i - 1); j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= n; j++) {
                System.out.print("* ");
            }
            System.out.print("\n");
        }
    }
}
