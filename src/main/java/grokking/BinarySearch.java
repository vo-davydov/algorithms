package grokking;

public class BinarySearch {

    /**
     * This method just for test 'Binary Search'
     * <p>
     * low, high and mid are indexes of the array
     * guess it's a variable that might be needed
     *
     * @param arr  int[] must be sorted
     * @param find an int number to find
     * @return a found index or null
     */
    public static Integer find(int[] arr, int find) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = arr[mid];

            if (guess == find) {
                return mid;
            }

            if (guess > find) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return null;
    }

    public static int findMissing(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int midIndex = (start + end) / 2;
            if (arr[midIndex] - midIndex == 1) {
                start = midIndex + 1;
            } else {
                end = midIndex - 1;
            }
        }

        return start + 1;
    }
}
