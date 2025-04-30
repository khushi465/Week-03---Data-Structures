public class FindSentenceContainingWord {

    // Method to find the first sentence containing the specific word
    public static String findSentenceWithWord(String[] sentences, String word) {
        // Iterate through the array of sentences
        for (String sentence : sentences) {
            // Check if the sentence contains the specific word
            if (sentence.contains(word)) {
                // Return the current sentence if the word is found
                return sentence;
            }
        }
        // If no sentence contains the word, return "Not Found"
        return "Not Found";
    }

    public static void main(String[] args) {
        // Example array of sentences
        String[] sentences = {
            "The quick brown fox jumps over the lazy dog.",
            "The sun rises in the east.",
            "I love programming in Java.",
            "The weather is beautiful today."
        };
        
        // Word to search for
        String word = "Java";
        
        // Find the sentence containing the word
        String result = findSentenceWithWord(sentences, word);
        
        // Print the result
        System.out.println(result);
    }
}