public class MatrixBinarySearch {

    // Method to perform binary search on the matrix
    public static boolean searchMatrix(int[][] matrix, int target) {
        // Edge case: if the matrix is empty, return false
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Treat the 2D matrix as a 1D array and apply binary search
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Convert mid index to row and column in the 2D matrix
            int midValue = matrix[mid / cols][mid % cols];

            // If the target is found, return true
            if (midValue == target) {
                return true;
            }

            // If the target is smaller than midValue, search the left half
            if (midValue > target) {
                right = mid - 1;
            } 
            // If the target is larger than midValue, search the right half
            else {
                left = mid + 1;
            }
        }

        // If the target is not found, return false
        return false;
    }

    public static void main(String[] args) {
        // Example matrix where each row is sorted
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        // Test the searchMatrix method
        int target = 3;
        boolean found = searchMatrix(matrix, target);
        System.out.println("Target " + target + " found: " + found);

        target = 13;
        found = searchMatrix(matrix, target);
        System.out.println("Target " + target + " found: " + found);
    }
}