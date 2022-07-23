import java.util.Scanner;

public class Pattern8 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int count = 1;
        System.out.print("Enter No Of Lines To Print: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        // Outer Loop Controls Line Number
        // Inner Loop Contains What Is Printed In That Line

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // Just Random Stuff To Make The Final Output Look Cool
                // This Inner Condition Checking Has No Relation With Printing Logic
                if (count < 10) {
                    System.out.print(count + "  ");
                } else {
                    System.out.print(count + " ");
                }
                count++;
            }
            System.out.print("\n");
        }
    }
}