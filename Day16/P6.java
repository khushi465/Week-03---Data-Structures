import java.util.*;
public class P6 {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] sizes = {1_000, 100_000, 1_000_000};

        System.out.println("Search Performance (in ms):\n");

        for (int size : sizes) {
            System.out.println("Dataset Size: " + size);

            // Generate dataset
            int[] array = new int[size];
            Set<Integer> hashSet = new HashSet<>(size);
            Set<Integer> treeSet = new TreeSet<>();

            for (int i = 0; i < size; i++) {
                array[i] = i;
                hashSet.add(i);
                treeSet.add(i);
            }

            int target = size - 1; // search for last element (worst case for array)

            // Benchmark array (linear search)
            long arrayTime = benchmark(() -> {
                for (int value : array) {
                    if (value == target) break;
                }
            });

            // Benchmark HashSet
            long hashSetTime = benchmark(() -> {
                hashSet.contains(target);
            });

            // Benchmark TreeSet
            long treeSetTime = benchmark(() -> {
                treeSet.contains(target);
            });

            System.out.printf("Array   (O(N))     : %d ms\n", arrayTime);
            System.out.printf("HashSet (O(1))     : %d ms\n", hashSetTime);
            System.out.printf("TreeSet (O(log N)) : %d ms\n\n", treeSetTime);
        }
    }

    private static long benchmark(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000; // convert to milliseconds
    }
}
