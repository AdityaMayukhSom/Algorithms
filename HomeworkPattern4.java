import java.util.Scanner;

public class HomeworkPattern4 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter No Of Lines To Print: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        // Outer Loop Controls Line Number
        // Inner Loop Contains What Is Printed In That Line
        for (int i = 1; i <= n; i++) {
            int c = 1;
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(c + " ");
                c = c * (i - j) / j;
            }

            System.out.print("\n");
        }
    }
}
