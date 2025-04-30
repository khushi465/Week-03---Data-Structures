public class StringConcatenationPerformance {

    public static void main(String[] args) {
        // Number of concatenation iterations (1 million)
        int numIterations = 1000000;
        
        // String to append repeatedly
        String strToAppend = "hello";
        
        // Measure time taken by StringBuffer
        long startTime = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < numIterations; i++) {
            stringBuffer.append(strToAppend);
        }
        long endTime = System.nanoTime();
        long stringBufferTime = endTime - startTime;

        // Measure time taken by StringBuilder
        startTime = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numIterations; i++) {
            stringBuilder.append(strToAppend);
        }
        endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;

        // Output the time taken by both StringBuffer and StringBuilder
        System.out.println("Time taken by StringBuffer: " + stringBufferTime + " nanoseconds");
        System.out.println("Time taken by StringBuilder: " + stringBuilderTime + " nanoseconds");
    }
}