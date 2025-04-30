import java.util.HashSet;

public class RemoveDuplicates {

    public static String removeDuplicateCharacters(String input) {
        // Step 1: Initialize an empty StringBuilder and a HashSet
        StringBuilder stringBuilder = new StringBuilder();
        HashSet<Character> seenCharacters = new HashSet<>();

        // Step 2: Iterate over each character in the string
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // Step 3: If the character is not in the HashSet, append it to the StringBuilder
            if (!seenCharacters.contains(currentChar)) {
                stringBuilder.append(currentChar);
                seenCharacters.add(currentChar); // Add it to the HashSet
            }
        }

        // Step 4: Return the StringBuilder as a string without duplicates
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // Example input
        String input = "programming";

        // Call the removeDuplicateCharacters method and print the result
        String result = removeDuplicateCharacters(input);
        System.out.println("Original String: " + input);
        System.out.println("String without duplicates: " + result);
    }
}
