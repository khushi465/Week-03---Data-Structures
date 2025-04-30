
import java.util.Stack;

public class StockSpan {

    // Method to calculate span
    public static int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>(); // Stack stores indices

        for (int i = 0; i < n; i++) {
            // Pop prices from stack while current price is higher or equal
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }

            // If stack is empty, it means all previous prices were less than or equal
            span[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());

            // Push this index to the stack
            stack.push(i);
        }

        return span;
    }

    // Utility method to print an array
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Driver code
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] span = calculateSpan(prices);

        System.out.println("Stock Prices:");
        printArray(prices);
        System.out.println("Span Array:");
        printArray(span);
    }
}


