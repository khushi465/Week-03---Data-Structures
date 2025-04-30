
public class InsertionSort {

    // Method to perform Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        // Traverse through the array from the second element (index 1)
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // The element to be inserted into the sorted part of the array
            int j = i - 1;

            // Shift elements of arr[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            // Insert the key at its correct position
            arr[j + 1] = key;
        }
    }

    // Method to print the array
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] employeeIDs = {101, 205, 303, 402, 150, 87, 112}; // Array of employee IDs

        System.out.println("Original Employee IDs:");
        printArray(employeeIDs);

        insertionSort(employeeIDs); // Sorting the array

        System.out.println("Sorted Employee IDs:");
        printArray(employeeIDs); // Printing sorted array
    }
}



