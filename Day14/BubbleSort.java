public class BubbleSort {

    // Method to perform Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        // Traverse through all elements in the array
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            // Last i elements are already sorted
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements and swap if needed
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            // If no two elements were swapped by inner loop, then the array is sorted
            if (!swapped) {
                break;
            }
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
        int[] studentMarks = {85, 60, 90, 78, 88, 76, 95}; // Array of student marks

        System.out.println("Original Marks:");
        printArray(studentMarks);

        bubbleSort(studentMarks); // Sorting the array

        System.out.println("Sorted Marks:");
        printArray(studentMarks); // Printing sorted array
    }
}