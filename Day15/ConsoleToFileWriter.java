
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;

public class ConsoleToFileWriter {

    public static void main(String[] args) {
        // File path where user input will be written
        String filePath = "user_input.txt";  // Replace with your desired file path
        
        // Create InputStreamReader to read from console (System.in) and wrap it in BufferedReader
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             FileWriter fileWriter = new FileWriter(filePath, true)) {  // 'true' for append mode

            String input;
            System.out.println("Enter text (type 'exit' to stop):");
            
            // Loop to read user input until "exit" is entered
            while (true) {
                input = consoleReader.readLine();  // Read user input

                if ("exit".equalsIgnoreCase(input)) {
                    break;  // Exit the loop if user types "exit"
                }

                // Write the input to the file as a new line
                fileWriter.write(input + System.lineSeparator());  // Adds a new line after each input
            }

            System.out.println("User input has been saved to the file.");

        } catch (IOException e) {
            // Handle any I/O exceptions that occur
            System.err.println("An error occurred while reading input or writing to the file: " + e.getMessage());
        }
    }
}


