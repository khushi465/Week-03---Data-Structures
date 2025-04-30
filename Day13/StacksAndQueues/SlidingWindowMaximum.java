
import java.util.*;

public class SlidingWindowMaximum {
    
    public static List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>(); // Stores indices

        for (int i = 0; i < nums.length; i++) {
            // Remove indices that are out of this window
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Remove indices of all elements smaller than current element
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add current index
            deque.offerLast(i);

            // Add max for the current window to result (start after k-1)
            if (i >= k - 1) {
                result.add(nums[deque.peekFirst()]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        List<Integer> maxInWindows = maxSlidingWindow(nums, k);

        System.out.println("Maximums in each sliding window:");
        for (int max : maxInWindows) {
            System.out.print(max + " ");
        }
    }
}

