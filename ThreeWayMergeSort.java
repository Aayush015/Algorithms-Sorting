// Three-way Merge Sort Algorithm

import java.io.*;
import java.util.Arrays;

public class ThreeWayMergeSort<T extends Comparable<T>> {
    /**
     * Method to sort algorithm by cloning original array
     * @param orgArr Array to be sorted.
     */
    public void threeWayMergeSort(T[] orgArr) {
        // Testing if array is null
        if (orgArr == null)
            return;

        // Cloning original Array to temp Array
        T[] tempArr = orgArr.clone();

        // Three-way merging on temp array
        threeWayMergeSort(tempArr, 0, orgArr.length, orgArr);

        // Copying elements from temp array to the original array
        System.arraycopy(tempArr, 0, orgArr, 0, tempArr.length);
    }

    /**
     * Merge-sorting method with recursive call after dividing the given array
     * into three parts, using Polymorphism for ease
     * @param orgArr Original Array containing elements for sorting.
     * @param low Starting index.
     * @param high Ending index.
     * @param finArr Final array to store sorted result.
     */
    private void threeWayMergeSort(T[] orgArr, int low, int high, T[] finArr) {
        // Base case to return once end of array is reached
        if (high - low < 2)
            return;

        // Dividing current range into three parts
        int mid1 = low + ((high - low) / 3);
        int mid2 = low + 2 * ((high - low) / 3) + 1;

        // Merge-sorting recursive call of three sub-ranges
        threeWayMergeSort(finArr, low, mid1, orgArr);
        threeWayMergeSort(finArr, mid1, mid2, orgArr);
        threeWayMergeSort(finArr, mid2, high, orgArr);

        // Merging the sorted arrays
        mergeThree(finArr, low, mid1, mid2, high, orgArr);
    }

    /**
     * Merge method to merge child arrays once they are sorted. Works by
     * merging three sorted arrays from orgArr into single sorted array finArr
     * @param orgArr Original array with sorted child arrays.
     * @param low Starting index of first range.
     * @param mid1 Ending index of first range.
     * @param mid2 Ending index of second range.
     * @param high Ending index of third range.
     * @param finArr Array to store merged parent.
     */
    private void mergeThree(T[] orgArr, int low, int mid1, int mid2, int high, T[] finArr) {
        int i = low, j = mid1, k = mid2, l = low;

        while (i < mid1 && j < mid2 && k < high) {
            T min = orgArr[i].compareTo(orgArr[j]) < 0 ?
                    (orgArr[i].compareTo(orgArr[k]) < 0 ? orgArr[i++] : orgArr[k++]) :
                    (orgArr[j].compareTo(orgArr[k]) < 0 ? orgArr[j++] : orgArr[k++]);

            finArr[l++] = min;
        }

        while (i < mid1 && j < mid2) {
            finArr[l++] = orgArr[i].compareTo(orgArr[j]) < 0 ? orgArr[i++] : orgArr[j++];
        }

        while (j < mid2 && k < high) {
            finArr[l++] = orgArr[j].compareTo(orgArr[k]) < 0 ? orgArr[j++] : orgArr[k++];
        }

        while (i < mid1 && k < high) {
            finArr[l++] = orgArr[i].compareTo(orgArr[k]) < 0 ? orgArr[i++] : orgArr[k++];
        }

        while (i < mid1) {
            finArr[l++] = orgArr[i++];
        }

        while (j < mid2) {
            finArr[l++] = orgArr[j++];
        }

        while (k < high) {
            finArr[l++] = orgArr[k++];
        }
    }

    /**
     * Main method for the running and timing of mergeSort algorithm
     */
    public static void main(String[] args) {

        //Change the input file size below
        int size = 30;

        //Change input type below, "ints" for Integer and "doubles" for Doubles
        String numberType = "ints";
        String inputFileName = "/home/roshan/IdeaProjects/mergeSort/inputs/random_numbers_" + numberType + "_" + size + ".txt";

        //Reading the input file and storing them in an array
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            Double[] data = br.lines().map(Double::parseDouble).toArray(Double[]::new);

            //Creating an instance of ThreeWayMergeSort
            ThreeWayMergeSort<Double> mergeSort = new ThreeWayMergeSort<>();

            //Timer for mergeSort
            long startTime = System.currentTimeMillis();
            mergeSort.threeWayMergeSort(data);
            long endTime = System.currentTimeMillis();

            //Uncomment below line to print sorted arrays
            //System.out.println("Sorted array: " + Arrays.toString(data));
            System.out.println("Running for 2^" + size + " size array.");
            System.out.println("Size of the array: " + data.length);
            System.out.println("Execution Time: " + (endTime - startTime) + " milliseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
