package grokking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    /**
     * This map should be sorted due to Bi search needs a sorted array
     */
    private final Map<Integer, Integer> sortedMap = new HashMap<>() {{
        put(0, -3);
        put(1, 1);
        put(2, 3);
        put(3, 5);
        put(4, 15);
        put(5, Integer.MAX_VALUE);
    }};

    /**
     * Values should be in sorted map but could be in random order
     */
    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, 1, 3, 5, -3, 15})
    public void find(int find) {
        int[] array = new int[sortedMap.values().size()];

        for (int i = 0; i < sortedMap.values().size(); i++) {
            array[i] = sortedMap.get(i);
        }

        Integer index = BinarySearch.find(array, find);
        assertEquals(sortedMap.get(index), find);

    }


    private final static int[] FIRST = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16};
    private final static int FIRST_VALUE = 11;

    static int[] SECOND = new int[]{1, 2, 4, 5, 6};
    private final static int SECOND_VALUE = 3;

    static int[] THIRD = new int[]{2, 3, 4, 5, 6, 7, 8};
    private final static int THIRD_VALUE = 1;

    static int[] FOURTH = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    private final static int FOURTH_VALUE = 15;

    static int[] FIFTH = new int[]{};
    private final static int FIFTH_VALUE = 1;

    @Test
    public void dummySearch() {
        assertEquals(FIRST_VALUE, BinarySearch.findMissing(FIRST), getErrorMessage(FIRST, FIRST_VALUE));
        assertEquals(SECOND_VALUE, BinarySearch.findMissing(SECOND), getErrorMessage(SECOND, SECOND_VALUE));
        assertEquals(THIRD_VALUE, BinarySearch.findMissing(THIRD), getErrorMessage(THIRD, THIRD_VALUE));
        assertEquals(FOURTH_VALUE, BinarySearch.findMissing(FOURTH), getErrorMessage(FOURTH, FOURTH_VALUE));
        assertEquals(FIFTH_VALUE, BinarySearch.findMissing(FIFTH), getErrorMessage(FIFTH, FIFTH_VALUE));
    }

    private String getErrorMessage(int[] array, int number) {
        return "In array: " + Arrays.toString(array) + " expected number: " + number;
    }
}