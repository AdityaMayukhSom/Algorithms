import java.util.Scanner;

public class HomeworkPattern2 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter No Of Lines To Print: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        // Outer Loop Controls Line Number
        // Inner Loop Contains What Is Printed In That Line
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (2 * i - 1); j++) {
                if ((j & 1) == 1) {
                    System.out.print(i);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
}
