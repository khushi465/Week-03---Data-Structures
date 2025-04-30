
public class CountingSort {

    public static void countingSort(int[] ages) {
        int minAge = 10;  // Minimum age
        int maxAge = 18;  // Maximum age
        
        // Step 1: Create and initialize the count array
        int[] count = new int[maxAge - minAge + 1];  // Array to store frequency of each age
        
        // Step 2: Store the frequency of each age in the count array
        for (int age : ages) {
            count[age - minAge]++;
        }

        // Step 3: Compute cumulative frequencies
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Step 4: Create an output array and place the elements in sorted order
        int[] output = new int[ages.length];
        for (int i = ages.length - 1; i >= 0; i--) {
            int age = ages[i];
            output[count[age - minAge] - 1] = age;
            count[age - minAge]--;
        }

        // Copy the sorted elements into the original array
        System.arraycopy(output, 0, ages, 0, ages.length);
    }

    public static void printArray(int[] arr) {
        for (int age : arr) {
            System.out.print(age + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Example array of student ages
        int[] ages = {12, 15, 10, 18, 13, 14, 17, 16, 15, 11, 13, 16};

        System.out.println("Original Ages:");
        printArray(ages);

        // Perform Counting Sort on ages
        countingSort(ages);

        System.out.println("Sorted Ages:");
        printArray(ages);
    }
}