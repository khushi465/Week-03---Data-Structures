public class PeakElementBinarySearch {

    // Function to find a peak element using Binary Search
    public static int findPeakElement(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if mid is a peak element
            if ((mid == 0 || arr[mid - 1] <= arr[mid]) &&
                (mid == arr.length - 1 || arr[mid + 1] <= arr[mid])) {
                return mid;  // Return the index of the peak element
            }

            // If the left neighbor is greater, move to the left half
            if (mid > 0 && arr[mid - 1] > arr[mid]) {
                right = mid - 1;
            }
            // If the right neighbor is greater, move to the right half
            else {
                left = mid + 1;
            }
        }

        // If no peak is found (though this shouldn't happen), return -1
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 20, 4, 1, 0}; // Sample array
        int peakIndex = findPeakElement(arr);

        if (peakIndex != -1) {
            System.out.println("Peak element is " + arr[peakIndex] + " at index " + peakIndex);
        } else {
            System.out.println("No peak element found");
        }
    }
}