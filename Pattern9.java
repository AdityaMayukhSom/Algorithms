import java.util.Scanner;

public class Pattern9 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter No Of Lines To Print: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        // Outer Loop Controls Line Number
        // Inner Loop Contains What Is Printed In That Line
        for (int i = 1; i <= n; i++) {
            int count = ((i & 1) == 1) ? 1 : 0;
            for (int j = 1; j <= i; j++) {
                System.out.print(count + " ");
                // Toggling Between 0 And 1.
                count = (count == 1) ? 0 : 1;
            }
            System.out.print("\n");
        }
    }
}