import java.util.Random;

public class quick_sort {
    public static void main(String[] args) {
        int[] arr = {12, 4, 5, 6, 7, 3, 1, 15, 8, 9};
        quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivotIndex = getRandomPivotIndex(low, high);
        int pivotValue = arr[pivotIndex];

        // Move the pivot element to the end of the array
        swap(arr, pivotIndex, high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivotValue) {
                i++;
                swap(arr, i, j);
            }
        }

        // Move the pivot element back to its correct position
        swap(arr, i + 1, high);

        return i + 1;
    }

    public static int getRandomPivotIndex(int low, int high) {
        Random rand = new Random();
        return rand.nextInt(high - low + 1) + low;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
