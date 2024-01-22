/*
    Project - QuickSort Algorithm
    Author - Aayush Kafle
 */
import java.io.*;
import java.util.Random;

public class quick_sort {

    /*
        Swap two doubles.
     */
    static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
        Get Random Pivot
     */
    static int generateRandomPivot(int low, int high) {
        Random random = new Random();
        return random.nextInt(high - low + 1) + low;
    }

    /*
        Implement randomized quicksort and recurse.
     */
    static void randomizedQuickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low, pivotIndex - 1);
            randomizedQuickSort(arr, pivotIndex + 1, high);
        }
    }

    /*
        Implement quick sort for two numbers in the array.
     */
    static int randomizedPartition(double[] arr, int low, int high) {
        int pivotIndex = generateRandomPivot(low, high);
        double pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivotValue) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    /*
        Read Input and Add it to the array.
     */
    private static int readDataFromFile(String file, double[] arr) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int count = 0;

        while ((line = reader.readLine()) != null) {
            arr[count] = Double.parseDouble(line);
            count++;
        }

        reader.close();
        return count;
    }

    public static void main(String[] args) {
        try {
            int fileSize = 20;
            String inputFile = "E:\\Project\\random_numbers_" + fileSize + ".txt";  // Provide input from SSD. 
            int arraySize = (int) Math.pow(2, 20);

            // array to store the input array
            double[] arr = new double[arraySize];

            // Read data from file
            long startTime = System.nanoTime();
            // n stores the number of inputs
            int n = readDataFromFile(inputFile, arr);
            long endTime = System.nanoTime();

            // Calculate and print loading time
            long loadingTime = endTime - startTime;
            System.out.println("Doubles");
            System.out.println("Data loading time: " + loadingTime + " nanoseconds");

            // Start the timer for the sorting algorithm
            startTime = System.nanoTime();

            // Call the randomized quicksort algorithm
            randomizedQuickSort(arr, 0, n);

            for (int i=0; i<n; i++){
                System.out.print(arr[i] + ", ");
            }
            System.out.println();
            // Stop the timer
            endTime = System.nanoTime();

            // Calculate and print running time
            long runningTime = endTime - startTime;
            System.out.println("Randomized Quicksort running time: " + runningTime + " nanoseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
