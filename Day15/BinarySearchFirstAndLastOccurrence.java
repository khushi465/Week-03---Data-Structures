public class BinarySearchFirstAndLastOccurrence {

    // Method to find the first occurrence of the target element
    public static int findFirstOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;  // Target found, store the index
                right = mid - 1;  // Continue searching in the left half
            } else if (arr[mid] < target) {
                left = mid + 1;  // Move to the right half
            } else {
                right = mid - 1;  // Move to the left half
            }
        }
        return result;
    }

    // Method to find the last occurrence of the target element
    public static int findLastOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;  // Target found, store the index
                left = mid + 1;  // Continue searching in the right half
            } else if (arr[mid] < target) {
                left = mid + 1;  // Move to the right half
            } else {
                right = mid - 1;  // Move to the left half
            }
        }
        return result;
    }

    // Method to find the first and last occurrences of the target element
    public static int[] findFirstAndLastOccurrence(int[] arr, int target) {
        int first = findFirstOccurrence(arr, target);
        int last = findLastOccurrence(arr, target);
        
        // If either first or last is -1, it means the target was not found
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        return new int[]{first, last};
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4, 5, 6, 7};
        int target = 2;
        
        // Find first and last occurrence
        int[] result = findFirstAndLastOccurrence(arr, target);
        
        // Print the result
        System.out.println("First occurrence: " + result[0]);
        System.out.println("Last occurrence: " + result[1]);

        // Test with a target not in the array
        target = 8;
        result = findFirstAndLastOccurrence(arr, target);
        System.out.println("First occurrence: " + result[0]);
        System.out.println("Last occurrence: " + result[1]);
    }
}
