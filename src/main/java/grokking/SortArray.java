package grokking;

public class SortArray {

    private SortArray() {

    }

    public static void selectionSorting(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            int smallestIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[smallestIndex] > array[j]) {
                    smallestIndex = j;
                }
            }
            if (smallestIndex != i) {
                int temp = array[i];
                array[i] = array[smallestIndex];
                array[smallestIndex] = temp;
            }
        }
    }
}