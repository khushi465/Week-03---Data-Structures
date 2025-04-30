public class P5 {
    
    // Recursive Fibonacci: O(2^n)
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative Fibonacci: O(n)
    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    // Benchmark utility
    public static long benchmark(Runnable method) {
        long start = System.nanoTime();
        method.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000; // ms
    }

    public static void main(String[] args) {
        int[] testValues = {10, 30};

        System.out.println("Fibonacci Benchmark (time in ms)\n");

        for (int n : testValues) {
            System.out.println("N = " + n);
            
            long recTime = benchmark(() -> {
                fibonacciRecursive(n); // no printing, just compute
            });
            System.out.println("Recursive: " + recTime + " ms");

            long iterTime = benchmark(() -> {
                fibonacciIterative(n);
            });
            System.out.println("Iterative: " + iterTime + " ms\n");
        }

        // Large test: Iterative only (recursive is impractical)
        int largeN = 50;
        System.out.println("N = " + largeN + " (Iterative only)");
        long iterTime = benchmark(() -> {
            fibonacciIterative(largeN);
        });
        System.out.println("Iterative: " + iterTime + " ms");
    }
}
