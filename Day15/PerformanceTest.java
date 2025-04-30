import java.io.*;
import java.util.*;

public class PerformanceTest {

    public static void main(String[] args) {
        // 1. StringBuilder and StringBuffer concatenation test
        System.out.println("StringBuilder and StringBuffer Performance Test:");
        
        // String list for concatenation
        String str = "hello";
        int iterations = 1000000;  // Concatenate 1 million times
        
        // Using StringBuilder
        long startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(str);
        }
        long endTime = System.nanoTime();
        System.out.println("StringBuilder time: " + (endTime - startTime) + " nanoseconds");

        // Using StringBuffer
        startTime = System.nanoTime();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sf.append(str);
        }
        endTime = System.nanoTime();
        System.out.println("StringBuffer time: " + (endTime - startTime) + " nanoseconds");

        
        // 2. FileReader and InputStreamReader Word Count Test
        System.out.println("\nFileReader and InputStreamReader Word Count Test:");
        
        // Path to the 100MB file (replace with the actual path on your machine)
        String filePath = "large_file.txt";  // Replace this path with an actual file path

        try {
            // Using FileReader and InputStreamReader to read the file
            FileReader fileReader = new FileReader(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileReader);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            long fileStartTime = System.nanoTime();
            String line;
            int wordCount = 0;
            
            // Read each line and count words
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into words based on whitespace
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }

            long fileEndTime = System.nanoTime();
            System.out.println("Word count: " + wordCount);
            System.out.println("Time taken to read and count words: " + (fileEndTime - fileStartTime) + " nanoseconds");

            // Close the resources
            bufferedReader.close();
            inputStreamReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
