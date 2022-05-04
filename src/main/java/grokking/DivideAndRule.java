package grokking;

public class DivideAndRule {

    /**
     * sum array elements
     */
    public static int getSum(int[] array) {
        return getSum(array, 0);
    }

    private static int getSum(int[] array, int currentIndex) {

        if (currentIndex < array.length) {
            int nextIndex = currentIndex + 1;
            if (nextIndex == array.length) {
                return array[currentIndex];
            }

            //base case
            return array[currentIndex] + getSum(array, nextIndex);
        }

        return 0;
    }

    /**
     * get max array elements
     */
    public static int getMax(int[] array) {
        return getMax(array, Integer.MIN_VALUE, 0);
    }

    private static int getMax(int[] array, int max, int index) {
        if (index < array.length) {

            if (array[index] > max) {
                max = array[index];
            }

            return getMax(array, max, index + 1);
        }

        return max;
    }

    /**
     * get index
     */
    public static int getIndex(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        return getIndex(array, low, high, value);
    }

    private static int getIndex(int[] array, int low, int high, int value) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (array[mid] == value) {
            return mid;
        }

        if (array[mid] > value) {

            return getIndex(array, low, mid - 1, value);
        } else {
            return getIndex(array, mid + 1, high, value);
        }
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (array.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2;
        int midValue = array[middle];

        int i = low, j = high;

        while (i <= j) {
            while (array[i] < midValue) {
                i++;
            }

            while (array[j] > midValue) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(array, low, j);
        }

        if (high > i) {
            quickSort(array, i, high);
        }

    }


}
