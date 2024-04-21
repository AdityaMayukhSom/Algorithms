import java.util.Arrays;

public class Heap {
    private int[] arr;
    private int size;

    public Heap(final int capacity) {
        this.arr = new int[capacity];
        this.size = 0;
    }

    public void insert(int val) {
        int index = this.size;
        this.arr[index] = val;
        this.size++;

        int temp;
        while (index > 0) {
            int parent = (index - 1) / 2;
            // considering min heap, in case of max heap
            // inequality sign will be reversed
            if (this.arr[index] > this.arr[parent]) {
                temp = this.arr[parent];
                this.arr[parent] = this.arr[index];
                this.arr[index] = temp;
                index = parent;
            } else {
                // if the parent is not smaller, we have reached at the
                // corrent node hence just return from insert
                return;
            }
        }
    }

    public int delete() throws ArrayIndexOutOfBoundsException {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException(-1);
        }

        // recover the top most element from the heap
        int elem = arr[0];

        // put the last element at the top most element of the heap
        arr[0] = arr[size - 1];

        // as we have placed the last element at the top, we need to decrement the size
        size--;

        // restore heap property, i.e. proparage the top node to it's position
        int index = 0;
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largeIndex = index;

            if (left < size)
                largeIndex = (arr[left] > arr[largeIndex]) ? left : largeIndex;
            if (right < size)
                largeIndex = (arr[right] > arr[largeIndex]) ? right : largeIndex;

            if (largeIndex != index) {
                int temp = arr[largeIndex];
                arr[largeIndex] = arr[index];
                arr[index] = temp;
                index = largeIndex;
            } else {
                break;
            }
        }

        return elem;
    }

    private static void heapify(int[] arr, int idx) {
        while (idx < arr.length) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;

            int largest = idx;
            if (left < arr.length)
                largest = (arr[left] > arr[largest]) ? left : largest;
            if (right < arr.length)
                largest = (arr[right] > arr[largest]) ? right : largest;

            if (largest == idx) {
                break;
            } else {
                int temp = arr[largest];
                arr[largest] = arr[idx];
                arr[idx] = temp;
                idx = largest;
            }
        }
    }

    public static void heapify(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; --i) {
            heapify(arr, i);
        }
    }

    @Override
    public String toString() {
        StringBuffer sbfr = new StringBuffer();
        for (int i = 0; i < this.size; i++) {
            sbfr.append(String.format("%d ", this.arr[i]));
        }
        sbfr.append(String.format("\b\n"));
        return sbfr.toString();
    }

    public static void main(String[] args) {
        Heap heap = new Heap(100);
        heap.insert(50);
        heap.insert(30);
        heap.insert(90);
        heap.insert(100);
        heap.insert(60);
        heap.insert(40);

        System.out.println(heap.toString());
        System.out.println(heap.delete());
        System.out.println(heap.delete());
        System.out.println(heap.delete());

        heap.insert(70);

        int[] arr = new int[] { 40, 30, 80, 60, 10, 20, 70, 50 };
        Heap.heapify(arr);
        System.out.println(Arrays.toString(arr));

    }

}