import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCountInFile {

    public static void main(String[] args) {
        // Path to the file and the target word to search for
        String filePath = "sample.txt";  // Replace with the actual path to your file
        String targetWord = "Java";      // Replace with the word you want to count

        // Initialize the counter variable
        int wordCount = 0;

        // Try to open the file and read it line by line
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            // Read the file line by line
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into words using space as delimiter
                String[] words = line.split("\\s+");

                // Loop through the words and count occurrences of the target word
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        wordCount++;
                    }
                }
            }

            // Print the final count of the target word
            System.out.println("The word '" + targetWord + "' appears " + wordCount + " times in the file.");

        } catch (IOException e) {
            // Handle any I/O exceptions (e.g., file not found)
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}