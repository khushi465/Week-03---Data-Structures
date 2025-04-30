
import java.util.*;

public class P1{
    public static int linearSearch(int[] arr, int el){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==el){
                return i;
            }
        }
        return -1;
    }
    public static int binarySearch(int[] arr, int el){
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == el) {
                return mid;
            } else if (arr[mid] < el) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    
    }
    public static int[] generateRandomArray(int size){
        Random random=new Random();
        int arr[]=new int[size];
        for (int i = 0; i < size; i++) {
            arr[i]=random.nextInt(size*10);
            // arr[i]=(int)Math.random()*size+1;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] small=generateRandomArray(1000);
        int[] medium=generateRandomArray(10000);
        int[] large=generateRandomArray(1000000);
        int t=999;
        long startTime=System.nanoTime();
        int linearResult=linearSearch(small, t);
        long endTime=System.nanoTime();
        System.out.println("Linear Search (1,000): " + (endTime - startTime) / 1000000.0 + " ms");

        startTime = System.nanoTime();
        linearResult = linearSearch(medium, t);
        endTime = System.nanoTime();
        System.out.println("Linear Search (10,000): " + (endTime - startTime) / 1000000.0 + " ms");

        startTime = System.nanoTime();
        linearResult = linearSearch(large, t);
        endTime = System.nanoTime();
        System.out.println("Linear Search (1,000,000): " + (endTime - startTime) / 1000000.0 + " ms");

        // Binary Search Tests (includes sorting time)
        startTime = System.nanoTime();
        int binaryResult = binarySearch(small, t);
        endTime = System.nanoTime();
        System.out.println("Binary Search (1,000): " + (endTime - startTime) / 1000000.0 + " ms");

        startTime = System.nanoTime();
        binaryResult = binarySearch(medium, t);
        endTime = System.nanoTime();
        System.out.println("Binary Search (10,000): " + (endTime - startTime) / 1000000.0 + " ms");

        startTime = System.nanoTime();
        binaryResult = binarySearch(large, t);
        endTime = System.nanoTime();
        System.out.println("Binary Search (1,000,000): " + (endTime - startTime) / 1000000.0 + " ms");
    

    }
}