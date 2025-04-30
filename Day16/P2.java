import java.util.*;
class Bubble {
    public static void bubbleSort(int[] arr){
        int n=arr.length;
        for (int i = 0; i < n-1; i++) {
            boolean swapped=false;
            for (int j = 0; j < n-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    swapped=true;
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if(!swapped){
                break;
            }
        }
    }
}
class Merge{
    static void mergeSort(int start, int end,int[] arr){
        if(start<end){
            int mid=start+(end-start)/2;
            mergeSort(start, mid, arr);
            mergeSort(mid+1, end, arr);
            merge(start, mid, end, arr);
        }
    }
    static void merge(int start, int mid, int end, int[] arr){
        int n1=mid-start+1;
        int n2=end-mid;
        int[] left=new int[n1];
        int[] right=new int[n2];
        System.arraycopy(arr, start, left, 0, n1);
        System.arraycopy(arr, mid+1, right, 0, n2);
        int i=0,j=0,k=start;
        while(i<n1&&j<n2){
            if(left[i]<right[j]){
                arr[k++]=left[i++];
            }
            else{
                arr[k++]=right[j++];
            }
        }
        while(i<n1){arr[k++]=left[i++];}
        while(j<n2){arr[k++]=right[j++];}
    }
}
class Quick{
    static void quickSort(int start, int end, int[] arr){
        int n=arr.length;
        if(start<end){
            int pi=partition(start, end, arr);
            quickSort(start, pi-1, arr);
            quickSort(pi+1, end, arr);
        }
    }
    static int partition(int start, int end, int[] arr){
        int pivot=arr[end];
        int i=start-1;
        for (int j = start; j < end; j++) {
            if(arr[j]<pivot){
                i++;
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
            }
        }
        int temp=arr[i+1];
        arr[i+1]=arr[end];
        arr[end]=temp;
        return i+1;
    }
}

public class P2{
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000};
        String[] algorithms = {"Bubble", "Merge", "Quick"};
        
        for (int size : sizes) {
            System.out.println("\nDataset size: " + size);
            int[] original = generateRandomArray(size);
            
            for (String algo : algorithms) {
                int[] arr = Arrays.copyOf(original, original.length);
                long start = System.nanoTime();
                
                switch (algo) {
                    case "Bubble" -> Bubble.bubbleSort(arr);
                    case "Merge" -> Merge.mergeSort(0, arr.length-1,arr);
                    case "Quick" -> Quick.quickSort(0, arr.length-1,arr);
                }
                
                long end = System.nanoTime();
                double timeMs = (end - start) / 1_000_000.0;
                System.out.printf("%-6s Sort: %.2f ms%n", algo, timeMs);
            }
        }
    }
    
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }
}
