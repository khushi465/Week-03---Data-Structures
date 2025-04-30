public class SelectionSort {

    // Method to perform selection sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        // Traverse through all array elements
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in the unsorted part of the array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first unsorted element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Method to print the array
    public static void printArray(int[] arr) {
        for (int score : arr) {
            System.out.print(score + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Array representing students' exam scores
        int[] examScores = {85, 70, 95, 60, 90, 80, 75};

        System.out.println("Original Exam Scores:");
        printArray(examScores);

        selectionSort(examScores);  // Sorting the array using Selection Sort

        System.out.println("Sorted Exam Scores:");
        printArray(examScores);  // Printing the sorted array
    }
}