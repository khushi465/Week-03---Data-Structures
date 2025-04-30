import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileLineByLine {

    public static void main(String[] args) {
        // Path to the file to be read
        String filePath = "sample.txt"; // Replace with your file path

        // Create FileReader and BufferedReader to read the file
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            // Read lines from the file until the end
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);  // Print each line to the console
            }

        } catch (IOException e) {
            // Handle any potential I/O exceptions (e.g., file not found)
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}