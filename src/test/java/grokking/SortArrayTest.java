package grokking;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortArrayTest {

    private final static int[] result = new int[]{-3, 1, 3, 5, 15, Integer.MAX_VALUE};
    private final static int[] array = new int[]{Integer.MAX_VALUE, 1, 3, 5, -3, 15};

    @Test
    public void selectionSortingTest() {
        SortArray.selectionSorting(array);
        assertArrayEquals(result, array);
    }
}

