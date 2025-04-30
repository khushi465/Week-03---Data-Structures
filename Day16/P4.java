import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class P4 {
    private static final int[] SIZES = {1, 100, 500}; // MB
    private static final int BUFFER_SIZE = 65536; // 64KB buffer
    private static final int WARMUP_ROUNDS = 3;
    private static final int MEASUREMENT_ROUNDS = 5;
    private static final String FILE_PATH = "testfile.dat";
    private static final int MB = 1024 * 1024;

    public static void main(String[] args) throws IOException {
        // Generate fresh test file of 500MB
        createTestFile(500); 

        // Test each size sequentially
        for (int sizeMB : SIZES) {
            long bytesToRead = (long) sizeMB * MB;
            System.out.printf("\n=== Testing %dMB ===\n", sizeMB);

            // FileReader benchmark
            System.out.println("FileReader:");
            benchmark(() -> {
                try {
                    readWithFileReader(bytesToRead);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // InputStreamReader benchmark
            System.out.println("InputStreamReader:");
            benchmark(() -> {
                try {
                    readWithInputStreamReader(bytesToRead);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void benchmark(Runnable test) {
        // Warmup
        for (int i = 0; i < WARMUP_ROUNDS; i++) {
            test.run();
        }

        // Measurement
        long totalTime = 0;
        for (int i = 0; i < MEASUREMENT_ROUNDS; i++) {
            long start = System.nanoTime();
            test.run();
            long end = System.nanoTime();
            totalTime += (end - start);
            System.gc();
        }

        double avgMs = totalTime / (MEASUREMENT_ROUNDS * 1_000_000.0);
        System.out.printf("Average: %.2f ms\n", avgMs);
    }

    private static void readWithFileReader(long bytesToRead) throws IOException {
        long charsToRead = bytesToRead / 2; // Assuming 2 bytes per char
        try (Reader reader = new BufferedReader(new FileReader(FILE_PATH), BUFFER_SIZE)) {
            char[] buffer = new char[BUFFER_SIZE];
            long totalRead = 0;

            while (totalRead < charsToRead) {
                int read = reader.read(buffer, 0, 
                    (int) Math.min(buffer.length, charsToRead - totalRead));
                if (read == -1) break;
                totalRead += read;
            }
        }
    }

    private static void readWithInputStreamReader(long bytesToRead) throws IOException {
        long charsToRead = bytesToRead / 2; // Assuming 2 bytes per char
        try (InputStreamReader reader = new InputStreamReader(
                new BufferedInputStream(new FileInputStream(FILE_PATH), BUFFER_SIZE),
                StandardCharsets.UTF_8)) {

            char[] buffer = new char[BUFFER_SIZE];
            long totalRead = 0;

            while (totalRead < charsToRead) {
                int read = reader.read(buffer, 0,
                    (int) Math.min(buffer.length, charsToRead - totalRead));
                if (read == -1) break;
                totalRead += read;
            }
        }
    }

    private static void createTestFile(int mb) throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) file.delete();

        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] buffer = new byte[1024];
            Arrays.fill(buffer, (byte) 'A');

            for (int i = 0; i < mb * 1024; i++) {
                out.write(buffer);
            }
        }
    }
}
