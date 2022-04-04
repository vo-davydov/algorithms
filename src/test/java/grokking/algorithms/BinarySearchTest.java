package grokking.algorithms;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

}