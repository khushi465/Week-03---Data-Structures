public class P3 {
    private static final int ITERATIONS = 1_000_000;
    
    public static void main(String[] args) {
        // Warm up JVM
        concatenateWithString(1_000);
        concatenateWithStringBuilder(1_000);
        concatenateWithStringBuffer(1_000);
        
        // Test with 1,000 concatenations
        System.out.println("1,000 concatenations:");
        measurePerformance(1_000);
        
        // Test with 10,000 concatenations
        System.out.println("\n10,000 concatenations:");
        measurePerformance(10_000);
        
        // Test with 1,000,000 concatenations
        System.out.println("\n1,000,000 concatenations:");
        measurePerformance(ITERATIONS);
    }
    
    private static void measurePerformance(int count) {
        long start, end;
        
        // String concatenation
        start = System.currentTimeMillis();
        concatenateWithString(count);
        end = System.currentTimeMillis();
        System.out.printf("String: %d ms%n", end - start);
        
        // StringBuilder concatenation
        start = System.currentTimeMillis();
        concatenateWithStringBuilder(count);
        end = System.currentTimeMillis();
        System.out.printf("StringBuilder: %d ms%n", end - start);
        
        // StringBuffer concatenation
        start = System.currentTimeMillis();
        concatenateWithStringBuffer(count);
        end = System.currentTimeMillis();
        System.out.printf("StringBuffer: %d ms%n", end - start);
    }
    
    private static String concatenateWithString(int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += "a";  // Creates new String object each time
        }
        return result;
    }
    
    private static String concatenateWithStringBuilder(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("a");  // Modifies internal buffer
        }
        return sb.toString();
    }
    
    private static String concatenateWithStringBuffer(int count) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sb.append("a");  // Modifies internal buffer with synchronization
        }
        return sb.toString();
    }
}