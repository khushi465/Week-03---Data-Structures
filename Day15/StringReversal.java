public class StringReversal {

    public static String reverseString(String input) {
        // Step 1: Create a new StringBuilder object
        StringBuilder stringBuilder = new StringBuilder();

        // Step 2: Append the input string to the StringBuilder
        stringBuilder.append(input);

        // Step 3: Reverse the string using the reverse() method
        stringBuilder.reverse();

        // Step 4: Convert the StringBuilder back to a string and return it
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // Example input
        String input = "hello";

        // Call the reverseString method and print the result
        String reversed = reverseString(input);
        System.out.println("Original String: " + input);
        System.out.println("Reversed String: " + reversed);
    }
}