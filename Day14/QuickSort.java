public class QuickSort {

    // Partition function to arrange elements around a pivot
    public static int partition(int[] arr, int low, int high) {
        // Pivot is the last element in the array
        int pivot = arr[high];
        int i = (low - 1); // Index of the smaller element

        // Loop through the array and rearrange elements
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++; // Increment the smaller element index
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap the pivot element with the element at arr[i + 1]
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Return the partition index
        return i + 1;
    }

    // QuickSort function
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pi = partition(arr, low, high);

            // Recursively sort the left and right subarrays
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
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
        int[] productPrices = {1500, 1200, 2500, 1000, 4500, 2000, 3500}; // Array of product prices

        System.out.println("Original Product Prices:");
        printArray(productPrices);

        quickSort(productPrices, 0, productPrices.length - 1); // Sorting the array using Quick Sort

        System.out.println("Sorted Product Prices:");
        printArray(productPrices); // Printing the sorted array
    }
}
