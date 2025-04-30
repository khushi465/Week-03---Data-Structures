import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class ReadBinaryData {

    public static void main(String[] args) {
        // Path to the file
        String filePath = "example.txt";  // Replace with the actual path to your file

        // Try to open the file and read it
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");  // Specify UTF-8 encoding
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            // Read the file line by line
            while ((line = bufferedReader.readLine()) != null) {
                // Print each line as characters
                System.out.println(line);
            }

        } catch (IOException e) {
            // Handle any I/O exceptions (e.g., file not found or encoding issues)
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}