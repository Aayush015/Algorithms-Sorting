import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// Java program to implement Max Heap
// using Quad Heap
public class QuadHeap {
    private double[] quadHeap;
    private int currentSize = 0;

    //constructor
    QuadHeap(int size)
    {
        quadHeap = new double[size];
        for (int i = 0; i < size; i++) {
            quadHeap[i] = -1;
        }
    }





    //returns true if heap is empty
    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    //returns the index of the parent of the node at index i
    private int parent(int i)
    {
        return (i - 1) / 4;
    }

    //returns the array of values of the children of the node at index i
    private double[] children(int i)
    {
        double[] children = new double[4];
        children[0] = quadHeap[(4 * i) + 1];
        children[1] = quadHeap[(4 * i) + 2];
        children[2] = quadHeap[(4 * i) + 3];
        children[3] = quadHeap[(4 * i) + 4];
        return children;
    }

    //swaps two nodes in the heap
    private int swap(int i, int j)
    {
        double temp = quadHeap[i];
        quadHeap[i] = quadHeap[j];
        quadHeap[j] = temp;
        return j;
    }

    //inset a value into the heap
    private void insert(int value)
    {
        int i = currentSize;
        if(i == quadHeap.length){
            double[] newArray = Arrays.copyOf(quadHeap, quadHeap.length * 2);
            for(int j = i; j < newArray.length; j++)
            {
                newArray[j] = -1;
            }
            quadHeap = newArray;
            System.out.println("Heap doubled in size");
        }
        quadHeap[i] = value;
        heapifyUp(i);
    }

    //heapify up
    private void heapifyUp(int i)
    {
        if(i != 0)
        {
            int parent = parent(i);
            if(quadHeap[i] > quadHeap[parent])
            {
                swap(i, parent);
                heapifyUp(parent);
            }
        }
    }

    //heapify down (not actually used and potentially broken)
    private void heapifyDown(int i)
    {
        double[] children = children(i);
        double max = quadHeap[i];
        int maxIndex = i;
        for(int j = 0; j < children.length; j++)
        {
            if(children[j] > max)
            {
                max = children[j];
                maxIndex = (4 * i) + j + 1;
            }
        }
        if(maxIndex != i)
        {
            swap(i, maxIndex);
            heapifyDown(maxIndex);
        }
    }

    //removes the max value from the heap
    private double extractMax()
    {
        double max = quadHeap[0];
        quadHeap[0] = quadHeap[currentSize - 1];
        quadHeap[currentSize - 1] = -1;
        heapifyDown(0);
        return max;
    }

    //returns the max value in the heap
    private double getMax()
    {
        return quadHeap[0];
    }

    //prints the heap
    private void printHeap()
    {
        for(int i = 0; i < currentSize; i++)
        {
            System.out.print(quadHeap[i] + " ");
        }
        System.out.println();
    }


    //generate 50 random numbers and insert them into the heap for a small test
    private void generateRandomNumbers()
    {
        for(int i = 0; i < 50; i++)
        {
            int random = (int)(Math.random() * 100);
            insert(random);
        }
    }


    //loads the integers from the file into the heap and heapify's it
    private void loadInts() throws FileNotFoundException {
        //create a File object with the file path
        File file = new File("D:/algorithmsDoubleInputs/random_numbers_30.txt");

        //create a Scanner object with the file
        Scanner scanner = new Scanner(file);


        //loop through the file and insert each integer into the heap
        int bufferSize = 1048576;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)), bufferSize)) {
            String line;
            while ((line = br.readLine()) != null) {
                double number = Double.parseDouble(line);
                if (currentSize == quadHeap.length) {
                    double[] newArray = Arrays.copyOf(quadHeap, quadHeap.length * 2);
                    for (int j = currentSize; j < newArray.length; j++) {
                        newArray[j] = -1;
                    }
                    quadHeap = newArray;
                    System.out.println("Heap doubled in size");
                }
                quadHeap[currentSize] = number;
                currentSize++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Heap size: " + currentSize + "\n heapifying...");

//get the start time
        long startTime = System.nanoTime();

//heapify the heap
        for (int i = currentSize - 1; i >= 0; i--) {
            heapifyUp(i);
        }

//get the end time
        long endTime = System.nanoTime();

//calculate the elapsed time in nanoseconds
        long elapsedTime = endTime - startTime;

//convert the elapsed time to milliseconds
        double elapsedTimeInMilliseconds = (double) elapsedTime / 1000000;

//print the elapsed time
        System.out.println("heapify took " + elapsedTimeInMilliseconds + " milliseconds.");
    }

    public static void main(String[] arg) throws FileNotFoundException {
        QuadHeap quadHeap = new QuadHeap(1048577);
        quadHeap.loadInts();

    }
}
