package general.bigO;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    /**
     * <p>
     * * Search in array if ID is know O(1)
     * <p>
     * * Delete in array without shift if ID is know O(1)
     * <p>
     * * Simple search O(n)
     * <p>
     * * Binary search O(log n)
     * <p>
     * * Quicksort O(n * log n)
     * <p>
     * * Selection sort  O(n2)
     * <p>
     * * Traveling salesperson O(n!)
     */

    public static void main(String[] args) {

    }

    /**
     * O(n log n)
     */
    public static void exampleA() {
        int n = 10000;
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j *= 2) {
                arrayList.add(i * j);
            }
        }
    }

    /**
     * O(n^2)
     */
    public static void exampleB() {
        int n = 10000;
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i += 2) {
            for (int j = i; j < n; j++) {
                arrayList.add(i * j);
            }
        }
    }

    /**
     * O(n)
     */
    public static void exampleC() {
        int n = 10000;
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arrayList.add(i * j);
                n--;
            }
        }
    }

    /**
     * O(n)
     */
    public static void exampleD() {
        factorial(12);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return n;
        }
        return n * factorial(n - 1);
    }

    /**
     * O(1.6^n)
     */
    public static void exampleE() {
        fib(20);
    }

    public static int fib(int n) {
        if ((n == 1) || (n == 2)) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
