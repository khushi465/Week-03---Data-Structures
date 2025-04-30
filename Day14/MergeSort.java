
public class MergeSort {

    // Method to merge two subarrays into a sorted array
    public static void merge(int[] arr, int left, int mid, int right) {
        // Calculate the sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, mid + 1, rightArray, 0, n2);

        // Merging the temporary arrays back into the original array
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        // Copy any remaining elements from leftArray
        while (i < n1) {
            arr[k++] = leftArray[i++];
        }

        // Copy any remaining elements from rightArray
        while (j < n2) {
            arr[k++] = rightArray[j++];
        }
    }

    // Recursive function to sort the array using merge sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = (left + right) / 2;

            // Recursively sort the left half
            mergeSort(arr, left, mid);

            // Recursively sort the right half
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Method to print the array
    public static void printArray(int[] arr) {
        for (int price : arr) {
            System.out.print(price + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] bookPrices = {250, 120, 350, 100, 450, 200, 300}; // Array of book prices

        System.out.println("Original Book Prices:");
        printArray(bookPrices);

        mergeSort(bookPrices, 0, bookPrices.length - 1); // Sorting the array using Merge Sort

        System.out.println("Sorted Book Prices:");
        printArray(bookPrices); // Printing the sorted array
    }
}

