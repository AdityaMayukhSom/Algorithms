public class CodingExercise1 {
    public static void main(String[] args) {
        System.out.println(power(4, 4));
    }

    public static int power(int base, int exponent) {
        if (base == 0) {
            return base;
        }
        if (exponent == 0) {
            return 1;
        }
        int halfPower = power(base, exponent / 2);
        if ((exponent & 1) == 0)
            return halfPower * halfPower;
        else
            return base * halfPower * halfPower;
    }
}
