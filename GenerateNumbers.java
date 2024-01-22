import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SplittableRandom;

public class GenerateNumbers {
    public static void main(String[] args) throws IOException {
        int[] sizes = {20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        for (int size : sizes) {
            long startTime = System.nanoTime();
            SplittableRandom random = new SplittableRandom();

            // Specify the full path to the directory
            String directoryPath = "E:\\Project";

            // Modify the FileWriter to include the full path
            BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + "\\random_doubles_" + size + ".txt"));

            int count = (int) Math.pow(2, size);
            for (int i = 0; i < count; i++) {
                writer.write(Double.toString(random.nextDouble()));
                writer.newLine();
            }
            writer.close();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // in milliseconds
            System.out.println("Generated " + count + " random doubles in " + duration + " ms for size 2^" + size);
        }
    }
}
