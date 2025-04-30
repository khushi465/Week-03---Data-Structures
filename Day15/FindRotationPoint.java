public class FindRotationPoint {

    // Method to find the index of the smallest element in a rotated sorted array
    public static int findRotationPoint(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        // Perform binary search
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If the middle element is greater than the rightmost element,
            // the smallest element is in the right half
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                // Otherwise, the smallest element is in the left half
                right = mid;
            }
        }

        // When left == right, we have found the rotation point
        return left;
    }

    public static void main(String[] args) {
        // Example rotated sorted array
        int[] arr = {6, 7, 9, 15, 19, 2, 3};

        // Find the index of the smallest element
        int rotationPoint = findRotationPoint(arr);

        // Print the index of the smallest element
        System.out.println("The smallest element is at index: " + rotationPoint);
        System.out.println("The smallest element is: " + arr[rotationPoint]);
    }
}