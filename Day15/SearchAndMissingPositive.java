import java.util.Arrays;

public class SearchAndMissingPositive {

    // Function to find the first missing positive integer using Linear Search
    public static int findFirstMissingPositive(int[] arr) {
        int n = arr.length;

        // Step 1: Ignore negative numbers and numbers greater than n
        for (int i = 0; i < n; i++) {
            if (arr[i] <= 0 || arr[i] > n) {
                arr[i] = n + 1;  // Marking these values out of range
            }
        }

        // Step 2: Use the array itself to mark the presence of numbers
        for (int i = 0; i < n; i++) {
            int num = Math.abs(arr[i]);
            if (num <= n && arr[num - 1] > 0) {
                arr[num - 1] = -arr[num - 1];  // Mark as visited by making it negative
            }
        }

        // Step 3: Find the first index with a positive number
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                return i + 1;  // First missing positive integer
            }
        }

        return n + 1;  // If all numbers from 1 to n are present
    }

    // Function to perform Binary Search to find the index of the target
    public static int binarySearch(int[] arr, int target) {
        Arrays.sort(arr);  // Sort the array before performing binary search
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;  // Target found
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;  // Target not found
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, -1, 1};

        // Find the first missing positive integer
        int missingPositive = findFirstMissingPositive(arr);
        System.out.println("First Missing Positive Integer: " + missingPositive);

        // Find the index of the target number using Binary Search
        int target = 4;
        int targetIndex = binarySearch(arr, target);
        if (targetIndex != -1) {
            System.out.println("Index of target " + target + ": " + targetIndex);
        } else {
            System.out.println("Target " + target + " not found in the array.");
        }
    }
}


