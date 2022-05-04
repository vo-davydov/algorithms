package grokking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivideAndRuleTest {

    @Test
    public void calcElements() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        assertEquals(15, DivideAndRule.getSum(array));

        array = new int[]{1, 1, 1, 1, 1};
        assertEquals(5, DivideAndRule.getSum(array));

        array = new int[]{0, 0, 0, 0, 0};
        assertEquals(0, DivideAndRule.getSum(array));

        array = new int[]{-1, -2, -3, -4, -5};
        assertEquals(-15, DivideAndRule.getSum(array));

        array = new int[]{-1, -1, -1, -1, -1};
        assertEquals(-5, DivideAndRule.getSum(array));

        array = new int[]{};
        assertEquals(0, DivideAndRule.getSum(array));

        array = new int[]{888};
        assertEquals(888, DivideAndRule.getSum(array));
    }

    @Test
    public void getMax() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        assertEquals(5, DivideAndRule.getMax(array));

        array = new int[]{1, 1, 1, 1, 1};
        assertEquals(1, DivideAndRule.getMax(array));

        array = new int[]{0, 0, 0, 0, 0};
        assertEquals(0, DivideAndRule.getMax(array));

        array = new int[]{-1, -2, -3, -4, -5};
        assertEquals(-1, DivideAndRule.getMax(array));

        array = new int[]{-1, -1, -1, -1, -1};
        assertEquals(-1, DivideAndRule.getMax(array));

        array = new int[]{};
        assertEquals(Integer.MIN_VALUE, DivideAndRule.getMax(array));

        array = new int[]{888};
        assertEquals(888, DivideAndRule.getMax(array));
    }


    @Test
    public void getIndex() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        assertEquals(1, DivideAndRule.getIndex(array, 2));

        array = new int[]{-5, -4, -3, -2, -1};
        assertEquals(0, DivideAndRule.getIndex(array, -5));

        array = new int[]{-5, -4, -3, -2, -1};
        assertEquals(4, DivideAndRule.getIndex(array, -1));

        array = new int[]{};
        assertEquals(-1, DivideAndRule.getIndex(array, 0));

        array = new int[]{888};
        assertEquals(0, DivideAndRule.getIndex(array, 888));
    }

    @Test
    public void quickSort() {
        int[] array = new int[]{3, 1, 4, 1, 5, 8, 2, 6, 10, 16, 7};
        int[] sortedArray = new int[]{1, 1, 2, 3, 4, 5, 6, 7, 8, 10, 16};
        DivideAndRule.quickSort(array);

        assertEquals(sortedArray.length, array.length);

        for (int i = 0; i < sortedArray.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }

    }

}