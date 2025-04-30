import java.util.Stack;

public class RecursiveStackSort {

    // Main method to sort the stack
    public static void sortStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int top = stack.pop();
            sortStack(stack); // Recursively sort remaining stack
            insertInSortedOrder(stack, top); // Insert current element at correct position
        }
    }

    // Helper method to insert an element into a sorted stack
    private static void insertInSortedOrder(Stack<Integer> stack, int element) {
        // Base case: either stack is empty or top of stack is smaller than element
        if (stack.isEmpty() || element > stack.peek()) {
            stack.push(element);
        } else {
            int temp = stack.pop();
            insertInSortedOrder(stack, element); // Recursive insert
            stack.push(temp); // Restore the popped element
        }
    }

    // Utility method to display stack elements
    public static void printStack(Stack<Integer> stack) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
    }

    // Driver code
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        stack.push(5);

        System.out.println("Original Stack:");
        printStack(stack);

        sortStack(stack);

        System.out.println("\nSorted Stack:");
        printStack(stack);
    }
}