public class LinearSearchFirstNegative {
    
    // Method to find the first negative number using linear search
    public static int findFirstNegative(int[] arr) {
        // Iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // Check if the current element is negative
            if (arr[i] < 0) {
                // Return the index of the first negative number
                return i;
            }
        }
        // If no negative number is found, return -1
        return -1;
    }
    
    public static void main(String[] args) {
        // Example array
        int[] arr = {3, 5, 7, -2, 8, -1, 4};
        
        // Find the index of the first negative number
        int result = findFirstNegative(arr);
        
        if (result != -1) {
            System.out.println("First negative number is at index: " + result);
        } else {
            System.out.println("No negative number found.");
        }
    }
}
