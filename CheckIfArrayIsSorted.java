public class CheckIfArrayIsSorted {
    public static void main(String[] args) {
        int[] array1 = new int[] { 1, 2, 3, 4, 5 };
        int[] array2 = new int[] { 1, 2, 3, 4, 4 };
        int[] array3 = new int[] { 1, 2, 3 };
        int[] array4 = new int[] { 2, 3 };
        int[] array5 = new int[] { 6, 5 };
        System.out.println(isSorted(array1));
        System.out.println(isSorted(array2));
        System.out.println(isSorted(array3));
        System.out.println(isSorted(array4));
        System.out.println(isSorted(array5));
    }

    public static boolean isSorted(int[] array) {
        return isSorted(array, 0, array.length);
    }

    // n is length of array
    public static boolean isSorted(int[] array, int index, int n) {

        // if it is an empty array or singleton array
        // return true
        if (n == 1 || n == 0) {
            return true;
        }

        // Both of these base cases will give correct answer
        if (index == (n - 2)) {
            return (array[n - 2] < array[n - 1]);
        }
        // if (index == (n - 1)) {
        // return true;
        // }

        // Check if this element is smaller than the next element
        // And if the array after this element is sorted
        return ((array[index] < array[index + 1]) && isSorted(array, index + 1, n));
    }
}
