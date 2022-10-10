public class CodingExercise3 {
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3, 4, 5 };
        System.out.println(productofArray(arr, 5));
    }

    public static int productofArray(int A[], int N) {
        if (N == 1)
            return A[0];
        return A[N - 1] * productofArray(A, N - 1);
    }
}
