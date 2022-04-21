package general.recursion;

import general.recursion.domain.Item;
import general.recursion.domain.Knapsack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursionTest {

    @Test
    public void doExponentiation() {
        assertEquals(1.0, Recursion.doExponentiation(2, 0));
        assertEquals(16.0, Recursion.doExponentiation(2, 4));
        assertEquals(0.0625, Recursion.doExponentiation(2, -4));
    }

    private final static Item[] items = new Item[]{
            new Item("laptop", 4, 50_000.0),
            new Item("guitar", 5, 60_000.0),
            new Item("computer", 10, 70_000.0),
            new Item("smartphone", 2, 10_000.0),
            new Item("book", 6, 16_000.0),
            new Item("ring", 1, 9_000.0),
            new Item("watch", 3, 11_000.0),
            new Item("play", 7, 88_000.0),
            new Item("box", 6, 56_000.0)
    };

    private final static Knapsack knapsack = new Knapsack(10, null);

    /**
     * guitar - 1_500$, 1 kg
     * <p>
     * box - 3_000$, 4 kg
     * <p>
     * laptop - 2_000$, 3 kg
     * <p>
     * knapsack size is 4 kg
     * <pre>
     *          1           2           3           4
     * guitar   1_500       1_500       1_500       1_500   (guitar)
     * box      1_500       1_500       1_500       3_000   (box)
     * laptop   1_500       1_500       2_000       3_500   (laptop + guitar)
     * </pre>
     */
    private final static Item[] secondItems = new Item[]{
            new Item("guitar", 1, 1_500.0),
            new Item("box", 4, 3_000.0),
            new Item("laptop", 3, 2_000.0)
    };

    private final static Knapsack secondKnapsack = new Knapsack(4, null);


    @Test
    public void doKnapsack() {
        assertEquals(119_000.0, Recursion.findBestForKnapsack(items, knapsack));
        assertEquals(3_500.0, Recursion.findBestForKnapsack(secondItems, secondKnapsack));
    }
}